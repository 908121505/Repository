package com.calf.cn.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class SFtpUtil {
	
	private final static Logger logger = LoggerFactory.getLogger(SFtpUtil.class);
	
	private static SFtpUtil sf = new SFtpUtil();
	
	private boolean close=false;
	
	private ChannelSftp channelSftp;
	
	private static String host;
	
	private static int port;
	
	private static String username;
	
	private static String password;
	
	public static String productimagepath;
	
	public static String imgUrl;

	public static String fileUrl;

	public static String ossUrl;
	
	public static String bucketName;

	/**
	 * 连接sftp服务器
	 * 
	 * @param host
	 *            主机
	 * @param port
	 *            端口
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @return
	 */
	
	 static {
		InputStream is = null;
		try {
			Properties constant = new Properties();
			is = SFtpUtil.class.getClassLoader().getResourceAsStream("ftp.properties");
			if (is != null) {
				constant.load(is);
			}
			host= constant.getProperty("hostname");
			port= Integer.parseInt(constant.getProperty("port"));
			username= constant.getProperty("username");
			password= constant.getProperty("password");
			productimagepath=constant.getProperty("productimagepath"); 
			imgUrl=constant.getProperty("imgUrl"); 
			fileUrl=constant.getProperty("fileUrl"); 
			ossUrl=constant.getProperty("ossUrl"); 
			bucketName= constant.getProperty("bucketName");
		} catch (IOException e) {
			logger.error("读取ftp配置文件出错", e);
		} catch (Exception e) {
			logger.error("SFtpUtil init error", e);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					logger.error("关闭ftp配置文件流出错", e);
				}
			}
		}
	}
	
	public  boolean isClose(){
		return close;
	}
	public void connect(String host, int port, String username,
			String password) {
		ChannelSftp sftp = null;
		try {
			JSch jsch = new JSch();
			jsch.getSession(username, host, port);
			Session sshSession = jsch.getSession(username, host, port);
			System.out.println("Session created.");
			sshSession.setPassword(password);
			Properties sshConfig = new Properties();
			sshConfig.put("StrictHostKeyChecking", "no");
			sshSession.setConfig(sshConfig);
			sshSession.connect();
			System.out.println("Session connected.");
			System.out.println("Opening Channel.");
			Channel channel = sshSession.openChannel("sftp");
			channel.connect();
			sftp = (ChannelSftp) channel;
			System.out.println("Connected to " + host + ".");
			close = true;
			channelSftp = sftp;
		} catch (Exception e) {
			e.printStackTrace();
			close = false;
			channelSftp.quit();
		}
	}

	/**
	 * 上传文件
	 * 
	 * @param directory
	 *            上传的目录
	 * @param uploadFile
	 *            要上传的文件
	 * @param sftp
	 */
	public void upload(String directory, String uploadFile,String fileName) {
		try {
			this.channelSftp.cd(directory);
			File file = new File(uploadFile);
			this.channelSftp.put(new FileInputStream(file), fileName);
		} catch (Exception e) {
			close = false;
			channelSftp.quit();
			e.printStackTrace();
		}
	}

	/**
	 * 下载文件
	 * 
	 * @param directory
	 *            下载目录
	 * @param downloadFile
	 *            下载的文件
	 * @param saveFile
	 *            存在本地的路径
	 * @param sftp
	 */
	public void download(String directory, String downloadFile,
			String saveFile) {
		try {
			this.channelSftp.cd(directory);
			File file = new File(saveFile);
			this.channelSftp.get(downloadFile, new FileOutputStream(file));
		} catch (Exception e) {
			close = false;
			channelSftp.quit();
			e.printStackTrace();
		}
	}

	/**
	 * 删除文件
	 * 
	 * @param directory
	 *            要删除文件所在目录
	 * @param deleteFile
	 *            要删除的文件
	 * @param sftp
	 */
	public void delete(String directory, String deleteFile) {
		try {
			this.channelSftp.cd(directory);
			this.channelSftp.rm(deleteFile);
		} catch (Exception e) {
			close = false;
			channelSftp.quit();
			e.printStackTrace();
		}
	}

	/**
	 * 列出目录下的文件
	 * 
	 * @param directory
	 *            要列出的目录
	 * @param sftp
	 * @return
	 * @throws SftpException
	 */
	@SuppressWarnings("rawtypes")
	public Vector listFiles(String directory)
			throws SftpException {
		return this.channelSftp.ls(directory);
	}
   
	/**
	 * 初始化SFTP链接 移动设备
	 * @return
	 */
	public static SFtpUtil getSFtpUtil(){
		if(!sf.isClose()||sf==null){
			sf.connect(host, port, username, password);
		}
		return sf;
	}
	
	public void createDir(String path, String pathName)
	  {
	    try
	    {
	      this.channelSftp.cd(path);
	      this.channelSftp.mkdir(pathName);
	    } catch (SftpException e) {
	      e.printStackTrace();
	    }
	  }
	
	
	public static void main(String[] args) {
		SFtpUtil sf = SFtpUtil.getSFtpUtil();
		System.out.println(sf);
	}
}
