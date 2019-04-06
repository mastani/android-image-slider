package app.mastani.imageslider.sample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import app.mastani.imageslider.DrawableSlide
import app.mastani.imageslider.ImageSlider
import app.mastani.imageslider.ImageUrlSlide
import app.mastani.imageslider.Slide

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ImageSlider.imageLoaderService = PicassoImageLoader()

        val slides = arrayListOf<Slide>(
            ImageUrlSlide("https://picsum.photos/600/300/?random").apply {
                title = "Hey i'm first!"
                onClick = View.OnClickListener {
                    Toast.makeText(baseContext, "Hello!", Toast.LENGTH_LONG).show()
                }
            },

            ImageUrlSlide("https://picsum.photos/600/300/?random&x").apply {
                title = "Hey i'm second!"
                interval = 5f
            },

            DrawableSlide(resources.getDrawable(R.drawable.ic_launcher_background)).apply {
                title = "Hey i'm third!"
                interval = 6f
            }
        )

        val slider: ImageSlider = findViewById(R.id.slider)
        slider.setSlides(slides)
    }
}
