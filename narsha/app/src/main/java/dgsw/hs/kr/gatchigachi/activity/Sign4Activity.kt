package dgsw.hs.kr.gatchigachi.activity

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.github.kittinunf.fuel.httpPost
import com.google.gson.Gson
import dgsw.hs.kr.gatchigachi.LoginActivity
import dgsw.hs.kr.gatchigachi.R
import dgsw.hs.kr.gatchigachi.SignActivity
import dgsw.hs.kr.gatchigachi.database.DBHelper
import dgsw.hs.kr.gatchigachi.model.User
import dgsw.hs.kr.gatchigachi.network.Network
import kotlinx.android.synthetic.main.activity_sign4.*

class Sign4Activity :AppCompatActivity() {

    val network = Network()
    lateinit var myDb : DBHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        setContentView(R.layout.activity_sign4)

        val intent = intent
        myDb = DBHelper(this)



        btn_sign4_sign.setOnClickListener {

            val id : String = intent.getStringExtra("id")
            val pw : String = intent.getStringExtra("pw")
            val name : String = intent.getStringExtra("name")
            val phone : String = intent.getStringExtra("phone")
            val email : String = intent.getStringExtra("email")
            val tec : String = intent.getStringExtra("tec")
            val inter : String = intent.getStringExtra("interested")
            val github : String = edit_sign4_github.text.toString()
            val field : String = edit_sign4_field.text.toString()
            val position : String = intent.getStringExtra("position")
            val profile = "null"

            val tecArray = tec.split(" ".toRegex())
            val user = User(null,id,name,pw,email,inter,github,profile,field,tecArray,position,phone)

            network.signUpNt(user)

            val nextIntent = Intent(this, LoginActivity::class.java)
            startActivity(nextIntent)
            this.finish()
        }

    }

    override fun onResume() {
        super.onResume()

        edit_sign4_field.setText(null)
        edit_sign4_github.setText(null)
    }

}