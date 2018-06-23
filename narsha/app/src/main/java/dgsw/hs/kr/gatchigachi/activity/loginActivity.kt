package dgsw.hs.kr.gatchigachi

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.database.sqlite.SQLiteDatabase
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.annotation.RequiresApi
import android.util.Log
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.android.core.Json
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.httpPost
import com.google.gson.Gson
import dgsw.hs.kr.gatchigachi.R.id.*
import dgsw.hs.kr.gatchigachi.activity.LookForActivity
import dgsw.hs.kr.gatchigachi.activity.MainActivity
import dgsw.hs.kr.gatchigachi.database.DatabaseHelper

import dgsw.hs.kr.gatchigachi.model.User
import dgsw.hs.kr.gatchigachi.preference.Preference
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestedOrientation = (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        setContentView(R.layout.activity_login)

        edit_login_id.setText("a")
        edit_login_pw.setText("a12345678")

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
                if (call_server(id,pw) == 100) {
                    startActivity(nextIntent)
                }
            }
        }
    }

    private fun call_server(id:String,pw:String): Int {

        val URL = "http://115.68.182.229/go/user/signin"
        val json = HashMap<String,String>()
        json.put("id",id)
        val put = json.put("pw", pw)
        var code = 100

        URL.httpPost()
                .header(Pair("Content-Type", "application/json"))
                .body(Gson().toJson(json))
                .responseJson{ request, response, result ->
                    result.fold(success = { json ->
                        val loginJson = JSONObject(json.content)
                        val token = loginJson.getString("Token")

                        val preference = Preference(this)
                        preference.setToken(token)

                    }, failure = { error ->
                        Log.e("error ", error.toString())
                    })
                    println("==========================================================================")
                }

        return code
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
