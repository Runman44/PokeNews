package nl.mranderson.pokefeeds.video.model

sealed class VideoState {
    class Initial : VideoState()
    class Loading : VideoState()
    class Error(val throwable : Throwable) : VideoState()
    class Done(val result: List<Video>) : VideoState()
}