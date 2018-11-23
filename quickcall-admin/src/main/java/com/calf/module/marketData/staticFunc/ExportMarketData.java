package com.calf.module.marketData.staticFunc;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.calf.cn.utils.DateUtil;
import com.calf.module.marketData.vo.MarketDataVO;
import com.csvreader.CsvWriter;

/**
 * CSV导出工具
 * @author zhaozheyi
 *
 */
public class ExportMarketData {
	private static final Logger LOGGER = LoggerFactory.getLogger(ExportMarketData.class);
	private static final String[] headers = new String[]{"时间","渠道名","激活数","注册数","券使用数","充值人数","充值次数","充值金额","总下单人数","总下单次数","总下单金额","哄睡单数","咨询单数","叫醒单数"};

	
	public static boolean exportCsv(List<MarketDataVO> dataList, HttpServletResponse getResponse) {
        boolean returnFlag = true;
        java.io.OutputStream out = null;
        java.io.FileInputStream in = null;
        try {
            //写入临时文件
            File tempFile = File.createTempFile("vehicle", ".csv");
            CsvWriter csvWriter = new CsvWriter(tempFile.getCanonicalPath(), ',', Charset.forName("GBK"));
            // 写表头
            long s = System.currentTimeMillis();
            csvWriter.writeRecord(headers);
            for (MarketDataVO data : dataList) {
            	csvWriter.write(data.getDate());
            	csvWriter.write(data.getAppChannelName());
            	csvWriter.write(data.getActiveNum());
            	csvWriter.write(data.getRegisterNum());
            	csvWriter.write(data.getCouponNum());
            	csvWriter.write(data.getRechargeNum());
            	csvWriter.write(data.getRechargeTime());
            	csvWriter.write(data.getRechargeTotal());
            	csvWriter.write(data.getOrderNum());
            	csvWriter.write(data.getOrderTime());
            	csvWriter.write(data.getOrderTotal());
            	csvWriter.write(data.getSleepNum());
            	csvWriter.write(data.getConsultNum());
            	csvWriter.write(data.getWakeNum());
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
