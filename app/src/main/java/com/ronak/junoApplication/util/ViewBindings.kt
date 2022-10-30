package com.ronak.junoApplication.util

import android.widget.ImageView
import android.widget.ViewFlipper
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

    @JvmStatic
    @BindingAdapter("displayedChild")
    fun setDisplayedChild(viewFlipper: ViewFlipper?, childPosition: Int) {
        if (viewFlipper != null && viewFlipper.childCount > childPosition && viewFlipper.displayedChild != childPosition) {
            viewFlipper.displayedChild = childPosition
        }
    }
}