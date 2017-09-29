package us.buddman.bubblepop

import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.github.nitrico.lastadapter.LastAdapter
import com.github.nitrico.lastadapter.LayoutHandler
import kotlinx.android.synthetic.main.activity_story_chat.*
import kotlinx.android.synthetic.main.fragment_story_chatlist.*
import us.buddman.bubblepop.databinding.ChatInsideContentBinding
import us.buddman.bubblepop.models.User
import us.buddman.bubblepop.utils.ChatUtil
import us.buddman.bubblepop.utils.CredentialsManager
import java.util.*
import kotlin.collections.ArrayList

class StoryChatActivity : BaseActivity() {

    var chatArray: ArrayList<Chat> = ArrayList()
    var adapter: LastAdapter? = null
    override fun setDefault() {
        chatInsideRV.layoutManager = LinearLayoutManager(applicationContext)
        adapter = LastAdapter(chatArray, BR.content)
                .map<Chat, ChatInsideContentBinding>(R.layout.chat_inside_content) {
                    onBind { }
                }
                .into(chatInsideRV)
        send.setOnClickListener {
            onSend()
        }
        ChatUtil.instance.setOnReceiveListener(object: ChatUtil.OnReceiveListener{
            override fun onReceive(msg: String) {
                val c = Chat()
                Log.d("dudco", msg)
                val m = msg.split('/')
                if(m[0] == "msg"){
                    c.content = m[3]
                    c.fromMe = m[2] == CredentialsManager.instance.activeUser.second.nickname
                    c.time = m[1]
                    c.user = m[2]

                    receive(c)
                }
            }
        })
    }

    fun receive(chat : Chat){
        chatArray.add(chat)
        adapter!!.notifyItemInserted(chatArray.size)
        chatInsideRV.scrollToPosition(chatArray.size)
    }
    fun onSend(){
//        var newChat = Chat(true, CredentialsManager.instance., chatInput.text.toString(), Date(System.currentTimeMillis()))
//        chatArray.add(newChat)
//        adapter!!.notifyItemInserted(chatArray.size)
//        chatInsideRV.scrollToPosition(chatArray.size)
        ChatUtil.instance.send(chatInput.text.toString())
    }
    override fun onCreateViewId(): Int {
        return R.layout.activity_story_chat
    }

    override fun onCreateViewToolbarId(): Int {
        return 0
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.search -> {}
            R.id.menu -> {

            }
        }
        return super.onOptionsItemSelected(item)
    }
}

class Chat() {
    var fromMe: Boolean? = null
    var user: String? = null
    var content: String? = null
    var time: String? = null

    constructor(fromMe: Boolean, user: String, content: String, time: String) : this(){
        this.fromMe = fromMe
        this.user = user
        this.content = content
        this.time = time
    }


    var timeStr: String = ""
        get() = time.toString()
}