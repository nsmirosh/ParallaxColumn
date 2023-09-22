package nick.mirosh

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.util.TypedValue
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext
import nick.mirosh.parallaxcolumn.PictureUri
import java.io.FileInputStream
import java.io.OutputStream
import java.io.PrintWriter
import java.io.StringWriter
import java.net.HttpURLConnection
import java.net.URL


internal fun downloadImage(
    imageUrl: String,
) = try {
    val url = URL(imageUrl)
    val connection = url.openConnection() as HttpURLConnection
    connection.doInput = true
    connection.connect()

    val inputStream = connection.inputStream
    BitmapFactory.decodeStream(inputStream)
} catch (e: Exception) {
    e.logStackTrace("MainActivity")
    null
}

internal fun saveImageToInternalStorage(
    context: Context,
    bitmap: Bitmap,
    imageName: String
) {
    try {
        val fos: OutputStream =
            context.openFileOutput(imageName, Context.MODE_PRIVATE)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fos)
        fos.close()
    } catch (e: Exception) {
        e.printStackTrace()
        Log.e("ParallaxScreen", "saveImageToInternalStorage: failure")
        Log.e("ParallaxScreen", "${e.message}")
    }
}

internal fun decodeImageFromInternalStorage(
    context: Context,
    imageName: String
): Bitmap? {
    return try {
        val fis: FileInputStream = context.openFileInput(imageName)
        BitmapFactory.decodeStream(fis)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

internal suspend fun downloadPictureToInternalStorage(
    fileName: String,
    url: String,
    context: Context
) {
    downloadImage(
        imageUrl = url,
    )?.let {
        Log.d("MainActivity", "downloadPictureToInternalStorage: ${it.byteCount} bytes")
        saveImageToInternalStorage(context = context, bitmap = it, imageName = fileName)
    }
}

internal fun decodeRawResource(resources: Resources, pictureId: Int): Bitmap? {
    val opts = BitmapFactory.Options().apply {
        inScaled =
            false  // ensure the bitmap is not scaled based on device density
    }
    val inputStream = resources.openRawResource(pictureId)
    return BitmapFactory.decodeResourceStream(
        resources,
        TypedValue(),
        inputStream,
        null,
        opts
    )
}

internal suspend fun loadPictures(
    pictureUri: List<PictureUri>,
    context: Context,
) =
    withContext(Dispatchers.IO) {
        mutableListOf<Deferred<Bitmap?>>().apply {
            pictureUri.forEach {
                add(async {
                    when (it) {
                        is PictureUri.RemoteUrl -> context.getPictureWithUrl(it.value)
                        is PictureUri.RawResource -> context.loadLocalPictures(it.value)
                    }
                })
            }
        }.awaitAll()
    }

internal fun calculateYOffset(
    totalColumnScrollFromTop: Int, cardHeight: Int, pictureHeight: Int
) = if (totalColumnScrollFromTop <= 0) {
    pictureHeight - cardHeight
}
else if (totalColumnScrollFromTop + cardHeight >= pictureHeight) {
    0
}
else {
    pictureHeight - cardHeight - (totalColumnScrollFromTop)
}

internal fun Context.loadLocalPictures(pictureId: Int) =
    decodeRawResource(
        this.resources, pictureId
    )


internal suspend fun Context.getPictureWithUrl(url: String): Bitmap? {
    val fileName = "img_${System.currentTimeMillis()}"
    downloadPictureToInternalStorage(
        fileName = fileName,
        url = url,
        context = this,
    )
    val bitmap = decodeImageFromInternalStorage(
        this,
        fileName
    )
    Log.d("ParallaxScreen", "bitmap.size = ${bitmap?.byteCount} bytes")
    return bitmap
}
