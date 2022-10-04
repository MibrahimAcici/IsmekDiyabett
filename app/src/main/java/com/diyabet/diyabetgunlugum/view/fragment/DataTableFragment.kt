package com.diyabet.diyabetgunlugum.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.LinearLayoutManager
import com.diyabet.diyabetgunlugum.model.DataTableData
import com.diyabet.diyabetgunlugum.adapter.DataTableAdapter
import com.diyabet.diyabetgunlugum.databinding.FragmentDataTableBinding

class DataTableFragment : Fragment() {
    private lateinit var binding: FragmentDataTableBinding
    private lateinit var dataTableAdapter: DataTableAdapter
    private var dataTableDataListToday= arrayListOf<DataTableData>()
    private var dataTableDataListMonth= arrayListOf<DataTableData>()
    private var dataTableDataListWeek= arrayListOf<DataTableData>()
    private var dataTableDataListYear= arrayListOf<DataTableData>()
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

        dataTableDataListToday.add(DataTableData("12:00","120","+15","1","2"))
        dataTableDataListToday.add(DataTableData("13:00","110","+12","2","3"))
        dataTableDataListToday.add(DataTableData("13:30","122","+11","1","2"))
        dataTableDataListToday.add(DataTableData("15:00","144","+12","1","3"))
        dataTableDataListToday.add(DataTableData("19:50","125","+10","1","2"))
        dataTableDataListToday.add(DataTableData("20:04","116","+17","1","3"))
        dataTableDataListToday.add(DataTableData("23:00","137","+20","1","2"))

        dataTableDataListWeek.add(DataTableData("Pzt","110","+12","1","3"))
        dataTableDataListWeek.add(DataTableData("Sal","120","+13","2","3"))
        dataTableDataListWeek.add(DataTableData("Çar","130","+11","1","3"))
        dataTableDataListWeek.add(DataTableData("Per","140","+10","2","3"))

        dataTableDataListMonth.add(DataTableData("Oca","100","+12","1","3"))
        dataTableDataListMonth.add(DataTableData("Şub","130","+11","2","3"))
        dataTableDataListMonth.add(DataTableData("Mar","120","+11","1","3"))
        dataTableDataListMonth.add(DataTableData("Nis","140","+10","1","3"))

        dataTableDataListYear.add(DataTableData("21","100","+12","1","3"))
        dataTableDataListYear.add(DataTableData("22","110","+11","2","3"))
        dataTableDataListYear.add(DataTableData("23","120","+10","1","3"))
        dataTableDataListYear.add(DataTableData("24","130","+10","2","3"))


        initAdapter()
        fetchData(dataTableDataListToday)

        val popupMenu= PopupMenu(context,binding.dataTableTimeCv,)
        popupMenu.menu.add(Menu.NONE,0,0,"BUGÜN")
        popupMenu.menu.add(Menu.NONE,1,1,"HAFTA")
        popupMenu.menu.add(Menu.NONE,2,2,"AY")
        popupMenu.menu.add(Menu.NONE,3,3,"YIL")


        popupMenu.setOnMenuItemClickListener { menuItem ->
            binding.dataTableOpacity.visibility=View.VISIBLE
            when (menuItem.itemId) {

                0 -> {
                    binding.dataTableTime.text="BUGÜN"
                    binding.dataTableOpacity.visibility=View.GONE
                    fetchData(dataTableDataListToday)
                }

                1 -> {
                    binding.dataTableTime.text="HAFTA"
                    binding.dataTableOpacity.visibility=View.GONE
                    fetchData(dataTableDataListWeek)
                }

                2 -> {
                    binding.dataTableTime.text="AY"
                    binding.dataTableOpacity.visibility=View.GONE
                    fetchData(dataTableDataListMonth)
                }

                3 -> {
                    binding.dataTableTime.text="YIL"
                    binding.dataTableOpacity.visibility=View.GONE
                    fetchData(dataTableDataListYear)
                }

            }
            false
        }

        binding.dataTableTimeCv.setOnClickListener {
            popupMenu.show()
            binding.dataTableOpacity.visibility=View.VISIBLE
        }



    }

    private fun initAdapter() {
        dataTableAdapter = DataTableAdapter()
        binding.rvDataTable.adapter = dataTableAdapter

        val layoutManager = LinearLayoutManager(context)
        binding.rvDataTable.layoutManager = layoutManager
    }

    private fun fetchData(arrayList: ArrayList<DataTableData>) {
        dataTableAdapter.setList(arrayList)

    }
}
