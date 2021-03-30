package com.first.testchat.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.first.testchat.*


class ChatFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_chat, container, false)
        val send_btn = v.findViewById<Button>(R.id.btn_send)
        val send_msg = v.findViewById<EditText>(R.id.send_msg)
        val rv_list = v.findViewById<RecyclerView>(R.id.rvlist)
        val vm = ViewModelProvider(requireActivity()).get(ViewModel::class.java)
        val adapter = Adapter()
        rv_list.adapter = adapter

        if (vm.mode == Mode.User) {
            vm.getConsultant {
                Handler(Looper.getMainLooper()).postDelayed({
                    showDialog(it)
                }, (it.minute * 60 * 1000).toLong())
            }
        }

        send_btn.setOnClickListener {
            val m = Message(send_msg.text.toString())
            vm.sendMsg(m)
        }

        vm.data.observe(viewLifecycleOwner, {
            adapter.setData(it)
        })

        return v
    }

    private fun showDialog(c: Consultant) {
        AlertDialog.Builder(activity).setTitle("бесплатное время закончена.")
            .setMessage(" для продолжение нужно платить ${c.prise} за час")
            .setCancelable(
                false
            )
            .setPositiveButton("оплатить") { _, _ ->
                updatePrise(c.prise)

            }.setNegativeButton("прервать")
            { _, _ -> findNavController().navigate(R.id.action_chatFragment_to_choiceFragment) }
            .create().show()
    }

    private fun updatePrise(prise: Float) {
        var p = 0f
        val h = Handler(Looper.getMainLooper())
        val updateTextTask = object : Runnable {
            override fun run() {
                p+=(prise.div(60).div(60))
                requireActivity().title = "$ $p"
                h.postDelayed(this, 1000)
            }
        }
        h.post(updateTextTask)
    }

}