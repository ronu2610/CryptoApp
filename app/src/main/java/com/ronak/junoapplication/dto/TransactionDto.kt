package com.ronak.junoapplication.dto

data class TransactionDto(
    var txn_logo: String? = null,
    var title: String? = null,
    var txn_time: String? = null,
    var txn_amount: String? = null,
    var txn_sub_amount: String? = null,
) {
    override fun toString(): String {
        return "TransactionDto(txn_logo=$txn_logo, title=$title, txn_time=$txn_time, txn_amount=$txn_amount, txn_sub_amount=$txn_sub_amount)"
    }
}