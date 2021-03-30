package com.first.testchat.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.first.testchat.Mode
import com.first.testchat.R
import com.first.testchat.ViewModel


class ChoiceFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_choice, container, false)
        val vm = ViewModelProvider(requireActivity()).get(ViewModel::class.java)

        val btn_user = v.findViewById<Button>(R.id.btn_user)
        val btn_consultant = v.findViewById<Button>(R.id.btn_consultant)

        btn_consultant.setOnClickListener {
            vm.mode=Mode.Consultant
            findNavController().navigate(R.id.action_choiceFragment_to_consultantFragment)
        }
        btn_user.setOnClickListener {
            vm.mode=Mode.User
            findNavController().navigate(R.id.action_choiceFragment_to_userFragment)
        }

        return v
    }

}