package nl.mranderson.mra_http.okhttp3.converters;

import org.jetbrains.annotations.Nullable;

import nl.mranderson.mra_http.Header;
import nl.mranderson.mra_http.Parameter;
import nl.mranderson.mra_http.ProgressCallback;
import nl.mranderson.mra_http.Request;
import nl.mranderson.mra_http.RequestBody;
import okhttp3.Headers;
import okhttp3.HttpUrl;


public class RequestConverter {
    private final RequestBodyConverter requestBodyConverter;

    public RequestConverter(RequestBodyConverter requestBodyConverter) {
        this.requestBodyConverter = requestBodyConverter;
    }

    public RequestConverter() {
        this(new RequestBodyConverter());
    }

    public okhttp3.Request convert(Request request, final @Nullable ProgressCallback progressCallback) {
        String method = request.getMethod().toString();
        HttpUrl enrichedUrl = createHttpUrl(request);
        Headers headers = createHeaders(request.getHeaders());

        RequestBody requestBody = request.getBody();
        okhttp3.RequestBody okHttpRequestBody = requestBody != null ? requestBodyConverter.convert(requestBody, progressCallback) : null;

        return new okhttp3.Request.Builder()
                .url(enrichedUrl)
                .headers(headers)
                .method(method, okHttpRequestBody)
                .build();
    }

    private static Headers createHeaders(Header[] headers) {
        Headers.Builder builder = new Headers.Builder();

        for (Header header : headers) {
            builder.add(header.getName(), header.getValue());
        }

        return builder.build();
    }

    private static HttpUrl createHttpUrl(Request request) {
        HttpUrl baseUrl = HttpUrl.parse(request.getUrl());
        HttpUrl.Builder urlBuilder = baseUrl.newBuilder();
        for (Parameter parameter : request.getUrlParameters()) {
            urlBuilder.addEncodedQueryParameter(parameter.getName(), parameter.getValue().toString());
        }
        return urlBuilder.build();
    }
}