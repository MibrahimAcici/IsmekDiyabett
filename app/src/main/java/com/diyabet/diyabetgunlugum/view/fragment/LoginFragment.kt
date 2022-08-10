package com.diyabet.diyabetgunlugum.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.navigation.Navigation
import com.diyabet.diyabetgunlugum.R
import com.diyabet.diyabetgunlugum.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
                btnRegister.setOnClickListener {
                    val action=LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
                    Navigation.findNavController(it).navigate(action)
                    //Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_registerFragment)
                }

                btnLogin.setOnClickListener {

                    if (tietEmail.text!!.isEmpty()) {
                        tilEmail.error = "Lütfen boş bırakmayınız"
                    } else {
                        tilEmail.error = null
                    }

                    if (tietPassword.text!!.isEmpty()) {
                        tilPassword.error = "Lütfen boş bırakmayınız"
                    } else {
                        tilPassword.error = null
                }

        }
        }
    }
}