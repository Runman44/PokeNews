package nl.mranderson.pokefeeds.video

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View
import nl.mranderson.pokefeeds.R

class ItemDecorator : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
        outRect?.left = view?.resources?.getDimension(R.dimen.tab_padding_bottom)?.toInt()
        outRect?.right = view?.resources?.getDimension(R.dimen.tab_padding_bottom)?.toInt()
        outRect?.top = view?.resources?.getDimension(R.dimen.tab_padding_bottom)?.toInt()
    }

}