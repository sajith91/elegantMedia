package com.android.elegantmedia.networkclass.repository

import com.android.elegantmedia.networkclass.api.ApiHelper

class Hotelepository(private val apiHelper: ApiHelper) {

    suspend fun getHotels() = apiHelper.getHotels()
}