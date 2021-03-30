package com.first.testchat.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.first.testchat.R
import com.first.testchat.User
import com.first.testchat.ViewModel
import com.google.android.material.textfield.TextInputEditText


class UserFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_user, container, false)
        val btn_next = v.findViewById<Button>(R.id.btn_next)

        btn_next.setOnClickListener {

            val telConsultant = v.findViewById<TextInputEditText>(R.id.telConsultant).text.toString()
            val name = v.findViewById<TextInputEditText>(R.id.name).text.toString()
            val tel = v.findViewById<TextInputEditText>(R.id.tel).text.toString()
            val vm = ViewModelProvider(requireActivity()).get(ViewModel::class.java)

            vm.addNewUser(User(telConsultant,tel,name))
            findNavController().navigate(R.id.action_userFragment_to_chatFragment)
        }

        return v
    }
}