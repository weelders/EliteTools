package fr.weelders.elitetools

import android.content.Context
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class ViewHolderGalnet(view: View) : RecyclerView.ViewHolder(view) {
    val tv_rvGalnet_title = view.findViewById<TextView>(R.id.tv_rvGalnet_title)
    val tv_rvGalnet_date = view.findViewById<TextView>(R.id.tv_rvGalnet_date)
    val tv_rvGalnet_content = view.findViewById<TextView>(R.id.tv_rvGalnet_content)

}

class RecyclerViewAdapterGalnet(val galnetNews: GalnetNews, val context: Context) :
    RecyclerView.Adapter<ViewHolderGalnet>() {

    override fun getItemCount(): Int {
        return galnetNews.items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderGalnet {
        return ViewHolderGalnet(
            LayoutInflater.from(context).inflate(R.layout.rv_row_galnet, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolderGalnet, position: Int) {
        val news = galnetNews.items[position]
        holder.tv_rvGalnet_title.setText(news.title)
        holder.tv_rvGalnet_date.setText(news.pubDate)
        holder.tv_rvGalnet_content.setText(news.description)

    }
}