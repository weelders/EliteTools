package fr.weelders.elitetools

import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //------------------------------------------------------------------------------------
        // Import Views
        //------------------------------------------------------------------------------------

        val btn_news = findViewById<Button>(R.id.btn_news)
        val btn_galnet = findViewById<Button>(R.id.btn_galnet)
        val btn_system = findViewById<Button>(R.id.btn_system)
        val btn_ship = findViewById<Button>(R.id.btn_ship)
        val btn_commodity = findViewById<Button>(R.id.btn_commodity)
        val btn_distance = findViewById<Button>(R.id.btn_distance)
        val btn_account = findViewById<Button>(R.id.btn_account)
        val btn_option = findViewById<Button>(R.id.btn_option)
        val btn_about = findViewById<Button>(R.id.btn_about)


        //------------------------------------------------------------------------------------
        // ANIMATION
        //------------------------------------------------------------------------------------

        //val bounceAnim = AnimationUtils.loadAnimation(this,R.anim.bounce)
        val speedBounceAnim = AnimationUtils.loadAnimation(this, R.anim.speed_bounce)
        //val interpolator = MyBounceInterpolator(0.2,20.0)
        //bounceAnim.interpolator = interpolator

        btn_news.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_left))
        btn_galnet.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_right))
        btn_system.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_left))
        btn_ship.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_right))
        btn_commodity.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_left))
        btn_distance.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_right))
        btn_account.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_left))
        btn_option.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_right))
        btn_about.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_bottom))


        //------------------------------------------------------------------------------------
        // OnClickListener
        //------------------------------------------------------------------------------------
        buttonListener(btn_news, speedBounceAnim)

        buttonListener(btn_galnet, speedBounceAnim,Intent(this,GalnetActivity::class.java))

        buttonListener(btn_system, speedBounceAnim, Intent(this, SystemActivity::class.java))

        buttonListener(btn_ship, speedBounceAnim, Intent(this, ShipActivity::class.java))

        buttonListener(btn_commodity, speedBounceAnim)

        buttonListener(btn_distance, speedBounceAnim, Intent(this, DistanceActivity::class.java))

        buttonListener(btn_account, speedBounceAnim)

        buttonListener(btn_option, speedBounceAnim)

        buttonListener(btn_about, speedBounceAnim)
    }

    private fun buttonListener(button: Button, animation: Animation, intent: Intent) {
        button.setOnClickListener {
            animation.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(p0: Animation?) {}


                override fun onAnimationEnd(p0: Animation?) {
                    startActivity(intent)
                }

                override fun onAnimationRepeat(p0: Animation?) {}
            })
            button.startAnimation(animation)
        }
    }

    fun buttonListener(button: Button, animation: Animation) {
        button.setOnClickListener {
            animation.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(p0: Animation?) {}


                override fun onAnimationEnd(p0: Animation?) {
                }

                override fun onAnimationRepeat(p0: Animation?) {}
            })
            button.startAnimation(animation)
        }
    }


}