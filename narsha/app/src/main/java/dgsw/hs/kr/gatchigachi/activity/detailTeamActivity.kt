package dgsw.hs.kr.gatchigachi

import android.content.Intent
import android.content.pm.ActivityInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import dgsw.hs.kr.gatchigachi.R
import dgsw.hs.kr.gatchigachi.adapter.MemberGridAdapter
import dgsw.hs.kr.gatchigachi.adapter.TeamGridAdapter
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

        team_name.text = team!!.name
        team_subject.text = team!!.subject

        val teamMembers = network.getTeamMember(teamId,myDb,this)

    }

    fun notifyFinish(code :Long){
        this.code = code.toInt()
        var memberAdapter = MemberGridAdapter(this, myDb.selectTeamMembersByTeamId(team!!.id),myDb)
        team_member_list.adapter = memberAdapter
    }
}
