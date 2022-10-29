package com.ronak.junoApplication.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter

object ViewBindings {

    @JvmStatic
    @BindingAdapter(value = ["urlData", "placeHolderImage"], requireAll = false)
    fun setImage(imageView: ImageView?, urlData: String?, placeHolderImage: Int?) {
        imageView?.let {
            if (urlData != null) {
                ImageUtil.loadImage(it, urlData, placeHolderImage)
            }
        }
    }
}