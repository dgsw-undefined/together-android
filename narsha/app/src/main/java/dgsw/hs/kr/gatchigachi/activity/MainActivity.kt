package dgsw.hs.kr.gatchigachi

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import dgsw.hs.kr.gatchigachi.adapter.TeamGridAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var teamAdapter = TeamGridAdapter(this, DataService.teamData)
        team_grid_view.adapter = teamAdapter

        btn_trust.setOnClickListener {
            val nextIntent = Intent(this, DetailTeamActivity::class.java)
            startActivity(nextIntent)
        }

    }
}
