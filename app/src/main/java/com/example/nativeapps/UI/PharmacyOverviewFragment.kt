package com.example.nativeapps.UI

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.nativeapps.adapters.PharmacyAdapter
import com.example.nativeapps.adapters.PharmacyClickListener
import com.example.nativeapps.data.local.PharmacyAndFields
import com.example.nativeapps.databinding.FragmentPharmacyOverviewBinding
import com.example.nativeapps.util.Status.ERROR
import com.example.nativeapps.util.Status.LOADING
import com.example.nativeapps.util.Status.SUCCESS
import com.example.nativeapps.viewmodels.PharmacyOverviewViewModel
import org.koin.android.ext.android.inject

class PharmacyOverviewFragment : Fragment(), PharmacyClickListener {

    private lateinit var adapter: PharmacyAdapter
    private val loadingDialogFragment by lazy { LoadingFragment() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModel: PharmacyOverviewViewModel by inject()
        val binding = FragmentPharmacyOverviewBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        adapter = PharmacyAdapter(this)
        binding.adapter = adapter

        viewModel.pharmacies.observe(
            viewLifecycleOwner,
            Observer {
                it?.let { resource ->
                    when (resource.status) {
                        SUCCESS -> {
                            showProgress(false)
                            adapter.submitList(resource.data)
                        }
                        LOADING -> {
                            showProgress(true)
                        }
                        ERROR -> {
                            showProgress(false)
                        }
                    }
                }
            }
        )
        return binding.root
    }

    private fun showProgress(b: Boolean) {
        if (b) {
            if (!loadingDialogFragment.isAdded) {
                loadingDialogFragment.show(requireActivity().supportFragmentManager, "loader")
            }
        } else {
            if (loadingDialogFragment.isAdded) {
                loadingDialogFragment.dismissAllowingStateLoss()
            }
        }
    }

    override fun onPharmacyClicked(pharmacy: PharmacyAndFields) {
        Log.i("TAG", "Ik wil navigerern")
        navigateToDetail(pharmacy)
    }

    private fun navigateToDetail(pharmacyAndFields: PharmacyAndFields) {
        val directions =
            PharmacyOverviewFragmentDirections.actionPharmacyOverviewFragmentToPharmacyDetailFragment(
                pharmacyAndFields.pharmacy.recordid
            )
        findNavController().navigate(directions)
    }
}
