package app.mastani.imageslider

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView

abstract class Slide {
    var interval: Float = 0f
    var onClick: View.OnClickListener? = null
}

data class DrawableSlide(
    var drawable: Drawable,
    var title: String = ""
) : Slide()

data class ImageUrlSlide(
    var URL: String,
    var title: String = ""
) : Slide()

data class ImageViewSlide(
    var view: ImageView,
    var title: String = ""
) : Slide()

data class VideoUrlSlide(
    var URL: String,
    var autoPlay: Boolean = true
) : Slide()

data class WebPageSlide(
    var URL: String
) : Slide()