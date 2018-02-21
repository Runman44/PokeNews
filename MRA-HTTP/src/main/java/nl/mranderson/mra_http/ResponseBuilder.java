package nl.mranderson.mra_http;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ResponseBuilder {

    @Nullable
    private ResponseBody responseBody;
    private final List<Header> headers;
    private int statusCode;

    public ResponseBuilder(Response response) {
        this.headers = new ArrayList<>();
        this.responseBody = response.getBody();
        this.statusCode = response.getStatusCode();
        Collections.addAll(headers, response.getHeaders());
    }

    public ResponseBuilder(int statusCode) {
        this.headers = new ArrayList<>();
        this.statusCode = statusCode;
    }

    public ResponseBuilder header(String name, String value) {
        return header(new Header(name, value));
    }

    public ResponseBuilder header(Header header) {
        headers.add(header);
        return this;
    }

    public ResponseBuilder body(ResponseBody responseBody) {
        this.responseBody = responseBody;
        return this;
    }

    public Response build() {
        Header[] headerArray = headers.toArray(new Header[headers.size()]);
        return new Response(responseBody, headerArray, statusCode);
    }
}