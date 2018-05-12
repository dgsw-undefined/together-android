package dgsw.hs.kr.gatchigachi

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_sign2.*

class Sign2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign2)
        val intent = getIntent()
        val name: String = intent.getStringExtra("name")
        val id: String = intent.getStringExtra("id")
        val pw: String = intent.getStringExtra("pw")
        val pw2: String = intent.getStringExtra("pw2")
        val phone: String = intent.getStringExtra("phone")

        btn_sign2_do_sign.setOnClickListener {
            val Button_Animation : Animation = AnimationUtils.loadAnimation(this,R.anim.button_anim)
            Button_Animation.start()
            val nextIntent = Intent(this, LoginActivity::class.java)

            val tec: String = edit_sign2_tec.text.toString()
            val position: String = edit_sign2_position.text.toString()
            val github: String = edit_sign2_github.text.toString()
            val field: String = edit_sign2_field.text.toString()
            val interested: String = edit_sign2_interested.text.toString()

            if(Check(tec, position, github, field, interested) == 1){
                if ( call_server() == 1){
                    startActivity(nextIntent)
                }
            }
        }

        btn_sign2_to_sign.setOnClickListener {
            val Button_Animation : Animation = AnimationUtils.loadAnimation(this,R.anim.button_anim)
            Button_Animation.start()
            val nextIntent = Intent(this, SignActivity::class.java)
            startActivity(nextIntent)
        }

    }
    fun call_server() : Int {
        return 1
    }

    fun Check(tec: String, position: String, github : String, field : String, interested : String) : Int{
        if(tec.length == 0){
            Toast.makeText(this,"기술을 입력하세요", Toast.LENGTH_SHORT).show()
            edit_sign2_tec.requestFocus()
            return -1
        }
        if(position.length == 0){
            Toast.makeText(this,"직책을 입력하세요", Toast.LENGTH_SHORT).show()
            edit_sign2_position.requestFocus()
            return -1
        }
        if(github.length == 0){
            Toast.makeText(this,"깃허브 주소를 입력하세요", Toast.LENGTH_SHORT).show()
            edit_sign2_github.requestFocus()
            return -1
        }
        if(field.length == 0){
            Toast.makeText(this,"전공을 입력하세요", Toast.LENGTH_SHORT).show()
            edit_sign2_field.requestFocus()
            return -1
        }
        if(interested.length == 0){
            Toast.makeText(this,"관심분야를 입력하세요", Toast.LENGTH_SHORT).show()
            edit_sign2_interested.requestFocus()
            return -1
        }
        return -1;
    }
}
