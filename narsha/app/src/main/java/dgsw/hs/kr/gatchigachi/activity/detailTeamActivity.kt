package dgsw.hs.kr.gatchigachi

import android.content.pm.ActivityInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import dgsw.hs.kr.gatchigachi.R
import dgsw.hs.kr.gatchigachi.adapter.MemberGridAdapter
import dgsw.hs.kr.gatchigachi.adapter.TeamGridAdapter
import kotlinx.android.synthetic.main.activity_detail_team.*

class DetailTeamActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        setContentView(R.layout.activity_detail_team)

        var memberAdapter = MemberGridAdapter(this, DataService.MemberData)
        team_member_list.adapter = memberAdapter


    }
}
