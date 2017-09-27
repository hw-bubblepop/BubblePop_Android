package us.buddman.bubblepop.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout.HORIZONTAL
import com.github.nitrico.lastadapter.LastAdapter
import kotlinx.android.synthetic.main.fragment_main_bubblemoa.*
import kotlinx.android.synthetic.main.fragment_main_story.*
import us.buddman.bubblepop.BR
import us.buddman.bubblepop.R
import us.buddman.bubblepop.SettingsActivity
import us.buddman.bubblepop.databinding.BubblemoaMoimContentBinding
import us.buddman.bubblepop.models.Moim
import us.buddman.bubblepop.models.Study
import java.util.*

/**
 * Created by Junseok on 2017-09-21.
 */
class MainBubbleMoaFragment : Fragment() {

    var adapter: FragmentStatePagerAdapter? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater!!.inflate(R.layout.fragment_main_bubblemoa, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = BubbleMoaPager(fragmentManager)
        mainBubbleMoaPager.adapter = adapter
        mainBubbleMoaTabLayout.setupWithViewPager(mainBubbleMoaPager)
    }

}

class BubbleMoaPager(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return if (position == 0) StoryOpenChatListFragment() else StoryChatListFragment()
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence {
        return if (position == 0) "버블" else "채팅"
    }
}