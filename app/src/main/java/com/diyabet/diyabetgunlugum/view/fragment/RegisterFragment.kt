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
import com.diyabet.diyabetgunlugum.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentRegisterBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            val arrayAdapter = ArrayAdapter(requireContext(),
                R.layout.dropdown_item,resources.getStringArray(R.array.gender))
            tietGender.setAdapter(arrayAdapter)

                registerBtnRegister.setOnClickListener {
                    if (check_text()){
                        val action=RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
                        Navigation.findNavController(it).navigate(action)
                    //Navigation.findNavController(view).navigate(R.id.action_registerFragment_to_loginFragment)
                    }
                    else{
                        empty_text()
                    }
            }
        }
    }
    fun check_text(): Boolean {
        var isEmpty = true
        binding.apply {
            if (tietName.text!!.isEmpty()) {
                tietName.error = "Lütfen boş bırakmayınız"
                isEmpty = false
            }
            if (tietSurname.text!!.isEmpty()) {
                tietSurname.error = "Lütfen boş bırakmayınız"
                isEmpty = false
            }
            if (tietNumber.text!!.isEmpty()) {
                tietNumber.error = "Lütfen boş bırakmayınız"
                isEmpty = false
            }
            if (tietEmail.text!!.isEmpty()) {
                tietEmail.error = "Lütfen boş bırakmayınız"
                isEmpty = false
            } else if (!tietEmail.text!!.contains('@')) {
                tilEmail.endIconDrawable = null
                tietEmail.error = "E-mail formatı hatalı"
                isEmpty = false
            }
            if (tietPassword.text!!.isEmpty()) {
                tilPassword.endIconDrawable = null
                tietPassword.error = "Lütfen boş bırakmayınız"
                isEmpty = false
            }
            if (tietDate.text!!.isEmpty()) {
                tietDate.error = "Lütfen boş bırakmayınız"
                isEmpty = false
            }
            if (tietHeight.text!!.isEmpty()) {
                tietHeight.error = "Lütfen boş bırakmayınız"
                isEmpty = false
            }
            if (tietWeight.text!!.isEmpty()) {
                tietWeight.error = "Lütfen boş bırakmayınız"
                isEmpty = false
            }
        }


        return isEmpty
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
            tietNumber.doOnTextChanged { text, start, before, count ->
                if (text!!.isEmpty()) {
                    tilNumber.error = "Lütfen boş bırakmayınız"
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
            tietDate.doOnTextChanged { text, start, before, count ->
                if (text!!.isEmpty()) {
                    tilDate.error = "Lütfen boş bırakmayınız"
                    control = false
                }
            }
            tietHeight.doOnTextChanged { text, start, before, count ->
                if (text!!.isEmpty()) {
                    tilHeight.error = "Lütfen boş bırakmayınız"
                    control = false
                }
            }
            tietWeight.doOnTextChanged { text, start, before, count ->
                if (text!!.isEmpty()) {
                    tilWeight.error = "Lütfen boş bırakmayınız"
                    control = false
                }
            }
        }

        return control
    }
}