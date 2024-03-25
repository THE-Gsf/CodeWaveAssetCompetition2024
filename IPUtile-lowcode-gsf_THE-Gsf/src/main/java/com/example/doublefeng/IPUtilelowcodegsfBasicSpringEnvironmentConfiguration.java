package com.example.doublefeng;

import com.netease.lowcode.core.annotation.NaslLogic;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Enumeration;

/**
 * 加入spring环境配置（在spring.factories中指定）
 */
@Configuration
@ComponentScan(basePackageClasses = LibraryAutoScan.class)
public class IPUtilelowcodegsfBasicSpringEnvironmentConfiguration {

    @NaslLogic
    public static String getIp(){
        InetAddress inetAddress = null;
        try {
            inetAddress = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        String ipAddress = inetAddress.getHostAddress();
        System.out.println("IP 地址: " + ipAddress);
        return ipAddress;
    }

    @NaslLogic
    public  static String GetIPv4Address(){
        String ipv4 = null;
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface currentInterface = interfaces.nextElement();
                if (currentInterface.isUp()) {
                    Enumeration<InetAddress> addresses = currentInterface.getInetAddresses();
                    while (addresses.hasMoreElements()) {
                        InetAddress address = addresses.nextElement();
                        if (address instanceof Inet4Address && !address.isLoopbackAddress()) {
                            System.out.println("IPv4 地址: " + address.getHostAddress());
                            ipv4=address.getHostAddress();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ipv4;
    }

    public static void main(String[] args) {

        getIp();
        GetIPv4Address();
    }



}
