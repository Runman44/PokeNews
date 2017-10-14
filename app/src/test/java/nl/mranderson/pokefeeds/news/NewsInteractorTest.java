package nl.mranderson.pokefeeds.news;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import nl.mranderson.pokefeeds.interfaces.DataLoadedListener;

/**
 * Testing VideoPresenter
 */
public class NewsInteractorTest {

    private NewsInteractor sut;

    @Mock
    NewsInteractor newsInteractor;

    @Mock
    NewsFragment newsFragment;

    @Mock
    DataLoadedListener dataLoadedListener;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        sut = new NewsInteractorImpl();
    }

    @Test
    public void addition_isCorrect2() throws Exception {
        sut.getNews("http://bulbanews.bulbagarden.net/feed/news.rss", dataLoadedListener);

//        verify(videoInteractor, times(1)).getVideos(anyString(), any(DataLoadedListener.class));
//        verify(videoFragment, times(1)).showLoadingState();
    }
}