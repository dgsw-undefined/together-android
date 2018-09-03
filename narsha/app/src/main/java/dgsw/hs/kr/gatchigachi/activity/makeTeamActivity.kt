package dgsw.hs.kr.gatchigachi

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.*
import dgsw.hs.kr.gatchigachi.R
import dgsw.hs.kr.gatchigachi.activity.MainActivity
import dgsw.hs.kr.gatchigachi.database.DBHelper
import dgsw.hs.kr.gatchigachi.model.Team
import dgsw.hs.kr.gatchigachi.network.Network
import kotlinx.android.synthetic.main.activity_detail_team.*
import kotlinx.android.synthetic.main.activity_make_team.*
import java.util.ArrayList

class MakeTeamActivity : AppCompatActivity() {

    lateinit var myDb : DBHelper
    val network = Network()
    var teamId = 0


    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        setContentView(R.layout.activity_make_team)

        team_image_make.background = ShapeDrawable(OvalShape());
        team_image_make.clipToOutline = true;

        myDb = DBHelper(this)
        var list_of_items = arrayOf("2","3","4","5","6","7","8")
        btn_make_do_make_team.setOnClickListener {
            val teamName = edit_make_teamName.text.toString()
            val area = edit_make_field.text.toString()
            val teamMemberLimit = member_limit.selectedItem.toString().toInt()
            val teamSubject = edit_make_subject.text.toString()
            val teamLeaderField = myDb.selectMyInfo()!!.field
            val teamDocs = edit_docs.text.toString()

            val team = Team(
                    null,
                    teamName,
                    teamSubject,
                    area,
                    teamDocs,
                    myDb.selectMyInfo()!!.idx!!.toInt(),
                    null,
                    teamMemberLimit,
                    null
            )

            team.field = teamLeaderField

            network.teamRegistration(team,myDb,this)
        }

        val aa = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list_of_items)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        member_limit!!.adapter = aa
    }



    override fun onResume() {
        super.onResume()

        edit_make_field.setText(null)
        edit_make_subject.setText(null)
        edit_make_teamName.setText(null)
    }

    fun notifyFinish(id: Long) {
        teamId = id.toInt()
        network.getTeam(myDb,myDb.selectMyInfo()!!.idx!!.toInt(),this,true,1)
    }

    fun notifyFinish(id: Int) {
        network.getTeamMember(teamId,myDb,this,1)
    }

    fun notifyFinish(){
        val intent = Intent(this, DetailTeamActivity::class.java)
        intent.putExtra("teamId",teamId)
        startActivity(intent)
        finish()
    }

}