package us.buddman.bubblepop.models

/**
 * Created by Junseok on 2017-09-29.
 */
data class ChatRoom(
        val _id: String,
        val title: String,
        val lastChat: String,
        val lastChatTime: String,
        val thumbnail: String,
        val owner: String,
        val serial: String,
        val member: ArrayList<User>
){
    var memberString : String = ""
        get() {
            for(s in member){
                field += s.nickname + " "
            }
            return field
        }
}