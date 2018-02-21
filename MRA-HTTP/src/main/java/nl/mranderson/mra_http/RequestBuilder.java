package nl.mranderson.mra_http;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RequestBuilder {
    protected String url;
    protected final List<Parameter> urlParameters;
    protected final Method method;
    protected final List<Header> headers;
    @Nullable
    protected RequestBody body;

    public RequestBuilder(String url, Method method) {
        this.url = url;
        this.method = method;
        this.urlParameters = new ArrayList<>();
        this.headers = new ArrayList<>();
    }

    public RequestBuilder(Request request) {
        this.url = request.getUrl();
        this.urlParameters = new ArrayList<>();
        this.method = request.getMethod();
        this.headers = new ArrayList<>();
        this.body = request.getBody();

        Collections.addAll(headers, request.getHeaders());
        Collections.addAll(urlParameters, request.getUrlParameters());
    }

    public RequestBuilder url(String url) {
        this.url = url;
        return this;
    }

    public RequestBuilder urlParameter(String name, Object value) {
        return urlParameter(new Parameter(name, value));
    }

    public RequestBuilder urlParameter(Parameter parameter) {
        urlParameters.add(parameter);
        return this;
    }

    public RequestBuilder urlParameters(Parameter[] parameters) {
        Collections.addAll(urlParameters, parameters);
        return this;
    }

    public RequestBuilder header(String name, String value) {
        return header(new Header(name, value));
    }

    public RequestBuilder header(Header header) {
        headers.add(header);
        return this;
    }

    public RequestBuilder headers(Header[] headers) {
        this.headers.addAll(Arrays.asList(headers));
        return this;
    }

    public RequestBuilder body(RequestBody requestBody) {
        this.body = requestBody;
        return this;
    }

    // endregion

    public Request build() {
        Header[] headerArray = headers.toArray(new Header[headers.size()]);
        Parameter[] urlParameterArray = urlParameters.toArray(new Parameter[urlParameters.size()]);
        return new Request(method, url, urlParameterArray, headerArray, body);
    }
}
