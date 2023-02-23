package com.example.todo.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.ListFragment
import com.example.todo.R
import com.example.todo.databinding.ActivityMainBinding
import com.example.todo.home.setting.SettingFragment

class MainActivity : AppCompatActivity() {
    lateinit var viewBinding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        viewBinding.btnNav.setOnItemSelectedListener{
            when(it.itemId){
                R.id.nav_tasks_list->{
                    viewBinding.screenTitle.setText(R.string.title_tasks_list)
                    showFragment(ListFragment())
                }
                R.id.nav_tasks_setting->{
                    viewBinding.screenTitle.setText(R.string.title_tasks_setting)
                    showFragment(SettingFragment())
                }
            }
            return@setOnItemSelectedListener true
        }
        viewBinding.btnNav.selectedItemId = R.id.nav_tasks_list
    }
    fun showFragment(fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container,fragment)
            .commit()
    }
}