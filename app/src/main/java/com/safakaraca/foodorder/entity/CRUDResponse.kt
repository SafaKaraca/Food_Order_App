package com.safakaraca.foodorder.entity

import com.google.gson.annotations.SerializedName

data class CRUDResponse (@SerializedName ("success")var success:Int,
                         @SerializedName ("message")var message: String)