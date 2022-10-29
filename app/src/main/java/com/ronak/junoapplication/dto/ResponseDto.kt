package com.ronak.junoapplication.dto

data class ResponseDto(
    var crypto_balance: CryptoBalance? = null,
    var your_crypto_holdings: List<YourCryptoHolding>? = null,
    var crypto_prices: List<CryptoPrice>? = null,
    var all_transactions: List<TransactionDto>? = null,
) {
    override fun toString(): String {
        return "ResponseDto(crypto_balance=$crypto_balance, your_crypto_holdings=$your_crypto_holdings, crypto_prices=$crypto_prices, all_transactions=$all_transactions)"
    }
}