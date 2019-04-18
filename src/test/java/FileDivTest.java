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

    ClassLoader classLoader = getClass().getClassLoader();

    static boolean fileEquals(File file, File file2) throws IOException {
        try {
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
        } catch (FileNotFoundException e) {
            throw e;
        }
        return true;
    }

    @Test(expected = IllegalArgumentException.class)
    public void lineZero() throws IOException {
        new FileDiv("obama.txt", "kisilevPresident", 0, true, -1, -1).run();
        Assert.assertTrue(FileDivTest.fileEquals(new File("kisilevPresident1.txt"), new File(Objects.requireNonNull(classLoader.getResource("kiseliks1.txt")).getFile())));
    }

    @Test(expected = IllegalArgumentException.class)
    public void fileZero() throws IOException {
        new FileDiv("obama.txt", "kisilevPresident", -1, false, 0, -1).run();
        Assert.assertTrue(FileDivTest.fileEquals(new File("kisilevPresidenta.txt"), new File(Objects.requireNonNull(classLoader.getResource("kiseliksa.txt")).getFile())));

    }

    @Test(expected = IllegalArgumentException.class)
    public void charZero() throws IOException {
        new FileDiv("obama.txt", "kisilevPresident", -1, false, -1, 0).run();
        Assert.assertTrue(FileDivTest.fileEquals(new File("kisilevPresident1.txt"), new File(Objects.requireNonNull(classLoader.getResource("kiseliks123a.txt")).getFile())));


    }

    @Test(expected = IllegalArgumentException.class )
    public void inputNameError() throws IOException {
        new FileDiv("someFiles", "kisilevPresident", -1, false, -1, 0).run();
        Assert.assertTrue(FileDivTest.fileEquals(new File("kisilevPresident1.txt"), new File(Objects.requireNonNull(classLoader.getResource("kiseliks123a.txt")).getFile())));


    }


    @Test
    public void run() throws IOException {
        new FileDiv("obama.txt", "kisilevPresident", 100, true, -1, -1).run();
        Assert.assertTrue(FileDivTest.fileEquals(new File("kisilevPresident1.txt"), new File(Objects.requireNonNull(classLoader.getResource("kisel1.txt")).getFile())));
        Assert.assertTrue(FileDivTest.fileEquals(new File("kisilevPresident2.txt"), new File(Objects.requireNonNull(classLoader.getResource("kisel2.txt")).getFile())));
        Assert.assertTrue(FileDivTest.fileEquals(new File("kisilevPresident3.txt"), new File(Objects.requireNonNull(classLoader.getResource("kisel3.txt")).getFile())));
        Assert.assertTrue(FileDivTest.fileEquals(new File("kisilevPresident4.txt"), new File(Objects.requireNonNull(classLoader.getResource("kisel4.txt")).getFile())));


        new FileDiv("obama.txt", "kisilevPresident", -1, true, -1, 10000).run();
        Assert.assertTrue(FileDivTest.fileEquals(new File("kisilevPresident1.txt"), new File(Objects.requireNonNull(classLoader.getResource("kiseldos1.txt")).getFile())));
        Assert.assertTrue(FileDivTest.fileEquals(new File("kisilevPresident2.txt"), new File(Objects.requireNonNull(classLoader.getResource("kiseldos2.txt")).getFile())));

        new FileDiv("obama.txt", "kisilevPresident", -1, false, 2, -1).run();
        Assert.assertTrue(FileDivTest.fileEquals(new File("kisilevPresidenta.txt"), new File(Objects.requireNonNull(classLoader.getResource("kiseliks1234a.txt")).getFile())));
        Assert.assertTrue(FileDivTest.fileEquals(new File("kisilevPresidentb.txt"), new File(Objects.requireNonNull(classLoader.getResource("kiseliks1234b.txt")).getFile())));

        new FileDiv("obama.txt", null, -1, false, 2, -1).run();
        Assert.assertTrue(FileDivTest.fileEquals(new File("kisilevPresidenta.txt"), new File(Objects.requireNonNull(classLoader.getResource("xa.txt")).getFile())));
        Assert.assertTrue(FileDivTest.fileEquals(new File("kisilevPresidentb.txt"), new File(Objects.requireNonNull(classLoader.getResource("xb.txt")).getFile())));

        new FileDiv("obama.txt", "", 100, true, -1, -1).run();
        Assert.assertTrue(FileDivTest.fileEquals(new File("kisilevPresident4.txt"), new File(Objects.requireNonNull(classLoader.getResource("4.txt")).getFile())));

    }
}