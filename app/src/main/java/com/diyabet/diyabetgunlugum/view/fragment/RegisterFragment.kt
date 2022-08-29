package com.diyabet.diyabetgunlugum.view.fragment

import android.app.ActionBar
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ArrayAdapter
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.diyabet.diyabetgunlugum.R
import com.diyabet.diyabetgunlugum.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            val arrayAdapter = ArrayAdapter(
                requireContext(),
                R.layout.dropdown_item,
                resources.getStringArray(R.array.gender)
            )
            tietGender.setAdapter(arrayAdapter)

            registerBtnRegister.setOnClickListener {
                if (checkText()) {
                    val action = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
                    Navigation.findNavController(it).navigate(action)
                    //Navigation.findNavController(view).navigate(R.id.action_registerFragment_to_loginFragment)
                }
            }
            imgBackKey.setOnClickListener {
                Navigation.findNavController(view)
                    .navigate(R.id.action_registerFragment_to_loginFragment)
            }
            val callback = object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    Navigation.findNavController(view).navigate(R.id.action_registerFragment_to_loginFragment)
                }
            }
            requireActivity().onBackPressedDispatcher.addCallback(callback)
        }
    }

    fun checkText(): Boolean {
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
}