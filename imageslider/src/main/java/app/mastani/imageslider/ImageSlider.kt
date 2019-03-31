package app.mastani.imageslider

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.util.Log
import android.view.ViewGroup
import android.widget.FrameLayout
import app.mastani.imageslider.SnapHelper.Companion.NOTIFY_ON_SCROLL

class ImageSlider : FrameLayout {
    lateinit var rv: RecyclerView
    private val adapter by lazy { ImageSliderAdapter(context) }

    var indicatorVisibility: Boolean = true
    var indicatorSize: Float = 1f

    constructor(context: Context) : super(context) {
        setup()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        setup(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        setup(attrs)
    }

    private fun setup(attrs: AttributeSet? = null) {
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ImageSlider);
            indicatorVisibility = typedArray.getBoolean(R.styleable.ImageSlider_imageSlider_indicatorsVisibility, true)
            indicatorSize = typedArray.getDimension(R.styleable.ImageSlider_imageSlider_indicatorSize, 1f)

            typedArray.recycle();
        }

        initialRecyclerView()
    }

    private fun initialRecyclerView() {
        val snapHelper = PagerSnapHelper()

        rv = RecyclerView(context)
        rv.apply {
            layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = this@ImageSlider.adapter

            snapHelper.attachToRecyclerView(this)
            addOnScrollListener(SnapHelper(snapHelper, NOTIFY_ON_SCROLL) { position ->
                Log.d("ttttttttttttt", "selected " + position)
            })
        }

        addView(rv)
    }

    fun setSlides(slides: ArrayList<Slide>) {
        adapter.removeAll()
        adapter.add(slides)
        rv.scrollToPosition((Integer.MAX_VALUE / 2) - (Integer.MAX_VALUE / 2) % slides.size);
    }

    companion object {
        lateinit var imageLoaderService: ImageLoaderService
    }
}