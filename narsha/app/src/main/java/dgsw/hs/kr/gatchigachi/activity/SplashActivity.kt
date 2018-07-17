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

class SplashActivity : AppCompatActivity() {

    private var mDelayHandler: Handler? = null
    private val SPLASH_DELAY: Long = 3000

    internal val mRunnable: Runnable = Runnable {
        if (!isFinishing) {
            val intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        mDelayHandler = Handler() 

        //Navigate with delay
        mDelayHandler!!.postDelayed(mRunnable, SPLASH_DELAY)

        val uri : Uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.splash)
        splash_video.setVideoURI(uri)
        splash_video.start()

        val startAnim = AnimationUtils.loadAnimation(this, R.anim.splash_start)

//        splash_video.setOnClickListener {
//            val nextIntent = Intent(this, LoginActivity::class.java)
//            startActivity(nextIntent)
//            this.finish()
//        }



    }


}
