package nl.mranderson.mra_http;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for Parameter objects.
 */
public class Parameters {

    private Parameters() {
    }

    public static List<Parameter> findByName(Parameter[] parameters, String name) {
        ArrayList<Parameter> result = new ArrayList<>();

        for (Parameter parameter : parameters) {
            if (name.equals(parameter.getName())) {
                result.add(parameter);
            }
        }

        return result;
    }

    @Nullable
    public static Parameter findFirstByName(Parameter[] parameters, String name) {
        for (Parameter parameter : parameters) {
            if (name.equals(parameter.getName())) {
                return parameter;
            }
        }

        return null;
    }
}