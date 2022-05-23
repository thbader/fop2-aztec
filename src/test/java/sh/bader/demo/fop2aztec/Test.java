package sh.bader.demo.fop2aztec;

import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class Test {
    public static void main(String[] args) throws Exception {
        FopService fopService = new FopService();

        StreamSource xsl = new StreamSource(Test.class.getResourceAsStream("test.xsl"));
        StreamSource xml = new StreamSource(Test.class.getResourceAsStream("test.xml"));

        Path pdf = Path.of("target", "test.pdf");
        Files.deleteIfExists(pdf);

        try (OutputStream output = Files.newOutputStream(pdf, StandardOpenOption.CREATE_NEW)) {
            fopService.buildPDF(xsl, xml, output);
        }
    }
}
