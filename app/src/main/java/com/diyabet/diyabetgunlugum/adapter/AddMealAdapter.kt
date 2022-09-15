package com.diyabet.diyabetgunlugum.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.diyabet.diyabetgunlugum.databinding.AddMealItemBinding
import com.diyabet.diyabetgunlugum.MealTableItemData

class AddMealAdapter (val mealList: List<MealTableItemData>,val clickListener: ClickListener): RecyclerView.Adapter<AddMealAdapter.ViewHolder>() {

    interface ClickListener{
        fun onItemClick(data : MealTableItemData)
    }


    inner class ViewHolder (val binding : AddMealItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data : MealTableItemData){
            itemView.apply {
                with(data){
                    binding.tvAddedMeal.text = meal

                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddMealAdapter.ViewHolder {

        return ViewHolder(AddMealItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mealList[position])
        holder.itemView.setOnClickListener {
            clickListener.onItemClick(mealList.get(position))
        }

    }

    override fun getItemCount(): Int {
        return mealList.size
    }

    fun setlist(meal : List<MealTableItemData>){
        // this.mealList.clear()
        //this.mealList.addAll(meal)
        notifyDataSetChanged()
    }

}