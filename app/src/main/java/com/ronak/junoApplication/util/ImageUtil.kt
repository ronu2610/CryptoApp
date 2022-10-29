package com.ronak.junoApplication.util

import android.widget.ImageView
import coil.ImageLoader
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
}