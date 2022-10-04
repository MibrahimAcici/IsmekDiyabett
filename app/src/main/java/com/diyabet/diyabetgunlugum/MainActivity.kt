package com.diyabet.diyabetgunlugum

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.diyabet.diyabetgunlugum.databinding.ActivityMainBinding
import com.diyabet.diyabetgunlugum.handler.goToDeepLink
import com.diyabet.diyabetgunlugum.model.request.CreateTokenRequestModel
import com.diyabet.diyabetgunlugum.util.Constant
import com.diyabet.diyabetgunlugum.viewmodel.profil.ProfilViewModel
import com.orhanobut.hawk.Hawk
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val profilViewModel: ProfilViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Hawk.init(this).build()

        val createTokenRequestModel = CreateTokenRequestModel().apply {
            email = "admin@softverse"
            password = "Eyhz0zqydgZvDyxFByN0"
        }
        profilViewModel.createToken(createTokenRequestModel)

        profilViewModel.tokenResponse.observe(this) {
            it?.let {
                Hawk.put(Constant.TOKEN, it.token)
                Toast.makeText(this, it.token.toString(), Toast.LENGTH_LONG).show()
            }
        }

        //deeplink kullanımı
        val action: String? = intent?.action
        val data: Uri? = intent?.data
        if (data != null){
            Toast.makeText(applicationContext, data.toString(), Toast.LENGTH_SHORT).show()
        }
        val navController = findNavController(R.id.host_fragment)
        NavigationUI.setupWithNavController(binding.btmNav,navController)

        binding.btmNav.itemIconTintList = null

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            binding.btmNav.apply {
                when (destination.id) {
                    //R.id.loginFragment    ->  visibility= View.GONE
                    R.id.registerFragment  -> visibility=View.GONE
                    R.id.splashFragment  -> visibility= View.GONE
                    else -> visibility= View.VISIBLE
                }
            }
        }


    }
}