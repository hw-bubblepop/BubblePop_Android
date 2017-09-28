package us.buddman.bubblepop.fragment

import android.content.Intent
import android.net.Uri
import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.v4.view.ViewCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.nitrico.lastadapter.LastAdapter
import kotlinx.android.synthetic.main.fragment_main_friends.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import us.buddman.bubblepop.BR
import us.buddman.bubblepop.FriendsInfoActivity
import us.buddman.bubblepop.R
import us.buddman.bubblepop.databinding.FriendsContentBinding
import us.buddman.bubblepop.databinding.FriendsContentHeaderBinding
import us.buddman.bubblepop.models.User
import us.buddman.bubblepop.utils.CredentialsManager
import us.buddman.bubblepop.utils.NetworkHelper
import us.buddman.bubblepop.utils.TextUtils

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
        friendsRecyclerView.layoutManager = LinearLayoutManager(context)
        friendsList.add(Self(CredentialsManager.instance.activeUser.second))
        NetworkHelper.networkInstance.findMyFriend(CredentialsManager.instance.activeUser.second._id).enqueue(object : Callback<ArrayList<User>> {
            override fun onResponse(call: Call<ArrayList<User>>, response: Response<ArrayList<User>>) {
                friendsList.addAll(response.body()!!)
                friendsAdapter = LastAdapter(friendsList, BR.content)
                        .map<Self, FriendsContentHeaderBinding>(R.layout.friends_content_header) {
                            onBind {
                                it.binding.friendsContentHeaderImage.setImageURI(
                                        Uri.parse(TextUtils.getServerString((friendsList[it.adapterPosition] as Self).user.cards[0])), context)
                            }
                            onClick {
                                startActivity(Intent(context, FriendsInfoActivity::class.java)
                                        .putExtra("userData", (friendsList[it.adapterPosition] as Self).user))
                            }
                        }
                        .map<User, FriendsContentBinding>(R.layout.friends_content) {
                            onBind {
                                it.binding.friendsContentHeaderImage.setImageURI(
                                        Uri.parse(TextUtils.getServerString((friendsList[it.adapterPosition] as User).cards[0])), context)
                            }
                            onClick {
                                startActivity(Intent(context, FriendsInfoActivity::class.java)
                                        .putExtra("userData", friendsList[it.adapterPosition] as User))
                            }
                        }
                        .into(friendsRecyclerView)

            }

            override fun onFailure(call: Call<ArrayList<User>>, t: Throwable) {
                println(t!!.message)
            }

        })
        ViewCompat.setNestedScrollingEnabled(friendsRecyclerView, false)
    }

    fun onPageResume(){
        friendsList.clear()
        friendsList.add(Self(CredentialsManager.instance.activeUser.second))
        NetworkHelper.networkInstance.findMyFriend(CredentialsManager.instance.activeUser.second._id).enqueue(object : Callback<ArrayList<User>> {
            override fun onResponse(call: Call<ArrayList<User>>, response: Response<ArrayList<User>>) {
                friendsList.addAll(response.body()!!)
                friendsAdapter!!.notifyDataSetChanged()

            }
            override fun onFailure(call: Call<ArrayList<User>>, t: Throwable) {
                println(t!!.message)
            }
        })
    }

}

data class Self(var user: User)
