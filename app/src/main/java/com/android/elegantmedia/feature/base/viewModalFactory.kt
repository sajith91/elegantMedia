package com.android.elegantmedia.feature.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.elegantmedia.feature.hotelList.viewModel.HotelListViewModel
import com.android.elegantmedia.networkclass.api.ApiHelper
import com.android.elegantmedia.networkclass.repository.Hotelepository

class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HotelListViewModel::class.java)) {
            return HotelListViewModel(Hotelepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}