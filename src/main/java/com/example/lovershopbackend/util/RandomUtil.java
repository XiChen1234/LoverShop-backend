package com.example.lovershopbackend.util;

import java.util.Random;

/**
 * @Description 随机数工具，任何需要生成随机数的都从这里进行生成
 */
public class RandomUtil {
    /**
     * 获取八位随机数字符串
     * 用于用户生成账号
     *
     * @return 用户账号
     */
    public static String get8Random() {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            int num = random.nextInt(10);
            stringBuilder.append(num);
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        System.out.println(RandomUtil.get8Random());
    }
}
