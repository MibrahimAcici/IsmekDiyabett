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
                btnRecord.setOnClickListener {
                    Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_recordFragment)
                }
            tietEmail.doOnTextChanged { text, start, before, count ->
                if (text!!.isEmpty()) {
                    tilEmail.error = "Lütfen boş bırakmayınız"
                } else {
                    tilEmail.error = null
                }
            }
            tietPassword.doOnTextChanged { text, start, before, count ->
                if (text!!.isEmpty()) {
                    tilPassword.error = "Lütfen boş bırakmayınız"
                } else {
                    tilPassword.error = null
                }
            }
        }
    }
}