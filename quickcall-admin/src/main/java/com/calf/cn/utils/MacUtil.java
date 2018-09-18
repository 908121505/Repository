package com.calf.cn.utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

/**
 * 获取ip地址
 * @author guixin
 *
 */
public class MacUtil {

	public static void main(String[] args) throws SocketException,
			UnknownHostException {
		// 注释指定系统属性值
		// System.setProperty("java.net.preferIPv4Stack", "true");
		// System.setProperty("java.net.preferIPv6Addresses", "true");
		System.out.println("-------InetAddress.getLocalHost()");
		InetAddress addr = InetAddress.getLocalHost();

		System.out.println("HostName := " + addr.getHostName());
		System.out.println("HostAddress := " + addr.getHostAddress());

		System.out.println("-------InetAddress.getByName(\"micmiu.com\")");
		InetAddress addr2 = InetAddress.getByName("micmiu.com");

		System.out.println("HostName := " + addr2.getHostName());
		System.out.println("HostAddress := " + addr2.getHostAddress());
		System.out.println("MACAddress := " + getMACAddress(addr));
		System.out.println(getMACAddress());

	}

	public static String getOsName() {
		String os = "";
		os = System.getProperty("os.name");
		return os;
	}

	// 获取MAC地址的方法
	private static String getMACAddress(InetAddress ia) throws SocketException {
		// 获得网络接口对象（即网卡），并得到mac地址，mac地址存在于一个byte数组中。
		byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();

		// 下面代码是把mac地址拼装成String
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < mac.length; i++) {
			if (i != 0) {
				sb.append("-");
			}
			// mac[i] & 0xFF 是为了把byte转化为正整数
			String s = Integer.toHexString(mac[i] & 0xFF);
			sb.append(s.length() == 1 ? 0 + s : s);
		}

		// 把字符串所有小写字母改为大写成为正规的mac地址并返回
		return sb.toString().toUpperCase();
	}

	public static String getMACAddress() throws SocketException {
		InetAddress addr = getAddress();
		return getMACAddress(addr);
	}

	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public static String getMACAddress(String ipAddress) throws IOException {
		String str = "", strMAC = "", macAddress = "";
		Process pp = Runtime.getRuntime().exec("nbtstat -a " + ipAddress);
		InputStreamReader ir = new InputStreamReader(pp.getInputStream());
		LineNumberReader input = new LineNumberReader(ir);
		for (int i = 1; i < 100; i++) {
			str = input.readLine();
			if (str != null) {
				if (str.indexOf("MAC Address") > 1) {
					strMAC = str.substring(str.indexOf("MAC Address") + 14,
							str.length());
					break;
				}
			}
		}
		//
		if (strMAC.length() < 17) {
			return "Error!";
		}

		macAddress = strMAC.substring(0, 2) + ":" + strMAC.substring(3, 5)
				+ ":" + strMAC.substring(6, 8) + ":" + strMAC.substring(9, 11)
				+ ":" + strMAC.substring(12, 14) + ":"
				+ strMAC.substring(15, 17);
		//
		return macAddress;
	}

	/**
	 * Get host IP address
	 *
	 * @return IP Address
	 * @throws SocketException
	 */
	private static InetAddress getAddress() throws SocketException {
		for (Enumeration<NetworkInterface> interfaces = NetworkInterface
				.getNetworkInterfaces(); interfaces.hasMoreElements();) {
			NetworkInterface networkInterface = interfaces.nextElement();
			if (networkInterface.isLoopback() || networkInterface.isVirtual()
					|| !networkInterface.isUp()) {
				continue;
			}
			Enumeration<InetAddress> addresses = networkInterface
					.getInetAddresses();
			if (addresses.hasMoreElements()) {
				return addresses.nextElement();
			}
		}
		return null;
	}

}