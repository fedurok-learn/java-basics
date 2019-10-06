
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KOmment {
    private static String delimiter = "//";
    private static String fname = new String();
    private static String outputFname = new String();
    // amount of whitespaces after the longest line
    private static int columnLength = 10;

    public static void main(String[] args) {
        boolean expectDelimiter = false;
        boolean expectColumnLength = false;
        boolean expectOutputFname = false;

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
                case "-o":
                case "--output": {
                    expectOutputFname = true;
                    break;
                }
                default: {
                    if (expectDelimiter) {
                        delimiter = arg;
                        expectDelimiter = false;
                    } else if (expectColumnLength) {
                        columnLength = Integer.parseInt(arg);
                        expectColumnLength = false;
                    } else if (expectOutputFname) {
                        outputFname = arg;
                        expectOutputFname = false;
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
        System.out.println("-o or --output outfname - set output file name(filename +'.pcom' by default)");
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
            BufferedWriter outputWriter = new BufferedWriter(new FileWriter(
                    outputFname.isEmpty() ? fname + ".pcom" : outputFname
            ));

            // transforming
            ArrayList<StringPair> codeAndComments = divide(new Scanner(file));
            int desiredLength = longestStringLength(codeAndComments) + columnLength;
            for (StringPair pair : codeAndComments) {
                String afterCode = pair.getSecond();
                if (!afterCode.isEmpty()) {
                    afterCode =
                            getWspaces(pair.getFirst().length(), desiredLength) +
                            delimiter +
                            afterCode;
                }

                outputWriter.write(pair.getFirst() + afterCode +  '\n');
            }

            outputWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("There are no such file");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static ArrayList<StringPair> divide(Scanner reader) {
        ArrayList<StringPair> resultArray = new ArrayList<StringPair>();
        Pattern reg = Pattern.compile("^(.*)" + delimiter + "(.*)$");
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            Matcher match = reg.matcher(line);

            if (match.find()) {
                resultArray.add(new StringPair(
                        match.group(1).replaceAll("\\s*$", ""),
                        match.group(2)
                ));
            } else {
                resultArray.add(new StringPair(
                   line, ""
                ));
            }
        }

        return resultArray;
    }

    private static String getWspaces(int codeLength, int desiredLength) {
        return new String(new char[desiredLength - codeLength]).replace('\0', ' ');
    }

    private static int longestStringLength(ArrayList<StringPair> codeAndComments) {
        int maxLength = 0;
        for (StringPair codeComment : codeAndComments) {
            maxLength = Math.max(codeComment.getFirst().length(), maxLength);
        }

        return maxLength;
    }

    private static void abort() {
        System.out.println("Wrong usage of a program, see '--help'");
    }
}

class StringPair {

    /**
     * The first element of this <code>StringPair</code>
     */
    private String first;

    /**
     * The second element of this <code>StringPair</code>
     */
    private String second;

    /**
     * Constructs a new <code>StringPair</code> with the given values.
     *
     * @param first  the first element
     * @param second the second element
     */
    public StringPair(String first, String second) {

        this.first = first;
        this.second = second;
    }

    public String getFirst() {
        return first;
    }

    public String getSecond() {
        return second;
    }
}
