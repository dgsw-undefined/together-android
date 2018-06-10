package dgsw.hs.kr.gatchigachi.activity

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.github.kittinunf.fuel.httpPost
import com.google.gson.Gson
import dgsw.hs.kr.gatchigachi.LoginActivity
import dgsw.hs.kr.gatchigachi.R
import dgsw.hs.kr.gatchigachi.model.User
import kotlinx.android.synthetic.main.activity_sign4.*

class Sign4Activity :AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        setContentView(R.layout.activity_sign4)

        val intent = getIntent()

        val id : String = intent.getStringExtra("id")
        val pw : String = intent.getStringExtra("pw")
        val name : String = intent.getStringExtra("name")
        val phone : String = intent.getStringExtra("phone")
        val email : String = intent.getStringExtra("email")
        val tec : String = intent.getStringExtra("tec")
        val interested : String = intent.getStringExtra("interested")
        val position : String = intent.getStringExtra("position")

        btn_sign4_sign.setOnClickListener {
            val nextIntent = Intent(this, LoginActivity::class.java)
            startActivity(nextIntent)
        }
        /*val tecArray = tec.split(" ".toRegex())
        val user = User(name,id,pw,phone,tecArray.toTypedArray(),interested,github,field,position,mail)*/
    }

    private fun signUpNt(user: User){
        val URL = "http://115.68.182.229/go/user/signup"
        URL.httpPost()
                .header(Pair("Content-Type", "application/json"))
                .body(Gson().toJson(user))
                .responseObject(User.Deserializer()) { request, response, result ->
                    Toast.makeText(this, response.toString(), Toast.LENGTH_SHORT).show()
                    println(response.toString())
                    println(request.toString())
                }
    }

    //private fun Check() git, field 검사

}