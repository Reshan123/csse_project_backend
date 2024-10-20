package com.HospitalManagementSystem.HospitalManagementSystem.util;

import java.util.Random;

public class PasswordUtil {
    private static PasswordUtil instance;
    private static final Random RANDOM = new Random();

    private PasswordUtil() {
    }
    public static synchronized PasswordUtil getInstance() {
        if (instance == null) {
            instance = new PasswordUtil();
        }
        return instance;
    }
    public String generateRandomPassword() {
        StringBuilder password = new StringBuilder();

        // Use constants from Constants class
        for (int i = 0; i < Constants.PASSWORD_LENGTH; i++) {
            password.append(Constants.PASSWORD_CHARACTERS.charAt(RANDOM.nextInt(Constants.PASSWORD_CHARACTERS.length())));
        }
        return password.toString();
    }
}
