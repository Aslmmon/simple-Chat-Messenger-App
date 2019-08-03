package com.example.sorsorchat.features.chat_log


interface ChatLogContract {

    interface Presenter{
        fun listenToMessages()
    }

    interface View{
        fun passTextToChatFrom(text:String)
        fun passTextToChatTo(text:String)
    }
}
