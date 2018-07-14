package dgsw.hs.kr.gatchigachi.activity

import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.animation.*
import dgsw.hs.kr.gatchigachi.R
import dgsw.hs.kr.gatchigachi.TrustActivity
import dgsw.hs.kr.gatchigachi.adapter.TeamGridAdapter
import dgsw.hs.kr.gatchigachi.database.DBHelper
import dgsw.hs.kr.gatchigachi.network.Network
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {

    val network =  Network()
    lateinit var myDb : DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        setContentView(R.layout.activity_main)
        myDb = DBHelper(this)

        val timer = Timer()

        user_profile_main.background = ShapeDrawable(OvalShape())
        user_profile_main.clipToOutline = true
        
        var teamAdapter = TeamGridAdapter(this, myDb.selectAllTeam())
        team_grid_view.adapter = teamAdapter

        trust_count.setOnClickListener {
            val nextIntent = Intent(this, TrustActivity::class.java)
            startActivity(nextIntent)
        }

        truster_count.setOnClickListener {
            val nextIntent = Intent(this, TrustActivity::class.java)
            startActivity(nextIntent)
        }

        val user = myDb.selectMyInfo()
        Log.e("a", user!!.name)
    }
}
        /*btn_trust.setOnClickListener {
            val nextIntent = Intent(this, DetailTeamActivity::class.java)
            startActivity(nextIntent)
        }*/


        /*btn_open_detail.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){
                bottom_aaa.startAnimation(down_anim)
                timer.schedule(timerTask { this }, 5000)
                bottom_aaa.y = bottom_aaa.y+400

                detail.visibility = VISIBLE

            } else{
                bottom_aaa.startAnimation(up_anim)
                timer.schedule(timerTask { this }, 5000)
                bottom_aaa.y = bottom_aaa.y-400

                detail.visibility = INVISIBLE


            }

        }*/

        /*val toggle = ActionBarDrawerToggle(
                this, wrap, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        wrap.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)*/

    /*override fun onBackPressed() {
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
    }*/
