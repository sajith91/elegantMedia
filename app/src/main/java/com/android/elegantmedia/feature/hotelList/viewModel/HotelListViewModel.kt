package com.android.elegantmedia.feature.hotelList.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.android.elegantmedia.networkclass.repository.Hotelepository
import com.android.elegantmedia.util.Resource
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers


class HotelListViewModel( private val repository: Hotelepository) : ViewModel() {
   var userName :String ="";
   var userEmail: String ="";

   /**
    * get hotel list
    */
   fun getHotels() = liveData(Dispatchers.IO) {
      emit(Resource.loading(data = null))
      try {
         print("success")
         emit(Resource.success(data = repository.getHotels()))
      } catch (exception: Exception) {
         print("fail")
         emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
      }
   }
}