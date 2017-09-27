package us.buddman.bubblepop;

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.annotation.LayoutRes
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem

abstract class BaseActivity : AppCompatActivity() {

    lateinit var toolbar: Toolbar
    internal var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(onCreateViewId())
        if (onCreateViewToolbarId() != 0) {
            toolbar = findViewById(onCreateViewToolbarId()) as Toolbar
            setSupportActionBar(toolbar)
            toolbar.setTitleTextColor(Color.WHITE)
            toolbar.contentInsetStartWithNavigation = 0
            toolbar.setTitleTextAppearance(applicationContext, R.style.ActionBar_NameText)
            supportActionBar!!.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(applicationContext, R.color.colorPrimary)))
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = Color.parseColor("#5780e5")
            window.navigationBarColor = Color.BLACK
        }
        setDefault()
    }

    protected abstract fun setDefault()

    @LayoutRes
    protected abstract fun onCreateViewId(): Int

    @IdRes
    protected abstract fun onCreateViewToolbarId(): Int


    fun disableToggle() {
        this.supportActionBar!!.setDisplayHomeAsUpEnabled(false)
    }

    fun enableToggle() {
        this.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    fun setToolbarTitle(titleStr: String) {
        this.supportActionBar!!.title = titleStr
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
