package org.focusns.common.web.page.engine.widget;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class WidgetOutputStream extends ServletOutputStream {

    private OutputStream outputStream;

    public WidgetOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    @Override
    public void write(int b) throws IOException {
        outputStream.write(b);
    }

}
