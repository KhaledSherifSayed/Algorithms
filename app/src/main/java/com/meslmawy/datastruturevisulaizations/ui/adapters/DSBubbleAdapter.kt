package com.meslmawy.datastruturevisulaizations.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.meslmawy.datastruturevisulaizations.databinding.BubleItemBinding
import com.meslmawy.datastruturevisulaizations.models.BubbleItem


class DSBubbleAdapter() : ListAdapter<BubbleItem, DSBubbleAdapter.DSBubbleItemViewHolder>(DiffCallback) {

    /**
     * Callback for calculating the diff between two non-null items in a list.
     *
     * Used by ListAdapter to calculate the minumum number of changes between and old list and a new
     * list that's been passed to `submitList`.
     */
    companion object DiffCallback : DiffUtil.ItemCallback<BubbleItem>() {
        override fun areItemsTheSame(oldItem: BubbleItem, newItem: BubbleItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: BubbleItem, newItem: BubbleItem): Boolean {
            return newItem.id == oldItem.id
        }
    }



    /**
     * ViewHolder for Groups items. All work is done by data binding.
     */
    class DSBubbleItemViewHolder(val viewDataBinding: BubleItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {

        fun bind(user: BubbleItem) {
            viewDataBinding.item = user
            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            viewDataBinding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): DSBubbleItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = BubleItemBinding.inflate(layoutInflater, parent, false)
                return DSBubbleItemViewHolder(binding)
            }
        }
    }

    /**
     * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
     * an item.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DSBubbleItemViewHolder {
        return DSBubbleItemViewHolder.from(parent)

    }



    override fun onBindViewHolder(holder: DSBubbleItemViewHolder, position: Int) {
        holder.viewDataBinding.also {
            holder.bind(getItem(position))
        }
    }

}
