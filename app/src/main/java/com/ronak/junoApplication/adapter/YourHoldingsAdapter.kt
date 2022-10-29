package com.ronak.junoApplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ronak.junoApplication.databinding.LayoutYourHoldingsBinding
import com.ronak.junoApplication.dto.YourCryptoHolding

class YourHoldingsAdapter(
    private var isValues: Boolean,
    private var holdingsList: List<YourCryptoHolding>?,
) : RecyclerView.Adapter<YourHoldingsAdapter.HoldingsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoldingsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = LayoutYourHoldingsBinding.inflate(
            layoutInflater,
            parent,
            false
        )
        return HoldingsViewHolder(binding)
    }

    inner class HoldingsViewHolder(var binding: LayoutYourHoldingsBinding) :
        ViewHolder(binding.root) {
        fun bind(yourCryptoHolding: YourCryptoHolding?) {
            binding.isValues = isValues
            binding.yourHolding = yourCryptoHolding
            binding.executePendingBindings()
        }
    }

    override fun getItemCount(): Int {
        return holdingsList?.size ?: 0
    }

    override fun onBindViewHolder(holder: HoldingsViewHolder, position: Int) {
        holder.bind(holdingsList?.get(position))
    }
}