package fr.weelders.elitetools

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.rv_row_system.view.*

class ViewHolderSystem(view: View) : RecyclerView.ViewHolder(view) {
    val tv_rvSystem_name = view.tv_rvSystem_name
    val tv_rvSystem_allegiance = view.tv_rvSystem_allegiance
    val tv_rvSystem_power = view.tv_rvSystem_power
    val tv_rvSystem_economy = view.tv_rvSystem_economy
    val tv_rvSystem_security = view.tv_rvSystem_security
}

class RecyclerViewAdapterSystem(val systemList: List<ComplexeStations>, val context: Context) :
    RecyclerView.Adapter<ViewHolderSystem>() {

    override fun getItemCount(): Int {
        return systemList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderSystem {
        return ViewHolderSystem(
            LayoutInflater.from(context).inflate(R.layout.rv_row_system, parent, false)
        )
    }

    override fun onBindViewHolder(holderSystem: ViewHolderSystem, position: Int) {
        val system = systemList[position]
        holderSystem.tv_rvSystem_name.setText(makeFistCharCapital(system.systemPops.systemPops.name))
        holderSystem.tv_rvSystem_allegiance.setText(makeFistCharCapital(system.systemPops.systemPops.allegiance ?: "N/A"))
        holderSystem.tv_rvSystem_economy.setText(makeFistCharCapital(system.systemPops.systemPops.primary_economy ?: "N/A"))
        holderSystem.tv_rvSystem_power.setText(makeFistCharCapital(system.systemPops.systemPops.power ?: "N/A"))
        holderSystem.tv_rvSystem_security.setText(makeFistCharCapital(system.systemPops.systemPops.security ?: "N/A"))
    }
}