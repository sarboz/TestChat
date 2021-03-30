package com.first.testchat

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModel : ViewModel() {
    private val repos: Repository = Repository()
    val data: MutableLiveData<MutableList<Message>> = MutableLiveData()
    private  var tel =""
    var mode:Mode=Mode.Empty

    fun addNewUser(u: User) {
        tel= u.telConsultant
        get()
        repos.saveUser(u)
    }
    fun addNewConsultant(c: Consultant) {
        tel= c.telConsultant
        get()
        repos.saveConsultant(c)
    }
    fun get(){
        repos.get( {
            data.value = it.value
        },tel)
    }
    fun sendMsg(msg: Message) {
        repos.send_msg(msg,tel)
    }

    fun getConsultant(OnResult:(c:Consultant)->Unit){
        repos.getConsultant(tel) {
            OnResult(it)
        }
    }

}