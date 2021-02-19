package com.android.elegantmedia.feature.hotelList.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HotelImage (
    val small: String,
    val medium: String,
    val large: String
): Parcelable