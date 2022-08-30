package com.diyabet.diyabetgunlugum.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.diyabet.diyabetgunlugum.MealTableData
import com.diyabet.diyabetgunlugum.adapter.MealTableAdapter
import com.diyabet.diyabetgunlugum.databinding.FragmentMealTableBinding
import com.diyabet.diyabetgunlugum.view.MealTableItemData


class MealTableFragment : Fragment() {

    lateinit var binding: FragmentMealTableBinding
    private var mealTableAdapter = MealTableAdapter()
    private var mealTableDataList= arrayListOf<MealTableData>()

    private var mealTableItemDataList= arrayListOf<MealTableItemData>()


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

        mealTableDataList.add(MealTableData("11.08", listOf(MealTableItemData("2 Tane Haşlanmış Yumurta"),MealTableItemData("Yarım Beyaz Ekmek"),MealTableItemData("100 gr Nutella"),MealTableItemData("Kibrit Kutusu Beyaz Peynir"))))
        mealTableDataList.add(MealTableData("13.08", listOf(MealTableItemData("1 Kase Yoğurt"),MealTableItemData("4 Adet Kuru Kayısı"))))
        mealTableDataList.add(MealTableData("19.00", listOf(MealTableItemData("1.5 İskender"),MealTableItemData("1 Lt Kola"),MealTableItemData("1 Fırın Sütlaç"),MealTableItemData("Çoban Salatası"),MealTableItemData("Turşu Kavurma"),MealTableItemData("1 Tırnak Pide"))))
        mealTableDataList.add(MealTableData("23.00", listOf(MealTableItemData("Cips"),MealTableItemData("350 Ml Kola"))))

        if (mealTableDataList.isEmpty()){
            binding.mealTableWarning.visibility=View.VISIBLE
            binding.mealTableRecyclerView.visibility=View.INVISIBLE
        }else{
            binding.mealTableRecyclerView.visibility=View.VISIBLE
            initAdapter()
            fetchData(mealTableDataList)
        }

        binding.mealTableFab.setOnClickListener {
            val action=MealTableFragmentDirections.actionMealTableFragmentToAddMealFragment()
            Navigation.findNavController(it).navigate(action)
        }

        binding.apply {
            mealTableDate.setOnClickListener{

                val datePickerFragment = DatePickerFragment()
                val supportFragmentManager = requireActivity().supportFragmentManager

                supportFragmentManager.setFragmentResultListener(
                    "REQUEST_KEY",
                    viewLifecycleOwner
                ) { resultKey, bundle ->
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