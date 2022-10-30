package com.ronak.cryptoApplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ronak.cryptoApplication.databinding.LayoutYourHoldingsBinding
import com.ronak.cryptoApplication.dto.CryptoPrice
import com.ronak.cryptoApplication.dto.YourCryptoHolding
import com.ronak.cryptoApplication.util.BuyButtonClickListener

class YourHoldingsAdapter(
    private var isValues: Boolean,
    private var holdingsList: List<YourCryptoHolding>?,
    private var cryptoPrices: List<CryptoPrice>?,
    private var onBuyClickListener: BuyButtonClickListener?,
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
            binding.btnBuy.setOnClickListener {
                onBuyClickListener?.onBuyButtonClick(yourCryptoHolding?.logo,
                    yourCryptoHolding?.title,
                    cryptoPrices?.get(adapterPosition)?.current_price_in_usd)
            }
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