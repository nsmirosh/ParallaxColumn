package nick.mirosh

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import nick.mirosh.parallaxcolumn.ParallaxColumn

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                ParallaxColumnRunner()
//                var ordinaryParallaxState by remember { mutableStateOf(false) }
//                Column {
//                    Button(
//                        onClick = {
//                            ordinaryParallaxState = true
//                        }
//                    ) {
//                        Text("ParallaxColumn")
//                    }
//                    Button(
//                        onClick = {
//                            ordinaryParallaxState = false
//                        }
//                    ) {
//
//                        Text("UriParallaxColumn")
//                    }
//                    if (ordinaryParallaxState) {
//                        ParallaxColumnRunner()
//                    } else {
////                        UriParallaxColumn()
//                    }
//                }
            }
        }
    }
}


@Composable
fun ParallaxColumnRunner() {

    val authors = listOf(
        "Felix Mittermeier" to "https://www.pexels.com/@felixmittermeier/",
        "Sam Willis" to "https://www.pexels.com/@sam-willis-457311/",
        "Matthew Montrone" to "https://www.pexels.com/@matthew-montrone-230847/",
        "Amine Msiouri" to "https://www.pexels.com/@mohamedelaminemsiouri/",
        "Lukas Ldutko" to "https://www.pexels.com/@lukas-dlutko-1278617/"
    )
    val context = LocalContext.current
    val urls = listOf("2832034", "1154610", "1179229", "3284167", "2440299")
        .map {
            "https://images.pexels.com/photos/$it/pexels-photo-$it.jpeg?auto=compress&cs=tinysrgb&w=1280&h=1920&dpr=2"
        }
    urls.forEach {
        Log.d("MainActivity", "ParallaxColumnRunner: it = $it")
    }
    val bitmaps = remember {
        mutableStateListOf<Bitmap?>()
    }
    LaunchedEffect(Unit) {
        withContext(Dispatchers.IO) {
            bitmaps.addAll(urls.map {
                context.getPictureWithUrl(it)
            })
        }
    }

    if (bitmaps.isNotEmpty()) {
        val actualBitmaps = bitmaps.filterNotNull().toList()
        Log.d("MainActivity", "ParallaxColumnRunner: actualBitmaps = $actualBitmaps ")
        ParallaxColumn(bitmaps = actualBitmaps) {
            Box(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(8.dp),
            ) {
                Text(
                    text = authors[it].first,
                    modifier = Modifier
                )
            }
        }
    }
}