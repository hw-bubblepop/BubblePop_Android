package us.buddman.bubblepop;

import android.content.Intent
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_main_story.*
import us.buddman.bubblepop.fragment.*
import us.buddman.bubblepop.utils.ChatUtil

class MainActivity : BaseActivity() {

    lateinit var pagerAdapter: MainPagerAdapter
    var isLoaded : Boolean = false

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
        isLoaded = true

        ChatUtil.instance
    }
    open fun updateFriendList(){
        (pagerAdapter.instantiateItem(mainPager, 1) as MainFriendsFragment).onPageResume()
    }

    fun startCardEdit(){
        startActivityForResult(Intent(this@MainActivity, MakeCardActivity::class.java), 6974)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 6974 && resultCode == RESULT_OK){
            (pagerAdapter.instantiateItem(mainPager, 1) as MainFriendsFragment).onPageResume()
            (pagerAdapter.instantiateItem(mainPager, 2) as MainBubbleCardFragment).onPageResume()
            (pagerAdapter.instantiateItem(mainPager, 4) as MainMyInfoFragment).onPageResume()
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
