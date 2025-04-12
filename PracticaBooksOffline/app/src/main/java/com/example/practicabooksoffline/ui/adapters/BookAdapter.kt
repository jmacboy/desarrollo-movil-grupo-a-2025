package com.example.practicabooksoffline.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.practicabooksoffline.databinding.FragmentBookItemBinding
import com.example.practicabooksoffline.db.models.Book
import java.util.ArrayList


/**
 * [RecyclerView.Adapter] that can display a [Book].
 */
class BookAdapter(
    private var values: List<Book>
) : RecyclerView.Adapter<BookAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentBookItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.idView.text = item.id.toString()
        holder.contentView.text = item.nombre
    }

    override fun getItemCount(): Int = values.size
    fun setData(newValues: ArrayList<Book>) {
        this.values = newValues
        notifyDataSetChanged()
    }

    inner class ViewHolder(binding: FragmentBookItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val idView: TextView = binding.itemNumber
        val contentView: TextView = binding.content

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

}