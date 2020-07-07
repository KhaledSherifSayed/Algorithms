package com.meslmawy.datastruturevisulaizations.ui.bubble

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.meslmawy.datastruturevisulaizations.databinding.BubbleFragmentBinding
import com.meslmawy.datastruturevisulaizations.ui.adapters.DSBubbleAdapter

class BubbleFragment : Fragment() {

    companion object {
        fun newInstance() = BubbleFragment()
    }

    private lateinit var viewModel: BubbleViewModel
    private lateinit var dsItemAdapter: DSBubbleAdapter
    private lateinit var binding: BubbleFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BubbleFragmentBinding.inflate(inflater)
        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this
        viewModel = ViewModelProviders.of(this).get(BubbleViewModel::class.java)
        binding.viewModel = viewModel
        val manager = GridLayoutManager(activity, 8)
        dsItemAdapter = DSBubbleAdapter()
        binding.bubbleItems.layoutManager = manager
        binding.bubbleItems.adapter = dsItemAdapter
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
        viewModel.getText()?.observe(viewLifecycleOwner,
            Observer<String?> { s ->
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
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(BubbleViewModel::class.java)
    }
}