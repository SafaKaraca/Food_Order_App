package com.safakaraca.foodorder.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BasketFoodsResponse(@SerializedName("sepet_yemekler")
                               @Expose var basketFoods: List<BasketFoods>,
                               @SerializedName ("success")
                               @Expose var success: Int) {
}