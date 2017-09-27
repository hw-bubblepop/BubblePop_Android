package us.buddman.bubblepop.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.github.nitrico.lastadapter.LastAdapter
import kotlinx.android.synthetic.main.fragment_main_info.*
import us.buddman.bubblepop.BR
import us.buddman.bubblepop.R
import us.buddman.bubblepop.SettingsActivity
import us.buddman.bubblepop.databinding.BubblemoaMoimContentBinding
import us.buddman.bubblepop.databinding.ContentSettingsBubblecreditBinding
import us.buddman.bubblepop.databinding.ContentSettingsHeaderBinding
import us.buddman.bubblepop.databinding.ContentSettingsNormalBinding
import us.buddman.bubblepop.models.Moim
import us.buddman.bubblepop.models.Study
import us.buddman.bubblepop.models.User
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by Junseok on 2017-09-21.
 */
class MainMyInfoFragment : Fragment() {

    var adapter: LastAdapter? = null
    var infoArray: ArrayList<Any> = ArrayList()
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater!!.inflate(R.layout.fragment_main_info, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        infoArray = arrayListOf(
                InfoContent("버블카드 편집", "내 버블카드를 편집합니다."),
                InfoContent("스터디 모임 및 관리", "내가 생성한 스터디나 모임을 확인하고 관리합니다."),
                InfoContent("스토어 보관함", "스토어에서 구매한 상품을 관리합니다."),
                InfoContent("결제 내역"),
                InfoContent("버블카드 리스트", "지금까지 만든 버블카드를 불러옵니다."),
                InfoContent("차단 목록"),
                InfoContent("로그아웃"),
                InfoContent("공지 및 이벤트", "BubblePop의 공지사항과 이벤트를 확인합니다.")
        )
        myInfoRecyclerView.layoutManager = LinearLayoutManager(context)
        adapter = LastAdapter(infoArray, BR.content)
                .map<User, ContentSettingsHeaderBinding>(R.layout.content_settings_header) {
                    onBind { }
                }
                .map<Integer, ContentSettingsBubblecreditBinding>(R.layout.content_settings_bubblecredit) {
                    onBind { }
                }
                .map<InfoContent, ContentSettingsNormalBinding>(R.layout.content_settings_normal) {
                    onBind {holder ->
                        holder.run {
                            if((infoArray[adapterPosition] as InfoContent).content.equals("")) holder.binding.infoContent.visibility = View.GONE
                        }
                    }
                }
                .into(myInfoRecyclerView)
    }

}

data class InfoContent(var title: String, var content: String = ""){
    constructor(title : String) : this(title, "")
}