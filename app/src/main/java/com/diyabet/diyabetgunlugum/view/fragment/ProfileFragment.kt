package com.diyabet.diyabetgunlugum.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.diyabet.diyabetgunlugum.ProfileData
import com.diyabet.diyabetgunlugum.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    lateinit var userProfile : ProfileData
    private val args: ProfileFragmentArgs by navArgs()

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
/*
        if(args.editUserProfile?.profile_name.toString() == binding.profileName.text.toString()){

            val profileName=args.editUserProfile?.profile_name
            val profileSurname=args.editUserProfile?.profile_surname
            val profileNumber=args.editUserProfile?.profile_number
            val profileMail=args.editUserProfile?.profile_mail
            val profileDate=args.editUserProfile?.profile_date
            val profileGender=args.editUserProfile?.profile_gender
            val profileHeight=args.editUserProfile?.profile_height
            val profileWeight=args.editUserProfile?.profile_weight

            binding.profileName.text = profileName.toString()
            binding.profileSurname.text = profileSurname.toString()
            binding.profileNumber.text = profileNumber.toString()
            binding.profileMail.text = profileMail.toString()
            binding.profileDate.text = profileDate.toString()
            binding.profileGender.text = profileGender.toString()
            binding.profileHeight.text = profileHeight.toString()
            binding.profileWeight.text = profileWeight.toString()

        }
*/

        userProfile = ProfileData(binding.profileName.text.toString(),binding.profileSurname.text.toString(),binding.profileNumber.text.toString(),binding.profileMail.text.toString(),binding.profileDate.text.toString(),binding.profileGender.text.toString(),binding.profileHeight.text.toString(),binding.profileWeight.text.toString())

        binding.profileBtnEdit.setOnClickListener {
            val action=ProfileFragmentDirections.actionProfileFragmentToEditProfileFragment(userProfile)
            Navigation.findNavController(it).navigate(action)
        }
    }

}