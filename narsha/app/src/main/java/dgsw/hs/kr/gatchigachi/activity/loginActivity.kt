package dgsw.hs.kr.gatchigachi

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Switch
import android.widget.Toast
import dgsw.hs.kr.gatchigachi.R.layout.activity_sign
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_login_to_sign.setOnClickListener {
            val Button_Animation : Animation = AnimationUtils.loadAnimation(this,R.anim.button_anim)
            Button_Animation.start()
            val nextIntent = Intent(this, SignActivity::class.java)
            startActivity(nextIntent)
        }

        btn_login_do_login.setOnClickListener {
            val Button_Animation : Animation = AnimationUtils.loadAnimation(this,R.anim.button_anim)
            Button_Animation.start()
            val nextIntent = Intent(this, MainActivity::class.java)

            val id : String = edit_login_id.text.toString()
            val pw : String = edit_login_pw.text.toString()

            if(check(id, pw) == 1){
                // 중복 등 데이터 확인
                if(call_server() == 1){

                    startActivity(nextIntent)
                }
            }
        }
    }
    fun call_server() : Int {
        return 1
    }
    fun check(id : String, pw : String) : Int {
        if(id.length == 0){
            Toast.makeText(this,"ID를 입력하세요", Toast.LENGTH_SHORT).show()
            edit_login_id.requestFocus();
            return -1
        }

        if(pw.length == 0){
            Toast.makeText(this,"비밀번호를 입력하세요", Toast.LENGTH_SHORT).show()
            edit_login_pw.requestFocus()
            return -1
        }

        else if(pw.length < 8){
            Toast.makeText(this,"8자 이상의 비밀번호를 입력하세요", Toast.LENGTH_SHORT).show()
            edit_login_pw.requestFocus()
            return -1
        }

        return 1
    }
}
