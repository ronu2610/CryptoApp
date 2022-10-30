package com.ronak.cryptoApplication.util

import android.widget.ImageView
import android.widget.TextView
import android.widget.ViewFlipper
import androidx.databinding.BindingAdapter
import com.ronak.cryptoApplication.R
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*

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

    @JvmStatic
    @BindingAdapter("amountText")
    fun setAmountText(textView: TextView?, amountText: BigDecimal?) {
        textView?.let {
            val formatter = NumberFormat.getCurrencyInstance(Locale("en", "US"))
            if (amountText != null) {
                if (amountText.remainder(BigDecimal.ONE) > BigDecimal.ZERO) {
                    formatter.maximumFractionDigits = 2
                } else {
                    formatter.maximumFractionDigits = 0
                }
                textView.text = formatter.format(amountText)
            } else textView.setText(R.string.na)
        }
    }
}