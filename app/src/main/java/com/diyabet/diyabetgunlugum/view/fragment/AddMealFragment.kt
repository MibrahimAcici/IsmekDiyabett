package com.diyabet.diyabetgunlugum.view.fragment

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.diyabet.diyabetgunlugum.*
import com.diyabet.diyabetgunlugum.adapter.AddMealAdapter
import com.diyabet.diyabetgunlugum.databinding.AddMealBottomSheetCorrectBinding
import com.diyabet.diyabetgunlugum.databinding.AddMealBottomSheetIncorrectBinding
import com.diyabet.diyabetgunlugum.databinding.FragmentAddMealBinding
import com.diyabet.diyabetgunlugum.model.MealTableData
import com.diyabet.diyabetgunlugum.model.MealTableItemData
import com.diyabet.diyabetgunlugum.util.Constant
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.*
import kotlin.collections.ArrayList

class AddMealFragment :Fragment(), AddMealAdapter.ClickListener {
    private lateinit var binding: FragmentAddMealBinding
    private val calendar = Calendar.getInstance()
    private lateinit var addedMealAdapter: AddMealAdapter
    private var list: ArrayList<MealTableItemData> = ArrayList()

    private lateinit var mealTableData: MealTableData

    private val args: AddMealFragmentArgs by navArgs()

    lateinit var correctBottomSheetBinding: AddMealBottomSheetCorrectBinding
    lateinit var incorrectBottomSheetBinding: AddMealBottomSheetIncorrectBinding
    lateinit var correctBottomSheet: BottomSheetDialog
    lateinit var inCorrectBottomSheet: BottomSheetDialog

    val hour = calendar.get(Calendar.HOUR)
    val minute = calendar.get(Calendar.MINUTE)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
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

        initMeal()

        binding.btnAddNewMeal.setOnClickListener {

            addedMealAdapter = AddMealAdapter(list, this)
            binding.rvMealTable.adapter = addedMealAdapter

            val layoutManager = LinearLayoutManager(context)
            binding.rvMealTable.layoutManager = layoutManager

            list.add(MealTableItemData(binding.edtMeal.text.toString()))
            addedMealAdapter.setlist(list)
            Toast.makeText(context, "${binding.edtMeal.text} listeye eklendi.", Toast.LENGTH_LONG).show()
            binding.edtMeal.text.clear()

            val itemSwipe = object :ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
                override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    showDialog(viewHolder)
                }
            }
            val swap = ItemTouchHelper(itemSwipe)
            swap.attachToRecyclerView(binding.rvMealTable)

        }

        binding = FragmentAddMealBinding.bind(view)

        binding.apply {
            btnTimePicker.setOnClickListener {

                val datePickerFragment = DatePickerFragment()
                val supportFragmentManager = requireActivity().supportFragmentManager

                supportFragmentManager.setFragmentResultListener("REQUEST_KEY", viewLifecycleOwner) { resultKey, bundle->
                    if (resultKey == Constant.REQUEST_KEY) {
                        val date = bundle.getString("SELECTED_DATE")
                        showTimePickerDialog()
                        binding.addMealDateTextview.text = date
                    }
                }
                datePickerFragment.show(supportFragmentManager, "DatePickerFragment")
            }
        }
        binding.addMealBtnMealSave.setOnClickListener {

            if (list.isEmpty()) {
                inCorrectBottomSheet.show()
            } else {
                correctBottomSheet.show()
                mealTableData = MealTableData(binding.addMealDateTextview.text.toString(), binding.addMealTimeTextview.text.toString(), list)

                findNavController().previousBackStackEntry?.savedStateHandle?.set("meal", mealTableData)

                findNavController().navigateUp()
            }
        }

    }

    private fun showDialog(viewHolder: RecyclerView.ViewHolder) {
        var builder = AlertDialog.Builder(activity)
        builder.setTitle(getString(R.string.questıon_tıtle))
        builder.setMessage("Öğünü silmek istediğinize emin misiniz ? ")
        builder.setPositiveButton("Onayla") { dialog, which->
            val position = viewHolder.bindingAdapterPosition
            list.removeAt(position)
            addedMealAdapter.notifyItemRemoved(position)
        }
        builder.setNegativeButton("İptal") { dialog, which->
            val position = viewHolder.bindingAdapterPosition
            addedMealAdapter.notifyItemChanged(position)
        }
        builder.show()
    }

    private fun showTimePickerDialog() {
        val timePicker = TimePickerFragment { onTimeSelected(it) }
        timePicker.show(requireActivity().supportFragmentManager, "time")

    }

    private fun onTimeSelected(time: String) {
        binding.addMealTimeTextview.setText(time)
    }

    override fun onItemClick(data: MealTableItemData) {

    }

    private fun initMeal() {
        //viewModel.mealTableData = args.currentMealTableData!!

        if (args.currentMealTableData == null) {
            prepareInsertProcess()
        } else {
            prepareUpdateProcess()
        }
    }

    private fun prepareUpdateProcess() {
        binding.addMealTitleTextview.text = "ÖĞÜN DÜZENLE"
        binding.addMealTitleTextview.setTextColor(binding.addMealTitleTextview.context.getColor(R.color.black))
        binding.addMealDateTextview.text = args.currentMealTableData?.date.toString()
        binding.addMealTimeTextview.text = args.currentMealTableData?.time.toString()
        list = args.currentMealTableData!!.mealList
    }

    private fun prepareInsertProcess() {
        binding.addMealTitleTextview.text = "YENİ ÖĞÜN"

    }

}