package com.first.testchat

import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class Repository {
    val instance = FirebaseDatabase.getInstance();

    fun saveUser(u: User) {
        instance.getReference("data/user").child(u.tel)
            .setValue(mapOf("telConsultant" to u.telConsultant, "tel" to u.tel, "name" to u.name))
    }

    fun getConsultant(tel: String, OnResult: (c: Consultant) -> Unit) {
        instance.getReference("data/consultant").child(tel)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    snapshot.getValue(Consultant::class.java)?.let {
                        OnResult(it)
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                }
            })
    }

    fun saveConsultant(c: Consultant) {
        instance.getReference("data/consultant").child(c.telConsultant)
            .setValue(
                mapOf(
                    "telConsultant" to c.telConsultant,
                    "subject" to c.subject,
                    "name" to c.name,
                    "prise" to c.prise,
                    "minute" to c.minute
                )
            )
    }

    fun get(onResult: (MutableLiveData<MutableList<Message>>) -> Unit, tel: String) {
        val data: MutableLiveData<MutableList<Message>> = MutableLiveData()
        instance.getReference("data/messages").child(tel)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val d: MutableList<Message> = ArrayList<Message>()

                        for (p in snapshot.children)
                            d.add(Message(p.getValue(String::class.java)!!))

                    data.value = d
                    onResult(data)
                }

                override fun onCancelled(error: DatabaseError) {}
            })
    }

    fun send_msg(msg: Message, tel: String) {
        instance.getReference("data/messages").child(tel).push().setValue(msg.text)
    }
}
