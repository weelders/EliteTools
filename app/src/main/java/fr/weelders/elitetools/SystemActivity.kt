package fr.weelders.elitetools

import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SystemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_system)

        //The keybord's window cover the layout without adjust him
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)

        //------------------------------------------------------------------------------------
        // Import Views
        //------------------------------------------------------------------------------------
        val et_system_name = findViewById<EditText>(R.id.et_system_name)
        val btn_system_search = findViewById<Button>(R.id.btn_system_search)
        val btn_system_locate = findViewById<Button>(R.id.btn_system_locate)

        //------------------------------------------------------------------------------------
        // OnClickListener
        //------------------------------------------------------------------------------------

        btn_system_search.setOnClickListener {
            var systemName = et_system_name.text.toString()

            try {
                systemName = userInputCheck(systemName, this)
            } catch (e: UserInputException) {
                Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
            }


        }


    }
}