package dgsw.hs.kr.gatchigachi.activity

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.animation.*
import android.widget.Toast
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import dgsw.hs.kr.gatchigachi.DataService
import dgsw.hs.kr.gatchigachi.DetailTeamActivity
import dgsw.hs.kr.gatchigachi.R
import dgsw.hs.kr.gatchigachi.adapter.TeamGridAdapter
import dgsw.hs.kr.gatchigachi.preference.Preference
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        var teamAdapter = TeamGridAdapter(this, DataService.teamData)
        team_grid_view.adapter = teamAdapter

        btn_trust.setOnClickListener {
            val nextIntent = Intent(this, DetailTeamActivity::class.java)
            startActivity(nextIntent)
        }

        val preference = Preference(this)

        Toast.makeText(this, preference.getToken(), Toast.LENGTH_SHORT).show()

        btn_open_detail.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){
                aaa.y = aaa.y+300

                detail.visibility = VISIBLE

            } else{

                aaa.y = aaa.y-300

                detail.visibility = INVISIBLE


            }

        }

        val URL = "http://115.68.182.229/go/user/signin"

        URL.httpGet().responseString { request, response, result ->
            //do something with response
            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                }
                is Result.Success -> {
                    val data = result.get()
                }
            }
        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
