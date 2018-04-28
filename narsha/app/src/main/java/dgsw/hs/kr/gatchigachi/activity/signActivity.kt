package gateegachi.dgsw.kr.narsha

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import dgsw.hs.kr.gatchigachi.R
import dgsw.hs.kr.gatchigachi.R.layout.activity_login
import dgsw.hs.kr.gatchigachi.R.layout.activity_sign2
import kotlinx.android.synthetic.main.activity_sign.*

class signActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign)

        btn_sign_to_login.setOnClickListener{
            val nextIntent = Intent(this, activity_login::class.java)
            startActivity(nextIntent)
        }

        btn_sign_to_sign2.setOnClickListener {
            val nextIntent = Intent(this, activity_sign2::class.java)
            startActivity(nextIntent)
        }
    }
}
