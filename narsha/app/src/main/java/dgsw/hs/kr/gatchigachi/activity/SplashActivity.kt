package dgsw.hs.kr.gatchigachi.activity

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import dgsw.hs.kr.gatchigachi.LoginActivity
import dgsw.hs.kr.gatchigachi.R
import kotlinx.android.synthetic.main.activity_splash.*
import android.support.v4.content.ContextCompat.startActivity



class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val uri : Uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.splash)
        splash_video.setVideoURI(uri)
        splash_video.start()

        val startAnim = AnimationUtils.loadAnimation(this, R.anim.splash_start)

//        Handler().postDelayed({
//            /* 메뉴액티비티를 실행하고 로딩화면을 죽인다.*/
//            val mainIntent = Intent(this, LoginActivity::class.java)
//            this.startActivity(mainIntent)
//            this.finish()
//        })

        splash_video.setOnClickListener {
            val nextIntent = Intent(this, LoginActivity::class.java)
            startActivity(nextIntent)
            this.finish()
        }



    }


}
