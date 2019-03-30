package app.mastani.imageslider

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.FrameLayout

class ImageSlider : FrameLayout {
    lateinit var recyclerView: RecyclerView
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
        val layoutParam = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        recyclerView = RecyclerView(context)
        recyclerView.layoutParams = layoutParam
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        addView(recyclerView)
    }

    public fun setSlides(slides: ArrayList<Slide>) {
        adapter.add(slides)
    }
}