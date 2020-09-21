package fr.weelders.elitetools

import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.MultiAutoCompleteTextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.concurrent.thread

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
        rv_system.adapter = RecyclerViewAdapterSystem(ArrayList<Docs>(), this)

        //------------------------------------------------------------------------------------
        // OnClickListener
        //------------------------------------------------------------------------------------

        btn_system_search.setOnClickListener {
            var systemName = et_system_name.text.toString()

            try {
                systemName = userInputCheck(systemName, this)
                thread {
                    try {
                        val listSystem = getSystem(systemName)
                        runOnUiThread {
                            rv_system.adapter = RecyclerViewAdapterSystem(listSystem, this)
                        }
                    } catch (e: Exception) {
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