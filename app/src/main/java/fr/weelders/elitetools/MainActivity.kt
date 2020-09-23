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

        val bounceAnim = AnimationUtils.loadAnimation(this,R.anim.bounce)
        val speedBounceAnim = AnimationUtils.loadAnimation(this,R.anim.speed_bounce)
        val interpolator = MyBounceInterpolator(0.2,20.0)
        bounceAnim.interpolator = interpolator

        btn_news.startAnimation(bounceAnim)
        btn_galnet.startAnimation(bounceAnim)
        btn_system.startAnimation(bounceAnim)
        btn_ship.startAnimation(bounceAnim)
        btn_commodity.startAnimation(bounceAnim)
        btn_distance.startAnimation(bounceAnim)
        btn_account.startAnimation(bounceAnim)
        btn_option.startAnimation(bounceAnim)
        btn_about.startAnimation(bounceAnim)


        //------------------------------------------------------------------------------------
        // OnClickListener
        //------------------------------------------------------------------------------------

        btn_system.setOnClickListener {
            val intentSystem = Intent(this, SystemActivity::class.java)
            speedBounceAnim.setAnimationListener(object : Animation.AnimationListener{
                override fun onAnimationStart(p0: Animation?) {

                }

                override fun onAnimationEnd(p0: Animation?) {
                    startActivity(intentSystem)
                }

                override fun onAnimationRepeat(p0: Animation?) {

                }

            })
            btn_system.startAnimation(speedBounceAnim)
        }

        btn_distance.setOnClickListener {
            val intentSystem = Intent(this, DistanceActivity::class.java)
            speedBounceAnim.setAnimationListener(object : Animation.AnimationListener{
                override fun onAnimationStart(p0: Animation?) {
                }

                override fun onAnimationEnd(p0: Animation?) {
                    startActivity(intentSystem)
                }

                override fun onAnimationRepeat(p0: Animation?) {
                }

            })
            btn_distance.startAnimation(speedBounceAnim)
        }

        btn_ship.setOnClickListener {
            val intentSystem = Intent(this, ShipActivity::class.java)
            startActivity(intentSystem)
        }
    }


}