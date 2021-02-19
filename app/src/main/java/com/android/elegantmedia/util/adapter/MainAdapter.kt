package com.android.elegantmedia.util.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.elegantmedia.databinding.ItemRowBinding
import com.android.elegantmedia.feature.hotelList.models.Hotel


//class MainAdapter (private val hotels: ArrayList<Hotel>) : RecyclerView.Adapter<MainAdapter.DataViewHolder>(){
//
//    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//
//        fun bind(hotel :Hotel) {
//            itemView.apply {
//                textViewAddress.text = hotel.address
//                texttitle.text = hotel.title
//                val media= hotel.image.small
//                if (media !== null) {
//                    Glide.with(this)
//                        .load(media)
//                        .into(imageView)
//                } else {
//                    imageView.setImageResource(R.drawable.ic_launcher_background)
//                }
//
//            }
//        }
//    }
//
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
//        DataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false))
//
//    override fun getItemCount(): Int = hotels.size
//
//    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
//        holder.bind(hotels[position])
//    }
//
//    fun addHotels(hotels: ArrayList<Hotel>) {
//        this.hotels.apply {
//            clear()
//            addAll(hotels)
//        }
//        notifyDataSetChanged()
//    }
//
//}

class MainAdapter(val clickListener: ItemClickListener) : ListAdapter<Hotel, MainAdapter.ViewHolder>(SleepNightDiffCallback()) {
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

class SleepNightDiffCallback : DiffUtil.ItemCallback<Hotel>() {

    override fun areItemsTheSame(oldItem: Hotel, newItem: Hotel): Boolean {
        return oldItem.id == newItem.id
    }


    override fun areContentsTheSame(oldItem: Hotel, newItem: Hotel): Boolean {
        return oldItem == newItem
    }
}

class ItemClickListener(val clickListener: (h: Hotel) -> Unit) {
    fun onClick(hotel: Hotel) =clickListener(hotel);
}
