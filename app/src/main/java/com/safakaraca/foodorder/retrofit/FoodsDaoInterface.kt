package com.safakaraca.foodorder.retrofit

import com.safakaraca.foodorder.entity.BasketFoodsResponse
import com.safakaraca.foodorder.entity.CRUDResponse
import com.safakaraca.foodorder.entity.FoodsResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface FoodsDaoInterface {

    @GET("yemekler/tumYemekleriGetir.php")
    fun tumYemekler(): Call<FoodsResponse>

    @POST("yemekler/sepeteYemekEkle.php")
    @FormUrlEncoded
    fun sepeteYemekEkle(@Field("sepet_yemek_id") foodId:Int,
                        @Field("yemek_adi") foodName: String,
                        @Field("yemek_resim_adi") foodImageName:String,
                        @Field("yemek_fiyat") foodPrice:Int,
                        @Field("yemek_siparis_adet") foodAet:Int,
                        @Field("kullanici_adi") userName:String) : Call<CRUDResponse>

    @POST("yemekler/sepettekiYemekleriGetir.php")
    @FormUrlEncoded
    fun sepettekiYemekler(@Field("kullanici_adi") userName: String):Call<BasketFoodsResponse>

    @POST("yemekler/sepettenYemekSil.php")
    @FormUrlEncoded
    fun sepettenYemekSil(@Field("sepet_yemek_id") basketFoodId:Int,
                         @Field("kullanici_adi") userName: String):Call<CRUDResponse>


}