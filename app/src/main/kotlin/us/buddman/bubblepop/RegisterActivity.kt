package us.buddman.bubblepop

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class RegisterActivity : BaseActivity() {
    override fun setDefault() {
    }

    override fun onCreateViewId(): Int {
        return R.layout.activity_register
    }

    override fun onCreateViewToolbarId(): Int {
        return 0
    }
}