package dgsw.hs.kr.gatchigachi

import android.content.Intent
import android.content.pm.ActivityInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.httpPost
import com.google.gson.Gson
import dgsw.hs.kr.gatchigachi.activity.MainActivity
import dgsw.hs.kr.gatchigachi.database.DBHelper
import dgsw.hs.kr.gatchigachi.model.User
import dgsw.hs.kr.gatchigachi.network.Network
import dgsw.hs.kr.gatchigachi.preference.Preference
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {

    lateinit var myDb : DBHelper
    val network =  Network()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        myDb = DBHelper(this)
        val preference = Preference(this)

        requestedOrientation = (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        setContentView(R.layout.activity_login)

        edit_login_id.setText("admin")
        edit_login_pw.setText("admin1")

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
                val code = network.networt_Login(id,pw,myDb,preference)
                // 중복 등 데이터 확인
                if (code == 100) {
                    val nt : Int = network.network_GetAllTeam(myDb,preference)
                    startActivity(nextIntent)
                }
            }
        }
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

        else if (pw.length < 1) {
            Toast.makeText(this, "8자 이상의 비밀번호를 입력하세요", Toast.LENGTH_SHORT).show()
            edit_login_pw.requestFocus()
            return -1
        }



        return 1

    }
}
