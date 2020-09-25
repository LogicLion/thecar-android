package com.xiulian.thecara.utils;

import java.security.MessageDigest;

/**
 * MD5加密
 * @author wzh
 * @date 2018/10/22
 */
public class MD5Utils {
    public static String MD5(String key) {
        char hexDigits[] = {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
        };
        try {
            byte[] btInput = key.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }
    private static final String hexDigIts[] = {"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f"}; /**
     /* MD5加密
     * @param origin 字符
     * @param charsetname 编码
     * @return
             */
    public static String MD5Encode(String origin, String charsetname){
        String resultString = null; try{ resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            if(null == charsetname || "".equals(charsetname)){
                resultString = byteArrayToHexString(md.digest(resultString.getBytes())); }
            else{
                resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetname)));
            }
        }
        catch (Exception e){

        } return resultString;
    }

    public static String byteArrayToHexString(byte b[]){
        StringBuffer resultSb = new StringBuffer(); for(int i = 0; i < b.length; i++){
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    public static String byteToHexString(byte b){
        int n = b; if(n < 0){
            n += 256;
        }
        int d1 = n / 16; int d2 = n % 16; return hexDigIts[d1] + hexDigIts[d2];
    }
}
