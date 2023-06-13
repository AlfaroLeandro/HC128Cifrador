package ar.edu.unlam.hc128.utils;

import java.util.Random;

public class RandomDataGenerator {

    private static int DATA_LENGTH = 16;
    public static String generate()
    {
        char c;
        char[] chars = "abcdefghijklmnopqrstuvwxyz1234567890".toCharArray();
        Random random = new Random();
        StringBuilder builder = new StringBuilder(DATA_LENGTH);
        for (int i = 0; i < DATA_LENGTH; i++) {
            c = chars[random.nextInt(chars.length)];
            builder.append(c);
        }
        return builder.toString();
    }

}
