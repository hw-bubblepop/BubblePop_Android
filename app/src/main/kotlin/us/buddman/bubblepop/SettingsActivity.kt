package us.buddman.bubblepop

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.github.nitrico.lastadapter.LastAdapter
import kotlinx.android.synthetic.main.activity_settings.*
import us.buddman.bubblepop.databinding.SettingsContentBinding

class SettingsActivity : BaseActivity() {

    var settingsArray: ArrayList<SettingsContent> = arrayListOf(
            SettingsContent("버블카드 편집", "내 버블카드를 편집합니다."),
            SettingsContent("프로필 편집", "프로필 사진, 정보 등을 편집합니다."),
            SettingsContent("스터디 및 모임 관리", "내가 생성한 스터디나 모임을 확인하고 관리합니다."),
            SettingsContent("스토어 보관함", "스토어에서 구매한 상품을 관리합니다."),
            SettingsContent("공지사항 및 이벤트", "BubblePop의 공지사항과 이벤트를 확인합니다.")
    )
    var adapter: LastAdapter? = null
    override fun setDefault() {
        settingsRecyclerView.layoutManager = LinearLayoutManager(applicationContext)
        adapter = LastAdapter(settingsArray, BR.content)
                .map<Pair<String, String>, SettingsContentBinding>(R.layout.settings_content) {
                    onBind { }
                }
                .into(settingsRecyclerView)

    }

    override fun onCreateViewId(): Int {
        return R.layout.activity_settings
    }

    override fun onCreateViewToolbarId(): Int {
        return 0
    }
}

data class SettingsContent(var title: String, var content: String)
