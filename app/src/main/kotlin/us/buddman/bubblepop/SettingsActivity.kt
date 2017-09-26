package us.buddman.bubblepop

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class SettingsActivity : BaseActivity() {


    override fun setDefault() {
        startActivity(Intent(applicationContext, MyProfileSettingsActivity::class.java))
    }

    override fun onCreateViewId(): Int {
        return R.layout.activity_settings
    }

    override fun onCreateViewToolbarId(): Int {
        return 0
    }
}
