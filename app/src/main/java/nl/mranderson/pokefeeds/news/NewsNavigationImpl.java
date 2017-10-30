package nl.mranderson.pokefeeds.news;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;

import java.lang.ref.WeakReference;

class NewsNavigationImpl implements NewsNavigation {

    private WeakReference<FragmentActivity> activity;

    NewsNavigationImpl(FragmentActivity activity) {
        this.activity = new WeakReference<>(activity);
    }

    @Override
    public void openDetailedPage(String link) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(link));
        activity.get().startActivity(intent);
    }
}
