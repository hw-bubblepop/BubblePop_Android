package us.buddman.bubblepop

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : BaseActivity() {
    override fun setDefault() {
        setToolbarTitle("회원가입")
        next.setOnClickListener{
            Toast.makeText(applicationContext, "서버가 꺼져있습니다.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateViewId(): Int {
        return R.layout.activity_register
    }

    override fun onCreateViewToolbarId(): Int {
        return R.id.toolbar
    }
}