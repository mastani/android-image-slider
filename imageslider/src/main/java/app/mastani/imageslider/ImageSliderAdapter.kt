package app.mastani.imageslider

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Gravity
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView

class ImageSliderAdapter(var context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var realPosition = -1
    private val slides by lazy { ArrayList<Slide>() }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            DRAWABLE -> DrawableViewHolder(context)
            IMAGE_URL -> ImageUrlViewHolder(context)
            else -> EmptyViewHolder(context)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val slide = getItem(position)

        when (holder) {
            is DrawableViewHolder -> holder.bind(slide as DrawableSlide)
            is ImageUrlViewHolder -> holder.bind(slide as ImageUrlSlide)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is DrawableSlide -> DRAWABLE
            is ImageUrlSlide -> IMAGE_URL
            is ImageViewSlide -> IMAGE_VIEW
            is VideoUrlSlide -> VIDEO_URL
            is WebPageSlide -> WEB_PAGE
            else -> EMPTY
        }
    }

    override fun getItemCount(): Int {
        return Integer.MAX_VALUE
    }

    private fun getItem(position: Int): Slide {
        realPosition = position % slides.size
        return slides[realPosition]
    }

    fun getRealSize(): Int {
        return this.slides.size
    }

    fun getRealPosition(): Int {
        return this.realPosition
    }

    fun removeAll() {
        this.slides.clear()
    }

    fun add(slides: ArrayList<Slide>) {
        for (slide in slides)
            this.slides.add(slide)

        notifyDataSetChanged()
    }

    open class SlideViewHolder(context: Context) : RecyclerView.ViewHolder(RelativeLayout(context)) {
        init {
            (itemView as RelativeLayout).apply {
                layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            }
        }
    }

    class EmptyViewHolder(context: Context) : SlideViewHolder(context) {}

    open class ImageViewHolder(context: Context) : SlideViewHolder(context) {
        val imageView = ImageView(context)
        val titleView = TextView(context)

        init {
            imageView.apply {
                val params = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT)
                layoutParams = params
            }

            titleView.apply {
                val params = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT)
                params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)
                params.bottomMargin = 40;
                params.leftMargin = 30;
                layoutParams = params
            }

            (itemView as RelativeLayout).apply {
                addView(imageView)
                addView(titleView)
            }
        }
    }

    class DrawableViewHolder(context: Context) : ImageViewHolder(context) {

        fun bind(slide: DrawableSlide) {
            titleView.text = slide.title
            imageView.setImageDrawable(slide.drawable)
        }
    }

    class ImageUrlViewHolder(context: Context) : ImageViewHolder(context) {

        fun bind(slide: ImageUrlSlide) {
            titleView.text = slide.title
            ImageSlider.imageLoaderService.loadImage(imageView, slide.URL)
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