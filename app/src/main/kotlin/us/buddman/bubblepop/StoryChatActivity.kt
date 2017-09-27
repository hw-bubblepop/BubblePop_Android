package us.buddman.bubblepop

import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.github.nitrico.lastadapter.LastAdapter
import com.github.nitrico.lastadapter.LayoutHandler
import kotlinx.android.synthetic.main.activity_story_chat.*
import kotlinx.android.synthetic.main.fragment_story_chatlist.*
import us.buddman.bubblepop.databinding.ChatInsideContentBinding
import us.buddman.bubblepop.models.User
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

    }

    fun onReceive(chat : Chat){
        chatArray.add(chat)
        adapter!!.notifyItemInserted(chatArray.size)
        chatInsideRV.scrollToPosition(chatArray.size)
    }
    fun onSend(){
        var newChat = Chat(true, User("email@email.com", "Junseok"), chatInput.text.toString(), Date(System.currentTimeMillis()))
        chatArray.add(newChat)
        adapter!!.notifyItemInserted(chatArray.size)
        chatInsideRV.scrollToPosition(chatArray.size)
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

data class Chat(
        var fromMe: Boolean,
        var user: User,
        var content: String,
        var time: Date
) {
    var timeStr: String = ""
        get() = time.toString()
}