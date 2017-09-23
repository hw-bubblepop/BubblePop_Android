package us.buddman.bubblepop.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout.HORIZONTAL
import com.github.nitrico.lastadapter.LastAdapter
import kotlinx.android.synthetic.main.fragment_main_bubblemoa.*
import us.buddman.bubblepop.BR
import us.buddman.bubblepop.R
import us.buddman.bubblepop.SettingsActivity
import us.buddman.bubblepop.databinding.BubblemoaMoimContentBinding
import us.buddman.bubblepop.models.Moim
import us.buddman.bubblepop.models.Study
import java.util.*

/**
 * Created by Junseok on 2017-09-21.
 */
class MainBubbleMoaFragment : Fragment() {

    private var moimAdapter: LastAdapter? = null
    private var studyAdapter: LastAdapter? = null
    private var moimList: ArrayList<Moim> = ArrayList()
    private var studyList: ArrayList<Study> = ArrayList()
    private var moimLinearLayoutManager: LinearLayoutManager? = null
    private var studyLinearLayoutManager: LinearLayoutManager? = null
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater!!.inflate(R.layout.fragment_main_bubblemoa, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize()
        bubbleMoaMoimRecyclerView.layoutManager = moimLinearLayoutManager
        bubbleMoaStudyRecyclerView.layoutManager = studyLinearLayoutManager
        bubbleMoaSettings.setOnClickListener { _ ->
            startActivity(Intent(context, SettingsActivity::class.java))
        }

        moimAdapter = LastAdapter(moimList, BR.content)
                .map<Moim, BubblemoaMoimContentBinding>(R.layout.bubblemoa_moim_content) {
                    onBind {

                    }
                }
                .into(bubbleMoaMoimRecyclerView)

        studyAdapter = LastAdapter(studyList, BR.content)
                .map<Study, BubblemoaMoimContentBinding>(R.layout.bubblemoa_study_content) {

                }
                .into(bubbleMoaStudyRecyclerView)
    }

    fun initialize() {
        moimLinearLayoutManager = LinearLayoutManager(context)
        moimLinearLayoutManager!!.orientation = HORIZONTAL
        studyLinearLayoutManager = LinearLayoutManager(context)
        studyLinearLayoutManager!!.orientation = HORIZONTAL
        addMoim()


    }

    fun addMoim() {
        for (i in 1..10) {
            moimList.run {
                add(Moim(comment = 0, date = Date(System.currentTimeMillis()), like = 0, title = "SK STAC 참가자 모임", content = "SK STAC 참가자 모임 SK STAC 참가자 모임"))
            }
            studyList.run {
                add(Study(comment = 0, date = Date(System.currentTimeMillis()), like = 0, title = "SK STAC 참가자 모임", content = "SK STAC 참가자 모임 SK STAC 참가자 모임"))
            }
        }
    }

}