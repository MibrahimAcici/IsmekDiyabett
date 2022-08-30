package com.diyabet.diyabetgunlugum.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.diyabet.diyabetgunlugum.R
import com.diyabet.diyabetgunlugum.databinding.AddMealBottomSheetCorrectBinding
import com.diyabet.diyabetgunlugum.databinding.AddMealBottomSheetIncorrectBinding
import com.diyabet.diyabetgunlugum.databinding.FragmentAddMealBinding
import com.diyabet.diyabetgunlugum.databinding.HomeBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.coroutines.delay
import okhttp3.internal.wait
import java.util.*

class AddMealFragment : Fragment() {
    private lateinit var binding: FragmentAddMealBinding
    private val calendar = Calendar.getInstance()

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
        correctBottomSheet = BottomSheetDialog(requireContext(), R.style.BottomSheetStyle)
        inCorrectBottomSheet = BottomSheetDialog(requireContext(), R.style.BottomSheetStyle)
        correctBottomSheet.setContentView(correctBottomSheetBinding.root)
        inCorrectBottomSheet.setContentView(incorrectBottomSheetBinding.root)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
                        tvTextTime.text = "Tarih : $date"

                    }
                }
                datePickerFragment.show(supportFragmentManager, "DatePickerFragment")

            }
        }

        binding.addMealBtnMealSave.setOnClickListener {
            if (binding.edtMeal.text.isNotEmpty()){
                correctBottomSheet.show()

            }else{
                inCorrectBottomSheet.show()
            }
            /*val action=AddMealFragmentDirections.actionAddMealFragmentToMealTableFragment()
            Navigation.findNavController(it).navigate(action)*/
        }
        binding.btnTimePicker2.setOnClickListener {
            showTimePickerDialog()

        }
    }
    private fun showTimePickerDialog(){
        val timePicker = TimePickerFragment{onTimeSelected(it)}
        timePicker.show(requireActivity().supportFragmentManager,"time")

    }

    private fun onTimeSelected(time : String){
        binding.tvTimePicker.setText("Saat : $time")
    }

}