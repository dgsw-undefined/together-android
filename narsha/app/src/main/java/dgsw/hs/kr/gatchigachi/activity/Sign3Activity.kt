package dgsw.hs.kr.gatchigachi

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_sign3.*

class Sign3Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        setContentView(R.layout.activity_sign3)

        val intent = getIntent()

        val id : String = intent.getStringExtra("id")
        val pw : String = intent.getStringExtra("pw")
        val pwc : String = intent.getStringExtra("pwc")
        val name : String = intent.getStringExtra("name")
        val phone : String = intent.getStringExtra("phone")
        val email : String = intent.getStringExtra("email")

        val tec : String = edit_sign3_tec.text.toString()
        val interested : String = edit_sign3_interested.text.toString()
        val position : String = edit_sign3_position.text.toString()


    }

    private fun Check(tec : String, interested : String, position : String) : Int{
        if(tec.isEmpty()){
            Toast.makeText(this, "기술을 입력하세요", Toast.LENGTH_SHORT).show()
            edit_sign3_tec.requestFocus()
            return -1
        }
        if(interested.isEmpty()){
            Toast.makeText(this, "관심사를 입력하세요", Toast.LENGTH_SHORT).show()
            edit_sign3_interested.requestFocus()
            return -1
        }
        if(position.isEmpty()){
            Toast.makeText(this, "직책을 입력하세요", Toast.LENGTH_SHORT).show()
            edit_sign3_position.requestFocus()
            return -1
        }

        return 1
    }

}