package nl.mranderson.mra_http.requestbodies;


import java.util.Arrays;

import nl.mranderson.mra_http.Parameter;
import nl.mranderson.mra_http.RequestBody;

public class FormBody implements RequestBody {
    private final Parameter[] parameters;
    private final String contentType;

    /**
     * Create a form with the specified encoding.
     */
    public FormBody(Parameter[] parameters, String contentType) {
        this.parameters = parameters;
        this.contentType = contentType;
    }

    @Override
    public String getContentType() {
        return contentType;
    }

    public Parameter[] getParameters() {
        return parameters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FormBody formBody = (FormBody) o;

        if (!Arrays.equals(parameters, formBody.parameters)) return false;
        return contentType.equals(formBody.contentType);

    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(parameters);
        result = 31 * result + contentType.hashCode();
        return result;
    }
}