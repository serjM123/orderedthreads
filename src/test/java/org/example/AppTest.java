package org.example;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AppTest {
    @Test
    public void testApp() throws InterruptedException {
        PrintStream out = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        App.main(new String[]{""});
        System.setOut(out);
        String[] lines = baos.toString().split("\\r?\\n");

        List<String> pattern = List.of("First", "Second", "Third", "Forth");
        for (int i = 0, m = 0; i < lines.length; i++, m = i % pattern.size()) {
            assertEquals(lines[i], pattern.get(m));
        }
    }
}
