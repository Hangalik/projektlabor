package hu.bme.annaATbarbies.sokoban;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SkeletonHelper {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static String indent = "";

    public static void appendIndent() {
        indent = indent.concat("\t");
    }

    public static void popIndent() {
        if (indent.length() > 0)
            indent = indent.substring(0, indent.length() - 1);
    }

    public static String read() {
        String ret = null;
        try {
            ret = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public static void write(String str) {
        System.out.println(indent + str);
    }
}
