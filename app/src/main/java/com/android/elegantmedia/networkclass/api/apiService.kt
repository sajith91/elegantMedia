package com.android.elegantmedia.networkclass.api


import com.android.elegantmedia.feature.hotelList.models.HotelListResponse
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiService {
    @Headers("Content-Type:application/json")
    @GET("hotels.json")
    suspend fun getHotels(): HotelListResponse
}