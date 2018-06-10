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

        val id: String = intent.getStringExtra("id")
        val pw: String = intent.getStringExtra("pw")

        btn_sign2_to_sign3.setOnClickListener {
            val nextIntent = Intent(this, Sign3Activity::class.java)
            val name : String = edit_sign2_name.text.toString()
            val phone : String = edit_sign2_phone.text.toString()
            val email : String = edit_sign2_email.text.toString()

            if(Check(name, phone, email) == 1){
                nextIntent.putExtra("name",name)
                nextIntent.putExtra("phone",phone)
                nextIntent.putExtra("email",email)
                nextIntent.putExtra("id",id)
                nextIntent.putExtra("pw",pw)
                startActivity(nextIntent)
            }

        }

    }



    private fun Check(name : String, phone : String, email : String) : Int{
        if(name.isEmpty()){
            Toast.makeText(this,"이름을 입력하세요", Toast.LENGTH_SHORT).show()
            edit_sign2_name.requestFocus()
            return -1
        }
        if(phone.isEmpty()){
            Toast.makeText(this,"전화번호를 입력하세요", Toast.LENGTH_SHORT).show()
            edit_sign2_phone.requestFocus()
            return -1
        }
        if(email.isEmpty()){
            Toast.makeText(this,"이메일을 입력하세요", Toast.LENGTH_SHORT).show()
            edit_sign2_email.requestFocus()
            return -1
        }

        return 1;

    }
}
