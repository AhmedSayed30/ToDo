package com.example.todo.home.addTask

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todo.R
import com.example.todo.database.MyDataBase
import com.example.todo.database.model.Task
import com.example.todo.databinding.FragmentAddTaskBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.Calendar

class AddTaskBottomSheet :BottomSheetDialogFragment (){
        lateinit var viewBinding: FragmentAddTaskBinding
        override fun onCreateView(
                inflater: LayoutInflater,
                container: ViewGroup?,
                savedInstanceState: Bundle?
        ): View? {
                viewBinding= FragmentAddTaskBinding.inflate(inflater,container,false)
                return viewBinding.root
        }
        var onDismissListener : onDismissListener?=null
        override fun onDismiss(dialog: DialogInterface) {
                super.onDismiss(dialog)
                onDismissListener?.onDismiss()
        }
        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
                super.onViewCreated(view, savedInstanceState)
                setDate()
                viewBinding.taskDate.setOnClickListener{
                        showDatePicker()
                }
                viewBinding.submit.setOnClickListener {
                        addTask()
                }
        }
        fun validate():Boolean{
                var valid = true
                val title =viewBinding.taskTitle.editText?.text.toString()
                val desc =viewBinding.taskDesc.editText?.text.toString()
                if (title.isNullOrBlank()){
                        viewBinding.taskTitle.error="Please Enter Title"
                        valid = false
                }else{
                        viewBinding.taskTitle.error=null
                }
                if (desc.isNullOrBlank()){
                        viewBinding.taskDesc.error="Please Enter Description"
                        valid = false
                }else{
                        viewBinding.taskDesc.error=null
                }
                return valid
        }
        fun addTask(){
                if (validate()==false){
                        return
                }
                val title =viewBinding.taskTitle.editText?.text.toString()
                val desc =viewBinding.taskDesc.editText?.text.toString()
                MyDataBase.getDataBase(requireActivity())
                        .taskDao()
                        .insertTask(Task(
                                title = title,
                                description = desc,
                                date = currentDate.timeInMillis
                        ))
                showTaskInsertedDialog()
        }
        fun showTaskInsertedDialog(){
                val alertDialogBinding = AlertDialog.Builder(activity)
                        .setMessage("Task Inserted Successfully")
                        .setPositiveButton(R.string.ok,
                         { dialog, btnId ->
                                 dialog.dismiss()
                                 dismiss()
                         }).setCancelable(false);
                alertDialogBinding.show()
        }
        var currentDate = Calendar.getInstance()
        fun setDate(){
                viewBinding.taskDateText.setText("" + currentDate.get(Calendar.DAY_OF_MONTH)+"/"
                        +currentDate.get(Calendar.MONTH)+1+"/"
                        +currentDate.get(Calendar.YEAR))
        }
        private fun showDatePicker() {
                val datePicker = DatePickerDialog(requireActivity(),
                { datePicker, year, month, day ->
                        currentDate.set(Calendar.YEAR,year)
                        currentDate.set(Calendar.MONTH,month)
                        currentDate.set(Calendar.DAY_OF_MONTH,day)

                        setDate()
                },
                currentDate.get(Calendar.YEAR),
                currentDate.get(Calendar.MONTH),
                currentDate.get(Calendar.DAY_OF_MONTH))
                datePicker.show()
        }
}