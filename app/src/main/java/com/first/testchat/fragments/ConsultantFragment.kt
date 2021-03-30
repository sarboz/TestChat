package com.first.testchat.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.first.testchat.Consultant
import com.first.testchat.R
import com.first.testchat.ViewModel
import com.google.android.material.textfield.TextInputEditText


class ConsultantFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_consultant, container, false)
        val btn_next = v.findViewById<Button>(R.id.btn_next)

        btn_next.setOnClickListener {
            val telConsultant =
                v.findViewById<TextInputEditText>(R.id.telConsultant).text.toString()
            val name = v.findViewById<TextInputEditText>(R.id.name).text.toString()
            val subject = v.findViewById<TextInputEditText>(R.id.subject).text.toString()
            val minute = v.findViewById<TextInputEditText>(R.id.minute).text.toString()
            val prise = v.findViewById<TextInputEditText>(R.id.prise).text.toString()

            val c = Consultant(telConsultant, subject, name, prise.toFloat(), minute.toInt());
            val vm = ViewModelProvider(requireActivity()).get(ViewModel::class.java)

            vm.addNewConsultant(c)
            findNavController().navigate(R.id.action_consultantFragment_to_chatFragment)
        }

        return v
    }

}