package nl.mranderson.mra_http.okhttp3.bodies;

import java.io.IOException;

import nl.mranderson.mra_http.Parameter;
import nl.mranderson.mra_http.requestbodies.FormBody;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;

public class OkHttpUrlEncodedForm extends RequestBody {
    private final RequestBody requestBody;

    public OkHttpUrlEncodedForm(FormBody formBody) {
        requestBody = createRequestBody(formBody);
    }

    @Override
    public MediaType contentType() {
        return requestBody.contentType();
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {
        requestBody.writeTo(sink);
    }

    private static okhttp3.FormBody createRequestBody(FormBody formBody) {
        okhttp3.FormBody.Builder builder = new okhttp3.FormBody.Builder();

        for (Parameter parameter : formBody.getParameters()) {
            builder.add(parameter.getName(), parameter.getValue().toString());
        }

        return builder.build();
    }
}

