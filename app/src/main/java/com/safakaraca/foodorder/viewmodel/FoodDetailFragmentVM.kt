package com.safakaraca.foodorder.viewmodel

import androidx.lifecycle.ViewModel
import com.safakaraca.foodorder.databinding.FragmentFoodDetailBinding
import com.safakaraca.foodorder.repo.FoodsDaoRepository

class FoodDetailFragmentVM : ViewModel(){

    var fdao: FoodsDaoRepository

    init {
        fdao = FoodsDaoRepository()
    }

    fun sepetEkle(yemek_id: Int, yemek_adi: String, yemek_resim_adi: String, yemek_fiyat: Int, yemek_siparis_adet: Int, kullanici_adi: String) {
        fdao.sepeteEkle(
            yemek_id,
            yemek_adi,
            yemek_resim_adi,
            yemek_fiyat,
            yemek_siparis_adet,
            kullanici_adi
        )
    }

    fun buttonEkleCikar(tasarim: FragmentFoodDetailBinding) {
        fdao.btnAddMinus(tasarim)
    }
}