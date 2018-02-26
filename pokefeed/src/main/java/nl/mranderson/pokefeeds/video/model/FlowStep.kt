package nl.mranderson.pokefeeds.video.model

sealed class FlowStep {
    class External(val link : String?) : FlowStep()
}