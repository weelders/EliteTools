package fr.weelders.elitetools

import android.content.Intent
import android.os.Bundle
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
        // OnClickListener
        //------------------------------------------------------------------------------------

        btn_system.setOnClickListener {
            val intentSystem = Intent(this, SystemActivity::class.java)
            startActivity(intentSystem)
        }
    }


}