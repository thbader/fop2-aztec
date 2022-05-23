package sh.bader.demo.fop2aztec;

import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopConfParser;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.FopFactoryBuilder;
import org.apache.xmlgraphics.util.MimeConstants;
import org.xml.sax.SAXException;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

public class FopService {
    private final FopFactory fopFactory;

    private final FOUserAgent userAgent;

    public FopService() throws FopConfigurationException {
        try {
            FopConfParser fopConfParser = new FopConfParser(
                    this.getClass().getResourceAsStream("fop.xml"),
                    new File(".").toURI(),
                    new ClasspathResolver()
            );

            FopFactoryBuilder factoryBuilder = fopConfParser.getFopFactoryBuilder();

            fopFactory = factoryBuilder.build();

            userAgent = fopFactory.newFOUserAgent();
        } catch (IOException | SAXException e) {
            throw new FopConfigurationException("failed to configure fop service", e);
        }
    }

    public void buildPDF(Source xsl, Source xml, OutputStream pdf) throws FopBuildException {
        try {
            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, userAgent, pdf);

            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(xsl);

            Result result = new SAXResult(fop.getDefaultHandler());
            transformer.transform(xml, result);
        } catch (FOPException | TransformerException e) {
            throw new FopBuildException();
        }
    }
}
