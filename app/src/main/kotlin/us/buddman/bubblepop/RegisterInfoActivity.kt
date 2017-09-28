package us.buddman.bubblepop

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_register_info.*
import us.buddman.bubblepop.models.User
import us.buddman.bubblepop.utils.TextUtils

class RegisterInfoActivity : BaseActivity() {

    var user: User? = null
    var locationList = arrayListOf(
            "서울",
            "경기",
            "수원",
            "인천",
            "천안",
            "대전",
            "전주",
            "광주",
            "대구",
            "부산",
            "울산",
            "창원",
            "평택",
            "세종",
            "충북",
            "충남",
            "전북",
            "전남",
            "경북",
            "강원",
            "경남",
            "제주",
            "해외"
    )
    var jobList = arrayListOf(
            "사무/금융직",
            "연구원, 엔지니어",
            "건축, 설계",
            "간호 및 기타 의료사",
            "디자이너",
            "언론인",
            "교사 및 강사",
            "공무원, 공사",
            "자영업, 사업",
            "예술가, 프리랜서",
            "승무원/항공관련",
            "서비스/영업",
            "학생",
            "변호사, 법조인",
            "회계사 등 전문직",
            "교수",
            "기타"
    )
    lateinit var receivedIntent: Intent

    override fun setDefault() {
        receivedIntent = intent
        setToolbarTitle("회원가입")
        user = intent.getSerializableExtra("userData") as User
        val locationAdapter = ArrayAdapter(applicationContext, android.R.layout.simple_list_item_1, locationList)
        val jobAdapter = ArrayAdapter(applicationContext, android.R.layout.simple_list_item_1, jobList)
        locationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        jobAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        placeInfo.adapter = locationAdapter
        jobInfo.adapter = jobAdapter
        whoareu.text = user!!.nickname + "님은\n어떤 사람인가요?"
        next.setOnClickListener {
            if (TextUtils.isFilled(organizationInfo, masterInfo, positionInfo)) {
                user!!.location = placeInfo.selectedItemPosition
                startActivity(Intent(applicationContext, RegisterCategoryActivity::class.java)
                        .putExtra("userData", user))
                finish()
            } else Toast.makeText(applicationContext, "빈칸을 모두 입력해주세요.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateViewId(): Int {
        return R.layout.activity_register_info
    }

    override fun onCreateViewToolbarId(): Int {
        return R.id.toolbar
    }
}