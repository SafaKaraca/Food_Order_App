package com.safakaraca.foodorder.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.safakaraca.foodorder.entity.Foods
import com.safakaraca.foodorder.repo.FoodsDaoRepository

class HomeFragmentVM : ViewModel() {
    var foodsList = MutableLiveData<List<Foods>>()
    val fdao = FoodsDaoRepository()

    init {
        yemekleriYukle()
        foodsList = fdao.yemekleriGetir()

    }

    fun yemekleriYukle(){
        fdao.tumYemekleriAl()
    }
}