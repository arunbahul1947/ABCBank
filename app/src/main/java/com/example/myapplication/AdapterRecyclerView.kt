package com.example.myapplication

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterRecyclerView(val activity:Activity,val list:ArrayList<DataClass>) : RecyclerView.Adapter<AdapterRecyclerView.ViewHolder>() {

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterRecyclerView.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_layout, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: AdapterRecyclerView.ViewHolder, position: Int) {
        holder.bindItems(list.get(position))
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return list.size
    }

    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(data: DataClass) {
            val imageViewMain = itemView.findViewById(R.id.imageViewMain) as ImageView
            val textViewName = itemView.findViewById(R.id.textViewUsername) as TextView

            data.image?.toInt()?.let { imageViewMain.setImageResource(it) }
            textViewName.text = data.name


        }
    }
}