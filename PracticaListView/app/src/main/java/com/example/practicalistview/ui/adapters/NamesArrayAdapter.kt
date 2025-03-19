package com.example.practicalistview.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.practicalistview.R

class NamesArrayAdapter(context: Context, resource: Int, objects: Array<out String>) :
    ArrayAdapter<String>(context, resource, objects) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var row = convertView
        if (row == null) {
            row = LayoutInflater.from(context)
                .inflate(R.layout.list_item_view, parent, false)
        }
        val lblName = row?.findViewById<TextView>(R.id.lblName)
        lblName?.text = getItem(position)
        return row!!
    }
}