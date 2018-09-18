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
import org.jetbrains.anko.custom.async
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.doAsyncResult
import org.json.JSONObject
import android.content.DialogInterface
import android.os.Looper
import android.support.v7.app.AlertDialog
import dgsw.hs.kr.gatchigachi.activity.LookForActivity


class LoginActivity : AppCompatActivity() {

    lateinit var myDb : DBHelper
    val network =  Network()
    var code :Int =100
    var nextIntent = Intent()
    var userIdx = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        nextIntent = Intent(this, MainActivity::class.java)


        myDb = DBHelper(this)
        val user = myDb.selectMyInfo()

        requestedOrientation = (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        setContentView(R.layout.activity_login)

        if(user!= null){
            userIdx = user.idx!!.toInt()
            notifyFinish(100.toLong())
        }

        btn_login_to_sign.setOnClickListener {
            val nextIntent = Intent(this, SignActivity::class.java)
            val animation = AnimationUtils.loadAnimation(this, R.anim.button_anim)
            btn_login_to_sign.startAnimation(animation)
            startActivity(nextIntent)

        }



        btn_login_do_login.setOnClickListener {

            val animation = AnimationUtils.loadAnimation(this, R.anim.button_anim)
            btn_login_do_login.startAnimation(animation)
            val id: String = edit_login_id.text.toString()
            val pw: String = edit_login_pw.text.toString()

            Log.e("a","a")

            if (check(id, pw) == 1) {
                network.login(id,pw,myDb,this)
            }


        }
    }

    fun notifyFinish(userIdx :Long){
        if (userIdx.toInt() == 100){
            network.getTeam(myDb, this.userIdx,this,true,0)
            return
        }
        this.userIdx = userIdx.toInt()
        network.getTeam(myDb,userIdx.toInt(),this,true,0)
    }

    fun notifyFinish(code:Int){
        if (code > 1000){
            nextIntent.putExtra("isTeamListEmpty", 1)
        }
        network.getTeamList(myDb)
        network.getUserList(myDb)
        nextIntent.putExtra("userIdx", userIdx)
        startActivity(nextIntent)
        finish()
    }

    fun check(id: String, pw: String): Int {

        if (id.isEmpty()) {
            Toast.makeText(this, "ID를 입력하세요", Toast.LENGTH_SHORT).show()
            edit_login_id.requestFocus()
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
