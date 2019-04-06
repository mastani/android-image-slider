package app.mastani.imageslider

import android.content.Context
import android.os.Handler
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.FrameLayout

class ImageSlider : FrameLayout {
    lateinit var rv: RecyclerView
    private val adapter by lazy { ImageSliderAdapter(context) }
    private val hHandler by lazy { Handler() }
    private var hRunnable: Runnable? = null

    var loop: Boolean = true
    var loopInterval: Float = 3f
    var loopDirection: Int = 1
    var indicatorVisibility: Boolean = true
    var indicatorSize: Float = 1f

    var position = 0

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
            loop = typedArray.getBoolean(R.styleable.ImageSlider_imageSlider_loop, true)
            loopInterval = typedArray.getFloat(R.styleable.ImageSlider_imageSlider_loopInterval, 3f)
            loopDirection = typedArray.getInt(R.styleable.ImageSlider_imageSlider_loopDirection, 1)
            indicatorVisibility = typedArray.getBoolean(R.styleable.ImageSlider_imageSlider_indicatorsVisibility, true)
            indicatorSize = typedArray.getDimension(R.styleable.ImageSlider_imageSlider_indicatorSize, 1f)

            typedArray.recycle();
        }

        initialRecyclerView()
        initialTimer()
    }

    private fun initialRecyclerView() {
        val snapHelper = PagerSnapHelper()

        rv = RecyclerView(context)
        rv.apply {
            layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = this@ImageSlider.adapter

            snapHelper.attachToRecyclerView(this)
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                }

                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)

                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        val pos = (this@apply.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition();
                        position = (adapter as ImageSliderAdapter).calcRealPosition(pos)
                        hHandler.postDelayed(hRunnable, getCurrentSlideInterval())
                    }
                }
            })
        }

        addView(rv)
    }

    private fun initialTimer() {
        if (!loop) return

        hRunnable = object : Runnable {
            override fun run() {
                rv.smoothScrollBy(loopDirection * rv.width, 0)
            }
        }
        hHandler.postDelayed(hRunnable, getCurrentSlideInterval())
    }

    private fun getCurrentSlideInterval(): Long {
        if (adapter.getRealSize() > 0) {
            val slide = adapter.getItem(position)
            if (slide.interval > 0) return (slide.interval * 1000).toLong()
        }

        return (loopInterval * 1000).toLong()
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