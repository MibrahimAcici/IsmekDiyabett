package com.diyabet.diyabetgunlugum.view.fragment.profil

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.diyabet.diyabetgunlugum.model.ProfileData
import com.diyabet.diyabetgunlugum.databinding.FragmentProfileBinding
import com.diyabet.diyabetgunlugum.model.request.CreateTokenRequestModel
import com.diyabet.diyabetgunlugum.viewmodel.profil.ProfilViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private val profilViewModel: ProfilViewModel by viewModels()
    lateinit var userProfile : ProfileData

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val createTokenRequestModel = CreateTokenRequestModel().apply {
            email = "admin@softverse"
            password = "Eyhz0zqydgZvDyxFByN0"
        }
        profilViewModel.createToken(createTokenRequestModel)

        profilViewModel.tokenResponse.observe(viewLifecycleOwner) {
            it?.let {
                Toast.makeText(requireContext(), it.token.toString(), Toast.LENGTH_LONG).show()
            }
        }

        userProfile = ProfileData(binding.profileName.text.toString(),binding.profileSurname.text.toString(),binding.profileNumber.text.toString(),binding.profileMail.text.toString(),binding.profileDate.text.toString(),binding.profileGender.text.toString(),binding.profileHeight.text.toString(),binding.profileWeight.text.toString())

        binding.profileBtnEdit.setOnClickListener {
            val action= ProfileFragmentDirections.actionProfileFragmentToEditProfileFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }

}