package com.diyabet.diyabetgunlugum.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.diyabet.diyabetgunlugum.MealTableData
import com.diyabet.diyabetgunlugum.adapter.MealTableAdapter
import com.diyabet.diyabetgunlugum.databinding.FragmentAddMealBinding
import com.diyabet.diyabetgunlugum.databinding.FragmentMealTableBinding


class MealTableFragment : Fragment() {
    private lateinit var binding: FragmentMealTableBinding
    private var mealTableAdapter = MealTableAdapter()
    private var dataList : ArrayList<MealTableData> = arrayListOf()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMealTableBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataList.add(MealTableData("11.02","150gr tavuk ve pilav"))
        dataList.add(MealTableData("15.07","bir avuç ceviz"))
        dataList.add(MealTableData("19.00","bir kase çorba ve sebze yemeği"))
        dataList.add(MealTableData("21.00","3 dilim meyve"))

        binding.rvMealTable.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        binding.rvMealTable.adapter = mealTableAdapter

        mealTableAdapter.setList(dataList)


        binding.mealTableFab.setOnClickListener {
            val action=MealTableFragmentDirections.actionMealTableFragmentToAddMealFragment()
            Navigation.findNavController(it).navigate(action)
        }

    }


}