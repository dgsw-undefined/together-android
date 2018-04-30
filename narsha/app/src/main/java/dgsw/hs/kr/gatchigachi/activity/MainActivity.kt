package dgsw.hs.kr.gatchigachi

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridView
import dgsw.hs.kr.gatchigachi.adapter.TeamGridAdapter
import gateegachi.dgsw.kr.narsha.trustActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gridView : GridView = findViewById(R.id.team_grid_view)

        var teamAdapter = TeamGridAdapter(this, DataService.teamData)
        gridView.adapter = teamAdapter

    }
}
