package com.meslmawy.datastruturevisulaizations.ui.selection

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.meslmawy.datastruturevisulaizations.R
import com.meslmawy.datastruturevisulaizations.databinding.BubbleFragmentBinding
import com.meslmawy.datastruturevisulaizations.databinding.SelectionFragmentBinding
import com.meslmawy.datastruturevisulaizations.ui.adapters.DSBubbleAdapter
import com.meslmawy.datastruturevisulaizations.ui.bubble.BubbleViewModel

class SelectionFragment : Fragment() {

    companion object {
        fun newInstance() = SelectionFragment()
    }

    private lateinit var viewModel: SelectionViewModel
    private lateinit var dsItemAdapter: DSBubbleAdapter
    private lateinit var binding: SelectionFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SelectionFragmentBinding.inflate(inflater)
        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this
        viewModel = ViewModelProviders.of(this).get(SelectionViewModel::class.java)
        binding.viewModel = viewModel
        val manager = GridLayoutManager(activity, 8)
        dsItemAdapter = DSBubbleAdapter()
        binding.selectItems.layoutManager = manager
        binding.selectItems.adapter = dsItemAdapter
        viewModel.unSortedData.observe(viewLifecycleOwner, Observer {
            it?.let {
                dsItemAdapter.submitList(it)
                dsItemAdapter.notifyDataSetChanged()
            }
        })

        binding.runButton.setOnClickListener {
            viewModel.runData()
        }

        binding.resetButton.setOnClickListener {
            viewModel.resetData()
        }

        binding.stepButton.setOnClickListener {
            viewModel.stepData()
        }
        viewModel.getText()?.observe(viewLifecycleOwner,
            Observer { s ->
                binding.sortingDesMethods.text = s
            })

        viewModel.getResetEnabled()?.observe(viewLifecycleOwner,
            Observer { s ->
                if (s != null) {
                    if(s)
                        binding.resetButton.alpha = 1f
                    else
                        binding.resetButton.alpha = .5f

                    binding.resetButton.isEnabled = s
                }
            })

        viewModel.getRunEnabled()?.observe(viewLifecycleOwner,
            Observer { s ->
                if (s != null) {
                    if(s)
                        binding.runButton.alpha = 1f
                    else
                        binding.runButton.alpha = .5f

                    binding.runButton.isEnabled = s
                }
            })

        viewModel.getStepEnabled()?.observe(viewLifecycleOwner,
            Observer { s ->
                if (s != null) {
                    if(s)
                        binding.stepButton.alpha = 1f
                    else
                        binding.stepButton.alpha = .5f

                    binding.stepButton.isEnabled = s
                }
            })
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SelectionViewModel::class.java)
        // TODO: Use the ViewModel
    }

}