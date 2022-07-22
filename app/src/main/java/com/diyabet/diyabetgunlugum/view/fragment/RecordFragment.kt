package com.diyabet.diyabetgunlugum.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.widget.doOnTextChanged
import androidx.navigation.Navigation
import com.diyabet.diyabetgunlugum.R
import com.diyabet.diyabetgunlugum.databinding.FragmentRecordBinding

class RecordFragment : Fragment() {

    private lateinit var binding: FragmentRecordBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentRecordBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            empty_text()
            val arrayAdapter = ArrayAdapter(requireContext(),
                R.layout.dropdown_item,resources.getStringArray(R.array.ebeveyn))
            autoComplateTextView.setAdapter(arrayAdapter)

                btnRecord.setOnClickListener {
                    if (check_text()){
                    Navigation.findNavController(view).navigate(R.id.action_recordFragment_to_loginFragment)
                }
            }
        }
    }
    fun check_text(): Boolean {
        var control = true
        binding.apply {
            if (tietName.text!!.isEmpty()) {
                tietName.error = "Lütfen boş bırakmayınız"
                control = false
            }
            if (tietSurname.text!!.isEmpty()) {
                tietSurname.error = "Lütfen boş bırakmayınız"
                control = false
            }
            if (tietEmail.text!!.isEmpty()) {
                tietEmail.error = "Lütfen boş bırakmayınız"
                control = false
            } else if (!tietEmail.text!!.contains('@')) {
                tilEmail.endIconDrawable = null
                tietEmail.error = "E-mail formatı hatalı"
                control = false
            }
            if (tietPassword.text!!.isEmpty()) {
                tilPassword.endIconDrawable = null
                tietPassword.error = "Lütfen boş bırakmayınız"
                control = false
            }
        }


        return control
    }

    fun empty_text(): Boolean {
        var control = true
        binding.apply {
            tietName.doOnTextChanged { text, start, before, count ->
                if (text!!.isEmpty()) {
                    tilName.error = "Lütfen boş bırakmayınız"
                    control = false
                }
            }
            tietSurname.doOnTextChanged { text, start, before, count ->
                if (text!!.isEmpty()) {
                    tilSurname.error = "Lütfen boş bırakmayınız"
                    control = false
                }
            }
            tietEmail.doOnTextChanged { text, start, before, count ->
                if (text!!.isEmpty()) {
                    tilEmail.error = "Lütfen boş bırakmayınız"
                    control = false
                }
            }
            tietPassword.doOnTextChanged { text, start, before, count ->
                if (text!!.isEmpty()) {
                    tilPassword.error = "Lütfen boş bırakmayınız"
                    control = false
                }
            }
        }

        return control
    }
}