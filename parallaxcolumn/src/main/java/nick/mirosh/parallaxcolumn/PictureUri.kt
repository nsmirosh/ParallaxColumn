package nick.mirosh.parallaxcolumn

/**
 * @param pictureUris - URLs of pictures to be downloaded via network or R.raw.id's
 * to be loaded from the "raw" folder and drawn on top of the inverted card background
 * @param cardHeightInDp - height of the card in density pixels
 * @param parallaxScrollSpeed - speed of the parallax effect relative to the column scroll speed
 * @param content - content to be drawn on top of the card
 */
sealed class PictureUri {
    data class RemoteUrl(val value: String) : PictureUri()
    data class RawResource(val value: Int) : PictureUri()
}
