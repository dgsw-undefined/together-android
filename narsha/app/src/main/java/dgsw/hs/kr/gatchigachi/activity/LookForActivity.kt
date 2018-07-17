package dgsw.hs.kr.gatchigachi.activity

import android.content.pm.ActivityInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import dgsw.hs.kr.gatchigachi.DataService
import dgsw.hs.kr.gatchigachi.R
import dgsw.hs.kr.gatchigachi.adapter.BestTrusterAdapter
import dgsw.hs.kr.gatchigachi.adapter.SearchPersonAdapter
import dgsw.hs.kr.gatchigachi.adapter.SearchTeamAdaper
import kotlinx.android.synthetic.main.activity_look_for.*

class LookForActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        setContentView(R.layout.activity_look_for)


        val list = AnimationUtils.loadAnimation(this, R.anim.search_list)
        val bestTruster = BestTrusterAdapter(this, DataService.BestTrusterData)
        val searchPerson = SearchPersonAdapter(this, DataService.SearchUserData)
        val searchTeam = SearchTeamAdaper(this, DataService.SearchTeamData)

        list_search_result.adapter = bestTruster

        btn_lookfor_person.setOnClickListener {

            list_search_result.startAnimation(list)
            list_search_result.adapter = searchPerson

//            val myDB = DatabaseHelper(this)
        }



        btn_lookfor_team.setOnClickListener {


            list_search_result.startAnimation(list)

            list_search_result.adapter = searchTeam
        }
    }

}
