package com.mindstroid.mindstroidgameapplication.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.SecureRandom;

public class GameIdGenerator {

    private static Logger audit = LogManager.getLogger("audit-log");

    public static String generateGameId(int length) {

        String canUsedCharacters = "abcdefghijklmnopqrstuvwxyz";

        SecureRandom secureRandom = new SecureRandom();
        StringBuilder gameId = new StringBuilder();

        try {
            for (int c = 0; c < length; c++) {
                int random = secureRandom.nextInt(canUsedCharacters.length());
                char randomChar = canUsedCharacters.charAt(random);
                gameId.append(randomChar);
            }
        } catch (Exception e) {
            audit.info("game id generator,fail," + e.getMessage());
        }

        return gameId.toString();
    }
}
