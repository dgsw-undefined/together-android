package dgsw.hs.kr.gatchigachi

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.github.kittinunf.fuel.Fuel
import dgsw.hs.kr.gatchigachi.R.anim.logo_events_test
import dgsw.hs.kr.gatchigachi.R.id.loginLinear1
import dgsw.hs.kr.gatchigachi.activity.MainActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        startAnimation()


        btn_login_to_sign.setOnClickListener {
            val nextIntent = Intent(this, SignActivity::class.java)
            val animation = AnimationUtils.loadAnimation(this,R.anim.button_anim)
            btn_login_to_sign.startAnimation(animation)
            startActivity(nextIntent)
        }

        btn_login_do_login.setOnClickListener {
            val nextIntent = Intent(this, MainActivity::class.java)
            val animation = AnimationUtils.loadAnimation(this,R.anim.button_anim)
            btn_login_do_login.startAnimation(animation)
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
    private fun call_server() : Int {
        Fuel.get("http://10.80.162.9:8080/user").response{ request, response, result ->
            println(request)
            println(response)
            Toast.makeText(this, response.data.toString(), Toast.LENGTH_SHORT).show()
            val (bytes, error) = result
            if( bytes != null) {
                println(bytes)
            }
            if( error != null) {
                println(error)
            }
        }
        return 0
    }

    private fun startAnimation() {
        val logo_events1 = AnimationUtils.loadAnimation(this, R.anim.logo_events1)
        val logo_events2 = AnimationUtils.loadAnimation(this, R.anim.logo_events2)
        val logo_events3 = AnimationUtils.loadAnimation(this, R.anim.logo_events3)
        val logo_events4 = AnimationUtils.loadAnimation(this, R.anim.logo_events4)
        val test = AnimationUtils.loadAnimation(this, R.anim.logo_events_test)

        loginLinear1.startAnimation(logo_events1)
        loginLinear2.startAnimation(logo_events2)
        loginLinear3.startAnimation(logo_events3)
        loginLinear4.startAnimation(logo_events4)



        return
    }

    private fun check(id : String, pw : String) : Int {
        if(id.isEmpty()){
            Toast.makeText(this,"ID를 입력하세요", Toast.LENGTH_SHORT).show()
            edit_login_id.requestFocus();
            return -1
        }

        if(pw.isEmpty()){
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
