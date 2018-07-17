package dgsw.hs.kr.gatchigachi.activity

import android.content.Intent
import android.net.Uri
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

        val uri : Uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.splash)
        splash_video.setVideoURI(uri)
        splash_video.start()

        val startAnim = AnimationUtils.loadAnimation(this, R.anim.splash_start)

        splash_video.setOnClickListener {
            val nextIntent = Intent(this, LoginActivity::class.java)
            startActivity(nextIntent)
            this.finish()
        }



    }


}
