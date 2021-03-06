package fr.weelders.elitetools

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.net.ConnectException
import java.net.SocketTimeoutException
import kotlin.Exception
import kotlin.concurrent.thread

class GalnetActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_galnet)

        //------------------------------------------------------------------------------------
        // Import Views
        //------------------------------------------------------------------------------------
        val rv_galnet = findViewById<RecyclerView>(R.id.rv_galnet)
        val pb_galnet = findViewById<ProgressBar>(R.id.pb_galnet)
        rv_galnet.layoutManager = LinearLayoutManager(this)

        try {
            pb_galnet.visibility = View.VISIBLE
            thread {
                try {
                    val news = getGalnetNews()
                    if (news != null) {
                        runOnUiThread {
                            rv_galnet.adapter = RecyclerViewAdapterGalnet(news, this@GalnetActivity)
                        }
                    } else {
                        runOnUiThread {
                            Toast.makeText(applicationContext, getString(R.string.error_generic), Toast.LENGTH_SHORT).show()
                        }
                    }
                }catch (e:Exception){
                    e.printStackTrace()
                    runOnUiThread {
                        Toast.makeText(this, getString(R.string.error_generic), Toast.LENGTH_SHORT).show()
                    }
                }finally {
                    runOnUiThread {
                        pb_galnet.visibility = View.GONE
                    }
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