package com.android.elegantmedia.util.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.elegantmedia.databinding.ItemRowBinding
import com.android.elegantmedia.feature.hotelList.models.Hotel

class HotelListAdapter(val clickListener: ItemClickListener) : ListAdapter<Hotel, HotelListAdapter.ViewHolder>(ListChangeCallback()) {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item,clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: ItemRowBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(
            item: Hotel,
            clickListener: ItemClickListener
        ) {
            binding.hotel = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemRowBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}




class ListChangeCallback : DiffUtil.ItemCallback<Hotel>() {

    override fun areItemsTheSame(oldItem: Hotel, newItem: Hotel): Boolean {
        return oldItem.id == newItem.id
    }


    override fun areContentsTheSame(oldItem: Hotel, newItem: Hotel): Boolean {
        return oldItem == newItem
    }
}

/**
 * click listener
 */
class ItemClickListener(val clickListener: (h: Hotel) -> Unit) {
    fun onClick(hotel: Hotel) =clickListener(hotel);
}
