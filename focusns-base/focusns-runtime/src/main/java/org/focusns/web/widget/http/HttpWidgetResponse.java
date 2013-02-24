package org.focusns.web.widget.http;

import org.focusns.web.widget.WidgetResponse;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;

public class HttpWidgetResponse implements WidgetResponse {

    private boolean commited;
	private PrintWriter writer;
	private ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	
	public HttpWidgetResponse() {
	}

	public PrintWriter getWriter() {
		if(writer==null) {
			this.writer = new ResponsePrintWriter(new PrintWriter(outputStream));
		}
		//
		return writer; 
	}

	public OutputStream getOutputStream() {
		return outputStream;
	}

    public void commit() {
        this.commited = true;
    }

    public boolean isCommitted() {
        return commited;
    }

    @Override
	public String toString() {
		return outputStream.toString();
	}
	
	private class ResponsePrintWriter extends PrintWriter {

		public ResponsePrintWriter(Writer out) {
			super(out, true);
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

        @Override
		public void flush() {
			super.flush();
		}
	}

}
