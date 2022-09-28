package com.diyabet.diyabetgunlugum.view.fragment

import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.diyabet.diyabetgunlugum.R
import com.diyabet.diyabetgunlugum.databinding.FragmentQrBinding
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter


class QrFragment : Fragment() {
    private lateinit var binding: FragmentQrBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentQrBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.qrScan.setOnClickListener {
            val action=QrFragmentDirections.actionQrFragmentToQrScanFragment()
            Navigation.findNavController(it).navigate(action)
        }

        val qrData = "diyabetgunlugum://user?iacici".trim()

        if (qrData.isEmpty()){
            Toast.makeText(requireContext(),"enter some data",Toast.LENGTH_LONG).show()
        }else{
            val qrWriter = QRCodeWriter()
            try {
                val bitMatrix = qrWriter.encode(qrData,BarcodeFormat.QR_CODE,512,512)
                val qrWidth = bitMatrix.width
                val qrHeight = bitMatrix.height
                val bmp = Bitmap.createBitmap(qrWidth,qrHeight, Bitmap.Config.RGB_565)
                for (x in 0 until qrWidth){
                    for (y in 0 until qrHeight){
                        bmp.setPixel(x,y,if(bitMatrix[x,y]) Color.BLACK else Color.WHITE)
                    }
                }
                binding.qrCode.setImageBitmap(bmp)
            }catch (e:WriterException){
                e.printStackTrace()
            }
        }

    }

}