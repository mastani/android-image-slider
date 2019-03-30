package app.mastani.imageslider

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView

abstract class Slide

data class DrawableSlide(
    var drawable: Drawable,
    var loopInterval: Int = 1,
    var visibilty: Boolean = true,
    var onClick: View.OnClickListener? = null
) : Slide()

data class ImageUrlSlide(
    var URL: String,
    var loopInterval: Int = 1,
    var visibilty: Boolean = true,
    var onClick: View.OnClickListener? = null
) : Slide()

data class ImageViewSlide(
    var view: ImageView,
    var loopInterval: Int = 1,
    var visibilty: Boolean = true,
    var onClick: View.OnClickListener? = null
) : Slide()

data class VideoUrlSlide(
    var URL: String,
    var loopInterval: Int = 1,
    var visibilty: Boolean = true,
    var autoPlay: Boolean = true,
    var onClick: View.OnClickListener? = null
) : Slide()

data class WebPageSlide(
    var URL: String,
    var loopInterval: Int = 1,
    var visibilty: Boolean = true,
    var onClick: View.OnClickListener? = null
) : Slide()