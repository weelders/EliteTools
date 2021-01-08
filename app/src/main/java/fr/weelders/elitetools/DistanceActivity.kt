package fr.weelders.elitetools

import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.net.ConnectException
import java.net.SocketTimeoutException
import kotlin.concurrent.thread

class DistanceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_distance)

        //The keybord's window cover the layout without adjust him
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)

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
                        if (result::class == Double::class) {
                            runOnUiThread { tv_distance_result.text = "$result AL" }
                        } else {
                            println(result.toString())
                            runOnUiThread {
                                Toast.makeText(
                                    this,
                                    result.toString(),
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }

                    } catch (e: SocketTimeoutException) { //Timeout catch
                        e.printStackTrace()
                        runOnUiThread {
                            Toast.makeText(this, getString(R.string.server_no_response), Toast.LENGTH_SHORT).show()
                        }
                    } catch (e: ConnectException) { //Connection fail catch
                        e.printStackTrace()
                        runOnUiThread {
                            Toast.makeText(this, getString(R.string.error_connection), Toast.LENGTH_SHORT).show()
                        }
                    } catch (e: Exception) { //Generic catch
                        e.printStackTrace()
                        runOnUiThread {
                            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            } catch (e: UserInputException) {
                e.printStackTrace()
                Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}