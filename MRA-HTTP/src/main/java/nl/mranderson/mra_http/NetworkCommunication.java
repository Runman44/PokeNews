package nl.mranderson.mra_http;


public interface NetworkCommunication {

    /**
     * Execute a request synchronously.
     */
    Response execute(Request request) throws Exception;

    /**
     * Execute a request asynchronously.
     */
    void execute(Request request, ResponseCallback callback);

    /**
     * Execute a request asynchronously.
     */
    void execute(Request request, ResponseCallback callback, ProgressCallback progressCallback);

    /**
     * Create a new RequestBuilder.
     *
     * @param urlPath the path part of the target url to execute the request at
     * @param method .
     */
    RequestBuilder createRequestBuilder(String urlPath, Method method);
}
