package nl.mranderson.pokefeeds.video;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import nl.mranderson.pokefeeds.interfaces.DataLoadedListener;
import nl.mranderson.pokefeeds.network.GenericStatus;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Testing VideoPresenter
 */
public class VideoPresenterTest {

    private VideoPresenter sut;

    @Mock
    VideoService videoService;

    @Mock
    VideoFragment videoFragment;

    @Mock
    DataLoadedListener dataLoadedListener;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        sut = new VideoPresenter(videoService);
    }

    @Test
    public void addition_isCorrect2() throws Exception {
        sut.attach(videoFragment);
        sut.onEmptyButtonTapped();

        verify(videoService, times(1)).getVideos(anyString(), any(DataLoadedListener.class));
        verify(videoFragment, times(1)).setLoadingState();
    }

    @Test
    public void addition_isCorrect3() throws Exception {
        sut.attach(videoFragment);
        sut.onRetryButtonTapped();

        verify(videoService, times(1)).getVideos(anyString(), any(DataLoadedListener.class));
        verify(videoFragment, times(1)).setLoadingState();
    }

    @Test
    public void addition_isCorrect4() throws Exception {
        sut.attach(videoFragment);
        sut.onRefreshSwiped();

        verify(videoService, times(1)).getVideos(anyString(), any(DataLoadedListener.class));
    }

    @Test
    public void addition_isCorrect5() throws Exception {
        sut.attach(videoFragment);
        sut.onItemLinkTapped("");

        verify(videoFragment, times(1)).onReadMoreClicked(anyString());
    }

    @Test
    public void addition_isCorrect435() throws Exception {
        sut.attach(videoFragment);
        sut.onLoadData();

        verify(videoService, times(1)).getVideos(anyString(), any(DataLoadedListener.class));
        verify(videoFragment, times(1)).setLoadingState();
    }

    @Test
    public void addition_isCorrect43e5() throws Exception {
        sut.attach(videoFragment);

        dataLoadedListener.onDataLoaded(GenericStatus.EXCEPTION, null);

        verify(videoFragment, times(1)).setExceptionState();
    }
}