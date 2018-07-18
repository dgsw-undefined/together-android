package dgsw.hs.kr.gatchigachi

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
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
import kotlinx.android.synthetic.main.activity_make_team.*
import java.util.ArrayList

class MakeTeamActivity : AppCompatActivity() {

    val network = Network()
    var myDb : DBHelper? = null
    var teamIdx = 0

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        setContentView(R.layout.activity_make_team)

         myDb = DBHelper(this)

        val user = myDb!!.selectMyInfo()

        btn_make_do_make_team.setOnClickListener {

            val teamName = edit_make_teamName.text.toString()
            val area = edit_make_field.text.toString()
//            val teamMemberLimit = member_limit.selectedItem.toString().toInt()
            val teamMemberLimit = 9
            val teamSubject = edit_make_subject.text.toString()
            val teamLeaderField = user!!.field
            val teamDocs = edit_docs.text.toString()

            val team = Team(
                    null,
                    teamName,
                    teamSubject,
                    area,
                    teamDocs,
                    null,
                    null,
                    teamMemberLimit,
                    null
            )

            network.teamRegistration(team,teamLeaderField, myDb!!,this)

        }

      }

    fun notifyFinish(id:Long){
        teamIdx = id.toInt()
        network.getTeam(this!!.myDb!!,myDb!!.selectMyInfo()!!.idx!!.toInt(),this,true,2)
    }

    fun notifyFinish(code:Int){
        network.getTeamMember(teamIdx,myDb!!,this,1)
    }

    fun notifyFinish(){
        val intent = Intent(this, DetailTeamActivity::class.java)
        intent.putExtra("teamId",teamIdx)
        this.startActivity(intent)
    }

    override fun onResume() {
        super.onResume()

        edit_make_field.setText(null)
        edit_make_subject.setText(null)
        edit_make_teamName.setText(null)
    }

}
