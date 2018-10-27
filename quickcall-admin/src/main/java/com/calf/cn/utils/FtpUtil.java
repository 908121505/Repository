package com.calf.cn.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.util.ResourceBundle;

import org.apache.commons.net.ftp.FTPClient;

/**
 * 
 * @author zhoujian
 * @version 2015年5月19日下午1:43:57
 *
 */
public class FtpUtil {

	private static String hostname;
	private static int port;
	private static String username;
	private static String password;

	static {
		ResourceBundle rb = ResourceBundle.getBundle("properties/ftp");
		hostname = rb.getString("hostname");
		port = Integer.valueOf(rb.getString("port"));
		username = rb.getString("username");
		password = rb.getString("password");
	}

	/**
	 * 
	 * @param pathname ftp文件上传目录
	 * @param remote 上传文件修改后的名字
	 * @param filePath 被上传文件的地址
	 */
	public static void ftpUpload(String pathname, String remote, String filePath) {
		FTPClient ftpClient = new FTPClient();
		InputStream local = null;
		try {
			ftpClient.connect(hostname, port);
			System.out.println(ftpClient.login(username, password));

			ftpClient.enterLocalPassiveMode();
			ftpClient.changeWorkingDirectory(pathname);
			ftpClient.makeDirectory(pathname);
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);

			local = new FileInputStream(filePath);
			ftpClient.storeFile(remote, local);
			ftpClient.getStatus();
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				local.close();
				ftpClient.logout();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * @param pathname ftp文件上传目录
	 * @param remote 上传文件修改后的名字
	 * @param filePath 被上传文件的地址
	 */
	public static void ftpUpload(String pathname, String remote, InputStream local) {
		FTPClient ftpClient = new FTPClient();
		try {
			ftpClient.connect(hostname, port);
			ftpClient.login(username, password);

			ftpClient.enterLocalPassiveMode();
//			ftpClient.makeDirectory(pathname);
			ftpClient.changeWorkingDirectory(pathname);
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);

			ftpClient.storeFile(remote, local);
			ftpClient.getStatus();
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				local.close();
				ftpClient.logout();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 验证文件类型是否合法
	 * @param type
	 * @return
	 */
	public static boolean validateFileType(String type){
		boolean flag = true;
		if("image/png".equals(type) || "image/jpeg".equals(type)){
			flag = false;
		}
		return flag;
	}
	
	/**
	 * 获取文件后缀名
	 * @param type
	 * @return
	 */
	public static String getFileType(String type){
		String file_suffix = null;
		if("image/png".equals(type)){
			file_suffix = ".png";
		} else if ("image/jpeg".equals(type)){
			file_suffix = ".jpg";
		}
		return file_suffix;
	}

	public static void main(String[] args) {
	}
}
