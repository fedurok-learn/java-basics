
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class KOmment {
    private String delimiter = "//";
    private String fname;
    // amount of whitespaces after the longest line
    private int columnLength = 10;

    public static void main(String[] args) {
        boolean expectDelimiter = false;

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
                default: {
                    if (expectDelimiter) {
                        delimiter = arg;
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

    private void process() {
        assert(!fname.isEmpty());

        // opening input file
        File file = new File(fname);

        // opening output file
        try {
            FileWriter outputFile = new FileWriter(fname + ".pcom");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        // transforming
        try {
            Scanner reader = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("There are no such file");
            return;
        }
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            String code = getCode(line);
            String comment = getComment(line);
            int wspaceNum =
            outputFile.writeln(
                     + delimiter + getComment(line)
            );
        }
    }

    private static String getComment(String line) {

    }

    private static String getCode(String line) {

    }

    private static void abort() {
        System.out.println("Wrong usage of a programm, see '--help'");
    }

    private static void help() {
        System.out.println("usage");
        System.out.println("KOmment -d '//' filename");
        System.out.println("Flags: ");
        System.out.println("-d or --delimiter ';;' - set comment delimiter ('//' by default)");
        System.out.println("-h or --help for help");
        System.out.println("program outputs file names: 'filename.pcom'");
    }
}
