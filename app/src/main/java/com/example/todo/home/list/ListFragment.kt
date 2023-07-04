package com.example.todo.home.list

import android.content.Context
import android.icu.util.Calendar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.todo.database.MyDataBase
import com.example.todo.databinding.FragmentListBinding

class ListFragment : Fragment() {
    lateinit var viewBinding : FragmentListBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentListBinding.inflate(inflater,container,false)
        return viewBinding.root
    }
    lateinit var taskAdapter:TasksRecyclerAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        taskAdapter = TasksRecyclerAdapter(null)
        viewBinding.tasksRecycler.adapter = taskAdapter
        viewBinding.calendarView.setOnDateChangedListener { widget, date, selected ->
            currentDate.set(Calendar.DAY_OF_MONTH,date.day)
            currentDate.set(Calendar.MONTH,date.month-1)
            currentDate.set(Calendar.YEAR,date.year)
            if (selected){
                loadTasks()
            }
        }
    }
    val currentDate = Calendar.getInstance()

    override fun onResume() {
        super.onResume()
        loadTasks()
    }
    fun loadTasks() {
        if (!isResumed){
            return
        }
        val tasks = MyDataBase.getDataBase(requireActivity())
            .taskDao()
            .getTasksByDate(currentDate.timeInMillis)
        taskAdapter.changeData(tasks)
    }
}