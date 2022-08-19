package com.diyabet.diyabetgunlugum.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.diyabet.diyabetgunlugum.MealTableData
import com.diyabet.diyabetgunlugum.databinding.MealTableItemBinding

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
        val data = mealdatalist[position]

        holder.binding.tvMealHour.text = data.saat
        holder.binding.tvMeal.text = data.meal

    }
    override fun getItemCount() : Int = mealdatalist.size

    inner class MealViewHolder(val binding: MealTableItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }

}