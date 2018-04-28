package dgsw.hs.kr.gatchigachi

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridView
import dgsw.hs.kr.gatchigachi.adapter.TeamGridAdapter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gridView : GridView = findViewById(R.id.team_grid_view)

        var teamAdapter = TeamGridAdapter(this, DataService.teamData)
        gridView.adapter = teamAdapter

    }
}
