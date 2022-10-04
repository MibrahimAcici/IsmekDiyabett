package com.diyabet.diyabetgunlugum.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.diyabet.diyabetgunlugum.model.MealTableData
import com.diyabet.diyabetgunlugum.databinding.MealTableItemBinding
import com.diyabet.diyabetgunlugum.view.fragment.MealTableFragmentDirections

class MealTableAdapter() : RecyclerView.Adapter<MealTableAdapter.MealViewHolder> () {

    private val mealdatalist : ArrayList<MealTableData> = arrayListOf()


    fun setList(mealList : List<MealTableData>){
        mealdatalist.clear()
        mealdatalist.addAll(mealList)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : MealTableAdapter.MealViewHolder{
        return MealViewHolder(MealTableItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MealTableAdapter.MealViewHolder, position: Int) {
        val mealTableData = mealdatalist[position]
        holder.binding.mealTableItemTv.text = mealTableData.time

        holder.binding.mealTableItemRv.apply {
            val mealTableInnerAdapter = MealTableItemAdapter()
            adapter = mealTableInnerAdapter
            layoutManager = LinearLayoutManager(context)

            mealTableInnerAdapter.setList(mealTableData.mealList)
        }

        holder.binding.root.setOnClickListener {
            val action= MealTableFragmentDirections.actionMealTableFragmentToAddMealFragment(mealTableData)
            Navigation.findNavController(it).navigate(action)
        }
    }
    override fun getItemCount() : Int = mealdatalist.size

    inner class MealViewHolder(val binding: MealTableItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }

}