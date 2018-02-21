//package nl.mranderson.pokefeeds.news.data;
//
//import java.util.List;
//
//import nl.mranderson.pokefeeds.news.data.store.ItemDataStore;
//
//public class ItemDataRepository<T> implements ItemRepository<T> {
//
//    private static ItemDataRepository itemDataRepository;
//    private ItemDataStoreFactory itemDataStoreFactory;
//
//    private ItemDataRepository(ItemDataStoreFactory itemDataStoreFactory) {
//        this.itemDataStoreFactory = itemDataStoreFactory;
//    }
//
//    //TODO should be static.
//    public static ItemRepository getInstance() {
//        if (itemDataRepository == null) {
//            //TODO generic not here right.
////            itemDataRepository = new ItemDataRepository(new ItemDataStoreFactory(new ItemCacheImpl<T>()));
//        }
//        return itemDataRepository;
//    }
//
//    @Override
//    public List<T> getAll() {
//        ItemDataStore itemDataStore = itemDataStoreFactory.create();
////        return itemDataStore.get();
//        return null;
//    }
//
//
//}
