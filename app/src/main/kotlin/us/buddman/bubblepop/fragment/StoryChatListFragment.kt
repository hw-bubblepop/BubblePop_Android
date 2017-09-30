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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import us.buddman.bubblepop.R
import us.buddman.bubblepop.StoryChatActivity
import us.buddman.bubblepop.databinding.ChatContentBinding
import us.buddman.bubblepop.models.ChatRoom
import us.buddman.bubblepop.utils.ChatUtil
import us.buddman.bubblepop.utils.CredentialsManager
import us.buddman.bubblepop.utils.NetworkHelper

/**
 * Created by Junseok on 2017-09-21.
 */
class StoryChatListFragment : Fragment() {

    var chatAdapter: LastAdapter? = null
    var chatArray: ArrayList<ChatRoom> = ArrayList()
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater!!.inflate(R.layout.fragment_story_chatlist, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        storyChatRecyclerView.layoutManager = LinearLayoutManager(context)
        initialize()

        addChat.setOnClickListener {
            ChatUtil.instance.send("click public chat add ")
        }
    }

    fun initialize() {
        NetworkHelper.networkInstance.getChatList(CredentialsManager.instance.activeUser.second._id).enqueue(object : Callback<ArrayList<ChatRoom>> {
            override fun onResponse(call: Call<ArrayList<ChatRoom>>?, response: Response<ArrayList<ChatRoom>>?) {
                chatArray.addAll(response!!.body()!!)
                chatAdapter = LastAdapter(chatArray, BR.content)
                        .map<ChatRoom, ChatContentBinding>(R.layout.chat_content) {
                            onBind {

                            }
                            onClick { startActivity(Intent(context, StoryChatActivity::class.java)) }
                        }
                        .into(storyChatRecyclerView)
            }

            override fun onFailure(call: Call<ArrayList<ChatRoom>>?, t: Throwable?) {
            }

        })
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