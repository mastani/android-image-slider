package app.mastani.imageslider

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SnapHelper

class SnapHelper(
    private val snapHelper: SnapHelper,
    var behavior: Int = NOTIFY_ON_SCROLL,
    var onSnapPositionChangeListener: ((position: Int) -> Unit)
) : RecyclerView.OnScrollListener() {
    private var snapPosition = RecyclerView.NO_POSITION

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        if (behavior == NOTIFY_ON_SCROLL)
            dispatchSnapPositionChange(recyclerView)
    }

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        if (behavior == NOTIFY_ON_SCROLL_STATE_IDLE && newState == RecyclerView.SCROLL_STATE_IDLE)
            dispatchSnapPositionChange(recyclerView)
    }

    private fun dispatchSnapPositionChange(recyclerView: RecyclerView) {
        val snapPosition = (recyclerView.adapter as ImageSliderAdapter).getRealPosition()
        val snapPositionChanged = this.snapPosition != snapPosition
        if (snapPositionChanged) {
            onSnapPositionChangeListener.invoke(snapPosition)
            this.snapPosition = snapPosition
        }
    }

    companion object {
        const val NOTIFY_ON_SCROLL = 0
        const val NOTIFY_ON_SCROLL_STATE_IDLE = 1
    }
}