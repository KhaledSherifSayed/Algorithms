package com.meslmawy.datastruturevisulaizations.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.meslmawy.datastruturevisulaizations.databinding.HomeFragmentBinding
import com.meslmawy.datastruturevisulaizations.ui.adapters.DSItemAdapter
import com.meslmawy.datastruturevisulaizations.ui.adapters.DSItemCallBack

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel
    private var dsItemAdapter: DSItemAdapter? = null
    private lateinit var binding: HomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HomeFragmentBinding.inflate(inflater)
        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        binding.viewModel = viewModel
        setupAdapter()
        setupRefresher()
        return binding.root
    }
    private fun setupAdapter(){
        dsItemAdapter = DSItemAdapter(DSItemCallBack { item ->
            when (item.id) {
                1 -> {
                    view?.findNavController()?.navigate(HomeFragmentDirections.actionHomeToBubbleSort())
                }
            }
        })
        // Sets the adapter of the RecyclerView
        binding.sortItems.adapter = dsItemAdapter
    }

    private fun setupRefresher(){
        viewModel.sortdsItems.observe(viewLifecycleOwner, Observer { it ->
            dsItemAdapter?.submitList(it)
        })
    }
}
