package com.honglu.quickcall.common.api.util;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by len.song on 2017-12-08.
 */
public class SystemUtils {
    private static String localIp = null;
    private static List<String> ips = new ArrayList(2);
    private static String hostName;

    public SystemUtils() {
    }

    private static void fetchHostName() throws IOException {
        InetAddress addr = InetAddress.getLocalHost();
        hostName = addr.getHostName();
    }

    private static void fetchHostIps() throws IOException {
        InetAddress[] arr$ = InetAddress.getAllByName(hostName);
        int len$ = arr$.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            InetAddress address = arr$[i$];
            if (address instanceof Inet4Address && !address.isLoopbackAddress()) {
                ips.add(address.getHostAddress());
            }
        }

    }

    private static void fetchLocalIp() {
        if (!ips.isEmpty()) {
            localIp = (String)ips.get(0);
        }

    }

    public static String getLocalIp() {
        return localIp;
    }

    public static String getHostName() {
        return hostName;
    }

    static {
        try {
            fetchHostName();
            fetchHostIps();
            fetchLocalIp();
        } catch (IOException var1) {
            var1.printStackTrace();
        }

    }
}
