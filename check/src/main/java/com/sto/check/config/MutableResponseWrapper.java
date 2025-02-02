package com.sto.check.config;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.WriteListener;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class MutableResponseWrapper extends HttpServletResponseWrapper {
    private ByteArrayOutputStream content;

    public MutableResponseWrapper(HttpServletResponse response) {
        super(response);
        this.content = new ByteArrayOutputStream();
    }

    @Override
    public ServletOutputStream getOutputStream() {
        return new CustomServletOutputStream(content);
    }

    public String getResponseContent() {
        return IOUtils.toString(content.toByteArray(), StandardCharsets.UTF_8.toString());
    }

    private static class CustomServletOutputStream extends ServletOutputStream {
        private OutputStream buffer;

        public CustomServletOutputStream(OutputStream content) {
            this.buffer = content;
        }

        @Override
        public boolean isReady() {
            return true;
        }

        @Override
        public void setWriteListener(WriteListener writeListener) {
            throw new RuntimeException("Not implemented");
        }

        @Override
        public void write(int b) throws IOException {
            this.buffer.write(b);
        }
    }
}
