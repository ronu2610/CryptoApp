package com.ronak.junoApplication.util

import androidx.core.text.isDigitsOnly
import java.text.NumberFormat
import java.util.*

object StringUtil {

    fun currencyFormatter(number: String?): String? {
        val formatter = NumberFormat.getCurrencyInstance(Locale("en", "IN"))
        formatter.maximumFractionDigits = 0
        var formattedNumber: String? = "NA"
        if (number != null && number.isDigitsOnly()) {
            formattedNumber = formatter.format(number)
        }
        return formattedNumber
    }
}