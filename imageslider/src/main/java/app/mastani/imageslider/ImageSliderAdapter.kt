package app.mastani.imageslider

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout

class ImageSliderAdapter(var context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val slides by lazy { ArrayList<Slide>() }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            DRAWABLE -> DrawableViewHolder(ImageView(context))
            else -> EmptyViewHolder(View(context))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val slide = slides.get(position)

        when (holder) {
            is DrawableViewHolder -> holder.bind(slide as DrawableSlide)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (slides.get(position)) {
            is DrawableSlide -> DRAWABLE
            is ImageUrlSlide -> IMAGE_URL
            is ImageViewSlide -> IMAGE_VIEW
            is VideoUrlSlide -> VIDEO_URL
            is WebPageSlide -> WEB_PAGE
            else -> EMPTY
        }
    }

    override fun getItemCount(): Int {
        return slides.size
    }

    fun add(slides: ArrayList<Slide>) {
        for (slide in slides)
            this.slides.add(slide)

        notifyDataSetChanged()
    }

    class DrawableViewHolder : RecyclerView.ViewHolder {
        constructor(view: ImageView) : super(view) {
            view.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        }

        fun bind(drawable: DrawableSlide) {
            (itemView as ImageView).setImageDrawable(drawable.drawable)
        }
    }

    class EmptyViewHolder : RecyclerView.ViewHolder {
        constructor(itemView: View) : super(itemView) {

        }
    }

    companion object {
        private const val EMPTY = -1
        private const val DRAWABLE = 0
        private const val IMAGE_URL = 1
        private const val IMAGE_VIEW = 2
        private const val VIDEO_URL = 3
        private const val WEB_PAGE = 4
    }
}