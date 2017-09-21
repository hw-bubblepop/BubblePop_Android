package us.buddman.bubblepop

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MyProfileSettingsActivity : BaseActivity() {


    override fun setDefault() {
    }

    override fun onCreateViewId(): Int {
        return R.layout.activity_my_profile_settings
    }

    override fun onCreateViewToolbarId(): Int {
        return 0
    }
}
