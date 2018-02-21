package nl.mranderson.mra_http;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public final class ResponseBodyUtil {

    private static final int BUFFER_SIZE = 1024;

    private ResponseBodyUtil() {
    }

    public static String stringOf(ResponseBody responseBody) {
        byte[] bytes = byteArrayOf(responseBody);
        return new String(bytes, Charset.defaultCharset());
    }

    private static byte[] byteArrayOf(ResponseBody responseBody) {
        InputStream inputStream = responseBody.getContentStream();

        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[BUFFER_SIZE];
        int length;

        try {
            while ((length = inputStream.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("Invalid response body", e);
        }
        finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                // Ignored, there's not much we can do.
            }
        }



        return result.toByteArray();
    }
}