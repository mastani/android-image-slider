package app.mastani.imageslider.sample

import android.widget.ImageView
import app.mastani.imageslider.ImageLoaderService
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso

class PicassoImageLoader : ImageLoaderService {

    override fun loadImage(view: ImageView, url: String) {
        Picasso.get().load(url).fit().centerCrop().into(view)
    }
}