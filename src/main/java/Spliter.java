import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Spliter {
    @Option(name = "-o")
    String outputName;
    @Option(name = "-d")
    boolean nameToNumber;
    @Option(name = "-l", forbids = {"-c", "-n"})
    int linesSize = -1;
    @Option(name = "-n", forbids = {"-c", "-l"})
    int fileCount = -1;
    @Option(name = "-c", forbids = {"-l", "-n"})
    int charsSize = -1;
    @Argument(required = true, index = 0)
    String inputName;


    public static void main(String[] args) throws IOException {
        new Spliter().launcher(args);

    }

    private void launcher(String[] args) throws IOException {
        CmdLineParser parseString = new CmdLineParser(this);
        try {
            parseString.parseArgument(args);
        } catch (CmdLineException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            System.err.println("java -jar split.jar -d -l|-c|-n num -o basicOutputName inputFileName");
            parseString.printUsage(System.err);
        }
        FileDiv file=new FileDiv(inputName, outputName, linesSize, nameToNumber, fileCount, charsSize);
        file.run();
    }
}