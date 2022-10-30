package com.ronak.cryptoApplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ronak.cryptoApplication.databinding.LayoutCryptoPriceBinding
import com.ronak.cryptoApplication.dto.CryptoPrice
import com.ronak.cryptoApplication.util.BuyButtonClickListener

class CurrentPricesAdapter(
    private var cryptosList: List<CryptoPrice>?,
    private var onBuyClickListener: BuyButtonClickListener,
) : RecyclerView.Adapter<CurrentPricesAdapter.CurrentPriceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrentPriceViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = LayoutCryptoPriceBinding.inflate(
            layoutInflater,
            parent,
            false
        )
        return CurrentPriceViewHolder(binding)
    }


    inner class CurrentPriceViewHolder(var binding: LayoutCryptoPriceBinding) :
        ViewHolder(binding.root) {
        fun bind(cryptoPrice: CryptoPrice?) {
            val context = binding.root.context
            binding.cryptoPrice = cryptoPrice
            binding.tvGrowth.setTextColor(ContextCompat.getColor(context,
                if (adapterPosition % 2 == 0) android.R.color.holo_red_light else android.R.color.holo_green_dark))
            binding.btnBuy.setOnClickListener {
                onBuyClickListener.onBuyButtonClick(cryptoPrice?.logo, cryptoPrice?.title,
                    cryptoPrice?.current_price_in_usd)
            }
            binding.executePendingBindings()
        }
    }

    override fun getItemCount(): Int {
        return cryptosList?.size ?: 0
    }

    override fun onBindViewHolder(holder: CurrentPriceViewHolder, position: Int) {
        holder.bind(cryptosList?.get(position))
    }
}