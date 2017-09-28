package us.buddman.bubblepop.fragment

import android.support.v4.app.Fragment
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import kotlinx.android.synthetic.main.fragment_main_bubblecard.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import us.buddman.bubblepop.R
import us.buddman.bubblepop.models.User
import us.buddman.bubblepop.utils.CredentialsManager
import us.buddman.bubblepop.utils.NetworkHelper

/**
 * Created by Junseok on 2017-09-21.
 */
class MainBubbleCardFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater!!.inflate(R.layout.fragment_main_bubblecard, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        findByPhone.setOnClickListener {
            MaterialDialog.Builder(activity)
                    .title("전화번호로 추가하기")
                    .input("전화번호 입력...", "", false) { dialog, input ->
                        run {
                            NetworkHelper.networkInstance.findUserByPhone(input.toString().replace("-", "")).enqueue(object : Callback<User> {
                                override fun onResponse(call: Call<User>?, friendresponse: Response<User>?) {
                                    when (friendresponse!!.code()) {
                                        200 -> run {
                                            NetworkHelper.networkInstance.addFriend(
                                                    CredentialsManager.instance.activeUser.second._id,
                                                    friendresponse.body()!!._id)
                                                    .enqueue(object : Callback<User> {
                                                        override fun onResponse(call: Call<User>?, response: Response<User>?) {
                                                            when (response!!.code()) {
                                                                200 -> run {
                                                                    Toast.makeText(context, friendresponse.body()!!.nickname + " 님이 친구로 추가되었습니다", Toast.LENGTH_SHORT).show()
                                                                }
                                                                409 -> {
                                                                    Toast.makeText(context, friendresponse.body()!!.nickname + " 님은 이미 친구입니다.", Toast.LENGTH_SHORT).show()
                                                                }
                                                                else ->
                                                                    Toast.makeText(context, "친구를 추가할 수 없습니다.", Toast.LENGTH_SHORT).show()
                                                            }
                                                        }

                                                        override fun onFailure(call: Call<User>?, t: Throwable?) {
                                                            println(t!!.localizedMessage)
                                                        }
                                                    })
                                        }
                                        else -> Toast.makeText(context, "해당 번호로 친구를 찾을 수 없습니다.", Toast.LENGTH_SHORT).show()
                                    }
                                }

                                override fun onFailure(call: Call<User>?, t: Throwable?) {
                                    println(t!!.localizedMessage)
                                }
                            })
                        }
                    }
                    .inputType(InputType.TYPE_CLASS_PHONE)
                    .show()
        }
        findByEmail.setOnClickListener {
            MaterialDialog.Builder(activity)
                    .title("이메일로 추가하기")
                    .input("이메일 입력...", "", false) { dialog, input ->
                        run {
                            NetworkHelper.networkInstance.findUserByEmail(input.toString()).enqueue(object : Callback<User> {
                                override fun onResponse(call: Call<User>?, friendresponse: Response<User>?) {
                                    when (friendresponse!!.code()) {
                                        200 -> run {
                                            NetworkHelper.networkInstance.addFriend(
                                                    CredentialsManager.instance.activeUser.second._id,
                                                    friendresponse.body()!!._id)
                                                    .enqueue(object : Callback<User> {
                                                        override fun onResponse(call: Call<User>?, response: Response<User>?) {
                                                            when (response!!.code()) {
                                                                200 -> run {
                                                                    Toast.makeText(context, friendresponse.body()!!.nickname + " 님이 친구로 추가되었습니다", Toast.LENGTH_SHORT).show()
                                                                }
                                                                409 -> {
                                                                    Toast.makeText(context, friendresponse.body()!!.nickname + " 님은 이미 친구입니다.", Toast.LENGTH_SHORT).show()
                                                                }
                                                                else ->
                                                                    Toast.makeText(context, "친구를 추가할 수 없습니다.", Toast.LENGTH_SHORT).show()
                                                            }
                                                        }

                                                        override fun onFailure(call: Call<User>?, t: Throwable?) {
                                                            println(t!!.localizedMessage)
                                                        }
                                                    })
                                        }
                                        else -> Toast.makeText(context, "해당 이메일로 친구를 찾을 수 없습니다.", Toast.LENGTH_SHORT).show()
                                    }
                                }

                                override fun onFailure(call: Call<User>?, t: Throwable?) {
                                    println(t!!.localizedMessage)
                                }
                            })
                        }
                    }
                    .inputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS)
                    .show()
        }
    }
}