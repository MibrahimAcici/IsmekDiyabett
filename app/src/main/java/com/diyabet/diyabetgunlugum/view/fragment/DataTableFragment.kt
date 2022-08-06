package com.diyabet.diyabetgunlugum.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.diyabet.diyabetgunlugum.Data
import com.diyabet.diyabetgunlugum.adapter.DataTableAdapter
import com.diyabet.diyabetgunlugum.databinding.FragmentDataTableBinding

class DataTableFragment : Fragment() {
    private lateinit var binding: FragmentDataTableBinding
    private lateinit var dataTableAdapter: DataTableAdapter
    private var dataList= arrayListOf<Data>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDataTableBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        dataList.add(Data("12:00","120","15","1","2"))
        dataList.add(Data("13:00","110","12","2","3"))
        dataList.add(Data("13:30","122","11","1","2"))
        dataList.add(Data("15:00","144","12","1","3"))
        dataList.add(Data("19:50","125","10","1","2"))
        dataList.add(Data("20:04","116","17","1","3"))
        dataList.add(Data("23:00","137","20","1","2"))

        initAdapter()
        fetchData()
    }

    private fun initAdapter() {
        dataTableAdapter = DataTableAdapter()
        binding.rvDataTable.adapter = dataTableAdapter

        val layoutManager = LinearLayoutManager(context)
        binding.rvDataTable.layoutManager = layoutManager
    }

    private fun fetchData() {
        dataTableAdapter.setList(dataList)

    }
}
