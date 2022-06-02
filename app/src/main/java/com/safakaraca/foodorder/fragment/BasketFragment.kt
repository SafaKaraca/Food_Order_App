package com.safakaraca.foodorder.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar
import com.safakaraca.foodorder.R
import com.safakaraca.foodorder.adapter.BasketFoodsAdapter
import com.safakaraca.foodorder.databinding.FragmentBasketBinding
import com.safakaraca.foodorder.viewmodel.BasketFragmentVM

class BasketFragment : Fragment() {
    private lateinit var tasarim: FragmentBasketBinding
    private lateinit var viewModel: BasketFragmentVM
    private lateinit var adapter: BasketFoodsAdapter



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tasarim = DataBindingUtil.inflate(inflater,R.layout.fragment_basket, container, false)
        tasarim.basketFragment = this

        tasarim.basketToolbarBaslik = "SEPETİNİZ"

        tasarim.textViewEmptyBasket.visibility = View.INVISIBLE

        viewModel.basketFoodsList.observe(viewLifecycleOwner) {
                basketFoodsList -> var toplamSonuc = 0
            basketFoodsList.map { toplamSonuc += it.foodPrice * it.foodAdet }
            tasarim.textViewTopPrice.text = toplamSonuc.toString() + " ₺"

            if(viewModel.basketFoodsList.value.isNullOrEmpty()){
                tasarim.textViewEmptyBasket.visibility = View.VISIBLE
            }

            adapter = BasketFoodsAdapter(requireContext(), basketFoodsList, viewModel)
            tasarim.basketFoodsAdapter = adapter
        }

        tasarim.buttonOnayla.setOnClickListener {
            if (viewModel.basketFoodsList.value.isNullOrEmpty()){
                Snackbar.make(it,"Lütfen sepetinize ürün ekleyin.", Snackbar.LENGTH_SHORT).show()
            }else{
                Navigation.findNavController(it).navigate(R.id.actionBasketToOrdered)
            }
        }

        return tasarim.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: BasketFragmentVM by viewModels()
        viewModel = tempViewModel
    }
}