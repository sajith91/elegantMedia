package com.android.elegantmedia.feature.hotelList.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Hotel (
    val id: Int,
    val title: String,
    val description: String,
    val address: String,
    val postcode: String,
    val phoneNumber: String,
    val latitude: String,
    val longitude: String,
    val image : HotelImage
): Parcelable