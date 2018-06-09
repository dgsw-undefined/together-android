package dgsw.hs.kr.gatchigachi

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.github.kittinunf.fuel.httpPost
import com.google.gson.Gson
import dgsw.hs.kr.gatchigachi.model.User
import kotlinx.android.synthetic.main.activity_sign2.*

class Sign2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        setContentView(R.layout.activity_sign2)
        val intent = getIntent()
        val name: String = intent.getStringExtra("name")
        val id: String = intent.getStringExtra("id")
        val pw: String = intent.getStringExtra("pw")
        val mail: String = intent.getStringExtra("mail")
        val phone: String = intent.getStringExtra("phone")

        btn_sign2_do_sign.setOnClickListener {
            val nextIntent = Intent(this, LoginActivity::class.java)
            val animation = AnimationUtils.loadAnimation(this,R.anim.button_anim)
            btn_sign2_do_sign.startAnimation(animation)
            val tec: String = edit_sign2_tec.text.toString()
            val position: String = edit_sign2_position.text.toString()
            val github: String = edit_sign2_github.text.toString()
            val field: String = edit_sign2_field.text.toString()
            val interested: String = edit_sign2_interested.text.toString()

            val tecArray = tec.split(" ".toRegex())

            if(Check(tec, position, github, field, interested) == 1){

                val user = User(name,id,pw,phone,tecArray.toTypedArray(),interested,github,field,position,mail)
                signUpNt(user)

            }

        }

        btn_sign2_to_sign.setOnClickListener {

            val nextIntent = Intent(this, SignActivity::class.java)
            val animation = AnimationUtils.loadAnimation(this,R.anim.button_anim)
            btn_sign2_to_sign.startAnimation(animation)
            startActivity(nextIntent)

        }

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

    private fun Check(tec: String, position: String, github : String, field : String, interested : String) : Int{
        if(tec.isEmpty()){
            Toast.makeText(this,"기술을 입력하세요", Toast.LENGTH_SHORT).show()
            edit_sign2_tec.requestFocus()
            return -1
        }
        if(position.isEmpty()){
            Toast.makeText(this,"직책을 입력하세요", Toast.LENGTH_SHORT).show()
            edit_sign2_position.requestFocus()
            return -1
        }
        if(github.isEmpty()){
            Toast.makeText(this,"깃허브 주소를 입력하세요", Toast.LENGTH_SHORT).show()
            edit_sign2_github.requestFocus()
            return -1
        }
        if(field.isEmpty()){
            Toast.makeText(this,"전공을 입력하세요", Toast.LENGTH_SHORT).show()
            edit_sign2_field.requestFocus()
            return -1
        }
        if(interested.isEmpty()){
            Toast.makeText(this,"관심분야를 입력하세요", Toast.LENGTH_SHORT).show()
            edit_sign2_interested.requestFocus()
            return -1
        }
        return 1
    }
}
