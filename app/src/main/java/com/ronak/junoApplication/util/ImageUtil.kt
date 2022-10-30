package com.ronak.junoApplication.util

import android.net.Uri
import android.os.Build
import android.widget.ImageView
import androidx.annotation.RawRes
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.decode.SvgDecoder
import coil.load
import com.ronak.junoApplication.R

object ImageUtil {

    fun loadImage(imageView: ImageView, imageUri: String, placeholder: Int?) {
        val imageLoader =
            ImageLoader.Builder(imageView.context).components { add(SvgDecoder.Factory()) }.build()
        imageView.load(imageUri, imageLoader) {
            placeholder(placeholder ?: R.drawable.ic_app_icon)
        }
    }

    fun loadGif(imageView: ImageView, rawResId: Int, packageName: String) {
        val imageLoader = ImageLoader.Builder(imageView.context).components {
            if (Build.VERSION.SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }.build()
        imageView.load(getGifUri(rawResId, packageName), imageLoader) {
            placeholder(R.drawable.ic_app_icon)
        }
    }

    private fun getGifUri(@RawRes rawResId: Int, packageName: String): Uri? {
        return Uri.parse("android.resource://$packageName/$rawResId")
    }

}