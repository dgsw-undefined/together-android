package dgsw.hs.kr.gatchigachi

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import dgsw.hs.kr.gatchigachi.adapter.TrustListAdapter
import kotlinx.android.synthetic.main.activity_trust.*

class TrustActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        setContentView(R.layout.activity_trust)

        //val listView:ListView = findViewById(R.id.list_trust_view)
        var UserAdapter = TrustListAdapter(this, DataService.UserData)
        //val UserAdapter = UserGridAdapter(context = this, UserData = testArray)

        list_trust_view.adapter = UserAdapter

    }
}
