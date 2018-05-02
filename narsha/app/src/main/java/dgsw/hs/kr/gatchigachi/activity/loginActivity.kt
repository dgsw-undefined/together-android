package dgsw.hs.kr.gatchigachi

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import dgsw.hs.kr.gatchigachi.R
import dgsw.hs.kr.gatchigachi.R.layout.activity_sign
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_login_to_sign.setOnClickListener {
            val nextIntent = Intent(this, activity_sign::class.java)
            startActivity(nextIntent)
        }
    }
}
