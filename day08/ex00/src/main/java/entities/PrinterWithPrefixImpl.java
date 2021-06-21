package entities;

public class PrinterWithPrefixImpl implements Printer {
    Renderer renderer;
    String prefix;

    PrinterWithPrefixImpl(Renderer renderer) {
        this.renderer = renderer;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public void print(String msg) {
        renderer.renderMsg(prefix + msg);
    }
}
