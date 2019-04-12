import java.io.*;

class FileDiv extends Spliter {

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
                FileDiv.fileDiv(inputName, outputName, fileCount, nameToNumber);
        } else if (charsSize != -1) {
                FileDiv.charDiv(inputName, outputName, charsSize, nameToNumber);
        } else {
                FileDiv.lineDiv(inputName, outputName, linesSize, nameToNumber);
        }
    }

    private static void lineDiv(String inputName, String outputName, int countLine, boolean flag) throws IOException {
        if (countLine <= 0) throw new IllegalArgumentException("OOOOOOOOPS");
        int count = 1;
        int iterator = 1;
        BufferedReader reader = new BufferedReader(new FileReader(new File(inputName)));
        String firstLine = reader.readLine();
        String name;
        while (firstLine != null) {
            if (flag) {
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

    private static void charDiv(String inputName, String outputName, int countChar, boolean flag) throws IOException {
        int count = 1;
        if (countChar <= 0) throw new IllegalArgumentException("OOOOOOOOPS");
        int iterator = 1;
        BufferedReader reader = new BufferedReader(new FileReader(new File(inputName)));
        int firstChar = reader.read();
        String name;
        while (firstChar > -1) {
            if (flag) {
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

    private static void fileDiv(String inputName, String outputName, int countChar, boolean flag) throws IOException {
        if (countChar <= 0) throw new IllegalArgumentException("OOOOOOOOPS");
        BufferedReader reader = new BufferedReader(new FileReader(new File(inputName)));
        int count = 0;
        int firstLine = reader.read();
        while (firstLine > -1) {
            firstLine = reader.read();
            count++;
        }
        int countChars = (count % countChar == 0) ? count / countChar : count / countChar + 1;
        FileDiv.charDiv(
                inputName, outputName, countChars, flag);
    }
}

