package dgsw.hs.kr.gatchigachi

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Toast
import dgsw.hs.kr.gatchigachi.R
import dgsw.hs.kr.gatchigachi.R.layout.activity_login
import dgsw.hs.kr.gatchigachi.R.layout.activity_sign2
import kotlinx.android.synthetic.main.activity_sign.*

class SignActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign)

        btn_sign_to_login.setOnClickListener{
            val nextIntent = Intent(this, LoginActivity::class.java)
            startActivity(nextIntent)
        }

        btn_sign_to_sign2.setOnClickListener {
            val nextIntent = Intent(this, Sign2Activity::class.java)

            val name : String = edit_sign_name.text.toString()
            val id : String = edit_sign_id.text.toString()
            val pw : String = edit_sign_pw.text.toString()
            val pw2 : String = edit_sign_PWcheck.text.toString()
            val phone : String = edit_sign_phone.text.toString()

            if(Check(name, id,pw, pw2, phone) == 1){
                nextIntent.putExtra("ToSign2",name)
                nextIntent.putExtra("ToSign2",id)
                nextIntent.putExtra("ToSign2",pw)
                nextIntent.putExtra("ToSign2",pw2)
                nextIntent.putExtra("ToSign2",phone)
                startActivity(nextIntent)
            }
        }

        btn_sign_do_sign.setOnClickListener {
            val nextIntent = Intent(this, LoginActivity::class.java)
            val name : String = edit_sign_name.text.toString()
            val id : String = edit_sign_id.text.toString()
            val pw : String = edit_sign_pw.text.toString()
            val pw2 : String = edit_sign_PWcheck.text.toString()
            val phone : String = edit_sign_phone.text.toString()

            if(Check(name, id, pw, pw2, phone) == 1){
                //중복 등 데이터 확인
                startActivity(nextIntent)
            }

        }
    }

    fun Check(name :String, id : String, pw: String, pw2 : String, phone : String) : Int{
        if (name.length == 0){
            Toast.makeText(this, "이름을 입력하세요", Toast.LENGTH_SHORT).show()
            edit_sign_name.requestFocus()
            return -1
        }
        if (id.length == 0){
            Toast.makeText(this, "ID를 입력하세요", Toast.LENGTH_SHORT).show()
            edit_sign_id.requestFocus()
            return -1
        }
        if (pw.length == 0){
            Toast.makeText(this, "비밀번호를 입력하세요", Toast.LENGTH_SHORT).show()
            edit_sign_pw.requestFocus()
            return -1
        }
        else if(pw.length < 8){
            Toast.makeText(this,"8자 이상 비밀번호를 입력하세요", Toast.LENGTH_SHORT).show()
            edit_sign_pw.requestFocus()
            return -1
        }
        else if(!pw.equals(pw2)){
            Toast.makeText(this,"비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
            edit_sign_PWcheck.requestFocus()
            return -1
        }
        if (phone.length == 0){
            Toast.makeText(this, "전화번호를 입력하세요", Toast.LENGTH_SHORT).show()
            edit_sign_phone.requestFocus()
            return -1
        }
        return 1
    }
}
