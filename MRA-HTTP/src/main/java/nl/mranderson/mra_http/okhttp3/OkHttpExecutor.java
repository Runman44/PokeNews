package nl.mranderson.mra_http.okhttp3;


import org.jetbrains.annotations.Nullable;

import java.io.IOException;

import nl.mranderson.mra_http.ProgressCallback;
import nl.mranderson.mra_http.Request;
import nl.mranderson.mra_http.RequestExecutor;
import nl.mranderson.mra_http.Response;
import nl.mranderson.mra_http.ResponseCallback;
import nl.mranderson.mra_http.okhttp3.converters.RequestConverter;
import nl.mranderson.mra_http.okhttp3.converters.ResponseConverter;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;

/**
 * An executor that executes a request from the ING-HTTP module with OkHttp3.
 */
public class OkHttpExecutor implements RequestExecutor {

    private final OkHttpClient okHttpClient;
    private final RequestConverter requestConverter;
    private final ResponseConverter responseConverter;

    // region constructors

    public OkHttpExecutor(OkHttpClient okHttpClient, RequestConverter requestConverter, ResponseConverter responseConverter) {
        this.okHttpClient = okHttpClient;
        this.requestConverter = requestConverter;
        this.responseConverter = responseConverter;
    }

    public OkHttpExecutor(OkHttpClient okHttpClient) {
        this(okHttpClient, new RequestConverter(), new ResponseConverter());
    }

    // endregion

    // region RequestExecutor

    @Override
    public Response execute(Request request) throws Exception {
        // convert ING Request to OkHttp Request
        okhttp3.Request okHttpRequest = requestConverter.convert(request, null);
        // execute OkHttp request
        okhttp3.Response okHttpResponse = okHttpClient.newCall(okHttpRequest).execute();
        // convert OkHttp response to ING Response
        return responseConverter.convert(okHttpResponse);
    }

    @Override
    public void execute(Request request, ResponseCallback responseCallback) {
        execute(request, responseCallback, null);
    }

    @Override
    public void execute(Request request, final ResponseCallback responseCallback, final @Nullable ProgressCallback progressCallback) {
        try {
            // convert ING Request to OkHttp Request
            okhttp3.Request okHttpRequest = requestConverter.convert(request, progressCallback);
            // execute OkHttp request
            okHttpClient.newCall(okHttpRequest).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    responseCallback.onFailure(e);
                }

                @Override
                public void onResponse(Call call, okhttp3.Response okHttpResponse) throws IOException {
                    // convert OkHttp response to ING Response
                    Response response = responseConverter.convert(okHttpResponse);
                    responseCallback.onSuccess(response);
                }
            });
        } catch (Exception e) {
            responseCallback.onFailure(e);
        }
    }

    // endregion
}