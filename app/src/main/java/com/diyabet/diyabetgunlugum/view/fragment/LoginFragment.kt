package com.diyabet.diyabetgunlugum.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.diyabet.diyabetgunlugum.R
import com.diyabet.diyabetgunlugum.databinding.FragmentLoginBinding
import com.diyabet.diyabetgunlugum.model.request.LoginRequestModel
import com.diyabet.diyabetgunlugum.util.Constant
import com.diyabet.diyabetgunlugum.viewmodel.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()

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
                    val loginRequestModel = LoginRequestModel().apply {
                        email = tietEmail.text.toString()
                        password = tietPassword.text.toString()
                    }
                    loginViewModel.login(loginRequestModel)

                    /*val action=LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
                    Navigation.findNavController(it).navigate(action)
                    Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_registerFragment)*/
                }

                loginViewModel.loginResponse.observe(viewLifecycleOwner) {
                    it?.let {
                        if (it.status == "1"){
                            val bundle = Bundle()
                            bundle.putParcelable(Constant.LOGIN_RESPONSE, it)
                            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_homeFragment, bundle)
                        }
                    }
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