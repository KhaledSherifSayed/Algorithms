package com.meslmawy.datastruturevisulaizations.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.meslmawy.datastruturevisulaizations.databinding.DsitemLayoutBinding
import com.meslmawy.datastruturevisulaizations.models.DSItem


class DSItemAdapter(val callback : DSItemCallBack ) : ListAdapter<DSItem, DSItemAdapter.DSItemViewHolder>(DiffCallback) {

    /**
     * Callback for calculating the diff between two non-null items in a list.
     *
     * Used by ListAdapter to calculate the minumum number of changes between and old list and a new
     * list that's been passed to `submitList`.
     */
    companion object DiffCallback : DiffUtil.ItemCallback<DSItem>() {
        override fun areItemsTheSame(oldItem: DSItem, newItem: DSItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: DSItem, newItem: DSItem): Boolean {
            return newItem.id == oldItem.id
        }
    }

    /**
     * ViewHolder for Groups items. All work is done by data binding.
     */
    class DSItemViewHolder(val viewDataBinding: DsitemLayoutBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {

        fun bind(listener: DSItemCallBack, user: DSItem) {
            viewDataBinding.item = user
            viewDataBinding.itemCallBack = listener
            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            viewDataBinding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): DSItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = DsitemLayoutBinding.inflate(layoutInflater, parent, false)
                return DSItemViewHolder(binding)
            }
        }
    }

    /**
     * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
     * an item.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DSItemViewHolder {
        return DSItemViewHolder.from(parent)

    }



    override fun onBindViewHolder(holder: DSItemViewHolder, position: Int) {
        holder.viewDataBinding.also {
            holder.bind(callback, getItem(position))
        }
    }

}


/**
 * Click listener for Tourists. By giving the block a name it helps a reader understand what it does.
 */
class DSItemCallBack(val block: (DSItem) -> Unit) {
    /**
     * Called when a user is clicked
     * @param video the tourist that was clicked
     */
    fun onClick(user: DSItem) = block(user)
}