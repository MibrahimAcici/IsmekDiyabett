package com.diyabet.diyabetgunlugum

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.diyabet.diyabetgunlugum.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //deeplink kullanımı
        val action: String? = intent?.action
        val data: Uri? = intent?.data
        if (data!=null){
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