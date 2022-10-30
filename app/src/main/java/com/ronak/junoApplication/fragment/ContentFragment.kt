package com.ronak.junoApplication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ronak.junoApplication.JunoViewModel
import com.ronak.junoApplication.R
import com.ronak.junoApplication.adapter.CurrentPricesAdapter
import com.ronak.junoApplication.adapter.TransactionsAdapter
import com.ronak.junoApplication.adapter.YourHoldingsAdapter
import com.ronak.junoApplication.databinding.FragmentContentBinding
import com.ronak.junoApplication.dto.ResponseDto
import com.ronak.junoApplication.dto.TransactionDto
import com.ronak.junoApplication.remote.Resource
import com.ronak.junoApplication.remote.State
import com.ronak.junoApplication.util.BuyButtonClickListener

class ContentFragment : Fragment() {

    private lateinit var binding: FragmentContentBinding
    private var isValues = false
    private lateinit var junoViewModel: JunoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        junoViewModel = ViewModelProvider(requireActivity())[JunoViewModel::class.java]
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

        junoViewModel.responseResource.observe(viewLifecycleOwner) {
            handleResource(it)
        }

        if (isValues) {
            junoViewModel.getValues()
        } else {
            junoViewModel.getEmptyValues()
        }
        binding.isValues = isValues
    }

    private fun handleResource(it: Resource<ResponseDto?>) {
        when (it.state) {
            State.SUCCESS -> {
                if (isValues) renderData(junoViewModel.valuesResponseData.value)
                else renderData(junoViewModel.emptyResponseData.value)
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
            override fun onBuyButtonClick(logo: String?, crypto: String?, price: String?) {
                val transactionDto = TransactionDto()
                transactionDto.title = getString(R.string.bought_crypto, crypto)
                transactionDto.txn_amount = price ?: getString(R.string.na)
                transactionDto.txn_logo = logo
                transactionDto.txn_time = getString(R.string.just_now)
                transactionAdapter.updateTransactionDtoList(transactionDto)
                binding.groupTransaction.visibility = View.VISIBLE
            }
        }
}