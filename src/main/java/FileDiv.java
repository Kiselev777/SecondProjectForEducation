import java.io.*;

class FileDiv {
    private String outputName;
    private boolean nameToNumber;
    private int linesSize ;
    private int fileCount ;
    private int charsSize ;
    private String inputName;

    private static String intToChar(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("This number is bad");
        }
        StringBuilder result = new StringBuilder();
        do {
            result.append((char) (number % 26 + (int) 'a'));
            number /= 26;
        }
        while (number != 0);
        return result.reverse().toString();

    }

    FileDiv(String inputName, String outputName, int countLine, boolean flag, int file, int chars) {
        this.outputName = outputName;
        this.inputName = inputName;
        this.linesSize = countLine;
        this.nameToNumber = flag;
        this.fileCount = file;
        this.charsSize = chars;
    }

    void run() throws IOException {
        if (linesSize == -1 && fileCount == -1 && charsSize == -1)
            linesSize = 100;
        if (outputName == null)
            outputName = "x";
        if (outputName.equals("-")) outputName = inputName;
        if (fileCount != -1) {
                fileDiv( fileCount);
        } else if (charsSize != -1) {
                charDiv( charsSize);
        } else {
                lineDiv(linesSize);
        }
    }

    private  void lineDiv( int countLine) throws IOException {
        if (countLine <= 0) throw new IllegalArgumentException("OOOOOOOOPS");
        int count = 1;
        int iterator = 1;
        BufferedReader reader = new BufferedReader(new FileReader(new File(inputName)));
        String firstLine = reader.readLine();
        String name;
        while (firstLine != null) {
            if (nameToNumber) {
                name = String.format("%s%s.txt", outputName, String.valueOf(count));
            } else {
                name = String.format("%s%s.txt", outputName, intToChar(count - 1));
            }
            FileWriter file = new FileWriter(name);
            while (true) {
                if (iterator > countLine || firstLine == null) break;
                file.write(firstLine + "\n");
                iterator++;
                firstLine = reader.readLine();
            }
            iterator = 1;
            count++;
            file.close();
        }
        reader.close();

    }

    private  void charDiv( int countChar) throws IOException {
        int count = 1;
        if (countChar <= 0) throw new IllegalArgumentException("OOOOOOOOPS");
        int iterator = 1;
        BufferedReader reader = new BufferedReader(new FileReader(new File(inputName)));
        int firstChar = reader.read();
        String name;
        while (firstChar > -1) {
            if (nameToNumber) {
                name = String.format("%s%s.txt", outputName, String.valueOf(count));
            } else {
                name = String.format("%s%s.txt", outputName, intToChar(count - 1));
            }
            FileWriter file = new FileWriter(name);
            while (true) {
                if (iterator > countChar || firstChar <= -1) break;
                file.write((char) firstChar);
                iterator++;
                firstChar = reader.read();
            }
            iterator = 1;
            count++;
            file.close();
        }
        reader.close();
    }

    private  void fileDiv( int countChar) throws IOException {
        if (countChar <= 0) throw new IllegalArgumentException("OOOOOOOOPS");
        BufferedReader reader = new BufferedReader(new FileReader(new File(inputName)));
        int count = 0;
        int firstLine = reader.read();
        while (firstLine > -1) {
            firstLine = reader.read();
            count++;
        }
        int countChars = (count % countChar == 0) ? count / countChar : count / countChar + 1;
        charDiv(
                countChars);
    }
}

