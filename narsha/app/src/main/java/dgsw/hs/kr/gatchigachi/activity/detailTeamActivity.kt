package dgsw.hs.kr.gatchigachi

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import dgsw.hs.kr.gatchigachi.R.layout.activity_detail_team
import dgsw.hs.kr.gatchigachi.activity.AddTeamMemberActivity
import dgsw.hs.kr.gatchigachi.activity.LookForActivity
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
    var teamId = 0

    override fun onResume() {
        super.onResume()
        network.getTeamMember(teamId,myDb,this, 2)
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        setContentView(R.layout.activity_detail_team)

        myDb = DBHelper(this)

        teamId = intent.getIntExtra("teamId",0)

        team = myDb.selectTeamById(teamId)!!
        val myInfo = myDb.selectMyInfo()!!

        team_field_detail.text = team.area+" & "
        team_name_detail.text = team!!.name
        team_subject_detail.text = team!!.subject
        team_docs_detail.text = team!!.docs

        team_image .background = ShapeDrawable(OvalShape());
        team_image.clipToOutline = true;

        now_limit.text = "${team!!.member_count}/${team!!.member_limit}"

        if(team!!.leader_id == myInfo.idx!!.toInt()){
            btn_add_member_team.visibility = View.VISIBLE
        }

//        val teamMembers = network.getTeamMember(teamId,myDb,this)

        var memberAdapter = MemberAdapter(this, myDb.selectTeamMembersByTeamId(team.id!!.toInt()),myDb)
        team_member_list.adapter = memberAdapter

        btn_add_member_team.setOnClickListener {
            val intent = Intent(this,LookForActivity::class.java)
            intent.putExtra("type", 1)
            intent.putExtra("teamId", teamId)
            startActivity(intent)
        }

    }

    fun notifyFinish(){
        Log.e("aa",teamId.toString())

        val members = myDb.selectTeamMembersByTeamId(team.id!!.toInt())

        var memberAdapter = MemberAdapter(this, members,myDb)
        team_member_list.adapter = memberAdapter

        now_limit.text = "${members.size}/${team!!.member_limit}"
    }

}

