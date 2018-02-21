package nl.mranderson.mra_http.requestbodies;

import java.io.InputStream;

import nl.mranderson.mra_http.RequestBody;


public class InputStreamRequestBody implements RequestBody {
    private final InputStream inputStream;
    private final long contentLength;
    private final String contentType;

    public InputStreamRequestBody(InputStream inputStream, long contentLength, String contentType) {
        this.inputStream = inputStream;
        this.contentLength = contentLength;
        this.contentType = contentType;
    }

    @Override
    public String getContentType() {
        return contentType;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public long getLength() {
        return contentLength;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InputStreamRequestBody that = (InputStreamRequestBody) o;

        if (contentLength != that.contentLength) return false;
        if (!inputStream.equals(that.inputStream)) return false;
        return contentType.equals(that.contentType);
    }

    @Override
    public int hashCode() {
        int result = inputStream.hashCode();
        result = 31 * result + (int) (contentLength ^ (contentLength >>> 32));
        result = 31 * result + contentType.hashCode();
        return result;
    }
}
