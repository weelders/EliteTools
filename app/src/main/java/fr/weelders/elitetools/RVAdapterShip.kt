package fr.weelders.elitetools

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewHolderShip(view: View):RecyclerView.ViewHolder(view)
{
    val tv_rvShip_systemName = view.findViewById<TextView>(R.id.tv_rvShip_systemName)
    val tv_rvShip_stationName = view.findViewById<TextView>(R.id.tv_rvShip_stationName)
    val tv_rvShip_distance = view.findViewById<TextView>(R.id.tv_rvShip_distance)
    val tv_rvShip_starDistance = view.findViewById<TextView>(R.id.tv_rvShip_starDistance)
    val tv_rvShip_landing = view.findViewById<TextView>(R.id.tv_rvShip_landing)
}

class RecyclerViewAdapterShip(val listShipDistance: List<ShipSystemDistance>, val context: Context): RecyclerView.Adapter<ViewHolderShip>()
{
    override fun getItemCount(): Int {
        return listShipDistance.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderShip {
        return ViewHolderShip(
            LayoutInflater.from(context).inflate(R.layout.rv_row_ship, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolderShip, position: Int) {
        val systemDistance = listShipDistance[position]
        holder.tv_rvShip_systemName.text = makeFistCharCapital(systemDistance.shipSystem.name_system)
        holder.tv_rvShip_stationName.text = makeFistCharCapital(systemDistance.shipSystem.name_station)
        holder.tv_rvShip_distance.text ="${systemDistance.distance} AL"
        holder.tv_rvShip_starDistance.text = "${systemDistance.shipSystem.distance_to_star} LS"
        holder.tv_rvShip_landing.text = systemDistance.shipSystem.max_landing_pad_size
    }
}