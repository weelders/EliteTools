package fr.weelders.elitetools

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.concurrent.thread

class DistanceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_distance)

        //------------------------------------------------------------------------------------
        // Import Views
        //------------------------------------------------------------------------------------

        val et_distance_name1 = findViewById<EditText>(R.id.et_distance_name1)
        val et_distance_name2 = findViewById<EditText>(R.id.et_distance_name2)
        val btn_distance_search = findViewById<Button>(R.id.btn_distance_search)
        val btn_distance_locate = findViewById<Button>(R.id.btn_distance_locate)
        val tv_distance_result = findViewById<TextView>(R.id.tv_distance_result)

        //Reset TextView
        tv_distance_result.text = ""

        //------------------------------------------------------------------------------------
        // OnClickListener
        //------------------------------------------------------------------------------------

        btn_distance_search.setOnClickListener {
            var systemName1 = et_distance_name1.text.toString()
            var systemName2 = et_distance_name2.text.toString()
            try {
                systemName1 = userInputCheck(systemName1, this)
                systemName2 = userInputCheck(systemName2, this)
                thread {
                    try {
                        val result = getDistanceByNames(systemName1, systemName2)
                        runOnUiThread { tv_distance_result.text = "$result AL" }

                    } catch (e: Exception) {
                        e.printStackTrace()
                        runOnUiThread { Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show() }
                    }

                }
            } catch (e: UserInputException) {
                e.printStackTrace()
                Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}