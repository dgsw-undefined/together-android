package dgsw.hs.kr.gatchigachi

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import dgsw.hs.kr.gatchigachi.R
import kotlinx.android.synthetic.main.activity_sign2.*

class Sign2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign2)
        val intent = getIntent()
        val name : String = intent.getStringExtra("name")
        val id : String = intent.getStringExtra("id")
        val pw : String = intent.getStringExtra("pw")
        val pw2 : String = intent.getStringExtra("pw2")
        val phone : String = intent.getStringExtra("phone")

        btn_sign2_do_sign.setOnClickListener {
            val nextIntent = Intent(this, LoginActivity::class.java)

            val tec : String = edit_sign2_tec.text.toString()
            val position : String = edit_sign2_position.text.toString()
            val github : String = edit_sign2_github.text.toString()
            val field : String = edit_sign2_field.text.toString()
            startActivity(nextIntent)
        }

        btn_sign2_to_sign.setOnClickListener {
            val nextIntent = Intent(this, SignActivity::class.java)
            startActivity(nextIntent)
        }

    }
}
