package com.ronak.junoApplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ronak.junoApplication.databinding.LayoutTransactionBinding
import com.ronak.junoApplication.dto.TransactionDto

class TransactionsAdapter(
    private var transactionDtoList: List<TransactionDto>?,
) : RecyclerView.Adapter<TransactionsAdapter.TransactionViewHolder>() {

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