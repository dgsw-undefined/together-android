package dgsw.hs.kr.gatchigachi.activity

import android.content.Intent
import android.content.pm.ActivityInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Filter
import android.widget.SearchView
import android.widget.Toast
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

        var searchPerson : SearchPersonAdapter

        myDb = DBHelper(this)

        val teams = myDb.selectAllTeam()
        val users = myDb.selectAllUser()

        if(intent.getIntExtra("type" , 0 ) == 1){
            btn_lookfor_team.visibility = View.INVISIBLE
            searchPerson = SearchPersonAdapter(this, users, 1, this.intent.getIntExtra("teamId",0))
        }else{
            searchPerson = SearchPersonAdapter(this, users, 0,null)
            for (team in teams) {
                network.getTeamMember(team.id!!.toInt(), myDb, this,0)
            }
        }

        val list = AnimationUtils.loadAnimation(this, R.anim.search_list)

        var searchTeam = TeamGridAdapter(this, teams)

        grid_search_result.visibility = View.INVISIBLE
        list_search_result.startAnimation(list)
        list_search_result.adapter = searchPerson

        btn_lookfor_person.setOnClickListener {

            grid_search_result.visibility = View.INVISIBLE
            list_search_result.visibility = View.VISIBLE
            list_search_result.startAnimation(list)

            list_search_result.adapter = searchPerson
            search_lookfor.setQuery("",true)

        }

        btn_lookfor_team.setOnClickListener {

            list_search_result.visibility = View.INVISIBLE
            grid_search_result.visibility = View.VISIBLE
            grid_search_result.startAnimation(list)

            grid_search_result.adapter = searchTeam
            search_lookfor.setQuery("",true)

        }

        search_lookfor.isActivated = true
        search_lookfor.queryHint = "이름을 검색하시오"
        search_lookfor.onActionViewExpanded()
        search_lookfor.isIconified = false
        search_lookfor.clearFocus()


        btn_search_cancel.setOnClickListener {
            if(grid_search_result.visibility == View.VISIBLE){
                grid_search_result.visibility = View.INVISIBLE
                list_search_result.visibility = View.VISIBLE
                list_search_result.startAnimation(list)

                list_search_result.adapter = searchTeam
            }
            else if(list_search_result.visibility == View.VISIBLE){
                list_search_result.visibility = View.INVISIBLE
                grid_search_result.visibility = View.VISIBLE
                grid_search_result.startAnimation(list)

                grid_search_result.adapter = searchPerson
            }else{
                Toast.makeText(this, "ID를 입력하세요", Toast.LENGTH_SHORT).show()
            }

        }

        search_lookfor.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {

                if (grid_search_result.visibility == View.VISIBLE){
                    if (newText == ""){
                        searchTeam.teamData = teams
                        searchTeam.notifyDataSetChanged()
                    }
                    searchTeam.filter.filter(newText)
                }else{
                    if (newText == ""){
                        searchPerson.userList = users
                        searchPerson.notifyDataSetChanged()
                    }
                    searchPerson.filter.filter(newText)
                }
                return true
            }

            override fun onQueryTextSubmit(query: String): Boolean {

                if (grid_search_result.visibility == View.VISIBLE){
                    searchTeam.filter.filter(query)
                }else{
                    searchPerson.filter.filter(query)
                }
                return true
            }
        })

        search_lookfor.setOnCloseListener { false }

    }


}
