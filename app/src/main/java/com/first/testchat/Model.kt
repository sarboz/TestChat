package com.first.testchat

data class Message(val text:String)
data class Consultant(val telConsultant:String="", val subject:String="", val name :String="", val prise:Float= 0F, val minute:Int=0)
data class User(val telConsultant:String,val tel:String,val name :String)
enum class Mode{
    User,Consultant,Empty
}