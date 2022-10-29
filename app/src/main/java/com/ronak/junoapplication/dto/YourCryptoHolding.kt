package com.ronak.junoapplication.dto

data class YourCryptoHolding(
    var logo: String? = null,
    var title: String? = null,
    var current_bal_in_token: String? = null,
    var current_bal_in_usd: String? = null,
) {
    override fun toString(): String {
        return "YourCryptoHolding(logo=$logo, title=$title, current_bal_in_token=$current_bal_in_token, current_bal_in_usd=$current_bal_in_usd)"
    }
}