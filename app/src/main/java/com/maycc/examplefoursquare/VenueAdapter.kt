package com.maycc.examplefoursquare

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.item_layout_venue.view.*

class VenueAdapter(var venues: ArrayList<Venue>) : RecyclerView.Adapter<VenueAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_layout_venue, null, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return venues.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val venue = venues[position]

        holder.tvNameVenue.text = venue.name
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNameVenue:TextView = itemView.tv_name_venue
    }

}