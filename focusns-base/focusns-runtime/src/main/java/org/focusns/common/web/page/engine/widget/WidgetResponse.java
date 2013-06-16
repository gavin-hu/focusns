package org.focusns.common.web.page.engine.widget;

import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class WidgetResponse extends HttpServletResponseWrapper implements HttpServletResponse {

    private PrintWriter writer;
    private ResponseServletOutputStream outputStream;
    private ByteArrayOutputStream content = new ByteArrayOutputStream();

    public WidgetResponse(HttpServletResponse response) {
        super(response);
        //
    }

    @Override
    public void flushBuffer() throws IOException {
        FileCopyUtils.copy(content.toByteArray(), super.getOutputStream());
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        if(outputStream==null)  {
            this.outputStream = new ResponseServletOutputStream();
        }
        //
        return outputStream;
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        if (writer == null) {
            this.writer = new ResponsePrintWriter(getCharacterEncoding());
        }
        return writer;
    }

    @Override
    public String toString() {
        return getResponseAsString();
    }

    public String getResponseAsString() {
        try {
            String charEncoding = getResponse().getCharacterEncoding();
            if(StringUtils.hasText(charEncoding)) {
                return content.toString(charEncoding);
            }
            return content.toString();
        } catch (UnsupportedEncodingException e) {
            return e.getMessage();
        }
    }

    public byte[] getResponseAsByteArray() {
        return content.toByteArray();
    }

    private class ResponseServletOutputStream extends ServletOutputStream {

        @Override
        public void write(int b) throws IOException {
            content.write(b);
        }

        @Override
        public void write(byte[] b, int off, int len) throws IOException {
            content.write(b, off, len);
        }
    }

    private class ResponsePrintWriter extends PrintWriter {

        private ResponsePrintWriter(String characterEncoding) throws UnsupportedEncodingException {
            super(new OutputStreamWriter(content, characterEncoding));
        }

        @Override
        public void write(char buf[], int off, int len) {
            super.write(buf, off, len);
            super.flush();
        }

        @Override
        public void write(String s, int off, int len) {
            super.write(s, off, len);
            super.flush();
        }

        @Override
        public void write(int c) {
            super.write(c);
            super.flush();
        }
    }

}
