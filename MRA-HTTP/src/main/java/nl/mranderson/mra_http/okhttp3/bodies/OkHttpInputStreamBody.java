package nl.mranderson.mra_http.okhttp3.bodies;

import org.jetbrains.annotations.Nullable;

import java.io.IOException;

import nl.mranderson.mra_http.ProgressCallback;
import nl.mranderson.mra_http.requestbodies.InputStreamRequestBody;
import okhttp3.MediaType;
import okhttp3.internal.Util;
import okio.BufferedSink;
import okio.Okio;
import okio.Source;

public class OkHttpInputStreamBody extends okhttp3.RequestBody {
    private final InputStreamRequestBody body;

    @Nullable
    private final ProgressCallback progressCallback;

    private final static long BUFFER_SIZE = 2048L;

    public OkHttpInputStreamBody(InputStreamRequestBody body, @Nullable ProgressCallback progressCallback) {
        // OkHttp requires the ability to repeat reading from the InputStream, so
        // we need to reset it. This means we cannot support InputStreams that are not resettable.
        if (!body.getInputStream().markSupported()) {
            throw new IllegalArgumentException("InputStream must be resettable due to interceptors also reading from it");
        }

        this.body = body;
        this.progressCallback = progressCallback;
    }

    @Override
    public MediaType contentType() {
        return MediaType.parse(body.getContentType());
    }

    @Override
    public long contentLength() {
        return body.getLength();
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {
        Source source = null;
        try {
            source = Okio.source(body.getInputStream());

            if (progressCallback != null) {
                write(sink, source, progressCallback, contentLength());
            } else {
                write(sink, source);
            }
        } finally {
            body.getInputStream().reset();
            Util.closeQuietly(source);
        }
    }

    private void write(BufferedSink sink, Source source) throws IOException {
        source.read(sink.buffer(), BUFFER_SIZE);
        sink.writeAll(source);
    }

    private void write(BufferedSink sink, Source source, ProgressCallback callback, long contentLength) throws IOException {
        long total = 0L;
        callback.onRequestProgress(total, contentLength);
        long read;
        while ((read = source.read(sink.buffer(), BUFFER_SIZE)) != -1) {
            total += read;
            callback.onRequestProgress(total, contentLength);
            sink.flush();
        }
    }
}