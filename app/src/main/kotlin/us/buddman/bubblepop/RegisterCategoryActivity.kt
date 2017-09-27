package us.buddman.bubblepop

import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.support.v7.widget.GridLayoutManager
import com.github.nitrico.lastadapter.LastAdapter
import kotlinx.android.synthetic.main.activity_register_category.*
import us.buddman.bubblepop.databinding.ContentRegisterCategoryBinding

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
    override fun setDefault() {
        layoutManager = GridLayoutManager(applicationContext, 2)
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
                        likeArray[it.adapterPosition].isLike = !likeArray[it.adapterPosition].isLike
                        adapter!!.notifyDataSetChanged()
                    }
                }
                .into(registerCategoryRecyclerView)
    }

    override fun onCreateViewId(): Int {
        return R.layout.activity_register_category
    }

    override fun onCreateViewToolbarId(): Int {
        return 0
    }
}

data class LikeType(var title: String, var isLike: Boolean = false)