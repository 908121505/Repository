package com.honglu.quickcall.common.core.util;

import java.security.MessageDigest;

public class MD5 {

	public static String md5(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			byte b[] = md.digest();

			int i;

			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			str = buf.toString();
		} catch (Exception e) {
			e.printStackTrace();

		}
		return str;
	}
	public static void main(String[] args) {
//		System.out.println(md5("31119@qq.com"+"123456"));
		//fea239973a710f70f5fa032d2d29fee6
		System.out.println(md5("660726524"));
		//96e79218965eb72c92a549dd5a330112
		//fea239973a710f70f5fa032d2d29fee6
		//0930cfad5d5a46d8718f7f37726e1622
		//5943415ca2d8056489a01f8bc6af38c2
	}
}
