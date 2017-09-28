package us.buddman.bubblepop

import android.content.Intent
import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.support.v7.widget.GridLayoutManager
import android.widget.Toast
import com.github.nitrico.lastadapter.LastAdapter
import kotlinx.android.synthetic.main.activity_register_category.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import us.buddman.bubblepop.databinding.ContentRegisterCategoryBinding
import us.buddman.bubblepop.models.User
import us.buddman.bubblepop.utils.CredentialsManager
import us.buddman.bubblepop.utils.NetworkHelper

class RegisterCategoryActivity : BaseActivity() {
    var likeArray: ArrayList<LikeType> = arrayListOf(
            LikeType("교육"),
            LikeType("강연"),
            LikeType("세미나/컨퍼런스"),
            LikeType("문화/예술"),
            LikeType("취미활동"),
            LikeType("소모임/친목행사"),
            LikeType("공모전"),
            LikeType("전시/박람회"),
            LikeType("패션/뷰티"),
            LikeType("이벤트/파티"),
            LikeType("여행"),
            LikeType("후원금 모금"),
            LikeType("등산"),
            LikeType("봉사활동"),
            LikeType("공연/전시"),
            LikeType("음악/악기"),
            LikeType("낚시"),
            LikeType("차/오토바이"),
            LikeType("자전거"),
            LikeType("취미, 여가, 여행"),
            LikeType("사교/인맥"),
            LikeType("기타")
    )
    var layoutManager: GridLayoutManager? = null
    var adapter: LastAdapter? = null
    var count = 0
    var user: User? = null
    override fun setDefault() {
        setToolbarTitle("회원가입")
        layoutManager = GridLayoutManager(applicationContext, 2)
        user = intent.getSerializableExtra("userData") as User
        whoareu.text = user!!.nickname + "님은 \n무엇에 관심이 있나요?"
        registerCategoryRecyclerView.layoutManager = layoutManager
        adapter = LastAdapter(likeArray, BR.content)
                .map<LikeType, ContentRegisterCategoryBinding>(R.layout.content_register_category) {
                    onBind { holder ->
                        run {
                            holder.binding.registerContentTextView.setBackgroundResource(if (likeArray[holder.adapterPosition].isLike) R.drawable.round_blue_square else R.drawable.round_transparent_blue_square)
                            holder.binding.registerContentTextView.setTextColor(if (likeArray[holder.adapterPosition].isLike) Color.WHITE else ContextCompat.getColor(applicationContext, R.color.colorPrimary))
                        }
                    }
                    onClick {
                        if (likeArray[it.adapterPosition].isLike) {
                            count--
                            likeArray[it.adapterPosition].isLike = !likeArray[it.adapterPosition].isLike
                            adapter!!.notifyDataSetChanged()
                        } else {
                            if (count >= 5) {
                                Toast.makeText(applicationContext, "카테고리를 5개 이하로 설정해주세요!", Toast.LENGTH_SHORT).show()
                            } else {
                                count++
                                likeArray[it.adapterPosition].isLike = !likeArray[it.adapterPosition].isLike
                                adapter!!.notifyDataSetChanged()
                            }
                        }
                    }
                }
                .into(registerCategoryRecyclerView)
        finish.setOnClickListener {
            if (count == 0) Toast.makeText(applicationContext, "카테고리를 최소 1개 이상 선택해주세요.", Toast.LENGTH_SHORT).show()
            else {
                val likeList : ArrayList<Int> = ArrayList()
                (0..likeArray.size-1).filterTo(likeList) { likeArray[it].isLike }
                NetworkHelper.networkInstance.registerByLocal(
                        user!!.email,
                        user!!.password,
                        user!!.nickname,
                        user!!.age,
                        user!!.location,
                        likeList,
                        user!!.phone
                ).enqueue(object : Callback<User>{
                    override fun onResponse(call: Call<User>?, response: Response<User>?) {
                        when(response!!.code()){
                            200 -> run {
                                Toast.makeText(applicationContext, "회원가입에 성공했습니다!", Toast.LENGTH_SHORT).show()
                                finish()
                            }
                            else -> run {
                                Toast.makeText(applicationContext, "회원가입에 문제가 발생했습니다.", Toast.LENGTH_SHORT).show()
                                println(response.message())
                            }
                        }
                    }

                    override fun onFailure(call: Call<User>?, t: Throwable?) {
                        println(t!!.localizedMessage)
                    }

                })
            }
        }
    }

    override fun onCreateViewId(): Int {
        return R.layout.activity_register_category
    }

    override fun onCreateViewToolbarId(): Int {
        return R.id.toolbar
    }
}

data class LikeType(var title: String, var isLike: Boolean = false)