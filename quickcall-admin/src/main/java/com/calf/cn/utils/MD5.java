package com.calf.cn.utils;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.UUID;

/**
 * MD5加密算法
 * @author guixin
 *
 */
public class MD5 {

	static final String byte2hexString(byte[] bytes) {
		StringBuffer buf = new StringBuffer(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			if (((int) bytes[i] & 0xff) < 0x10) {
				buf.append("0");
			}
			buf.append(Long.toString((int) bytes[i] & 0xff, 16));
		}
		return buf.toString();
	}

	/**
	 * md5加密
	 * 
	 * @param sourceString
	 * @return
	 */
	public static String MD5Encode(String sourceString) {
		String resultString = null;
		try {
			resultString = new String(sourceString);
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byte2hexString(md.digest(resultString.getBytes()));
		} catch (Exception ex) {
		}
		return resultString;
	}

	public static String getUniqueCode() {
		String dateStr = DateUtil.dateFormat();
		return MD5Encode(dateStr + UUID.randomUUID().toString());
	}

	@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
	public static void main(String[] args) throws InterruptedException {
		//System.out.println(MD5Encode("123456"));
		String ss="7l4fSa%2BajOVw0jukxB63iXVyz/z/93HIXWnEbibMmR8PeP0lotCXs9VaGoSG1P53O06rwepxT%2BidXFo1qcBsqaqPFxRbJl2aDXk/BFc3j7PWO6%2BEs9qCw%2BSiiUVY1QW/0NgRSy4wU/%2BnEPOZ3sGGXGlgC/aOe1q%2ByfFcJXmZ1fXvD6FRTLjCikUcIcLnrXnSsNNgXSTObkkad1QN/qw04COqmAQcaB7vfbMGe2ejtwEPsztdE6m1Y4Ki1%2BvW0YpHeoVTyoqnnn/zkwN4CURgNX3f%2B/1ix1YJq%2BsNBcfuTLLz5qzAPpFBr1VfF65XX08KYYgt8LgJ6wWsalmOMs9RaNzJuaZMKC8k77QLLxOLzcnGwBpJdrAJnFTT6bZLfNVpsOwSghi6Np0As8azqauc4R/cQgWyoK9QElo5sRIqxVoReOAEP9823UDXNb%2BD7YnA";
		
		

	}
}
