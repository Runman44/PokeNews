package nl.mranderson.mra_http;

/**
 * Object that can execute Request instances and returns a Response object.
 */
public interface RequestExecutor {
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
    void execute(Request request, ResponseCallback responseCallback, ProgressCallback progressCallback);
}