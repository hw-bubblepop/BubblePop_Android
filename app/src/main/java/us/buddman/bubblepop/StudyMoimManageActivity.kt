package us.buddman.bubblepop

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.github.nitrico.lastadapter.LastAdapter
import kotlinx.android.synthetic.main.activity_study_moim_manage.*
import us.buddman.bubblepop.databinding.StudyMoimManageContentBinding
import us.buddman.bubblepop.models.Moim
import us.buddman.bubblepop.models.Study
import java.util.*

class StudyMoimManageActivity : BaseActivity() {

    var adapter: LastAdapter? = null
    var layoutManager: GridLayoutManager? = null
    var studyMoimArray: ArrayList<Any> = ArrayList()
    override fun setDefault() {
        initialize()
        adapter = LastAdapter(studyMoimArray, BR.content)
                .map<Study, StudyMoimManageContentBinding>(R.layout.study_moim_manage_content) {
                    onBind { }
                }
                .into(manageRecyclerView)
    }

    fun initialize() {
        layoutManager = GridLayoutManager(applicationContext, 2)
        manageRecyclerView.layoutManager = layoutManager
        studyMoimArray.run {
            add(Study("자고싶다", "집에보내줘", 0, 0, Date(System.currentTimeMillis())))
            add(Moim("자고싶다", "집에보내줘", 0, 0, Date(System.currentTimeMillis())))
        }
    }

    override fun onCreateViewToolbarId(): Int {
        return R.id.toolbar
    }

    override fun onCreateViewId(): Int {
        return R.layout.activity_study_moim_manage
    }

}
