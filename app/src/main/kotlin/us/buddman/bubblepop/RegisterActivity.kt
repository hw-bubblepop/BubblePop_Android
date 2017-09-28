package us.buddman.bubblepop

import android.content.Intent
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_register.*
import us.buddman.bubblepop.models.User
import us.buddman.bubblepop.utils.TextUtils

class RegisterActivity : BaseActivity() {
    var user: User = User()
    override fun setDefault() {
        setToolbarTitle("회원가입")
        next.setOnClickListener {
            if (TextUtils.isFilled(nameInput, emailInput, pwInput, ageInput)) {
                user.nickname = nameInput.text.toString()
                user.email = emailInput.text.toString()
                user.password = pwInput.text.toString()
                user.age = Integer.parseInt(ageInput.text.toString())
                startActivity(Intent(applicationContext, RegisterInfoActivity::class.java)
                        .putExtra("userData", user))
                finish()
            } else Toast.makeText(applicationContext, "빈칸을 모두 입력해주세요.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateViewId(): Int {
        return R.layout.activity_register
    }

    override fun onCreateViewToolbarId(): Int {
        return R.id.toolbar
    }

}