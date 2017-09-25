package us.buddman.bubblepop.fragment

import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.nitrico.fontbinder.BR
import com.github.nitrico.lastadapter.LastAdapter
import kotlinx.android.synthetic.main.fragment_story_openchatlist.*
import us.buddman.bubblepop.R
import us.buddman.bubblepop.databinding.ChatContentBinding

/**
 * Created by Junseok on 2017-09-21.
 */
class StoryOpenChatListFragment : Fragment() {

    var openChatAdapter: LastAdapter? = null
    var openChatArray: ArrayList<Chat> = ArrayList()
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater!!.inflate(R.layout.fragment_story_openchatlist, container, false)

    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        storyOpenChatRecyclerView.layoutManager = LinearLayoutManager(context)
        openChatAdapter = LastAdapter(openChatArray, BR.content)
                .map<Chat, ChatContentBinding>(R.layout.chat_content) {
                    onBind { }
                }
                .into(storyOpenChatRecyclerView)
    }

    fun initialize() {
        openChatArray.run {
            for (i in 1..10)
                add(Chat("건축학 공부 그룹", "레드스톤 있으신분?", 5))
        }
    }
}