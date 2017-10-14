package nl.mranderson.pokefeeds.news;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import nl.mranderson.pokefeeds.interfaces.DataLoadedListener;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Testing NewsPresenter
 */
public class NewsPresenterTest {

    private NewsPresenter sut;

    @Mock
    NewsInteractor newsInteractor;

    @Mock
    NewsFragment newsFragment;

    @Mock
    DataLoadedListener dataLoadedListener;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        sut = new NewsPresenter(newsInteractor, newsNavigation);
        sut.attach(newsFragment);
    }

    @Test
    public void addition_isCorrect2() throws Exception {
        sut.onEmptyButtonTapped();

        verify(newsInteractor, times(1)).getNews(anyString(), any(DataLoadedListener.class));
        verify(newsFragment, times(1)).showLoadingState();
    }

    @Test
    public void addition_isCorrect3() throws Exception {
        sut.onRetryButtonTapped();

        verify(newsInteractor, times(1)).getNews(anyString(), any(DataLoadedListener.class));
        verify(newsFragment, times(1)).showLoadingState();
    }

    @Test
    public void addition_isCorrect4() throws Exception {
        sut.onRefreshPulled();

        verify(newsInteractor, times(1)).getNews(anyString(), any(DataLoadedListener.class));
    }

    @Test
    public void addition_isCorrect5() throws Exception {
        sut.onItemLinkTapped("");

        verify(newsFragment, times(1)).onReadMoreClicked(anyString());
    }

    @Test
    public void addition_isCorrect435() throws Exception {
        sut.onLoadData();

        verify(newsInteractor, times(1)).getNews(anyString(), any(DataLoadedListener.class));
        verify(newsFragment, times(1)).showLoadingState();
    }

//    @Test
//    public void addition_isCorrect43e5() throws Exception {
//        dataLoadedListener.onDataLoaded(GenericStatus.EXCEPTION, null);
//
//        verify(newsFragment, times(1)).showExceptionState();
//    }
}