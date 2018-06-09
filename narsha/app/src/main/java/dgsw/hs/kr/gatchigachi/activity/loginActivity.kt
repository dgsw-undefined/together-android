package dgsw.hs.kr.gatchigachi

import android.content.Intent
import android.content.pm.ActivityInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.android.core.Json
import com.github.kittinunf.fuel.httpPost
import com.google.gson.Gson
import dgsw.hs.kr.gatchigachi.R.id.*
import dgsw.hs.kr.gatchigachi.activity.LookForActivity
import dgsw.hs.kr.gatchigachi.activity.MainActivity

import dgsw.hs.kr.gatchigachi.model.User
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestedOrientation = (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        setContentView(R.layout.activity_login)




        btn_login_to_sign.setOnClickListener {
            val nextIntent = Intent(this, SignActivity::class.java)
            val animation = AnimationUtils.loadAnimation(this, R.anim.button_anim)
            btn_login_to_sign.startAnimation(animation)
            startActivity(nextIntent)
        }

        btn_login_do_login.setOnClickListener {
            val nextIntent = Intent(this, MainActivity::class.java)
            val animation = AnimationUtils.loadAnimation(this, R.anim.button_anim)
            btn_login_do_login.startAnimation(animation)
            val id: String = edit_login_id.text.toString()
            val pw: String = edit_login_pw.text.toString()

            if (check(id, pw) == 1) {
                // 중복 등 데이터 확인
                if (call_server(id,pw) == 1) {
                    startActivity(nextIntent)
                }
            }
        }
    }

    private fun call_server(id:String,pw:String): Int {

        val URL = "http://115.68.182.229/go/user/signin"
        val json = HashMap<String,String>()
        json.put("id",id)
        json.put("pw",pw)

        URL.httpPost()
                .header(Pair("Content-Type", "application/json"))
                .body(Gson().toJson(json))
                .responseObject(User.Deserializer()) { request, response, result ->
                    Toast.makeText(this, response.toString(), Toast.LENGTH_SHORT).show()
                    println(response.toString())
                    println(request.toString())

                }
        return 1
    }

    fun check(id: String, pw: String): Int {

        if (id.isEmpty()) {
            Toast.makeText(this, "ID를 입력하세요", Toast.LENGTH_SHORT).show()
            edit_login_id.requestFocus();
            return -1
        }

        if (pw.isEmpty()) {
            Toast.makeText(this, "비밀번호를 입력하세요", Toast.LENGTH_SHORT).show()
            edit_login_pw.requestFocus()
            return -1
        }

        else if (pw.length < 8) {
            Toast.makeText(this, "8자 이상의 비밀번호를 입력하세요", Toast.LENGTH_SHORT).show()
            edit_login_pw.requestFocus()
            return -1
        }



        return 1

    }
}
