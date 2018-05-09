package dgsw.hs.kr.gatchigachi

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import dgsw.hs.kr.gatchigachi.R
import dgsw.hs.kr.gatchigachi.R.layout.activity_login
import dgsw.hs.kr.gatchigachi.R.layout.activity_sign2
import kotlinx.android.synthetic.main.activity_sign.*

class SignActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign)

        btn_sign_to_login.setOnClickListener{
            val nextIntent = Intent(this, LoginActivity::class.java)
            startActivity(nextIntent)
        }

        btn_sign_to_sign2.setOnClickListener {
            val nextIntent = Intent(this, Sign2Activity::class.java)

            val name : String = edit_sign_name.text.toString()
            val id : String = edit_sign_id.text.toString()
            val pw : String = edit_sign_pw.text.toString()
            val pw2 : String = edit_sign_PWcheck.text.toString()
            val phone : String = edit_sign_phone.text.toString()
            nextIntent.putExtra("ToSign2",name)
            nextIntent.putExtra("ToSign2",id)
            nextIntent.putExtra("ToSign2",pw)
            nextIntent.putExtra("ToSign2",pw2)
            nextIntent.putExtra("ToSign2",phone)
            startActivity(nextIntent)
        }
    }
}
