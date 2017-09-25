package us.buddman.bubblepop;

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import us.buddman.bubblepop.fragment.*

class MainActivity : BaseActivity() {

    lateinit var pagerAdapter: MainPagerAdapter
    override fun setDefault() {
        pagerAdapter = MainPagerAdapter(supportFragmentManager)
        mainPager.adapter = pagerAdapter
        mainPager.offscreenPageLimit = 5
        mainBottomBar.setOnTabSelectListener { tabId: Int ->
            when (tabId) {
                R.id.main_bubblemoa -> mainPager.currentItem = 0
                R.id.main_friends -> mainPager.currentItem = 1
                R.id.main_bubblecard -> mainPager.currentItem = 2
                R.id.main_story -> mainPager.currentItem = 3
                R.id.main_store -> mainPager.currentItem = 4
            }
        }
    }

    override fun onCreateViewId(): Int {
        return R.layout.activity_main
    }

    override fun onCreateViewToolbarId(): Int {
        return 0
    }

    class MainPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
        override fun getItem(position: Int): Fragment? {
            return when (position) {
                0 -> MainBubbleMoaFragment()
                1 -> MainFriendsFragment()
                2 -> MainBubbleCardFragment()
                3 -> MainStoryFragment()
                4 -> MainStoreFragment()
                else -> null
            }
        }

        override fun getCount(): Int {
            return 5
        }

        override fun getPageTitle(position: Int): CharSequence {
            return ""
        }
    }

}
