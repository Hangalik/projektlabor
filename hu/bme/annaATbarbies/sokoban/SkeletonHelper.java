package hu.bme.annaATbarbies.sokoban;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SkeletonHelper {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static String indent = "";
    private static final String singleIndent = "|\t";

    public static void appendIndent() {
        indent = indent.concat(singleIndent);
    }

    public static void popIndent() {
        if (indent.length() > 0)
            indent = indent.substring(0, indent.length() - singleIndent.length());
    }

    public static String read() {
        String ret = null;
        System.out.print(indent);
        try {
            ret = br.readLine();
        } catch (IOException e) {
            //NOP
        }
        return ret;
    }

    public static int readInt() {
        int responseNum;
        try {
            responseNum = Integer.parseInt(read());
        } catch (NumberFormatException e) {
            responseNum = -1;
        }
        return responseNum;
    }

    public static void write(String str) {
        System.out.println(indent + str);
    }
}
