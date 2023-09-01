package nick.mirosh.parallaxcolumn

import junit.framework.TestCase.assertNotNull
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import java.net.HttpURLConnection

class ImageUtilsTest {

    @Test
    fun downloadImageTest() {
        val mockedUrl = mock(URLWrapper::class.java)
        val mockedConnection = mock(HttpURLConnection::class.java)
        val mockedBitmapHelper = mock(BitmapHelper::class.java)
        `when`(mockedUrl.openConnection()).thenReturn(mockedConnection)
        val result = downloadImage2(mockedUrl, mockedBitmapHelper)
        assertNotNull(result)
    }
}