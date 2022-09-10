package com.diyabet.diyabetgunlugum.view.fragment

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.diyabet.diyabetgunlugum.MealData
import com.diyabet.diyabetgunlugum.R
import com.diyabet.diyabetgunlugum.adapter.AddMealAdapter
import com.diyabet.diyabetgunlugum.databinding.AddMealBottomSheetCorrectBinding
import com.diyabet.diyabetgunlugum.databinding.AddMealBottomSheetIncorrectBinding
import com.diyabet.diyabetgunlugum.databinding.FragmentAddMealBinding
import com.diyabet.diyabetgunlugum.databinding.HomeBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.coroutines.delay
import okhttp3.internal.wait
import java.util.*

class AddMealFragment : Fragment(), AddMealAdapter.ClickListener {
    private lateinit var binding: FragmentAddMealBinding
    private val calendar = Calendar.getInstance()
    private lateinit var addedMealAdapter : AddMealAdapter
    val list : ArrayList<MealData> = ArrayList()


    lateinit var correctBottomSheetBinding: AddMealBottomSheetCorrectBinding
    lateinit var incorrectBottomSheetBinding: AddMealBottomSheetIncorrectBinding
    lateinit var correctBottomSheet: BottomSheetDialog
    lateinit var inCorrectBottomSheet: BottomSheetDialog

    val hour = calendar.get(Calendar.HOUR)
    val minute = calendar.get(Calendar.MINUTE)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddMealBinding.inflate(inflater, container, false)
        correctBottomSheetBinding = AddMealBottomSheetCorrectBinding.inflate(inflater, container, false)
        incorrectBottomSheetBinding = AddMealBottomSheetIncorrectBinding.inflate(inflater, container, false)
        correctBottomSheet = BottomSheetDialog(requireContext(), com.diyabet.diyabetgunlugum.R.style.BottomSheetStyle)
        inCorrectBottomSheet = BottomSheetDialog(requireContext(), com.diyabet.diyabetgunlugum.R.style.BottomSheetStyle)
        correctBottomSheet.setContentView(correctBottomSheetBinding.root)
        inCorrectBottomSheet.setContentView(incorrectBottomSheetBinding.root)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*list.add(MealData("akşam yemeği"))
        list.add(MealData("öğlen yemeği "))
        list.add(MealData("asdasdasdaa"))*/

        binding.btnAddNewMeal.setOnClickListener {
            if (binding.edtMeal.text.isEmpty()){
                inCorrectBottomSheet.show()
                binding.rvMealTable.visibility=View.INVISIBLE
            }
            else{
                binding.rvMealTable.visibility=View.VISIBLE
                correctBottomSheet.show()
                addedMealAdapter = AddMealAdapter(list,this)
                binding.rvMealTable.adapter = addedMealAdapter

                val layoutManager = LinearLayoutManager(context)
                binding.rvMealTable.layoutManager = layoutManager

                list.add(MealData(binding.edtMeal.text.toString()))
                addedMealAdapter.setlist(list)
                //Toast.makeText(context,"${binding.edtMeal.text} listeye eklendi.",Toast.LENGTH_LONG).show()
                binding.edtMeal.text.clear()

                val itemSwipe = object : ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
                    override fun onMove(
                        recyclerView: RecyclerView,
                        viewHolder: RecyclerView.ViewHolder,
                        target: RecyclerView.ViewHolder
                    ): Boolean {
                        return false
                    }
                    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                        showDialog(viewHolder)
                    }
                }
                val swap = ItemTouchHelper(itemSwipe)
                swap.attachToRecyclerView(binding.rvMealTable)

            }

        }

        binding = FragmentAddMealBinding.bind(view)

        binding.apply {
            btnTimePicker.setOnClickListener{

                val datePickerFragment = DatePickerFragment()
                val supportFragmentManager = requireActivity().supportFragmentManager

                supportFragmentManager.setFragmentResultListener(
                    "REQUEST_KEY",
                    viewLifecycleOwner
                ) { resultKey, bundle ->
                    if (resultKey == "REQUEST_KEY") {
                        val date = bundle.getString("SELECTED_DATE")
                        showTimePickerDialog()
                        tvDatePicker.text = "Tarih : $date"
                    }
                }
                datePickerFragment.show(supportFragmentManager, "DatePickerFragment")
            }
        }

       /* binding.btnAddNewMeal.setOnClickListener {
            if (binding.edtMeal.text.isNotEmpty()){
                correctBottomSheet.show()
            }else{
                inCorrectBottomSheet.show()
            }
            /*val action=AddMealFragmentDirections.actionAddMealFragmentToMealTableFragment()
            Navigation.findNavController(it).navigate(action)*/
        }*/
    }

    private fun showDialog(viewHolder: RecyclerView.ViewHolder){
        var builder = AlertDialog.Builder(activity)
        builder.setTitle("Öğünü sil")
        builder.setMessage("Öğünü silmek istediğinize emin misiniz ? ")
        builder.setPositiveButton("Onayla"){ dialog, which ->
            val position = viewHolder.adapterPosition
            list.removeAt(position)
            addedMealAdapter.notifyItemRemoved(position)
        }
        builder.setNegativeButton("İptal"){ dialog, which ->
            val position = viewHolder.adapterPosition
            addedMealAdapter.notifyItemChanged(position)
        }
        builder.show()
    }
    private fun showTimePickerDialog(){
        val timePicker = TimePickerFragment{onTimeSelected(it)}
        timePicker.show(requireActivity().supportFragmentManager,"time")

    }
    private fun onTimeSelected(time : String){
        binding.tvTimePicker.setText("Saat : $time")
    }

    override fun onItemClick(data: MealData) {

    }


}