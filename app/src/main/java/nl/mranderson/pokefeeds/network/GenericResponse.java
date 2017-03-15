package nl.mranderson.pokefeeds.network;


import java.util.List;

public class GenericResponse {

    private List<GenericItem> items;
    private Exception exception;

    public List<GenericItem> getItems() {
        return items;
    }

    public void setItems(List<GenericItem> items) {
        this.items = items;
    }

    public void setException(final Exception exception) {
        this.exception = exception;
    }

    public boolean hasException() {
        return exception != null;
    }

}