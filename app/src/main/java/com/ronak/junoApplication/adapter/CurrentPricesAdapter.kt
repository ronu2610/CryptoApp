package com.ronak.junoApplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ronak.junoApplication.databinding.LayoutCryptoPriceBinding
import com.ronak.junoApplication.dto.CryptoPrice
import com.ronak.junoApplication.util.BuyButtonClickListener

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
            binding.cryptoPrice = cryptoPrice
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