package us.buddman.bubblepop.models

/**
 * Created by dudco on 2017. 9. 29..
 */
data class ChatRoom(
        val _id: String,
        val title: String,
        val lastChat: String,
        val lastChatTime: String,
        val thumbnail: String,
        val owner: String,
        val serial: String,
        val member: ArrayList<String>
)