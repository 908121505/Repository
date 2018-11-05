package com.honglu.quickcall.user.web.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import com.aliyun.oss.OSSClient;
import com.honglu.quickcall.common.core.util.UUIDUtils;
import com.honglu.quickcall.common.third.OSS.OSSUtil;

import it.sauronsoftware.jave.AudioAttributes;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncodingAttributes;

public class AudioUtils {
	public static File wavTomp3(File file, String outFile) {
		boolean status = false;

		try {
			return execute(file, outFile);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			status = false;
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 执行转化
	 * 
	 * @param source
	 *            输入文件
	 * @param desFileName
	 *            目标文件名
	 * @return 转换之后文件
	 */
	public static File execute(File source, String desFileName) throws Exception {
		File target = new File(desFileName);
		AudioAttributes audio = new AudioAttributes();
		audio.setCodec("libmp3lame");
		/*
		 * audio.setBitRate(new Integer(36000)); audio.setChannels(new Integer(2));
		 * audio.setSamplingRate(new Integer(44100));
		 */
		EncodingAttributes attrs = new EncodingAttributes();
		attrs.setFormat("mp3");
		attrs.setAudioAttributes(audio);
		Encoder encoder = new Encoder();
		encoder.encode(source, target, attrs);
		return target;
	}

	public static void main(String[] args) {
		try {
			File file1 = new File("C:\\Users\\liyingtang\\Desktop\\03ec6243a3a34d2ab407dc5d5f7b4d39(1).mp3");
			System.out.println(file1.exists());
			String imageName = UUIDUtils.getUUID() + "." + "mp3";
			String imgFolder = "voice";
			// 阿里云客户端
			OSSClient ossClient = OSSUtil.getOSSClient();
			File file = wavTomp3(file1, "1mp3");
			InputStream input = new FileInputStream(file);

			// 上传
			boolean flag = OSSUtil.uploadInputStreamObject2OSS(ossClient, input, imageName, imgFolder);
			System.out.println(OSSUtil.ossUrl + "/" + imgFolder + "/" + imageName);
			file.delete();
		} catch (Exception e) {

		}
	}

}
