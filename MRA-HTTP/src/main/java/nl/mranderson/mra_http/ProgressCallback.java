package nl.mranderson.mra_http;

/*
    This interface allows any service implementation to hook into a network call and retrieve progress updates.
    Currently, only request listeners are supported.
 */
public interface ProgressCallback {

    void onRequestProgress(long uploadedBytes, long totalBytes);

    // TODO implement the progress listener for the response calls as well.
    // TODO this will be picked up in a different story

}
