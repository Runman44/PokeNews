package nl.mranderson.mra_http.okhttp3.converters;

import nl.mranderson.mra_http.ResponseBody;

public class ResponseBodyConverter {

    public ResponseBody convert(okhttp3.ResponseBody body) {
        return new ResponseBody(body.byteStream(), body.contentLength(), body.contentType().toString());
    }
}