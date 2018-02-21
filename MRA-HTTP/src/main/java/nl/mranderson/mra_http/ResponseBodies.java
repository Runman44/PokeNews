package nl.mranderson.mra_http;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Util methods for {@link ResponseBody}
 */
public final class ResponseBodies {

    private ResponseBodies() {
        // No instantiations
    }

    public static String stringOf(ResponseBody responseBody) {
        byte[] bytes = byteArrayOf(responseBody);
        return new String(bytes, Charset.defaultCharset());
    }

    public static byte[] byteArrayOf(ResponseBody responseBody) {
        if (responseBody.getContentLength() < Integer.MIN_VALUE
                || responseBody.getContentLength() > Integer.MAX_VALUE) {
            throw new IllegalArgumentException(responseBody.getContentLength()
                    + " cannot be cast to int without changing its value.");
        }

        BufferedInputStream bis = null;
        try {
            bis = new BufferedInputStream(responseBody.getContentStream());
            ByteArrayOutputStream buf = new ByteArrayOutputStream((int) responseBody.getContentLength());
            int result = bis.read();
            while (result != -1) {
                buf.write((byte) result);
                result = bis.read();
            }
            return buf.toByteArray();
        } catch (IOException e) {
            throw new IllegalArgumentException("Invalid response body", e);
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException ex) {
                    // Ignored, there's not much we can do.
                }
            }
        }
    }
}