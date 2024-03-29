package com.example.todo.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task (
       @PrimaryKey(autoGenerate = true) var id:Int?=null,
       @ColumnInfo var title:String?=null,
       @ColumnInfo var description:String?=null,
       @ColumnInfo var date : Long? = null,
       @ColumnInfo var isDone : Boolean= false
        ){
}