package ro.inf.ucv.admitere.utils;

import java.util.Random;

public class Generator {

    private static final String SALT_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

    public String getGeneratedString() {
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) {
            int index = (int) (rnd.nextFloat() * SALT_CHARS.length());
            salt.append(SALT_CHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }
}
