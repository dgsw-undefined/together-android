package dgsw.hs.kr.gatchigachi.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import dgsw.hs.kr.gatchigachi.R
import dgsw.hs.kr.gatchigachi.adapter.SearchPersonAdapter
import dgsw.hs.kr.gatchigachi.database.DBHelper
import kotlinx.android.synthetic.main.activity_add_team_member.*

class AddTeamMemberActivity : AppCompatActivity() {

    var myDb : DBHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_team_member)

        myDb = DBHelper(this)

        val users = myDb!!.selectAllUser()

        val searchPerson = SearchPersonAdapter(this, users,0,null)

        list_search_result.adapter = searchPerson

    }
}
