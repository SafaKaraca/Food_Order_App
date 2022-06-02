package com.safakaraca.foodorder.repo

import androidx.lifecycle.MutableLiveData
import com.safakaraca.foodorder.databinding.FragmentFoodDetailBinding
import com.safakaraca.foodorder.entity.*
import com.safakaraca.foodorder.retrofit.ApiUtils
import com.safakaraca.foodorder.retrofit.FoodsDaoInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FoodsDaoRepository {
    var foodsList : MutableLiveData<List<Foods>>
    var fdaoi: FoodsDaoInterface
    var basketFoodsList: MutableLiveData<List<BasketFoods>>

    init {
        fdaoi = ApiUtils.getFoodsDaoInterface()
        foodsList = MutableLiveData()
        basketFoodsList = MutableLiveData()
    }

    fun yemekleriGetir():MutableLiveData<List<Foods>> {
        return foodsList
    }

    fun tumYemekleriAl() {
        fdaoi.tumYemekler().enqueue(object : Callback<FoodsResponse> {
            override fun onResponse(call: Call<FoodsResponse>, response: Response<FoodsResponse>) {
                val liste = response.body()?.foods
                foodsList.value = liste
            }
            override fun onFailure(call: Call<FoodsResponse>, t: Throwable) {}
        })
    }

    fun sepetYemekleriGetir(): MutableLiveData<List<BasketFoods>> {
        return basketFoodsList
    }

    fun tumSepetYemekleriAl(userName: String) {
        fdaoi.sepettekiYemekler("safakaraca")
            .enqueue(object : Callback<BasketFoodsResponse> {
                override fun onResponse(call: Call<BasketFoodsResponse>, response: Response<BasketFoodsResponse>) {
                    val liste = response.body()?.basketFoods
                    basketFoodsList.value = liste
                }
                override fun onFailure(call: Call<BasketFoodsResponse>, t: Throwable) {
                }
            })
    }

    fun sepeteEkle(basketFoodId: Int,
                   foodName: String,
                   foodImageName: String,
                   foodPrice: Int,
                   foodAdet: Int,
                   userName: String) {

        fdaoi.sepeteYemekEkle(basketFoodId, foodName, foodImageName, foodPrice, foodAdet, userName)
            .enqueue(object : Callback<CRUDResponse> {
                override fun onResponse(call: Call<CRUDResponse>, response: Response<CRUDResponse>) {
                    try {
                        if(basketFoodsList.value != null && basketFoodsList.value!!.isNotEmpty()) {
                            tumSepetYemekleriAl(userName)
                        }
                        tumSepetYemekleriAl(userName)

                    }catch (e: Exception){
                    }
                }
                override fun onFailure(call: Call<CRUDResponse>, t: Throwable) {
                }
            })
    }
    fun sepettenYemekSilme(basketFoodId: Int,
                           userName: String) {
        fdaoi.sepettenYemekSil(basketFoodId, userName).enqueue(object : Callback<CRUDResponse> {
            override fun onResponse(call: Call<CRUDResponse>, response: Response<CRUDResponse>) {

                try {
                    if(basketFoodsList.value!!.size == 1) {
                        basketFoodsList.value = emptyList()
                    }
                    tumSepetYemekleriAl(userName)

                }catch (e: Exception){
                }

            }
            override fun onFailure(call: Call<CRUDResponse>, t: Throwable) {

            }
        })
    }

    private fun arttirAzalt(adet: Int, tasarim: FragmentFoodDetailBinding) {
        tasarim.textViewFoodAdet.text = "$adet"
    }

    fun artir(tasarim: FragmentFoodDetailBinding) {
        arttirAzalt(tasarim.textViewFoodAdet.text.toString().toInt() + 1, tasarim)
    }

    fun azalt(tasarim: FragmentFoodDetailBinding) {
        arttirAzalt(tasarim.textViewFoodAdet.text.toString().toInt() - 1, tasarim)
        if (tasarim.textViewFoodAdet.text.toString().toInt() < 2) {
            tasarim.textViewFoodAdet.text = "1" }
    }

    fun btnAddMinus(tasarim: FragmentFoodDetailBinding) {
        tasarim.buttonPlus .setOnClickListener { artir(tasarim) }
        tasarim.buttonMinus.setOnClickListener { azalt(tasarim) }
    }
}