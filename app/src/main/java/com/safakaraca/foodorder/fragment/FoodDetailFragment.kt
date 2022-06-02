package com.safakaraca.foodorder.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.safakaraca.foodorder.R
import com.safakaraca.foodorder.databinding.FragmentFoodDetailBinding
import com.safakaraca.foodorder.entity.Foods
import com.safakaraca.foodorder.viewmodel.FoodDetailFragmentVM
import com.squareup.picasso.Picasso

class FoodDetailFragment : Fragment() {
    private lateinit var tasarim:FragmentFoodDetailBinding
    private lateinit var viewModel: FoodDetailFragmentVM
    val userName = "safakaraca"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: FoodDetailFragmentVM by viewModels()
        viewModel = tempViewModel
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tasarim = DataBindingUtil.inflate(inflater,R.layout.fragment_food_detail, container, false)

        tasarim.foodDetailFragment = this
        tasarim.foodDetailToolbarBaslik = "Food Order"

        val bundle: FoodDetailFragmentArgs by navArgs()
        val gelenFood = bundle.food

        tasarim.foodNesne = gelenFood
        tasarim.textViewFoodDetailName.text = gelenFood.foodName
        tasarim.textViewFoodDetailPrice.text = gelenFood.foodPrice.toString() + " ₺"

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${gelenFood.foodImageName}"
        Picasso.get().load(url).into(tasarim.imageViewFoodDetailImage)

        viewModel.buttonEkleCikar(tasarim)

        return tasarim.root
    }

    fun buttonAddToBasket(foodNesne: Foods, adet: String, it: View) {
        val snackbar = Snackbar.make(it,"${adet} adet ${foodNesne.foodName} başarıyla eklendi.",Snackbar.LENGTH_SHORT)
        viewModel.sepetEkle(
            foodNesne.foodId,
            foodNesne.foodName,
            foodNesne.foodImageName,
            foodNesne.foodPrice,
            adet.toInt(),
            userName)
        snackbar.show()
        Navigation.findNavController(it).navigate(R.id.actionDetailToBasket)
    }
}