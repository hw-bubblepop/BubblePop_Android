package us.buddman.bubblepop;

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import us.buddman.bubblepop.fragment.*

class MainActivity : BaseActivity() {

    override fun setDefault() {
        mainPager.adapter = MainPager(supportFragmentManager)
    }

    override fun onCreateViewId(): Int {
        return R.layout.activity_main
    }

    override fun onCreateViewToolbarId(): Int {
        return 0
    }

    class MainPager(fm: FragmentManager): FragmentStatePagerAdapter(fm){
        override fun getItem(position: Int): Fragment? {
            return when (position){
                0 -> MainBubbleMoaFragment()
                1 -> MainFriendsFragment()
                2 -> MainBubbleCardFragment()
                3 -> MainStoryFragment()
                4 -> MainStoreFragment()
                else -> null
            }
        }

        override fun getCount(): Int{
            return 5
        }

        override fun getPageTitle(position: Int): CharSequence {
            return ""
        }
    }

}
