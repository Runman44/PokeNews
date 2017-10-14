package nl.mranderson.pokefeeds.news;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;

//TODO memory leak?
class NewsNavigationImpl implements NewsNavigation {

    private FragmentActivity activity;

    NewsNavigationImpl(FragmentActivity activity) {
        this.activity = activity;
    }

    @Override
    public void openDetailedPage(String link) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(link));
        activity.startActivity(intent);
    }
}
