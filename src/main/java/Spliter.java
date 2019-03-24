import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.IOException;

public class Spliter {
    @Option(name = "-o")
    private String outputName;
    @Option(name = "-d")
    private boolean nameToNumber;
    @Option(name = "-l", forbids = {"-c", "-n"})
    private int linesSize = -1;
    @Option(name = "-n", forbids = {"-c", "-l"})
    private int fileCount = -1;
    @Option(name = "-c", forbids = {"-l", "-n"})
    private int charsSize = -1;
    @Argument(required = true, index = 0)
    private String inputName;

    public Spliter() {
    }

    public static void main(String[] args) throws IOException {
        new Spliter().launcher(args);

    }

    void launcher(String[] args) throws IOException {
        CmdLineParser parseString = new CmdLineParser(this);
        try {
            parseString.parseArgument(args);
        } catch (CmdLineException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            System.err.println("java -jar split.jar -d -l|-c|-n num -o basicOutputName inputFileName");
            parseString.printUsage(System.err);
        }
        if (linesSize == -1 && fileCount == -1 && charsSize == -1)
            linesSize = 100;
        if (outputName == null)
            outputName = "x";
        if (outputName.equals("-")) outputName = inputName;
        if (fileCount != -1) {
            FileDiv.fileDiv(inputName, outputName, fileCount, nameToNumber);
        } else if (charsSize != -1) {
            FileDiv.charDiv(inputName, outputName, charsSize, nameToNumber);
        } else {
            FileDiv.lineDiv(inputName, outputName, linesSize, nameToNumber);
        }

    }
}