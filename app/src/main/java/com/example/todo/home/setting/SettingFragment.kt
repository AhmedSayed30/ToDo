package com.example.todo.home.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.todo.databinding.FragmentListBinding

class SettingFragment:Fragment() {
    lateinit var viewBinding:FragmentListBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentListBinding.inflate(inflater,container,false)
        return viewBinding.root
    }
}