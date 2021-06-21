package entities;

import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

public class PrinterWithDateTimeImpl implements Printer {
    Renderer renderer;

    public PrinterWithDateTimeImpl(Renderer renderer) {
        this.renderer = renderer;
    }

    public void print(String msg) {
        renderer.renderMsg(msg + LocalDateTime.now());
    }
}
