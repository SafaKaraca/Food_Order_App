package com.safakaraca.foodorder.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.safakaraca.foodorder.entity.BasketFoods
import com.safakaraca.foodorder.repo.FoodsDaoRepository

class BasketFragmentVM : ViewModel() {

    var basketFoodsList: MutableLiveData<List<BasketFoods>>
    private var fdao = FoodsDaoRepository()
    val userName:String = "safakaraca"

    init {
        sepetYemekleriYukle(userName)
        basketFoodsList = fdao.sepetYemekleriGetir()
    }

    fun sepetYemekleriYukle(userName: String) {
        fdao.tumSepetYemekleriAl("safakaraca")
    }

    fun yemekSil(basketFoodId: Int, userName: String) {
        fdao.sepettenYemekSilme(basketFoodId, userName)
    }
}