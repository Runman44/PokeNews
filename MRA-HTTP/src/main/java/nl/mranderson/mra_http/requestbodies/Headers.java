package nl.mranderson.mra_http.requestbodies;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import nl.mranderson.mra_http.Header;

public class Headers {

    private Headers() {
    }

    public static List<Header> findByName(Header[] headers, String name) {
        ArrayList<Header> result = new ArrayList<>();

        for (Header header : headers) {
            if (name.equals(header.getName())) {
                result.add(header);
            }
        }

        return result;
    }

    @Nullable
    public static Header findFirstByName(Header[] headers, String name) {
        for (Header header : headers) {
            if (name.equals(header.getName())) {
                return header;
            }
        }

        return null;
    }
}