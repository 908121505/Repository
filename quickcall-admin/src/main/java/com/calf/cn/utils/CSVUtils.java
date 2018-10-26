package com.calf.cn.utils;

/**
 * @author luoyanchong
 * @ Description：csv导入导出util
 * @date 2018-06-19 14:05
 */

import com.csvreader.CsvWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.List;

public class CSVUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(CSVUtils.class);
    /**
     * 导出csv文件
     *
     * @param dataList 需要导出的数据
     * @param getResponse   HttpServletResponse
     * @param headers   csv 第一行（每列标题）
     * @return
     *  true--导出成功，false -- 导出失败
     */
    public static boolean exportCsv(List<?> dataList, HttpServletResponse getResponse, String[] headers) {
        boolean returnFlag = true;
        java.io.OutputStream out = null;
        java.io.FileInputStream in = null;
        try {
            //写入临时文件
            File tempFile = File.createTempFile("vehicle", ".csv");
            CsvWriter csvWriter = new CsvWriter(tempFile.getCanonicalPath(), ',', Charset.forName("UTF-8"));
            // 写表头
            long s = System.currentTimeMillis();
            csvWriter.writeRecord(headers);
            for (Object data : dataList) {
                Method[] methods = data.getClass().getDeclaredMethods();
                for (int i = 0; i < methods.length; i++) {
                    String method = methods[i].getName();
                    if (method.indexOf("get") > -1) {
                        Object obj = methods[i].invoke(data, new Object[0]);
                        csvWriter.write(obj != null ? obj.toString() : "");
                    }
                }
                csvWriter.endRecord();
            }
            csvWriter.close();
            long e = System.currentTimeMillis();

            System.err.println(e - s);

            /**
             * 写入csv结束，写出流
             */
            out = getResponse.getOutputStream();
            byte[] b = new byte[10240];
            java.io.File fileLoad = new java.io.File(tempFile.getCanonicalPath());
            getResponse.reset();
            getResponse.setContentType("application/csv");
            getResponse.setHeader("content-disposition", "attachment; filename=csv"+DateUtil.dateFormat()+".csv");
            long fileLength = fileLoad.length();
            String length1 = String.valueOf(fileLength);
            getResponse.setHeader("Content_Length", length1);
            in = new java.io.FileInputStream(fileLoad);
            int n;
            while ((n = in.read(b)) != -1) {
                // 每次写入out1024字节
                out.write(b, 0, n);
            }
        } catch (Exception e) {
            LOGGER.error("导出csv文件异常！异常信息：{}",e.getMessage(),e);
            e.printStackTrace();
            returnFlag = false;
        } finally {
            try {
                in.close();
                out.close();
            } catch (IOException e) {
                LOGGER.error("关闭csv文件流异常，异常信息：{}",e.getMessage(),e);
                e.printStackTrace();
            }
        }
        return returnFlag;
    }

}
