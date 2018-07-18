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
import dgsw.hs.kr.gatchigachi.adapter.TeamGridAdapter
import dgsw.hs.kr.gatchigachi.database.DBHelper
import dgsw.hs.kr.gatchigachi.network.Network
import kotlinx.android.synthetic.main.activity_look_for.*
import kotlinx.android.synthetic.main.search_result_person.*

class LookForActivity : AppCompatActivity() {

    val network = Network()
    lateinit var myDb : DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        setContentView(R.layout.activity_look_for)

        myDb = DBHelper(this)

        val teams = myDb.selectAllTeam()
        val users = myDb.selectAllUser()

        for (team in teams) {
            network.getTeamMember(team.id!!.toInt(), myDb, this,0)
        }

        val list = AnimationUtils.loadAnimation(this, R.anim.search_list)

        val searchPerson = SearchPersonAdapter(this, users)
        val searchTeam = TeamGridAdapter(this, teams)

        grid_search_result.visibility = View.INVISIBLE
        list_search_result.startAnimation(list)
        list_search_result.adapter = searchPerson

        btn_lookfor_person.setOnClickListener {

            grid_search_result.visibility = View.INVISIBLE
            list_search_result.visibility = View.VISIBLE
            list_search_result.startAnimation(list)

            list_search_result.adapter = searchPerson

        }



        btn_lookfor_team.setOnClickListener {

            list_search_result.visibility = View.INVISIBLE
            grid_search_result.visibility = View.VISIBLE
            grid_search_result.startAnimation(list)

            grid_search_result.adapter = searchTeam

        }
    }

}
