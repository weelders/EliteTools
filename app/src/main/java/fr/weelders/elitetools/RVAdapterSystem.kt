package fr.weelders.elitetools

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.rv_row_system.view.*

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val tv_rvSystem_name = view.tv_rvSystem_name
    val tv_rvSystem_allegiance = view.tv_rvSystem_allegiance
    val tv_rvSystem_power = view.tv_rvSystem_power
    val tv_rvSystem_economy = view.tv_rvSystem_economy
    val tv_rvSystem_security = view.tv_rvSystem_security
}

class RecyclerViewAdapterSystem(val systemList: ArrayList<Docs>, val context: Context) :
    RecyclerView.Adapter<ViewHolder>() {

    override fun getItemCount(): Int {
        return systemList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.rv_row_system, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val system = systemList[position]
        holder.tv_rvSystem_name.setText(makeFistCharCapital(system.name))
        holder.tv_rvSystem_allegiance.setText(makeFistCharCapital(system.allegiance))
        holder.tv_rvSystem_economy.setText(makeFistCharCapital(system.primary_economy))
        holder.tv_rvSystem_power.setText(makeFistCharCapital(system.power))
        holder.tv_rvSystem_security.setText(makeFistCharCapital(system.security))
    }
}