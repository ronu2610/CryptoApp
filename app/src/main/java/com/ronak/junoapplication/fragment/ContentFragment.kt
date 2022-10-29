package com.ronak.junoapplication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ronak.junoapplication.JunoViewModel
import com.ronak.junoapplication.databinding.FragmentContentBinding
import com.ronak.junoapplication.dto.ResponseDto
import com.ronak.junoapplication.remote.State

class ContentFragment : Fragment() {

    private lateinit var binding: FragmentContentBinding
    private var screenState = false
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
        arguments?.let { screenState = ContentFragmentArgs.fromBundle(it).state }

        junoViewModel.responseResource.observe(viewLifecycleOwner) {
            if (it.state == State.SUCCESS) {
                if (screenState) renderData(junoViewModel.valuesResponseData.value)
                else renderData(junoViewModel.emptyResponseData.value)
            }
        }

        if (screenState) {
            junoViewModel.getValues()
        } else {
            junoViewModel.getEmptyValues()
        }
    }

    private fun renderData(responseDto: ResponseDto?) {
        binding.tvData.text = responseDto.toString()
    }
}