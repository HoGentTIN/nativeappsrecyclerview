package com.example.nativeapps.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.nativeapps.R
import com.example.nativeapps.databinding.FragmentNewpharmacyBinding
import com.example.nativeapps.viewmodels.NewPharmacyViewModel
import org.koin.android.ext.android.inject

class NewPharmacyFragment() : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModel: NewPharmacyViewModel by inject()
        val binding = FragmentNewpharmacyBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this.viewLifecycleOwner
        addObservers(binding, viewModel)
        return binding.root
    }

    private fun addObservers(binding: FragmentNewpharmacyBinding, viewModel: NewPharmacyViewModel) {
        viewModel.formValid.observe(
            this.viewLifecycleOwner,
            {
                it.also { binding.btnNewAdd.isEnabled = it }
            },
        )

        viewModel.nameValid.observe(
            viewLifecycleOwner,
            {
                if (it) {
                    binding.tvNewNaam.error = ""
                } else {
                    binding.tvNewNaam.error = getString(R.string.error_naam)
                }
            }
        )

        viewModel.streetValid.observe(
            viewLifecycleOwner,
            {
                if (it) {
                    binding.tvNewStreet.error = ""
                } else {
                    binding.tvNewStreet.error = getString(R.string.error_straat)
                }
            }
        )
    }
}
