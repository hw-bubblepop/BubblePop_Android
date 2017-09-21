package us.buddman.bubblepop;

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun setDefault() {
        text.text = "hello world"
        disableToggle()

    }

    override fun onCreateViewId(): Int {
        return R.layout.activity_main
    }

    override fun onCreateViewToolbarId(): Int {
        return 0
    }

}
