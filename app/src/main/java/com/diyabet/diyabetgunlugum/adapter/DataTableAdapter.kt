package com.diyabet.diyabetgunlugum.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.diyabet.diyabetgunlugum.DataTableData
import com.diyabet.diyabetgunlugum.databinding.DataTableItemBinding

class DataTableAdapter(): RecyclerView.Adapter<DataTableAdapter.DataTableVH>() {

    private  val dataset:ArrayList<DataTableData> = arrayListOf()

    fun setList(dataset:List<DataTableData>) {
        this.dataset.clear()
        this.dataset.addAll(dataset)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataTableAdapter.DataTableVH {
        return DataTableVH(DataTableItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: DataTableAdapter.DataTableVH, position: Int) {
        val data= dataset[position]

        holder.binding.dataTableSaat.text=data.saat
        holder.binding.dataTableOrtalama.text=data.ortalama
        holder.binding.dataTableSapma.text=data.sapma
        holder.binding.dataTableHiper.text=data.hiper
        holder.binding.dataTableHipo.text=data.hipo

    }

    override fun getItemCount(): Int =dataset.size

    inner class DataTableVH(val binding: DataTableItemBinding): RecyclerView.ViewHolder(binding.root) {
    }
}