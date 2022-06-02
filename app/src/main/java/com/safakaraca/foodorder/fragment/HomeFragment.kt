package com.safakaraca.foodorder.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.safakaraca.foodorder.R
import com.safakaraca.foodorder.adapter.FoodsAdapter

import com.safakaraca.foodorder.databinding.FragmentHomeBinding
import com.safakaraca.foodorder.entity.Foods
import com.safakaraca.foodorder.viewmodel.HomeFragmentVM

class HomeFragment : Fragment() {
    private lateinit var tasarim:FragmentHomeBinding
    private lateinit var adapter: FoodsAdapter
    private lateinit var viewModel: HomeFragmentVM


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        tasarim = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        tasarim.homeToolbarBaslik = "FOODS"

        viewModel.foodsList.observe(viewLifecycleOwner){
                foodsList -> adapter = FoodsAdapter(requireContext(), foodsList)
            tasarim.foodsAdapter = adapter
        }


        tasarim.imageViewBasket.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.actionHomeToBasket)
        }


        return tasarim.root
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: HomeFragmentVM by viewModels()
        this.viewModel = tempViewModel
    }


}