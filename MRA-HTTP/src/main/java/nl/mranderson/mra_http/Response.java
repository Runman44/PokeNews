package nl.mranderson.mra_http;

import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

import nl.mranderson.mra_http.requestbodies.Headers;

public class Response {
    @Nullable
    private final ResponseBody body;
    private final Header[] headers;
    private final int statusCode;

    public Response(@Nullable ResponseBody body, Header[] headers, int statusCode) {
        this.body = body;
        this.headers = headers;
        this.statusCode = statusCode;
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

    @Nullable
    public ResponseBody getBody() {
        return body;
    }

    /**
     * @return the response code or -1 when the code is unknown.
     */
    public int getStatusCode() {
        return statusCode;
    }

    public boolean isSuccessful() {
        return statusCode >= 200 && statusCode < 300;
    }

    // region Object overrides


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Response response = (Response) o;

        if (statusCode != response.statusCode) return false;
        if (body != null ? !body.equals(response.body) : response.body != null) return false;
        return Arrays.equals(headers, response.headers);

    }

    @Override
    public int hashCode() {
        int result = body != null ? body.hashCode() : 0;
        result = 31 * result + Arrays.hashCode(headers);
        result = 31 * result + statusCode;
        return result;
    }

    @Override
    public String toString() {
        return "Response{" +
                "code='" + statusCode + '\'' +
                ", body='" + (body == null ? "null" : body) + '\'' +
                ", headers='" + Arrays.toString(headers)+ '\'' +
                '}';
    }

    // endregion
}
