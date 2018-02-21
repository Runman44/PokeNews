package nl.mranderson.mra_http;

import org.jetbrains.annotations.Nullable;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class ResponseBody {
    private final InputStream contentStream;
    private final long contentLength;
    @Nullable
    private final String contentType;

    public ResponseBody(InputStream contentStream, long contentLength, @Nullable String contentType) {
        this.contentStream = contentStream;
        this.contentLength = contentLength;
        this.contentType = contentType;
    }

    public ResponseBody(byte[] data, @Nullable String contentType) {
        this.contentStream = new ByteArrayInputStream(data);
        this.contentLength = data.length;
        this.contentType = contentType;
    }

    @Nullable
    public String getContentType() {
        return contentType;
    }

    public long getContentLength() {
        return contentLength;
    }

    public InputStream getContentStream() {
        return contentStream;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResponseBody that = (ResponseBody) o;

        if (contentLength != that.contentLength) return false;
        if (!contentStream.equals(that.contentStream)) return false;
        return contentType.equals(that.contentType);

    }

    @Override
    public int hashCode() {
        int result = contentStream.hashCode();
        result = 31 * result + (int) (contentLength ^ (contentLength >>> 32));
        result = 31 * result + contentType.hashCode();
        return result;
    }
}

