package dgsw.hs.kr.gatchigachi

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.*
import dgsw.hs.kr.gatchigachi.R
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

        btn_make_do_make_team.setOnClickListener {
            val teamName = edit_make_teamName.text
            val area = edit_make_field.text
            val teamMemberLimit = member_limit.selectedItem
            val teamSubject = edit_make_subject.text
            val teamLeaderField = myDb.selectMyInfo()!!.field

        }
//        val adapter = ArrayAdapter.createFromResource(this,android.R.layout.simple_spinner_dropdown_item,R.array.people)
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//        member_limit!!.adapter = adapter
//        member_limit.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onNothingSelected(parent: AdapterView<*>?) {
//
//            }
//
//            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) : Int{
//                val lim = parent!!.getItemAtPosition(position).toString().toInt()
//            }
//        }
//
//    }



    }

    override fun onResume() {
        super.onResume()

        edit_make_field.setText(null)
        edit_make_subject.setText(null)
        edit_make_teamName.setText(null)
    }
}
