package com.safakaraca.foodorder.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.safakaraca.foodorder.R
import com.safakaraca.foodorder.databinding.FragmentEnterBinding

class EnterFragment : Fragment() {
    private lateinit var tasarim:FragmentEnterBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tasarim = DataBindingUtil.inflate(inflater,R.layout.fragment_enter, container, false)

        tasarim.buttonEnter.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.actionEnterToHome)
        }

        return tasarim.root
    }
}