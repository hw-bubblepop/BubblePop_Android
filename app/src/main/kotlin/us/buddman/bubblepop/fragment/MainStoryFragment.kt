package us.buddman.bubblepop.fragment

import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_main_story.*
import us.buddman.bubblepop.R

/**
 * Created by Junseok on 2017-09-21.
 */
class MainStoryFragment : Fragment() {

    var adapter: FragmentStatePagerAdapter? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater!!.inflate(R.layout.fragment_main_story, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ChatPagerAdapter(activity.supportFragmentManager)
        mainStoryViewPager.adapter = adapter
        mainStoryTabLayout.setupWithViewPager(mainStoryViewPager)
    }
}

class ChatPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return if (position == 0) StoryChatListFragment() else StoryOpenChatListFragment()
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence {
        return if (position == 0) "채팅" else "공개 채팅"
    }
}