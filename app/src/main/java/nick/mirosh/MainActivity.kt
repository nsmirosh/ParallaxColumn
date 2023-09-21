package nick.mirosh

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                var ordinaryParallaxState by remember { mutableStateOf(true) }
                Column {
                    Button(
                        onClick = {
                            ordinaryParallaxState = true
                        }
                    ) {
                        Text("ParallaxColumn")
                    }
                    Button(
                        onClick = {
                            ordinaryParallaxState = false
                        }
                    ) {

                        Text("UriParallaxColumn")
                    }
                    if (ordinaryParallaxState) {
//                        ParallaxColumnRunner()
                    } else {
//                        UriParallaxColumn()
                    }
                }
            }
        }
    }
}


//@Composable
//fun ParallaxColumnRunner() {
//    val context = LocalContext.current
//    val urls = listOf(
//        "https://images.pexels.com/photos/2832034/pexels-photo-2832034.jpeg",
//        "https://images.pexels.com/photos/1154610/pexels-photo-1154610.jpeg",
//        "https://images.pexels.com/photos/1179229/pexels-photo-1179229.jpeg"
//    ).map {
//        "$it?auto=compress&cs=tinysrgb&w=1280&h=1920&dpr=2"
//    }
//    var bitmaps = remember {
//        mutableStateListOf<Bitmap?>()
//    }
//    LaunchedEffect(Unit) {
//        bitmaps.addAll(urls.map {
//            context.getPictureWithUrl(it)
//        })
//    }
//    val authors = listOf(
//        "Amine Msiouri",
//        "Connor Danylenko",
//        "Julia Volk",
//    )
//
//    if (bitmaps.isNotEmpty()) {
//        ParallaxColumn(bitmaps = bitmaps.filterNotNull().toList()) {
//            Box(
//                modifier = Modifier
//                    .align(Alignment.BottomStart)
//                    .padding(8.dp),
//            ) {
//                Text(
//                    text = authors[it],
//                    modifier = Modifier
//                )
//            }
//        }
//    }
//}