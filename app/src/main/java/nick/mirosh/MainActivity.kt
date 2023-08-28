package nick.mirosh

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import nick.mirosh.parallaxcolumn.InvertedParallaxColumn
import nick.mirosh.parallaxcolumn.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {


            }
        }
    }
}