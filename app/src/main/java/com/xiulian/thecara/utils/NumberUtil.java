package com.xiulian.thecara.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * @author wzh
 * @date 2019/5/5
 */
public class NumberUtil {

    /**
     * 转换为w 格式显示数字
     * @param num
     * @return
     */
    public static String formatNum(int num){
        if(num < 10000){
            return num + "";
        }else{
            DecimalFormat df=(DecimalFormat) NumberFormat.getInstance();
            df.applyPattern("##.#w");
            float a = num / 10000f;
            return df.format(a);
        }

    }
    /**
     * 转换为w 格式显示数字
     * @param num
     * @return
     */
    public static String formatNum(long num){
        if(num < 10000){
            return num + "";
        }else{
            DecimalFormat df=(DecimalFormat) NumberFormat.getInstance();
            df.applyPattern("##.#w");
            float a = num / 10000f;
            return df.format(a);
        }

    }
}
