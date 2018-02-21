package nl.mranderson.mra_http.requestbodies;

import java.util.ArrayList;

import nl.mranderson.mra_http.ContentTypes;
import nl.mranderson.mra_http.Parameter;

/**
 * Build a form.
 * By default it has as url-encoded content type.
 *
 * With URL-encoded forms, the parameter value Object instances are serialized with Object.toString()
 */
public class FormBodyBuilder {
    private final ArrayList<Parameter> parameters = new ArrayList<>();
    private final String contentType;

    public FormBodyBuilder(String contentType) {
        this.contentType = contentType;
    }

    public FormBodyBuilder() {
        this(ContentTypes.FORM_URL_ENCODED_UTF8);
    }

    public FormBodyBuilder parameter(String name, Object value) {
        return parameter(new Parameter(name, value));
    }

    public FormBodyBuilder parameter(Parameter parameter) {
        parameters.add(parameter);
        return this;
    }

    public FormBody build() {
        Parameter[] parameterArray = parameters.toArray(new Parameter[parameters.size()]);
        return new FormBody(parameterArray, contentType);
    }
}
