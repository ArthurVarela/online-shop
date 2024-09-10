package com.example.onlineshop.Model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItemsModel(
    var title: String = "",
    var description: String = "",
    var picUrl: ArrayList<String> = ArrayList(),
    var model: ArrayList<String> = ArrayList(),
    var price: Double = 0.0,
    var rating: Double = 0.0,
    var numberInCart: Int =  0,
    var showRecommended: Boolean =  false,
    var categoryId: Int = 0
) : Parcelable
