package dgsw.hs.kr.gatchigachi

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import dgsw.hs.kr.gatchigachi.R
import kotlinx.android.synthetic.main.activity_make_team.*

class MakeTeamActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_make_team)

        btn_make_do_make_team.setOnClickListener {
            val Button_Animation : Animation = AnimationUtils.loadAnimation(this,R.anim.button_anim)
            Button_Animation.start()

            val nextIntent = Intent(this, LoginActivity::class.java)
            startActivity(nextIntent)
        }

        btn_make_to_back.setOnClickListener {
            val Button_Animation : Animation = AnimationUtils.loadAnimation(this,R.anim.button_anim)
            Button_Animation.start()

            val nextIntent = Intent(this, LoginActivity::class.java)
            startActivity(nextIntent)
        }
    }


}
