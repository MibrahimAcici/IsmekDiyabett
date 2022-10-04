package com.diyabet.diyabetgunlugum.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.diyabet.diyabetgunlugum.databinding.MealTableInnerItemBinding
import com.diyabet.diyabetgunlugum.model.MealTableItemData

class MealTableItemAdapter() : RecyclerView.Adapter<MealTableItemAdapter.MealTableItemVH> () {

    private val mealTableItemDataList : ArrayList<MealTableItemData> = arrayListOf()

    fun setList(mealItemList : List<MealTableItemData>){
        mealTableItemDataList.clear()
        mealTableItemDataList.addAll(mealItemList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : MealTableItemAdapter.MealTableItemVH{
        return MealTableItemVH(MealTableInnerItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MealTableItemAdapter.MealTableItemVH, position: Int) {
        val dataInner = mealTableItemDataList[position]

        holder.binding.mealTableInnerItemTv.text = dataInner.meal
    }
    override fun getItemCount() : Int = mealTableItemDataList.size

    inner class MealTableItemVH(val binding: MealTableInnerItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }

}