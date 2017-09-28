package us.buddman.bubblepop

import android.content.Intent
import android.net.Network
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_auth.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import us.buddman.bubblepop.models.User
import us.buddman.bubblepop.utils.CredentialsManager
import us.buddman.bubblepop.utils.NetworkHelper

class AuthActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        if(CredentialsManager.instance.activeUser.first){
            NetworkHelper.networkInstance.loginByAuto(CredentialsManager.instance.activeUser.second._id).enqueue(object : Callback<User> {
                override fun onResponse(call: Call<User>?, response: Response<User>?) {
                    when(response!!.code()){
                        200 -> run {
                            CredentialsManager.instance.saveUserInfo(response.body()!!)
                            Toast.makeText(applicationContext, response.body()!!.nickname + "님 환영합니다.", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(applicationContext, MainActivity::class.java))
                            finish()
                        }
                        else -> run {
                            Toast.makeText(applicationContext, "세션이 만료되었습니다.", Toast.LENGTH_SHORT).show()
                            println(response.message())
                            loginForm.visibility = View.VISIBLE
                        }
                    }
                }

                override fun onFailure(call: Call<User>?, t: Throwable?) {
                    Toast.makeText(applicationContext, "로그인에 문제가 발생했습니다.", Toast.LENGTH_SHORT).show()
                    println(t!!.localizedMessage)
                }


            })
        } else loginForm.visibility = View.VISIBLE

        register.setOnClickListener {
            startActivity(Intent(applicationContext, RegisterActivity::class.java))
        }
        login.setOnClickListener {
            NetworkHelper.networkInstance.loginByLocal(
                    emailInput.text.toString(),
                    passwordInput.text.toString()
            ).enqueue(object : Callback<User> {
                override fun onResponse(call: Call<User>?, response: Response<User>?) {
                    when(response!!.code()){
                        200 -> run {
                            CredentialsManager.instance.saveUserInfo(response.body()!!)
                            Toast.makeText(applicationContext, response.body()!!.nickname + "님 환영합니다.", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(applicationContext, MainActivity::class.java))
                            finish()
                        }
                        else -> run {
                            Toast.makeText(applicationContext, "로그인에 문제가 발생했습니다.", Toast.LENGTH_SHORT).show()
                            println(response.message())
                        }
                    }
                }

                override fun onFailure(call: Call<User>?, t: Throwable?) {
                    Toast.makeText(applicationContext, "로그인에 문제가 발생했습니다.", Toast.LENGTH_SHORT).show()
                    println(t!!.localizedMessage)
                }

            })
        }
    }
}
