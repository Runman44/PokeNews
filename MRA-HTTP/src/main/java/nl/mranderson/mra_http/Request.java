package nl.mranderson.mra_http;

import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

import nl.mranderson.mra_http.requestbodies.Headers;

public class Request {
    private final Method method;
    private final String url;
    private final Header[] headers;
    private final Parameter[] urlParameters;
    @Nullable private final RequestBody body;

    public Request(Method method, String url, Parameter[] urlParameters, Header[] headers, @Nullable RequestBody body) {
        this.method = method;
        this.url = url;
        this.headers = headers;
        this.urlParameters = urlParameters;
        this.body = body;
    }

    public String getUrl() {
        return url;
    }

    public Method getMethod() {
        return method;
    }

    public Header[] getHeaders() {
        return headers;
    }

    public List<Header> getHeaders(String key) {
        return Headers.findByName(headers, key);
    }

    @Nullable
    public Header getHeaderFirst(String key) {
        return Headers.findFirstByName(headers, key);
    }

    public Parameter[] getUrlParameters() {
        return urlParameters;
    }

    public List<Parameter> getUrlParameters(String name) {
        return Parameters.findByName(urlParameters, name);
    }

    @Nullable
    public Parameter getUrlParameterFirst(String name) {
        return Parameters.findFirstByName(urlParameters, name);
    }

    @Nullable
    public RequestBody getBody() {
        return body;
    }

    // region Object overrides


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Request request = (Request) o;

        if (!url.equals(request.url)) return false;
        if (method != request.method) return false;
        if (!Arrays.equals(headers, request.headers)) return false;
        if (!Arrays.equals(urlParameters, request.urlParameters)) return false;
        return body != null ? body.equals(request.body) : request.body == null;

    }

    @Override
    public int hashCode() {
        int result = url.hashCode();
        result = 31 * result + method.hashCode();
        result = 31 * result + Arrays.hashCode(headers);
        result = 31 * result + Arrays.hashCode(urlParameters);
        result = 31 * result + (body != null ? body.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Request{" +
                "url='" + url + '\'' +
                ", method='" + method + '\'' +
                ", body='" + (body == null ? "null" : body) + '\'' +
                '}';
    }

    // endregion
}