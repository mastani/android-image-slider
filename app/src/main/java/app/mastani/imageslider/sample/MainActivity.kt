package app.mastani.imageslider.sample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import app.mastani.imageslider.DrawableSlide
import app.mastani.imageslider.ImageSlider
import app.mastani.imageslider.Slide

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val slides = arrayListOf<Slide>(
            DrawableSlide(resources.getDrawable(R.drawable.ic_launcher_background)),
            DrawableSlide(resources.getDrawable(R.drawable.ic_launcher_background))
        )

        val slider: ImageSlider = findViewById(R.id.slider)
        slider.setSlides(slides)
    }
}
