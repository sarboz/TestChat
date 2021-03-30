package com.first.testchat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter : RecyclerView.Adapter<Adapter.Holder>() {

    var list:MutableList<Message> = ArrayList()

    class Holder(v: View) : RecyclerView.ViewHolder(v) {
        var msg: TextView = v.findViewById(R.id.msg)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return Holder(v)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.msg.text = list[position].text
    }

    fun setData(data: MutableList<Message>) {
        list = data
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return list.size
    }
}