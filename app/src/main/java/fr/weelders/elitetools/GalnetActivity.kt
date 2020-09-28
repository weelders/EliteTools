package fr.weelders.elitetools

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception

class GalnetActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_galnet)

        //------------------------------------------------------------------------------------
        // Import Views
        //------------------------------------------------------------------------------------
        val rv_galnet = findViewById<RecyclerView>(R.id.rv_galnet)
        rv_galnet.layoutManager = LinearLayoutManager(this)

        try {
            GlobalScope.launch(Dispatchers.IO) {
                val news = getGalnetNews()
                if (news != null)
                {
                    runOnUiThread {
                        rv_galnet.adapter = RecyclerViewAdapterGalnet(news,this@GalnetActivity)
                    }
                }
                else
                {
                    runOnUiThread {
                        Toast.makeText(applicationContext, "Un problème est survenu", Toast.LENGTH_SHORT).show()
                    }
                }

            }
        }catch (e:Exception)
        {
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        }

    }
}