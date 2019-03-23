import java.io.*;

class FileDiv {

    static String intToChar(int number) {
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

    static void lineDiv(String inputName, int countLine, String outputName, boolean flag) throws IOException {
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
            while (iterator <= countLine && firstLine != null) {
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

    static void charDiv(String inputName, int countChar, String outputName, boolean flag) throws IOException {
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
            while (iterator <= countChar && firstChar > -1) {
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

    static void fileDiv(String inputName, int countChar, String outputName, boolean flag) throws IOException {
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
                inputName, countChars, outputName, flag);
    }
}

