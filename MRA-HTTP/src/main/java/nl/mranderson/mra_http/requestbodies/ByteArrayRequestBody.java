package nl.mranderson.mra_http.requestbodies;

import java.io.ByteArrayInputStream;

import nl.mranderson.mra_http.RequestBody;

public class ByteArrayRequestBody {

    private ByteArrayRequestBody() {
    }

    public static RequestBody requestBodyOf(byte[] bytes, String contentType) {
        ByteArrayInputStream stream = new ByteArrayInputStream(bytes);
        return new InputStreamRequestBody(stream, bytes.length, contentType);
    }
}
