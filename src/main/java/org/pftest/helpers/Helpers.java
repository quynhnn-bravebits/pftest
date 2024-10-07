package org.pftest.helpers;

import org.testng.Assert;

import java.io.*;
import java.nio.charset.Charset;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.regex.Pattern;

public class Helpers {
    public Helpers() {
        super();
    }

    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");

    public static String makeSlug(String input) {
        if (input == null)
            throw new IllegalArgumentException();

        String noWhiteSpace = WHITESPACE.matcher(input).replaceAll("_");
        String normalized = Normalizer.normalize(noWhiteSpace, Normalizer.Form.NFD);
        String slug = NONLATIN.matcher(normalized).replaceAll("");
        return slug.toLowerCase(Locale.ENGLISH);
    }

    public static String readFile(String file) throws IOException {
        Charset cs = Charset.forName("UTF-8");
        FileInputStream stream = new FileInputStream(file);
        try {
            Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
            StringBuilder builder = new StringBuilder();
            char[] buffer = new char[8192];
            int read;
            while ((read = ((BufferedReader) reader).read(buffer, 0, buffer.length)) > 0) {
                builder.append(buffer, 0, read);
            }
            return builder.toString();
        } finally {
            stream.close();
        }
    }

    /**
     * @return Get the path to your source directory with a / at the end
     */
    public static String getCurrentDir() {
        String current = System.getProperty("user.dir") + File.separator;
        return current;
    }

    /**
     * Create folder empty
     *
     * @param path is the path to create the folder
     */
    public static void createFolder(String path) {
        // File is a class inside java.io package
        File file = new File(path);

        String result = null;

        int lengthSum = path.length();
        int lengthSub = path.substring(0, path.lastIndexOf('/')).length();

        result = path.substring(lengthSub, lengthSum);

        if (!file.exists()) {
            file.mkdir();  // mkdir is used to create folder
            System.out.println("Folder " + file.getName() + " created: " + path);
        } else {
            System.out.println("Folder already created");
        }
    }

    /**
     * @param str        is value as type is String to be split based on condition
     * @param valueSplit the character to split the string into an array of values
     * @return array of string values after splitting
     */
    public static ArrayList<String> splitString(String str, String valueSplit) {
        return new ArrayList<>(Arrays.asList(str.split(valueSplit, 0)));
    }

    /**
     * Retry an action a number of times
     * @param action The action chain to retry
     * @param maxRetries The maximum number of retries
     */
    public static void retryAction(Runnable action, int maxRetries) {
        int attempts = 0;
        boolean success = false;

        while (attempts < maxRetries && !success) {
            try {
                action.run();
                success = true; // If action succeeds, set success to true
            } catch (AssertionError e) {
                attempts++;
                if (attempts >= maxRetries) {
                    System.err.println("Assertion failed after " + maxRetries + " attempts: " + e.getMessage());
                }
            } catch (Exception e) {
                attempts++;
                if (attempts >= maxRetries) {
                    Assert.fail("Action failed after " + maxRetries + " attempts", e);
                }
            }
        }
    }

}
