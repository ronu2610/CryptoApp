package com.ronak.junoApplication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ronak.junoApplication.JunoViewModel
import com.ronak.junoApplication.adapter.CurrentPricesAdapter
import com.ronak.junoApplication.adapter.TransactionsAdapter
import com.ronak.junoApplication.adapter.YourHoldingsAdapter
import com.ronak.junoApplication.databinding.FragmentContentBinding
import com.ronak.junoApplication.dto.ResponseDto
import com.ronak.junoApplication.remote.State

class ContentFragment : Fragment() {

    private lateinit var binding: FragmentContentBinding
    private var isValues = false
    private lateinit var junoViewModel: JunoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        junoViewModel = ViewModelProvider(requireActivity()).get(JunoViewModel::class.java)
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
            if (it.state == State.SUCCESS) {
                if (isValues) renderData(junoViewModel.valuesResponseData.value)
                else renderData(junoViewModel.emptyResponseData.value)
            }
        }

        if (isValues) {
            junoViewModel.getValues()
        } else {
            junoViewModel.getEmptyValues()
        }
        binding.isValues = isValues
    }

    private fun renderData(responseDto: ResponseDto?) {
        binding.responseDto = responseDto

        val holdingsAdapter = YourHoldingsAdapter(isValues, responseDto?.your_crypto_holdings)
        binding.rvHoldings.adapter = holdingsAdapter

        val priceAdapter = CurrentPricesAdapter(responseDto?.crypto_prices)
        binding.rvPrices.adapter = priceAdapter

        val transactionAdapter = TransactionsAdapter(responseDto?.all_transactions)
        binding.rvTransactions.adapter = transactionAdapter
    }
}