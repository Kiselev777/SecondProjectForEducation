import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.util.Objects;

import static org.junit.Assert.*;

public class FileDivTest {
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }


    static boolean fileEquals(File file, File file2) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        BufferedReader reader2 = new BufferedReader(new FileReader(file2));
        String s = reader.readLine();
        String s2 = reader2.readLine();
        while (true) {
            if ((s == null && s2 != null) || (s != null && s2 == null))
                return false;
            if (s == null && s2 == null)
                break;
            if (!s.equals(s2))
                return false;
            s = reader.readLine();
            s2 = reader2.readLine();
        }
        return true;
    }

    File createThisFilePLZ(String s) {
        ClassLoader classLoader = getClass().getClassLoader();
        return new File(Objects.requireNonNull(classLoader.getResource(s)).getFile());
    }

    @Test(expected = IllegalArgumentException.class)
    public void lineZero() throws Exception {
        new FileDiv("obama.txt", "kisilevPresident", 0, true, -1, -1).run();
        Assert.assertTrue(FileDivTest.fileEquals(new File("kisilevPresident1.txt"), createThisFilePLZ("kiseliks1.txt")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void fileZero() throws Exception {
        new FileDiv("obama.txt", "kisilevPresident", -1, false, 0, -1).run();
        Assert.assertTrue(FileDivTest.fileEquals(new File("kisilevPresidenta.txt"), createThisFilePLZ("kiseliksa.txt")));

    }

    @Test(expected = IllegalArgumentException.class)
    public void charZero() throws Exception {
        new FileDiv("obama.txt", "kisilevPresident", -1, false, -1, 0).run();
        Assert.assertTrue(FileDivTest.fileEquals(new File("kisilevPresident1.txt"), createThisFilePLZ("kiseliks123a.txt")));


    }

    @Test(expected = IllegalArgumentException.class)
    public void inputNameError() throws Exception {
        new FileDiv("someFiles", "kisilevPresident", -1, false, -1, 0).run();
        Assert.assertTrue(FileDivTest.fileEquals(new File("kisilevPresident1.txt"), createThisFilePLZ("kiseliks123a.txt")));


    }


    @Test
    public void run() throws Exception {
        new FileDiv("obama.txt", "kisilevPresident", 100, true, -1, -1).run();
        Assert.assertTrue(FileDivTest.fileEquals(new File("kisilevPresident1.txt"), createThisFilePLZ("kisel1.txt")));
        Assert.assertTrue(FileDivTest.fileEquals(new File("kisilevPresident2.txt"), createThisFilePLZ("kisel2.txt")));
        Assert.assertTrue(FileDivTest.fileEquals(new File("kisilevPresident3.txt"), createThisFilePLZ("kisel3.txt")));
        Assert.assertTrue(FileDivTest.fileEquals(new File("kisilevPresident4.txt"), createThisFilePLZ("kisel4.txt")));


        new FileDiv("obama.txt", "kisilevPresident", -1, true, -1, 10000).run();
        Assert.assertTrue(FileDivTest.fileEquals(new File("kisilevPresident1.txt"), createThisFilePLZ("kiseldos1.txt")));
        Assert.assertTrue(FileDivTest.fileEquals(new File("kisilevPresident2.txt"), createThisFilePLZ("kiseldos2.txt")));

        new FileDiv("obama.txt", "kisilevPresident", -1, false, 2, -1).run();
        Assert.assertTrue(FileDivTest.fileEquals(new File("kisilevPresidenta.txt"), createThisFilePLZ("kiseliks1234a.txt")));
        Assert.assertTrue(FileDivTest.fileEquals(new File("kisilevPresidentb.txt"),  createThisFilePLZ("kiseliks1234b.txt")));

        new FileDiv("obama.txt", null, -1, false, 2, -1).run();
        Assert.assertTrue(FileDivTest.fileEquals(new File("kisilevPresidenta.txt"), createThisFilePLZ("xa.txt")));
        Assert.assertTrue(FileDivTest.fileEquals(new File("kisilevPresidentb.txt"), createThisFilePLZ("xb.txt")));

        new FileDiv("obama.txt", "", 100, true, -1, -1).run();
        Assert.assertTrue(FileDivTest.fileEquals(new File("kisilevPresident4.txt"), createThisFilePLZ("4.txt")));

    }
}