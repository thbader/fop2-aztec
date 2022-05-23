package sh.bader.demo.fop2aztec;

public class FopBuildException extends Exception {
    public FopBuildException() {
        super();
    }

    public FopBuildException(String message) {
        super(message);
    }

    public FopBuildException(Throwable cause) {
        super(cause);
    }

    public FopBuildException(String message, Throwable cause) {
        super(message, cause);
    }
}
