package com.diyabet.diyabetgunlugum.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.diyabet.diyabetgunlugum.ProfileData
import com.diyabet.diyabetgunlugum.databinding.FragmentEditProfileBinding



class EditProfileFragment : Fragment() {
    private lateinit var binding: FragmentEditProfileBinding
    lateinit var editUserProfile : ProfileData
    private val args: EditProfileFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditProfileBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        val profileName=args.userProfile?.profile_name
        val profileSurname=args.userProfile?.profile_surname
        val profileNumber=args.userProfile?.profile_number
        val profileMail=args.userProfile?.profile_mail
        val profileDate=args.userProfile?.profile_date
        val profileGender=args.userProfile?.profile_gender
        val profileHeight=args.userProfile?.profile_height
        val profileWeight=args.userProfile?.profile_weight

        binding.editProfileName.setText(profileName.toString())
        binding.editProfileSurname.setText(profileSurname.toString())
        binding.editProfileNumber.setText(profileNumber.toString())
        binding.editProfileMail.setText(profileMail.toString())
        binding.editProfileDate.setText(profileDate.toString())
        binding.editProfileGender.setText(profileGender.toString())
        binding.editProfileHeight.setText(profileHeight.toString())
        binding.editProfileWeight.setText(profileWeight.toString())

        editUserProfile = ProfileData(binding.editProfileName.text.toString(),binding.editProfileSurname.text.toString(),binding.editProfileNumber.text.toString(),binding.editProfileMail.text.toString(),binding.editProfileDate.text.toString(),binding.editProfileGender.text.toString(),binding.editProfileHeight.text.toString(),binding.editProfileWeight.text.toString())

        binding.editProfileBtnSave.setOnClickListener {
            val action=EditProfileFragmentDirections.actionEditProfileFragmentToProfileFragment(editUserProfile)
            Navigation.findNavController(it).navigate(action)
        }

    }

}