package dgsw.hs.kr.gatchigachi

import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import dgsw.hs.kr.gatchigachi.activity.MainActivity
import dgsw.hs.kr.gatchigachi.adapter.UserGridAdapter
import dgsw.hs.kr.gatchigachi.model.User
import kotlinx.android.synthetic.main.activity_trust.*
import kotlinx.android.synthetic.main.user_list_item.*
import kotlinx.android.synthetic.main.user_list_item.view.*

class TrustActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        setContentView(R.layout.activity_trust)

        //val listView:ListView = findViewById(R.id.list_trust_view)
        var UserAdapter = UserGridAdapter(this, DataService.UserData)
        //val UserAdapter = UserGridAdapter(context = this, UserData = testArray)

        list_trust_view.adapter = UserAdapter

    }
}
