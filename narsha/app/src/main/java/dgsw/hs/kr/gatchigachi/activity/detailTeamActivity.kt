package dgsw.hs.kr.gatchigachi

import android.content.pm.ActivityInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import dgsw.hs.kr.gatchigachi.adapter.MemberAdapter
import dgsw.hs.kr.gatchigachi.database.DBHelper
import dgsw.hs.kr.gatchigachi.model.Team
import dgsw.hs.kr.gatchigachi.network.Network
import kotlinx.android.synthetic.main.activity_detail_team.*

class DetailTeamActivity : AppCompatActivity() {

    val network = Network()
    lateinit var myDb : DBHelper
    lateinit var team : Team
    var code = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        setContentView(R.layout.activity_detail_team)

        myDb = DBHelper(this)

        val teamId = intent.getIntExtra("teamId",0)

        team = myDb.selectTeamById(teamId)!!

        team_name_detail.text = team!!.name
        team_subject_detail.text = team!!.subject
        team_docs_detail.text = team!!.docs

//        val teamMembers = network.getTeamMember(teamId,myDb,this)

        var memberAdapter = MemberAdapter(this, myDb.selectTeamMembersByTeamId(team.id!!.toInt()),myDb)
        team_member_list.adapter = memberAdapter

        btn_back_detail_team.setOnClickListener {
            finish()
        }
    }

}

