package com.diyabet.diyabetgunlugum.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.diyabet.diyabetgunlugum.R
import com.diyabet.diyabetgunlugum.databinding.FragmentHomeBinding
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val lineEntryList = arrayListOf<Entry>()
        val xAxisList= arrayOf("06:00","12:00","18:00","20:00","00:00")

        lineEntryList.add(Entry(0f,100f))
        lineEntryList.add(Entry(1f,120f))
        lineEntryList.add(Entry(2f,107f))
        lineEntryList.add(Entry(3f,85f))
        lineEntryList.add(Entry(4f,103f))

        val lineDataSet= LineDataSet(lineEntryList,"Saat")
        val lineData= LineData(lineDataSet)

        lineDataSet.apply {
            valueTextSize=11f
            lineWidth=2f
            isHighlightEnabled= true //grafiğe tıklayınca + şeklinde x y eksenini gösteriyor
            setDrawHighlightIndicators(true)
            setDrawCircles(true)//grafikteki değerlerin nokta nokta işaretlenmesi
            setCircleColors(*ColorTemplate.MATERIAL_COLORS)//grafikteki değer noktalarının rengarenk gösterilmesi
            setDrawCircleHole(false)//grafikteki değer noktalarının içindeki boşlukları doldurma
            valueTextColor=ContextCompat.getColor(requireContext(), R.color.black)
            color=ContextCompat.getColor(requireContext(),R.color.white)
            mode=LineDataSet.Mode.CUBIC_BEZIER
            setDrawFilled(true)
            fillDrawable=ContextCompat.getDrawable(requireContext(),R.drawable.fill_graph)
            //valueTextColor=R.color.red
            //setColors(*ColorTemplate.MATERIAL_COLORS)
        }

        binding.lineChart.apply {
            animateXY(1000,1000)
            data=lineData
            legend.isEnabled=false
            setScaleEnabled(false) //grafiğe yaklaşma uzaklaşma
            description=null //description label yazısı kaldırma
            setDrawBorders(true) //çerçeve

        }

        binding.lineChart.axisLeft.apply {
            axisMinimum=70f
            axisMaximum=130f
            isEnabled=false
        }

        binding.lineChart.axisRight.apply {
            isEnabled=false
        }

        binding.lineChart.xAxis.apply {
            axisMinimum=0f
            position= XAxis.XAxisPosition.BOTTOM
            textSize=11f
            textColor=ContextCompat.getColor(requireContext(),R.color.white)
            valueFormatter= IndexAxisValueFormatter(xAxisList)
            axisLineWidth= 2f
            isGranularityEnabled=true
            setDrawGridLines(true)
            setDrawAxisLine(true)
            //granularity=4f
        }
    }

}