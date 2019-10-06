

public class KOmment {
    private String delimiter = "//";
    private String fname;

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

    private static void process() {
        assert(!fname.isEmpty());
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
    }
}
