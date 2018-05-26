package dgsw.hs.kr.gatchigachi.activity

import android.content.pm.ActivityInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import dgsw.hs.kr.gatchigachi.DataService
import dgsw.hs.kr.gatchigachi.R
import dgsw.hs.kr.gatchigachi.adapter.SearchPersonAdapter
import dgsw.hs.kr.gatchigachi.adapter.SearchTeamAdaper
import kotlinx.android.synthetic.main.activity_look_for.*

class LookForActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        setContentView(R.layout.activity_look_for)


        search_result_list_person.visibility = View.INVISIBLE
        search_result_list_team.visibility = View.INVISIBLE
        val BtnAnimation = AnimationUtils.loadAnimation(this, R.anim.button_anim)
        btn_lookfor_person.setOnClickListener {

            search_result_list_team.visibility = View.INVISIBLE
            val SearchPerson = SearchPersonAdapter(this, DataService.SearchUserData)
            search_result_list_person.adapter = SearchPerson
            search_result_list_person.visibility = View.VISIBLE

        }

        btn_lookfor_team.setOnClickListener {

            search_result_list_person.visibility = View.INVISIBLE
            val SearchTeam = SearchTeamAdaper(this, DataService.SearchTeamData)
            search_result_list_team.adapter = SearchTeam
            search_result_list_team.visibility = View.VISIBLE
        }
    }
}
