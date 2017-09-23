package us.buddman.bubblepop.fragment

import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.v4.view.ViewCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.nitrico.lastadapter.LastAdapter
import kotlinx.android.synthetic.main.fragment_main_friends.*
import us.buddman.bubblepop.BR
import us.buddman.bubblepop.R
import us.buddman.bubblepop.databinding.FriendsContentBinding
import us.buddman.bubblepop.databinding.FriendsContentHeaderBinding
import us.buddman.bubblepop.models.User

/**
 * Created by Junseok on 2017-09-21.
 */
class MainFriendsFragment : Fragment() {

    var friendsList: ArrayList<Any> = ArrayList()
    var friendsAdapter: LastAdapter? = null
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater!!.inflate(R.layout.fragment_main_friends, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()

    }

    fun initialize() {
        friendsList.add(Self())
        for (i in 1..10) friendsList.add(
                User("kotohana5706@edcan.kr", "오준석", "http")
        )
        friendsRecyclerView.layoutManager = LinearLayoutManager(context)
        friendsAdapter = LastAdapter(friendsList, BR.content)
                .map<Self, FriendsContentHeaderBinding>(R.layout.friends_content_header) {
                    onBind { }
                }
                .map<User, FriendsContentBinding>(R.layout.friends_content) {
                    onBind { }
                }
                .into(friendsRecyclerView)
        ViewCompat.setNestedScrollingEnabled(friendsRecyclerView, false)
    }
}

class Self {
    var name = "오준석"
    var position = "Android Developer"
    var comment = "I want to go home"
    var imgLink = ""
}
