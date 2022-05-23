package sh.bader.demo.fop2aztec;

import org.apache.fop.apps.io.ResourceResolverFactory;
import org.apache.xmlgraphics.io.Resource;
import org.apache.xmlgraphics.io.ResourceResolver;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

class ClasspathResolver implements ResourceResolver {
    private final ResourceResolver wrapped;

    public ClasspathResolver() {
        this.wrapped = ResourceResolverFactory.createDefaultResourceResolver();
    }

    @Override
    public Resource getResource(URI uri) throws IOException {
        if ("classpath".equals(uri.getScheme())) {
            return new Resource(ClasspathResolver.class.getResourceAsStream(uri.getSchemeSpecificPart()));
        }
        return this.wrapped.getResource(uri);
    }

    @Override
    public OutputStream getOutputStream(URI uri) throws IOException {
        if ("classpath".equals(uri.getScheme())) {
            throw new UnsupportedOperationException("resolver is read only ... usage of outputstream is not available");
        }
        return this.wrapped.getOutputStream(uri);
    }
}
