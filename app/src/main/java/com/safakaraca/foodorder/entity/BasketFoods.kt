package com.safakaraca.foodorder.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.safakaraca.foodorder.R
import java.io.Serializable

data class BasketFoods(@SerializedName("sepet_yemek_id")
                       @Expose var basketFoodId : Int,
                       @SerializedName("yemek_adi")
                       @Expose var foodName: String,
                       @SerializedName("yemek_resim_adi")
                       @Expose var foodImageName: String,
                       @SerializedName("yemek_fiyat")
                       @Expose var foodPrice: Int,
                       @SerializedName("yemek_siparis_adet")
                       @Expose var foodAdet : Int,
                       @SerializedName("kullanici_adi")
                       @Expose var userName: String = "safakaraca") : Serializable {
}