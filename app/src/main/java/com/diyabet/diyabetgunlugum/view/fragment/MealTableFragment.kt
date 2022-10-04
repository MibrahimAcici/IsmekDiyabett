package com.diyabet.diyabetgunlugum.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.diyabet.diyabetgunlugum.model.MealTableData
import com.diyabet.diyabetgunlugum.adapter.MealTableAdapter
import com.diyabet.diyabetgunlugum.databinding.FragmentMealTableBinding

class MealTableFragment :Fragment() {

    lateinit var binding: FragmentMealTableBinding
    private var mealTableAdapter = MealTableAdapter()

    private var mealTableDataList = arrayListOf<MealTableData>()

    //private val args: MealTableFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentMealTableBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<MealTableData>("meal")?.observe(viewLifecycleOwner) { result->
            mealTableDataList.add(result)
            binding.mealTableWarning.visibility = View.INVISIBLE
            initAdapter()
            fetchData(mealTableDataList)
        }

        if (mealTableDataList.isNotEmpty()) {
            binding.mealTableWarning.visibility = View.INVISIBLE
            binding.mealTableRecyclerView.visibility = View.VISIBLE
            initAdapter()
            fetchData(mealTableDataList)
        }

        binding.mealTableFab.setOnClickListener {
            val action = MealTableFragmentDirections.actionMealTableFragmentToAddMealFragment(null)
            Navigation.findNavController(it).navigate(action)
        }

        binding.apply {
            mealTableDate.setOnClickListener {

                val datePickerFragment = DatePickerFragment()
                val supportFragmentManager = requireActivity().supportFragmentManager

                supportFragmentManager.setFragmentResultListener("REQUEST_KEY", viewLifecycleOwner) { resultKey, bundle->
                    if (resultKey == "REQUEST_KEY") {
                        val date = bundle.getString("SELECTED_DATE")
                        mealTableDate.text = "$date"

                    }
                }
                datePickerFragment.show(supportFragmentManager, "DatePickerFragment")

            }
        }

    }

    private fun initAdapter() {
        mealTableAdapter = MealTableAdapter()
        binding.mealTableRecyclerView.adapter = mealTableAdapter

        val layoutManager = LinearLayoutManager(context)
        binding.mealTableRecyclerView.layoutManager = layoutManager
    }

    private fun fetchData(arrayList: ArrayList<MealTableData>) {
        mealTableAdapter.setList(arrayList)
    }

}