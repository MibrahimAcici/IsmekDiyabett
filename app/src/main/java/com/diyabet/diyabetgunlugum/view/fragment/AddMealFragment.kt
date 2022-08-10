package com.diyabet.diyabetgunlugum.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.diyabet.diyabetgunlugum.databinding.FragmentAddMealBinding

class AddMealFragment : Fragment() {
    private lateinit var binding: FragmentAddMealBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddMealBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addMealFab.setOnClickListener {
            val action=AddMealFragmentDirections.actionAddMealFragmentToMealTableFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }
}
