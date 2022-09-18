package org.cavedon.takehome.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.cavedon.takehome.databinding.ViewDisplayItemBinding

class DisplayItemAdapter :
    ListAdapter<DisplayItem, DisplayItemAdapter.DisplayItemViewHolder>(DisplayItemCallback) {

    companion object DisplayItemCallback : DiffUtil.ItemCallback<DisplayItem>() {
        override fun areItemsTheSame(oldItem: DisplayItem, newItem: DisplayItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: DisplayItem, newItem: DisplayItem): Boolean {
            return oldItem.getTitle() == newItem.getTitle()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DisplayItemViewHolder(
        ViewDisplayItemBinding.inflate(LayoutInflater.from(parent.context))
    )

    override fun onBindViewHolder(holder: DisplayItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DisplayItemViewHolder(private val binding: ViewDisplayItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: DisplayItem) {
            with(binding) {
                displayItemTitle.text = item.getTitle()
                displayItemSubtitle.text = item.getSubtitle()
            }
        }
    }
}