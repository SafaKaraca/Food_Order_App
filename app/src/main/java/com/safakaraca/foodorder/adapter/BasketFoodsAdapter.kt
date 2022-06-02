package com.safakaraca.foodorder.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.safakaraca.foodorder.databinding.CardBasketDesignBinding
import com.safakaraca.foodorder.databinding.CardDesignBinding
import com.safakaraca.foodorder.entity.BasketFoods
import com.safakaraca.foodorder.viewmodel.BasketFragmentVM
import com.squareup.picasso.Picasso

class BasketFoodsAdapter(var mContext: Context,
                         var basketFoodsList: List<BasketFoods>,
                         var viewModel: BasketFragmentVM) : RecyclerView.Adapter<BasketFoodsAdapter.CardTasarimTutucu>() {

    inner class CardTasarimTutucu(tasarim: CardBasketDesignBinding) :
        RecyclerView.ViewHolder(tasarim.root) {
        var tasarim:CardBasketDesignBinding

        init {
            this.tasarim = tasarim
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val layoutInflater = LayoutInflater.from(mContext)
        val tasarim = CardBasketDesignBinding.inflate(layoutInflater, parent, false)
        return CardTasarimTutucu(tasarim)
    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {
        val basketFood = basketFoodsList.get(position)
        val t = holder.tasarim
        t.foodBasketNesne = basketFood

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${basketFood.foodImageName}"
        Picasso.get().load(url).into(t.imageViewBasketFoodImage)

        t.imageViewBasketDelete.setOnClickListener {
            Snackbar.make(it, "${basketFood.foodName} silinsin mi?", Snackbar.LENGTH_LONG)
                .setAction("Evet") {
                    viewModel.yemekSil(basketFood.basketFoodId, basketFood.userName) }
                .show()
        }
    }

    override fun getItemCount(): Int {
        return basketFoodsList.size
    }


}