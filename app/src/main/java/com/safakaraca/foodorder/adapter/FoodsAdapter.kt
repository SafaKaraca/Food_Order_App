package com.safakaraca.foodorder.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.safakaraca.foodorder.databinding.CardDesignBinding
import com.safakaraca.foodorder.entity.Foods
import com.safakaraca.foodorder.fragment.HomeFragmentDirections
import com.squareup.picasso.Picasso

class FoodsAdapter(var mContext: Context,
                   var foodsList:List<Foods>) : RecyclerView.Adapter<FoodsAdapter.CardTasarimTutucu>(){

    inner class CardTasarimTutucu(tasarim:CardDesignBinding) :
        RecyclerView.ViewHolder(tasarim.root){
        var tasarim:CardDesignBinding

        init {
            this.tasarim = tasarim
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val layoutInflater = LayoutInflater.from(mContext)
        val tasarim = CardDesignBinding.inflate(layoutInflater,parent,false)
        return CardTasarimTutucu(tasarim)
    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {
        val food = foodsList.get(position)
        val t = holder.tasarim
        t.foodNesne = food

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${food.foodImageName}"
        Picasso.get().load(url).into(t.imageViewFoodImage)


        t.cardViewFood.setOnClickListener {
            val gecis = HomeFragmentDirections.actionHomeToDetail(food)
            Navigation.findNavController(it).navigate(gecis)
        }
    }

    override fun getItemCount(): Int {
        return foodsList.size

    }
}