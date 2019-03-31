package app.mastani.imageslider

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView

abstract class Slide

data class DrawableSlide(
    var drawable: Drawable,
    var title: String = "",
    var interval: Int = 1,
    var onClick: View.OnClickListener? = null
) : Slide()

data class ImageUrlSlide(
    var URL: String,
    var title: String = "",
    var interval: Int = 1,
    var onClick: View.OnClickListener? = null
) : Slide()

data class ImageViewSlide(
    var view: ImageView,
    var title: String = "",
    var interval: Int = 1,
    var onClick: View.OnClickListener? = null
) : Slide()

data class VideoUrlSlide(
    var URL: String,
    var interval: Int = 1,
    var autoPlay: Boolean = true,
    var onClick: View.OnClickListener? = null
) : Slide()

data class WebPageSlide(
    var URL: String,
    var interval: Int = 1,
    var onClick: View.OnClickListener? = null
) : Slide()