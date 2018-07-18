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
import kotlinx.android.synthetic.main.activity_make_team.*
import java.util.ArrayList

class MakeTeamActivity : AppCompatActivity() {

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        setContentView(R.layout.activity_make_team)

        val myDb = DBHelper(this)
        var list_of_items = arrayOf("2","3","4","5","6","7","8")
        btn_make_do_make_team.setOnClickListener {
            val teamName = edit_make_teamName.toString()
            val area = edit_make_field.toString()
            val teamMemberLimit = member_limit.selectedItem.toString().toInt()
            val teamSubject = edit_make_subject.toString()
            val teamLeaderField = myDb.selectMyInfo()!!.field
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
}