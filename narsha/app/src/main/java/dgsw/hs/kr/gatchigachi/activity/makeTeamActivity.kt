package dgsw.hs.kr.gatchigachi

import android.content.pm.ActivityInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.SpinnerAdapter
import dgsw.hs.kr.gatchigachi.R
import kotlinx.android.synthetic.main.activity_make_team.*

class MakeTeamActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        setContentView(R.layout.activity_make_team)

        val teamName = edit_make_teamName.text
        val teamField = edit_make_field.text
        val teamMemberLimit = member_limit.selectedItem

        /*val countAdapter = ArrayAdapter.createFromResource(this,)*/
    }
}
