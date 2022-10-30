package com.ronak.cryptoApplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ronak.cryptoApplication.databinding.LayoutTransactionBinding
import com.ronak.cryptoApplication.dto.TransactionDto

class TransactionsAdapter(
    private var transactionDtoList: MutableList<TransactionDto>?,
) : RecyclerView.Adapter<TransactionsAdapter.TransactionViewHolder>() {

    fun updateTransactionDtoList(transactionDto: TransactionDto?) {
        transactionDto?.let { this.transactionDtoList?.add(0, it) }
        notifyItemInserted(0)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = LayoutTransactionBinding.inflate(
            layoutInflater,
            parent,
            false
        )
        return TransactionViewHolder(binding)
    }


    inner class TransactionViewHolder(var binding: LayoutTransactionBinding) :
        ViewHolder(binding.root) {
        fun bind(transactionDto: TransactionDto?) {
            binding.transactionDto = transactionDto
            binding.executePendingBindings()
        }
    }

    override fun getItemCount(): Int {
        return transactionDtoList?.size ?: 0
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        holder.bind(transactionDtoList?.get(position))
    }
}