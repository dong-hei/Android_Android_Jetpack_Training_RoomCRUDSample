package com.dk.myapplication.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dk.myapplication.R
import com.dk.myapplication.db.entity.NumEntity

class CustomAdapter(private val dataSet : ArrayList<NumEntity>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    interface ItemClick {
        fun onClick(view : View , position: Int)
    }

    var updateClick : ItemClick? = null
    var deleteClick : ItemClick? = null

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val idArea : TextView
        val ranNum : TextView

        init{
            idArea = view.findViewById(R.id.id)
            ranNum = view.findViewById(R.id.randomNum)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.num_item, parent, false)

        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.idArea.text = dataSet[position].id.toString()
        holder.ranNum.text = dataSet[position].randomNum

        holder.itemView.findViewById<Button>(R.id.updateBtn).setOnClickListener { v->
            updateClick?.onClick(v, position)
        }

        holder.itemView.findViewById<Button>(R.id.deleteBtn).setOnClickListener { v->
            deleteClick?.onClick(v, position)
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}