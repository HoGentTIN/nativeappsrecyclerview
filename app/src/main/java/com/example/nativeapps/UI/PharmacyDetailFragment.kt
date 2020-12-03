package com.example.nativeapps.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.nativeapps.databinding.FragmentPharmacyDetailBinding
import com.example.nativeapps.viewmodels.PharmacyDetailViewModel
import org.koin.android.ext.android.inject

class PharmacyDetailFragment : Fragment() {

    val args: PharmacyDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModel: PharmacyDetailViewModel by inject()
        val binding = FragmentPharmacyDetailBinding.inflate(inflater, container, false)

        viewModel.updatePharmacy(args.pharmacyId)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.pharmacy.observe(
            viewLifecycleOwner,
            Observer {
                binding.tvPharmacyAddress.text = it.generateAddress()
            }
        )

        return binding.root
    }
}
