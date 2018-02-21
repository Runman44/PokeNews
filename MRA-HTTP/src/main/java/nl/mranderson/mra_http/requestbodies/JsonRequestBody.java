package nl.mranderson.mra_http.requestbodies;


import nl.mranderson.mra_http.ContentTypes;
import nl.mranderson.mra_http.RequestBody;

public class JsonRequestBody {

    private JsonRequestBody() {
    }

    public static RequestBody requestBodyOf(String json) {
        byte[] bytes = json.getBytes();
        return ByteArrayRequestBody.requestBodyOf(bytes, ContentTypes.APPLICATION_JSON);
    }

    public static RequestBody requestBodyOf(String json, String contentType) {
        byte[] bytes = json.getBytes();
        return ByteArrayRequestBody.requestBodyOf(bytes, contentType);
    }
}