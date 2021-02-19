package com.android.elegantmedia.feature.hoteldetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class HotelDetailFragmentFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HotelDetailViewModel::class.java)) {
            return HotelDetailViewModel() as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}