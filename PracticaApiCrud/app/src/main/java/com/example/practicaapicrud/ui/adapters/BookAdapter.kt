package com.example.practicaapicrud.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.practicaapicrud.api.dto.Book
import com.example.practicaapicrud.api.dto.Books
import com.example.practicaapicrud.databinding.BookListItemLayoutBinding

class BookAdapter(
    private var data: Books
) : RecyclerView.Adapter<BookAdapter.ViewHolder>() {
    private var listener: OnBookClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = BookListItemLayoutBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item, listener)
    }

    fun setData(newData: Books) {
        this.data = newData
        notifyDataSetChanged()
    }

    fun setOnBookClickListener(listener: OnBookClickListener) {
        this.listener = listener
    }

    fun removeItem(bookDeleted: Book) {
        val index = data.indexOfFirst { it.id == bookDeleted.id }
        Log.d("Book", "Removing item at index $index")
        data.removeAt(index)
        notifyItemRemoved(index)
    }

    fun updateItem(bookUpdated: Book?) {
        val index = data.indexOfFirst { it.id == bookUpdated?.id }
        if (index != -1) {
            data[index] = bookUpdated!!
            notifyItemChanged(index)
        }
    }

    fun addItem(bookInserted: Book?) {
        if (data.size == 0) {
            data.add(bookInserted!!)
            notifyItemInserted(0)
            return
        }
        data.add(1, bookInserted!!)
        notifyItemInserted(1)
    }

    class ViewHolder(private val binding: BookListItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Book, listener: OnBookClickListener?) {
            binding.lblBookName.text = item.nombre
            binding.lblBookAuthor.text = item.autor
            Glide.with(binding.root.context)
                .load(item.imagen)
                .into(binding.imgBookCover)
            binding.root.setOnClickListener {
                listener?.onBookClick(item)
            }
            binding.btnDeleteBook.setOnClickListener {
                listener?.onBookDeleteClick(item)
            }
        }
    }

    interface OnBookClickListener {
        fun onBookClick(book: Book)
        fun onBookDeleteClick(book: Book)
    }

}

