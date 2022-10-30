package com.ronak.cryptoApplication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ronak.cryptoApplication.CryptoViewModel
import com.ronak.cryptoApplication.R
import com.ronak.cryptoApplication.adapter.CurrentPricesAdapter
import com.ronak.cryptoApplication.adapter.TransactionsAdapter
import com.ronak.cryptoApplication.adapter.YourHoldingsAdapter
import com.ronak.cryptoApplication.databinding.FragmentContentBinding
import com.ronak.cryptoApplication.dto.ResponseDto
import com.ronak.cryptoApplication.dto.TransactionDto
import com.ronak.cryptoApplication.remote.Resource
import com.ronak.cryptoApplication.remote.State
import com.ronak.cryptoApplication.util.BuyButtonClickListener
import com.ronak.cryptoApplication.util.ImageUtil
import java.math.BigDecimal

class ContentFragment : Fragment() {

    private lateinit var binding: FragmentContentBinding
    private var isValues = false
    private lateinit var cryptoViewModel: CryptoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cryptoViewModel = ViewModelProvider(requireActivity())[CryptoViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentContentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let { isValues = ContentFragmentArgs.fromBundle(it).state }

        ImageUtil.loadGif(binding.ivMain, R.raw.crypto, requireContext().packageName)
        cryptoViewModel.responseResource.observe(viewLifecycleOwner) {
            handleResource(it)
        }

        if (isValues) {
            cryptoViewModel.getValues()
        } else {
            cryptoViewModel.getEmptyValues()
        }
        binding.isValues = isValues
    }

    private fun handleResource(it: Resource<ResponseDto?>) {
        when (it.state) {
            State.SUCCESS -> {
                if (isValues) renderData(cryptoViewModel.valuesResponseData.value)
                else renderData(cryptoViewModel.emptyResponseData.value)
            }
            State.LOADING -> {
                binding.displayedChild = State.LOADING.ordinal
            }
            else -> {
                Toast.makeText(requireContext(),
                    getString(R.string.error_placeholder, it.error),
                    Toast.LENGTH_SHORT).show()
                findNavController().navigateUp()
            }
        }
    }

    private fun renderData(responseDto: ResponseDto?) {
        binding.responseDto = responseDto

        val transactions = responseDto?.all_transactions?.toMutableList()
        if (!transactions.isNullOrEmpty()) {
            binding.groupTransaction.visibility = View.VISIBLE
        }
        val transactionAdapter = TransactionsAdapter(transactions)
        binding.rvTransactions.adapter = transactionAdapter

        val holdingsAdapter =
            YourHoldingsAdapter(isValues,
                responseDto?.your_crypto_holdings,
                responseDto?.crypto_prices,
                buyButtonClickListener(transactionAdapter))
        binding.rvHoldings.adapter = holdingsAdapter

        val priceAdapter = CurrentPricesAdapter(responseDto?.crypto_prices,
            buyButtonClickListener(transactionAdapter))
        binding.rvPrices.adapter = priceAdapter
        binding.displayedChild = State.SUCCESS.ordinal
    }

    private fun buyButtonClickListener(transactionAdapter: TransactionsAdapter) =
        object : BuyButtonClickListener {
            override fun onBuyButtonClick(logo: String?, crypto: String?, price: BigDecimal?) {
                val transactionDto = TransactionDto()
                transactionDto.title = getString(R.string.bought_crypto, crypto)
                transactionDto.txn_amount = price
                transactionDto.txn_logo = logo
                transactionDto.txn_time = getString(R.string.just_now)
                transactionDto.txn_sub_amount = getString(R.string.buy_price_placeholder, price)
                transactionAdapter.updateTransactionDtoList(transactionDto)
                binding.groupTransaction.visibility = View.VISIBLE
            }
        }
}