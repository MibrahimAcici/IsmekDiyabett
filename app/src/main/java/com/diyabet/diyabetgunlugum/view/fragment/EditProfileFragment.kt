package com.diyabet.diyabetgunlugum.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.diyabet.diyabetgunlugum.ProfileData
import com.diyabet.diyabetgunlugum.R
import com.diyabet.diyabetgunlugum.databinding.FragmentEditProfileBinding



class EditProfileFragment : Fragment() {
    private lateinit var binding: FragmentEditProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditProfileBinding.inflate(inflater, container, false)

        return binding.root

    }
//diyabetgunlugum://user?kjlkjkl
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            //binding.editProfileName.setText("ahmet")
            val arrayAdapter = ArrayAdapter(
                requireContext(),
                R.layout.dropdown_item,
                resources.getStringArray(R.array.gender)
            )
            editProfileGender.setAdapter(arrayAdapter)

            binding.editProfileBtnSave.setOnClickListener {
                if (checkText()) {

                val action = EditProfileFragmentDirections.actionEditProfileFragmentToProfileFragment()
                    Navigation.findNavController(it).navigate(action)
            }
            }




        }
    }

    private fun checkText(): Boolean {
        var isEmpty = true

        binding.apply {
        if (editProfileName.text!!.isEmpty()) {
            editProfileName.error = "Lütfen boş bırakmayınız"
            isEmpty = false
        } else (editProfileName.error) = null
        if (editProfileSurname.text!!.isEmpty()) {
            editProfileSurname.error = "Lütfen boş bırakmayınız"
            isEmpty = false
        } else (editProfileSurname.error) = null
        if (editProfileNumber.text!!.isEmpty()) {
            editProfileNumber.error = "Lütfen boş bırakmayınız"
            isEmpty = false
        } else (editProfileNumber.error) = null
        if (editProfileMail.text!!.isEmpty()) {
            editProfileMail.error = "Lütfen boş bırakmayınız"
            isEmpty = false
        } else if (!editProfileMail.text!!.contains('@')) {
            editProfileMail.error = "E-mail formatı hatalı"
            isEmpty = false
        } else (editProfileMail.error) = null
        if (editProfileDate.text!!.isEmpty()) {
            editProfileDate.error = "Lütfen boş bırakmayınız"
            isEmpty = false
        } else (editProfileDate.error) = null
        if (editProfileWeight.text!!.isEmpty()) {
            editProfileWeight.error = "Lütfen boş bırakmayınız"
            isEmpty = false
        }else(editProfileWeight.error) = null
        if (editProfileHeight.text!!.isEmpty()) {
            editProfileHeight.error = "Lütfen boş bırakmayınız"
            isEmpty = false
        } else (editProfileHeight.error) = null

        }
        return isEmpty

    }
}
/*
 binding.editProfileBtnSave.setOnClickListener {
            val action=EditProfileFragmentDirections.actionEditProfileFragmentToProfileFragment(editUserProfile)
            Navigation.findNavController(it).navigate(action)
        }
 */