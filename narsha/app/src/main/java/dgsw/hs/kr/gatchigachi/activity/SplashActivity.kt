package dgsw.hs.kr.gatchigachi.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import dgsw.hs.kr.gatchigachi.LoginActivity
import dgsw.hs.kr.gatchigachi.R
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val startanim = AnimationUtils.loadAnimation(this, R.anim.splash_start)
        val endanim = AnimationUtils.loadAnimation(this, R.anim.splash_end)

        start_splash.startAnimation(startanim)
        end_splash.startAnimation(endanim)

        val nextIntent = Intent(this, LoginActivity::class.java)
        startActivity(nextIntent)
    }
}
