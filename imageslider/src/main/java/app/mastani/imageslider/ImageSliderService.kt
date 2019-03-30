package app.mastani.imageslider

import android.support.annotation.DrawableRes
import android.widget.ImageView

interface ImageSliderService {
    fun loadImage(view: ImageView, url: String)
    fun loadImage(view: ImageView, url: String, @DrawableRes placeholder: Int)
    fun loadImage(view: ImageView, url: String, @DrawableRes placeholder: Int, @DrawableRes errorPlaceholder: Int)
}