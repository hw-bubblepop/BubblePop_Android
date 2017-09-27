package us.buddman.bubblepop

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_auth.*

class AuthActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        register.setOnClickListener {
            startActivity(Intent(applicationContext, RegisterActivity::class.java))
        }
        login.setOnClickListener {
            Toast.makeText(applicationContext, "서버가 꺼져있습니다.", Toast.LENGTH_SHORT).show()
        }
    }
}
