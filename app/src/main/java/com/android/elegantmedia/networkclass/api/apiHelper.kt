package com.android.elegantmedia.networkclass.api

class ApiHelper(private val apiService: ApiService) {

    suspend fun getHotels() = apiService.getHotels()
}