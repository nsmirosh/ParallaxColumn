# ParallaxColumn

This is a library that allows you to build a column with parallax cards

## Screenshot

## Setup

In your root `build.gradle` file add the following lines:

```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

In your app-level `build.gradle` file add the following lines:

```
    dependencies {
            implementation 'com.github.nsmirosh:ParallaxColumn:1.0.2'
    }
```

## Usage

There are two classes that you can use in your project:

`ParallaxColumn` and `UriParallaxColumn`

### ParallaxColumn

`ParallaxColumn` directly accepts bitmaps that you want to have displayed.
So a sample usage would be something like:

```kotlin
@Composable
fun ParallaxColumnRunner(bitmaps: List<Bitmap>) {
    val authors = listOf(
        "Amine Msiouri",
        "Connor Danylenko",
        "Julia Volk",
        "Lukas Dlutko"
    )

    ParallaxColumn(bitmaps = bitmaps) {
        Box(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(8.dp),
        ) {
            Text(
                text = authors[it],
                modifier = Modifier
            )
        }
    }
}

```

In case your bitmaps are larger than the screen size, both `ParallaxColumn`
and `UriParallaxColumn` will resize the bitmap to fit in the screen

### UriParallaxColumn

`UriParallaxColumn` accepts a list of either `RemoteUrl`s or  `RawResource`s.

`RemoteUrl` represents a remote url of an image that you want to display:

```kotlin
data class RemoteUrl(val url: String)
```
and `RawResource` represents a resource id of an image that is stored in the `raw` directory:

```kotlin
data class RawResource(val value: Int)
```

So a sample usage would something like:

```kotlin
@Composable
fun ParallaxColumnRunner(bitmaps: List<Bitmap>) {
    val rawResources = listOf(
        RawResource(R.raw.amine_msiouri),
        RawResource(R.raw.connor_danylenko),
        RawResource(R.raw.lukas_dlutko)
    )
    val authors = listOf(
        "Amine Msiouri",
        "Connor Danylenko",
        "Lukas Dlutko"
    )

    UriParallaxColumn(pictureUris = rawResources) {
        Box(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(8.dp),
        ) {
            Text(
                text = authors[it],
                modifier = Modifier
            )
        }
    }
}

```
