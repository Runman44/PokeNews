//package nl.mranderson.pokefeeds.news.data;
//
//
//import android.support.annotation.NonNull;
//
//import nl.mranderson.pokefeeds.news.data.store.CloudUserDataStore;
//import nl.mranderson.pokefeeds.news.data.store.DiskUserDataStore;
//import nl.mranderson.pokefeeds.news.data.store.ItemCache;
//import nl.mranderson.pokefeeds.news.data.store.ItemDataStore;
//
//public class ItemDataStoreFactory {
//
////    private final Context context;
//    private final ItemCache itemCache;
//
//    ItemDataStoreFactory(@NonNull ItemCache itemCache) {
////        this.context = context.getApplicationContext();
//        this.itemCache = itemCache;
//    }
//
//    /**
//     * 1. Is there news in the cache and not expired - DiskStore
//     * 2. Is there news in the cache but expired - CloudStore
//     * 3. No news in cache - CloudStore
//     * 4. No internet connection, even if empty - DiskStore
//     *
//     * @return a store that fits the current situation
//     */
//    ItemDataStore create() {
//        ItemDataStore itemDataStore;
//
////        if (!isNetworkConnectivity()) {
////            return new DiskUserDataStore(this.newsCache);
////        }
//
//        //TODO inject and make interface for cloud and disk
//        if (!this.itemCache.isExpired() && this.itemCache.isCached()) {
//            itemDataStore = new DiskUserDataStore(this.itemCache);
//        } else {
//            itemDataStore = new CloudUserDataStore();
//        }
//
//        return itemDataStore;
//    }
//
////    private boolean isNetworkConnectivity() {
////        boolean status = false;
////        try {
////            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
////            NetworkInfo netInfo = cm.getNetworkInfo(0);
////            NetworkInfo netInfo2 = cm.getNetworkInfo(1);
////            if (netInfo != null && netInfo.getState() == NetworkInfo.State.CONNECTED || netInfo2 != null && netInfo2.getState() == NetworkInfo.State.CONNECTED) {
////                status = true;
////            }
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////        return status;
////    }
//}
