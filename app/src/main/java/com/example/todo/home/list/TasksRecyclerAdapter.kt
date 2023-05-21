package com.example.todo.home.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.todo.database.model.Task
import com.example.todo.databinding.ItemTaskBinding
import kotlin.math.hypot

class TasksRecyclerAdapter(var items: List<Task>?):RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = ItemTaskBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.viewBinding.title.text = items?.get(position)?.title
        holder.viewBinding.desc.text = items?.get(position)?.description
    }


    override fun getItemCount():Int = items?.size ?:0


    fun changeData(newListOfTask:List<Task>?){
        items=newListOfTask
        notifyDataSetChanged()
    }

    class ViewHolder(val viewBinding:ItemTaskBinding):RecyclerView.ViewHolder(viewBinding.root){

    }

}