package com.diyabet.diyabetgunlugum.view.fragment

import android.content.pm.PackageManager
import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.text.util.Linkify
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.ui.graphics.Color
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.ScanMode
import com.diyabet.diyabetgunlugum.R
import com.diyabet.diyabetgunlugum.databinding.FragmentQrScanBinding

private const val CAMERA_REQUEST_CODE = 101

class QrScanFragment : Fragment() {
    private lateinit var binding: FragmentQrScanBinding
    private lateinit var codeScanner : CodeScanner


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentQrScanBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupPermissions()
        codeScanner()

    }

    fun codeScanner() {
        codeScanner = CodeScanner(requireContext(), binding.qrscanScanner)

        codeScanner.apply {
            camera = CodeScanner.CAMERA_BACK
            formats = CodeScanner.ALL_FORMATS

            autoFocusMode = AutoFocusMode.SAFE
            scanMode = ScanMode.CONTINUOUS
            isAutoFocusEnabled = true
            isFlashEnabled = false

            decodeCallback = DecodeCallback {
                activity?.runOnUiThread {
                   /* binding.qrscanTextView.setText(Html.fromHtml(it.text))
                    //binding.qrscanTextView.text = it.text
                    binding.qrscanTextView.movementMethod = LinkMovementMethod.getInstance()
                    binding.qrscanTextView.setLinkTextColor(R.color.primary)
                    //Linkify.addLinks(binding.qrscanTextView, Linkify.ALL)
*/
                    binding.qrscanTextView.setText(Html.fromHtml("<a href=\""+ it.text + "\">" + "QR sahibi kullanıcı bilgilerini göster" + "</a>"))
                    binding.qrscanTextView.setClickable(true)
                    binding.qrscanTextView.setMovementMethod (LinkMovementMethod.getInstance())

                }
            }
            errorCallback = ErrorCallback {
                activity?.runOnUiThread{
                    Log.e("Main", "Camera initilazation error: ${it.message}")
                }
            }

            binding.qrscanScanner.setOnClickListener {
                codeScanner.startPreview()
            }

        }
    }

    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }

    private fun setupPermissions(){
        val permission = ContextCompat.checkSelfPermission(requireContext(),
            android.Manifest.permission.CAMERA)
        if (permission != PackageManager.PERMISSION_GRANTED){
            makeRequest()
        }
    }
    private fun makeRequest(){
        ActivityCompat.requestPermissions(requireActivity(),
        arrayOf(android.Manifest.permission.CAMERA),
        CAMERA_REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            CAMERA_REQUEST_CODE -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(requireContext(),"You need the camera permission to be able to use this app", Toast.LENGTH_SHORT).show()
                }else{
                    //successful
                }
            }
        }
    }
}