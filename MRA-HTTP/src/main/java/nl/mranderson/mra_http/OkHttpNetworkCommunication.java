package nl.mranderson.mra_http;


public class OkHttpNetworkCommunication implements NetworkCommunication {

    private OkHttpClient okHttpClient;

    public OkHttpNetworkCommunication() {
        okHttpClient = new OkHttpClient();
    }

    @Override
    public Response request(RequestBuilder requestBuilder) {
        Response response = okHttpClient.newCall(request).execute();

        //TODO wrap the response in my response.

        return response;
    }
}
