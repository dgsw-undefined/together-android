package dgsw.hs.kr.gatchigachi.activity

import android.content.pm.ActivityInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import dgsw.hs.kr.gatchigachi.R
import kotlinx.android.synthetic.main.activity_look_for.*

class LookForActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        setContentView(R.layout.activity_look_for)

        search_result_list_person.visibility = View.INVISIBLE
        search_result_list_team.visibility = View.INVISIBLE

    }
}
