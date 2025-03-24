package com.example.practicarecyclerview.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practicarecyclerview.databinding.PersonItemViewBinding
import com.example.practicarecyclerview.models.Person

class PersonAdapter(
    private var data: MutableList<Person>
) : RecyclerView.Adapter<PersonAdapter.ViewHolder>() {
    private var listener: OnPersonClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PersonItemViewBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item, listener)
    }

    fun setData(newData: MutableList<Person>) {
        this.data = newData
        notifyDataSetChanged()
    }

    fun setOnPersonClickListener(listener: OnPersonClickListener) {
        this.listener = listener
    }

    fun removeItem(index: Int) {
        Log.d("Person", "Removing item at index $index")
        notifyItemRemoved(index)
    }

    fun updateById(sentId: Int) {
        val index = data.indexOfFirst { it.id == sentId }
        if (index != -1) {
            notifyItemChanged(index)
        }
    }

    class ViewHolder(private val binding: PersonItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Person, listener: OnPersonClickListener?) {
            binding.lblPhone.text = item.phone
            binding.lblFullName.text = "${item.name} ${item.lastName}"
            binding.root.setOnClickListener {
                listener?.onPersonClick(item)
            }
            binding.btnDeletePerson.setOnClickListener {
                listener?.onPersonDeleteClick(item)
            }
        }
    }

    interface OnPersonClickListener {
        fun onPersonClick(person: Person)
        fun onPersonDeleteClick(person: Person)
    }
}

