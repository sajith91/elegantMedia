package com.android.elegantmedia.feature.hotelList.ui


import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.android.elegantmedia.R
import com.android.elegantmedia.feature.hotelList.models.Hotel
import com.squareup.picasso.Picasso

@BindingAdapter("hotelTitle")
fun TextView.setTitle(item: Hotel) {
    text = item.title
}


@BindingAdapter("hotelAddress")
fun TextView.set(item: Hotel) {
    text = item.address
}

@BindingAdapter("imageUrl")
fun setImageUrl(
    view: ImageView,
    item:Hotel
) {
    Picasso.with(view.context)
        .load(item.image.small)
        .placeholder(R.drawable.default_hotel)
        .into(view)

}

@BindingAdapter("largeimageUrl")
fun loadmageUrl(
    view: ImageView,
    item:Hotel?
) {
    Picasso.with(view.context)
        .load(item?.image?.large)
        .placeholder(R.drawable.default_hotel)
        .into(view)
}
