package in.sannareddy.poker.engine.util;

import java.security.SecureRandom;

public class RandomUtil {
    private static SecureRandom random = new SecureRandom();

    public static int getRandomNumber(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }
}
