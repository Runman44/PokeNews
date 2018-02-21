package nl.mranderson.mra_http.okhttp3.converters;

import org.jetbrains.annotations.Nullable;

import nl.mranderson.mra_http.ContentTypes;
import nl.mranderson.mra_http.ProgressCallback;
import nl.mranderson.mra_http.RequestBody;
import nl.mranderson.mra_http.okhttp3.bodies.OkHttpInputStreamBody;
import nl.mranderson.mra_http.okhttp3.bodies.OkHttpUrlEncodedForm;
import nl.mranderson.mra_http.requestbodies.FormBody;
import nl.mranderson.mra_http.requestbodies.InputStreamRequestBody;

public class RequestBodyConverter {

    public okhttp3.RequestBody convert(RequestBody body, @Nullable ProgressCallback progressCallback) {
        if (body instanceof InputStreamRequestBody) {
            return new OkHttpInputStreamBody((InputStreamRequestBody) body, progressCallback);
        } else if (body instanceof FormBody) {
            FormBody formBody = (FormBody) body;
            String contentType = formBody.getContentType();
            boolean validContentType = ContentTypes.FORM_URL_ENCODED.equals(contentType)
                    || contentType.startsWith(ContentTypes.FORM_URL_ENCODED + ";");
            if (!validContentType) {
                throw new IllegalArgumentException("RequestBody contains FormBody with an unsupported content type: " + contentType);
            }
            return new OkHttpUrlEncodedForm(formBody);
        } else {
            throw new IllegalArgumentException("unsupported RequestBody of type " + body.getClass().getName());
        }
    }
}