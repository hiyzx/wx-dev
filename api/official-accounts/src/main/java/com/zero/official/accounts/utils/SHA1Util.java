package com.zero.official.accounts.utils;

import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author yezhaoxing
 * @date 2017/11/01
 */
@Slf4j
public class SHA1Util {

    public static String SHA1(String decript) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(decript.getBytes());
            byte[] messageDigest = digest.digest();
            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            // 字节数组转换为 十六进制 数
            for (byte aMessageDigest : messageDigest) {
                String shaHex = Integer.toHexString(aMessageDigest & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            log.error(e.getMessage(), e);
        }
        return "";
    }
}
