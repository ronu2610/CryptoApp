package com.ronak.cryptoApplication.util

import java.math.BigDecimal

interface BuyButtonClickListener {
    fun onBuyButtonClick(logo: String?, crypto: String?, price: BigDecimal?)
}