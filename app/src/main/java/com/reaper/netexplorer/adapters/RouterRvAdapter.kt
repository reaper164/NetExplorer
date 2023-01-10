package com.reaper.netexplorer.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.reaper.netexplorer.R
import com.reaper.netexplorer.model.Router

class RouterRvAdapter(private val routerList: List<Router>) :
    RecyclerView.Adapter<RouterRvAdapter.RouterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RouterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.router_item, parent, false)
        return RouterViewHolder(view)
    }

    override fun onBindViewHolder(holder: RouterViewHolder, position: Int) {
        holder.bind(routerList[position])
    }

    override fun getItemCount(): Int = routerList.size

    class RouterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ip = view.findViewById<TextView?>(R.id.router_ip)
        val model = view.findViewById<TextView?>(R.id.router_model)
        val auth = view.findViewById<TextView?>(R.id.router_auth)
        val ssid = view.findViewById<TextView?>(R.id.router_ssid)
        val macadr = view.findViewById<TextView?>(R.id.router_mac)
        val psk = view.findViewById<TextView?>(R.id.router_password)
        val status = view.findViewById<TextView?>(R.id.router_progress)
        val progress = view.findViewById<android.widget.ImageView?>(R.id.routerscan_prog)


        fun bind(router: Router) {
            ip.text = router.ip
            progress.setColorFilter(Color.GREEN)
            ssid.text = router.ssid
            psk.text = router.psk
            auth.text = router.auth

            ssid.visibility = View.VISIBLE
            psk.visibility = View.VISIBLE
            auth.visibility = View.VISIBLE
        }
    }
}