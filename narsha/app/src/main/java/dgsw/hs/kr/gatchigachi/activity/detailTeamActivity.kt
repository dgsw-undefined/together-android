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
import kotlinx.android.synthetic.main.activity_detail_team.*

class DetailTeamActivity : AppCompatActivity() {

    lateinit var myDb : DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        setContentView(R.layout.activity_detail_team)

        myDb = DBHelper(this)

        var memberAdapter = MemberGridAdapter(this, DataService.MemberData)
        team_member_list.adapter = memberAdapter

        val teamId = intent.getIntExtra("teamId",0)

        val team = myDb.selectTeamById(teamId)

        team_name.text = team!!.name
        team_subject.text = team!!.subject

    }
}
