package com.safakaraca.foodorder.viewmodel

import androidx.lifecycle.ViewModel
import com.safakaraca.foodorder.databinding.FragmentFoodDetailBinding
import com.safakaraca.foodorder.repo.FoodsDaoRepository

class FoodDetailFragmentVM : ViewModel() {

    private var fdao: FoodsDaoRepository

    init {
        fdao = FoodsDaoRepository()
    }

    fun sepetEkle(foodId: Int, foodName: String, foodImageName: String, foodPrice: Int, foodAdet: Int, userName: String) {
        fdao.sepeteEkle(
            foodId,
            foodName,
            foodImageName,
            foodPrice,
            foodAdet,
            userName
        )
    }

    fun buttonEkleCikar(tasarim: FragmentFoodDetailBinding) {
        fdao.btnAddMinus(tasarim)
    }
}