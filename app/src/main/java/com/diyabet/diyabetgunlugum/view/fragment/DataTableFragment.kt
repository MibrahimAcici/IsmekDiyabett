package com.diyabet.diyabetgunlugum.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.LinearLayoutManager
import com.diyabet.diyabetgunlugum.Data
import com.diyabet.diyabetgunlugum.adapter.DataTableAdapter
import com.diyabet.diyabetgunlugum.databinding.FragmentDataTableBinding

class DataTableFragment : Fragment() {
    private lateinit var binding: FragmentDataTableBinding
    private lateinit var dataTableAdapter: DataTableAdapter
    private var dataListToday= arrayListOf<Data>()
    private var dataListMonth= arrayListOf<Data>()
    private var dataListWeek= arrayListOf<Data>()
    private var dataListYear= arrayListOf<Data>()
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

        dataListToday.add(Data("12:00","120","+15","1","2"))
        dataListToday.add(Data("13:00","110","+12","2","3"))
        dataListToday.add(Data("13:30","122","+11","1","2"))
        dataListToday.add(Data("15:00","144","+12","1","3"))
        dataListToday.add(Data("19:50","125","+10","1","2"))
        dataListToday.add(Data("20:04","116","+17","1","3"))
        dataListToday.add(Data("23:00","137","+20","1","2"))

        dataListWeek.add(Data("Pzt","110","+12","1","3"))
        dataListWeek.add(Data("Sal","120","+13","2","3"))
        dataListWeek.add(Data("Çar","130","+11","1","3"))
        dataListWeek.add(Data("Per","140","+10","2","3"))

        dataListMonth.add(Data("Oca","100","+12","1","3"))
        dataListMonth.add(Data("Şub","130","+11","2","3"))
        dataListMonth.add(Data("Mar","120","+11","1","3"))
        dataListMonth.add(Data("Nis","140","+10","1","3"))

        dataListYear.add(Data("21","100","+12","1","3"))
        dataListYear.add(Data("22","110","+11","2","3"))
        dataListYear.add(Data("23","120","+10","1","3"))
        dataListYear.add(Data("24","130","+10","2","3"))


        initAdapter()
        fetchData(dataListToday)

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
                    fetchData(dataListToday)
                }

                1 -> {
                    binding.dataTableTime.text="HAFTA"
                    binding.dataTableOpacity.visibility=View.GONE
                    fetchData(dataListWeek)
                }

                2 -> {
                    binding.dataTableTime.text="AY"
                    binding.dataTableOpacity.visibility=View.GONE
                    fetchData(dataListMonth)
                }

                3 -> {
                    binding.dataTableTime.text="YIL"
                    binding.dataTableOpacity.visibility=View.GONE
                    fetchData(dataListYear)
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

    private fun fetchData(arrayList: ArrayList<Data>) {
        dataTableAdapter.setList(arrayList)

    }
}
