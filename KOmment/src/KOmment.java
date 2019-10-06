
import java.io.*;
import java.util.Scanner;

public class KOmment {
    private static String delimiter = "//";
    private static String fname;
    // amount of whitespaces after the longest line
    private static int columnLength = 10;

    public static void main(String[] args) {
        boolean expectDelimiter = false;
        boolean expectColumnLength = false;

        for (String arg : args) {
            switch (arg) {
                case "-h":
                case "--help": {
                    help();
                    return;
                }
                case "-d":
                case "--delimiter": {
                    expectDelimiter = true;
                    break;
                }
                case "-w":
                case "--whitespaces": {
                    expectColumnLength = true;
                    break;
                }
                default: {
                    if (expectDelimiter) {
                        delimiter = arg;
                        expectDelimiter = false;
                    } else if (expectColumnLength) {
                        columnLength = Integer.parseInt(arg);
                        expectColumnLength = false;
                    } else {
                        fname = arg;
                    }
                }
            }
        }

        if (fname.isEmpty()) {
            abort();
        } else {
            process();
        }
    }

    private static void help() {
        System.out.println("usage");
        System.out.println("KOmment -d '//' filename");
        System.out.println("Flags: ");
        System.out.println("-d or --delimiter ';;' - set comment delimiter ('//' by default)");
        System.out.println("-w or --whitespaces - set amount of whitespace to the length of the longest line " +
                "plus this parameter");
        System.out.println("-h or --help for help");
        System.out.println("program outputs file names: 'filename.pcom'");
    }

    private static void process() {
        assert (!fname.isEmpty());
        System.out.println(delimiter);
        System.out.println(fname);
        System.out.println(columnLength);
        try {
            // opening input file
            File file = new File(fname);
            // opening output file
            PrintWriter outputFile = new PrintWriter(fname + ".pcom", "UTF-8");
            // transforming
            Scanner reader = new Scanner(file);
            int wspaceNum = longestStringLength(reader) + columnLength;

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String code = getCode(line);
                String comment = getComment(line);
                outputFile.println(
                        code +
                        getWspaces(code.length(), wspaceNum) +
                        delimiter +
                        comment
                );
            }
        } catch (FileNotFoundException e) {
            System.out.println("There are no such file");
        }  catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    private static String getWspaces(int codeLength, int desiredLength) {
        return new String(new char[desiredLength - codeLength]).replace('\0', ' ');
    }

    private static int longestStringLength(Scanner reader) {
        int maxLength = 0;
        while (reader.hasNextLine()) maxLength = Math.max(reader.nextLine().length(), maxLength);

        return maxLength;
    }

    private static String getComment(String line) {
        return "hello";
    }

    private static String getCode(String line) {
        return "world";
    }

    private static void abort() {
        System.out.println("Wrong usage of a programm, see '--help'");
    }
}
