package dgsw.hs.kr.gatchigachi

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ListView
import dgsw.hs.kr.gatchigachi.DataService
import dgsw.hs.kr.gatchigachi.R
import dgsw.hs.kr.gatchigachi.adapter.TeamGridAdapter
import dgsw.hs.kr.gatchigachi.adapter.UserGridAdapter
import dgsw.hs.kr.gatchigachi.model.User
import kotlinx.android.synthetic.main.activity_trust.*

class TrustActivity : AppCompatActivity() {

    val testArray:ArrayList<User> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trust)

        //val listView:ListView = findViewById(R.id.list_trust_view)
        var UserAdapter = UserGridAdapter(this, DataService.UserData)
        //val UserAdapter = UserGridAdapter(context = this, UserData = testArray)



        list_trust_view.adapter = UserAdapter
    }
}