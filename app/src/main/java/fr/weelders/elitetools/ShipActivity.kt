package fr.weelders.elitetools

import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.net.ConnectException
import java.net.SocketTimeoutException
import kotlin.concurrent.thread

class ShipActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ship)

        //The keybord's window cover the layout without adjust him
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)

        //------------------------------------------------------------------------------------
        // Import Views
        //------------------------------------------------------------------------------------

        val spinner_ship = findViewById<Spinner>(R.id.spinner_ship)
        val seekBar_ship = findViewById<SeekBar>(R.id.seekBar_ship)
        val tv_ship_selectDistance = findViewById<TextView>(R.id.tv_ship_selectDistance)
        val et_ship_name = findViewById<EditText>(R.id.et_ship_name)
        val btn_ship_search = findViewById<Button>(R.id.btn_ship_search)
        val btn_ship_locate = findViewById<Button>(R.id.btn_ship_locate)
        val rv_ship = findViewById<RecyclerView>(R.id.rv_ship)
        val pb_ship = findViewById<ProgressBar>(R.id.pb_ship)

        //------------------------------------------------------------------------------------
        // SETUP VIEWS
        //------------------------------------------------------------------------------------

        //Setup adapter for spinner with ressources String Array in string.xml
        ArrayAdapter.createFromResource(
            this, R.array.spinner_ship_array, android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner_ship.adapter = adapter
        }

        //Remove message "RecyclerView: No adapter attached; skipping layout" in logs
        rv_ship.layoutManager = LinearLayoutManager(this)
        rv_ship.adapter = RecyclerViewAdapterShip(ArrayList(), this)

        //------------------------------------------------------------------------------------
        // SEEKBAR LISTENER
        //------------------------------------------------------------------------------------

        seekBar_ship.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                when (p0?.progress) {
                    0 -> tv_ship_selectDistance.text = "10 AL"
                    1 -> tv_ship_selectDistance.text = "20 AL"
                    2 -> tv_ship_selectDistance.text = "30 AL"
                    3 -> tv_ship_selectDistance.text = "40 AL"
                    4 -> tv_ship_selectDistance.text = "50 AL"
                    5 -> tv_ship_selectDistance.text = "60 AL"
                    6 -> tv_ship_selectDistance.text = "70 AL"
                    7 -> tv_ship_selectDistance.text = "80 AL"
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }
        })

        //------------------------------------------------------------------------------------
        // OnClickListener
        //------------------------------------------------------------------------------------

        btn_ship_search.setOnClickListener {
            btn_ship_search.startAnimation(AnimationUtils.loadAnimation(this, R.anim.speed_bounce))
            var systemName = et_ship_name.text.toString()
            val shipName = spinner_ship.selectedItem.toString()
            var distance = seekBar_ship.progress

            when (distance) {
                0 -> distance = 10
                1 -> distance = 20
                2 -> distance = 30
                3 -> distance = 40
                4 -> distance = 50
                5 -> distance = 60
                6 -> distance = 70
                7 -> distance = 80
            }

            try {
                btn_ship_search.isEnabled = false
                pb_ship.visibility = View.VISIBLE
                systemName = userInputCheck(systemName, this)
                thread {
                    try {
                        val listShipStation = getShips(systemName, distance, shipName)
                        if (listShipStation::class == String::class) {
                            println(listShipStation.toString())
                            runOnUiThread {
                                Toast.makeText(this, listShipStation.toString(), Toast.LENGTH_SHORT).show()
                            }
                        } else {
                            runOnUiThread {
                                rv_ship.adapter = RecyclerViewAdapterShip(listShipStation as List<ShipSystemDistance>, this)
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
                            Toast.makeText(this, getString(R.string.error_generic), Toast.LENGTH_SHORT).show()
                        }
                    } finally {
                        runOnUiThread {
                            btn_ship_search.isEnabled = true
                            pb_ship.visibility = View.INVISIBLE
                        }
                    }
                }
            } catch (e: UserInputException) {
                e.printStackTrace()
                Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
            }
        }

        btn_ship_locate.setOnClickListener {
            Toast.makeText(this, "Work in progress", Toast.LENGTH_SHORT).show()
        }
    }
}