package com.ronak.junoapplication.dto

class CryptoPrice(
    var logo: String? = null,
    var title: String? = null,
    var current_price_in_usd: String? = null,
) {
    override fun toString(): String {
        return "CryptoPrice(logo=$logo, title=$title, current_price_in_usd=$current_price_in_usd)"
    }
}