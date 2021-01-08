package fr.weelders.elitetools

import android.os.Bundle
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.MultiAutoCompleteTextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.net.ConnectException
import java.net.SocketTimeoutException

class SystemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_system)


        //The keybord's window cover the layout without adjust him
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)

        //------------------------------------------------------------------------------------
        // Import Views
        //------------------------------------------------------------------------------------
        val et_system_name = findViewById<MultiAutoCompleteTextView>(R.id.et_system_name)
        val btn_system_search = findViewById<Button>(R.id.btn_system_search)
        val btn_system_locate = findViewById<Button>(R.id.btn_system_locate)
        val rv_system = findViewById<RecyclerView>(R.id.rv_system)

        //Remove message "RecyclerView: No adapter attached; skipping layout" in logs
        rv_system.layoutManager = LinearLayoutManager(this)
        rv_system.adapter = RecyclerViewAdapterSystem(ArrayList<ComplexeStations>(), this)

        //------------------------------------------------------------------------------------
        // OnClickListener
        //------------------------------------------------------------------------------------

        btn_system_search.setOnClickListener {
            btn_system_search.startAnimation(AnimationUtils.loadAnimation(this, R.anim.speed_bounce))
            var systemName = et_system_name.text.toString()

            try {
                systemName = userInputCheck(systemName, this)
                if (systemName.length < 3) {
                    Toast.makeText(this, this.getString(R.string.error_name_tooSmall), Toast.LENGTH_SHORT).show()
                } else {
                    GlobalScope.launch(Dispatchers.IO) {
                        printSystem(rv_system, systemName)
                    }
                }
            } catch (e: UserInputException) {
                e.printStackTrace()
                Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun printSystem(rv_system: RecyclerView, systemName: String) {
        try {
            val listSystem = getSystem(systemName)
            if (listSystem::class == String::class) {
                runOnUiThread {
                    Toast.makeText(this, listSystem.toString(), Toast.LENGTH_SHORT).show()
                }
            } else if ((listSystem as List<ComplexeStations>).isEmpty()) {
                runOnUiThread {
                    Toast.makeText(this, this.getString(R.string.error_name_doestExist), Toast.LENGTH_SHORT).show()
                }
            } else {
                runOnUiThread {
                    rv_system.adapter = RecyclerViewAdapterSystem(listSystem, this)
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
}