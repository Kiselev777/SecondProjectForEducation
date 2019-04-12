import org.junit.Assert;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class FileDivTest {
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    static boolean FileEquals(File file, File file2) throws IOException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            BufferedReader reader2 = new BufferedReader(new FileReader(file2));
            String s = reader.readLine();
            String s2 = reader2.readLine();
            while (true){
                if ((s == null && s2 != null) || (s != null && s2 == null))
                    return false;
                if (s==null && s2==null)
                    break;
                if (!s.equals(s2))
                    return false;
                s=reader.readLine();
                s2=reader2.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return true;
    }


    @Test (expected =  IllegalArgumentException.class)
    public void run() throws IOException {
        new FileDiv("obama.txt", "kisilevPresident", 100, true, -1, -1).run();
        Assert.assertTrue(FileDivTest.FileEquals(new File("kisilevPresident1.txt"), new File("src\\main\\resources\\kisel1.txt")));

        new FileDiv("obama.txt", "kisilevPresident", -1, true, -1, 10000).run();
        Assert.assertTrue(FileDivTest.FileEquals(new File("kisilevPresident1.txt"), new File("src\\main\\resources\\kiseldos1.txt")));

        new FileDiv("obama.txt", "kisilevPresident", 0, true, -1, -1).run();
        Assert.assertTrue(FileDivTest.FileEquals(new File("kisilevPresident1.txt"), new File("src\\main\\resources\\kiseliks1.txt")));

        new FileDiv("obama.txt", "kisilevPresident", -1, false, 0, -1).run();
        Assert.assertTrue(FileDivTest.FileEquals(new File("kisilevPresidenta.txt"), new File("src\\main\\resources\\kiseliksa.txt")));

        new FileDiv("obama.txt", "kisilevPresident", -1, false, -1, 0).run();
        Assert.assertTrue(FileDivTest.FileEquals(new File("kisilevPresident1.txt"), new File("src\\main\\resources\\kiseliks123a.txt")));

        new FileDiv("obama.txt", "kisilevPresident", -1, false, 2, -1).run();
        Assert.assertTrue(FileDivTest.FileEquals(new File("kisilevPresident1.txt"), new File("src\\main\\resources\\kiseliks1234a.txt")));







    }
}