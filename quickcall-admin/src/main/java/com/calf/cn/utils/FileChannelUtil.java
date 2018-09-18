package com.calf.cn.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * 快速移动文件至新的文件中
 * @author guixin
 *
 */
public class FileChannelUtil {

	/**
	 * 
	 * @param fi 上传文件流
	 * @param target 保存到服务的目标文件
	 */
	public static void saveUploadFile(FileInputStream fi, File target) {
		FileOutputStream fo = null;

		FileChannel in = null;
		FileChannel out = null;
		try {
			fo = new FileOutputStream(target);
			in = fi.getChannel();// 得到对应的文件通道
			out = fo.getChannel();// 得到对应的文件通道
			in.transferTo(0, in.size(), out);// 连接两个通道，并且从in通道读取，然后写入out通道
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fi.close();
				in.close();
				fo.close();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
}
