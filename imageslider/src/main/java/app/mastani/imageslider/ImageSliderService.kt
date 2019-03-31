package app.mastani.imageslider

import android.support.annotation.DrawableRes
import android.widget.ImageView

interface ImageLoaderService {
    fun loadImage(view: ImageView, url: String)
}