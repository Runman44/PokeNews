package nl.mranderson.mra_http;

/**
 * An interface describing an an asynchronous network operation that either provides a request on success or fails with an Exception.
 */
public interface ResponseCallback {

    /**
     * @param response the HTTP server response
     */
    void onSuccess(Response response);

    /**
     * @param caught the IOException that was thrown during a failing operation
     */
    void onFailure(Exception caught);
}

