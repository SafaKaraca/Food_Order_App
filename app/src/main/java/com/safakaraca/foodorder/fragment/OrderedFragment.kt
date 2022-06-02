package com.safakaraca.foodorder.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.safakaraca.foodorder.R
import com.safakaraca.foodorder.databinding.FragmentOrderedBinding

class OrderedFragment : Fragment() {
    private lateinit var tasarim: FragmentOrderedBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tasarim = DataBindingUtil.inflate(inflater,R.layout.fragment_ordered, container, false)

        tasarim.orderedToolbarBaslik = "SİPARİŞİNİZ ALINDI !"

        tasarim.buttonReturnToHome.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.actionOrderedToHome)
        }

        return tasarim.root
    }
}