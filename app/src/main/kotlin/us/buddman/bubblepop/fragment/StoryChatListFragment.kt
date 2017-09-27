package us.buddman.bubblepop.fragment

import android.content.Intent
import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.nitrico.fontbinder.BR
import com.github.nitrico.lastadapter.LastAdapter
import kotlinx.android.synthetic.main.fragment_story_chatlist.*
import us.buddman.bubblepop.R
import us.buddman.bubblepop.StoryChatActivity
import us.buddman.bubblepop.databinding.ChatContentBinding

/**
 * Created by Junseok on 2017-09-21.
 */
class StoryChatListFragment : Fragment() {

    var chatAdapter: LastAdapter? = null
    var chatArray: ArrayList<Chat> = ArrayList()
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater!!.inflate(R.layout.fragment_story_chatlist, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        storyChatRecyclerView.layoutManager = LinearLayoutManager(context)
        chatAdapter = LastAdapter(chatArray, BR.content)
                .map<Chat, ChatContentBinding>(R.layout.chat_content) {
                    onBind { }
                    onClick { startActivity(Intent(context, StoryChatActivity::class.java)) }
                }
                .into(storyChatRecyclerView)
    }

    fun initialize() {
        chatArray.run {
            for (i in 1..10)
                add(Chat("건축학 공부 그룹", "레드스톤 있으신분?", 5))
        }
    }
}

data class Chat(
        val title: String,
        val content: String,
        val newMessage: Int
) {
    var newMessageStr: String = ""
        get() = newMessage.toString()
}