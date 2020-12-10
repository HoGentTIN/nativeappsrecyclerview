package com.example.nativeapps.UI

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.nativeapps.R
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

        binding.fbNewpharmacy.setOnClickListener {
            this.navigateToAddPharmacy()
        }

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

    override fun onCallClicked(phone: String) {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.setData(Uri.parse(getString(R.string.phone, phone)))
        startActivity(intent)
    }

    override fun onRouteClicked(address: String) {
        val gmUri = Uri.parse(getString(R.string.geo_location, address))
        val intent = Intent(Intent.ACTION_VIEW, gmUri)
        intent.setPackage(getString(R.string.google_maps_package))
        startActivity(intent)
    }

    private fun navigateToDetail(pharmacyAndFields: PharmacyAndFields) {
        val directions =
            PharmacyOverviewFragmentDirections.actionPharmacyOverviewFragmentToPharmacyDetailFragment(
                pharmacyAndFields.pharmacy.recordid
            )
        this.findNavController().navigate(directions)
    }

    private fun navigateToAddPharmacy() {
        val directions = PharmacyOverviewFragmentDirections.actionPharmacyOverviewFragmentToNewPharmacyFragment()
        this.findNavController().navigate(directions)
    }
}
