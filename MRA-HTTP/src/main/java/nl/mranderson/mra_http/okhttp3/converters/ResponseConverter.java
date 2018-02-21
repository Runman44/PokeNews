package nl.mranderson.mra_http.okhttp3.converters;

import nl.mranderson.mra_http.Response;
import nl.mranderson.mra_http.ResponseBody;
import nl.mranderson.mra_http.ResponseBuilder;
import okhttp3.Headers;

public class ResponseConverter {
    private final ResponseBodyConverter bodyConverter;

    public ResponseConverter(ResponseBodyConverter bodyConverter) {
        this.bodyConverter = bodyConverter;
    }

    public ResponseConverter() {
        this(new ResponseBodyConverter());
    }

    public Response convert(okhttp3.Response response) {
        ResponseBuilder builder = new ResponseBuilder(response.code());

        Headers headers = response.headers();
        for (String name : headers.names()) {
            builder.header(name, headers.get(name));
        }

        if (response.body() != null) {
            ResponseBody responseBody = bodyConverter.convert(response.body());
            builder.body(responseBody);
        }

        return builder.build();
    }
}