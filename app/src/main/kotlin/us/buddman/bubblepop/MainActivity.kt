package us.buddman.bubblepop;

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_main_story.*
import us.buddman.bubblepop.fragment.*
import us.buddman.bubblepop.utils.ChatUtil
import us.buddman.bubblepop.utils.CredentialsManager

class MainActivity : BaseActivity() {

    lateinit var pagerAdapter: MainPagerAdapter
    override fun setDefault() {
        pagerAdapter = MainPagerAdapter(supportFragmentManager)
        mainPager.adapter = pagerAdapter
        mainPager.offscreenPageLimit = 4
        mainBottomBar.setOnTabSelectListener { tabId: Int ->
            when (tabId) {
                R.id.main_bubblemoa -> mainPager.currentItem = 0
                R.id.main_friends -> mainPager.currentItem = 1
                R.id.main_bubblecard -> mainPager.currentItem = 2
                R.id.main_store -> mainPager.currentItem = 3
                R.id.main_more -> mainPager.currentItem = 4
            }
        }
        mainPager.currentItem = 2
        mainBottomBar.setDefaultTab(R.id.main_bubblecard)

        ChatUtil.instance
    }
    open fun updateFriendList(){
        (pagerAdapter.instantiateItem(mainPager, 1) as MainFriendsFragment).onPageResume()
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
                3 -> MainStoreFragment()
                4 -> MainMyInfoFragment()
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
