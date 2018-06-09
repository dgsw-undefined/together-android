package dgsw.hs.kr.gatchigachi

import android.content.Intent
import android.content.pm.ActivityInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.TextView
import android.widget.Toast
import dgsw.hs.kr.gatchigachi.R
import dgsw.hs.kr.gatchigachi.R.layout.activity_login
import dgsw.hs.kr.gatchigachi.R.layout.activity_sign2
import kotlinx.android.synthetic.main.activity_sign.*

class SignActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        setContentView(R.layout.activity_sign)

        btn_sign_to_sign2.setOnClickListener {
            val nextIntent = Intent(this, Sign2Activity::class.java)
            val id : String = edit_sign_id.text.toString()
            val pw : String = edit_sign_pw.text.toString()
            val pwc : String = edit_sign_pwc.text.toString()

            if(Check(id,pw,pwc) == 1){
                nextIntent.putExtra("id",id)
                nextIntent.putExtra("pw",pw)
                startActivity(nextIntent)
            }
        }
    }

    fun Check(id : String, pw: String, pwc : String) : Int{

        if (id.isEmpty()){
            Toast.makeText(this, "ID를 입력하세요", Toast.LENGTH_SHORT).show()
            edit_sign_id.requestFocus()
            return -1
        }
        if (pw.isEmpty()){
            Toast.makeText(this, "비밀번호를 입력하세요", Toast.LENGTH_SHORT).show()
            edit_sign_pw.requestFocus()
            return -1
        }
        else if(pw.isEmpty()){
            Toast.makeText(this,"8자 이상 비밀번호를 입력하세요", Toast.LENGTH_SHORT).show()
            edit_sign_pw.requestFocus()
            return -1
        }

        else if(!pw.equals(pwc)){
            Toast.makeText(this,"비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
            edit_sign_pwc.requestFocus()
            return -1
        }

        return 1
    }
}
