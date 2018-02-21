//package nl.mranderson.pokefeeds.news.data.store;
//
//import android.support.annotation.NonNull;
//
//import org.json.JSONArray;
//
//import java.util.List;
//
//import nl.mranderson.pokefeeds.news.NewsItem;
//
//public class CloudUserDataStore implements ItemDataStore {
//
//    private final String contentUrl = "https://bulbanews.bulbagarden.net/feed/news.rss";
////
////    @Inject
////    private NetworkCommunication networkCommunication;
////
////    @Inject
////    private Gson gson;
////
////    @Inject
////    private RssReader rssReader;
//
//    @Override
//    public void get() {
////        try {
////            JSONArray fromRssFeed = rssReader.getFromRssFeed(contentUrl);
////            List<NewsItem> newsItems = handleResult(fromRssFeed);
////            //TODO callback.
////        } catch (Exception ex) {
////
////        }
//    }
//
////    private void execute(String url) throws IOException {
////        networkCommunication.execute(new RequestBuilder(url, Method.GET), new ResponseCallback() {
////            @Override
////            public void onSuccess(Response response) {
////                List<NewsItem> newsItems = handleResult(response.getBody().getContentStream());
////            }
////
////            @Override
////            public void onFailure(Exception caught) {
////
////            }
////        });
////    }
//
//    @NonNull
//    private List<NewsItem> handleResult(JSONArray response) throws Exception {
////        return new ArrayList<>(gson.fromJson(response.toString(), new TypeToken<Collection<NewsItem>>() {
////        }.getType()));
//        return null;
//    }
//}