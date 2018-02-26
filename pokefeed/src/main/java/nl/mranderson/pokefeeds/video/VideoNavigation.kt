package nl.mranderson.pokefeeds.video

import android.app.Activity
import android.content.Intent
import android.net.Uri
import nl.mranderson.pokefeeds.video.model.FlowStep

class VideoNavigation(private val context : Activity?) {

    fun doStep(step: FlowStep?) {
        when (step) {
            is FlowStep.External -> openExternalLink(step.link)
        }
    }

    private fun openExternalLink(link : String?) {
        try {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(link)
            context?.startActivity(intent)
        } catch (ex: Exception) {
            //NO-OP
        }
    }
}