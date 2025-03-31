package com.example.practicaroom.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practicaroom.R
import com.example.practicaroom.databinding.PersonItemViewBinding
import com.example.practicaroom.db.models.PersonWithPhones

class PersonAdapter(
    private var data: MutableList<PersonWithPhones>
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

    fun setData(newData: MutableList<PersonWithPhones>) {
        this.data = newData
        notifyDataSetChanged()
    }

    fun setOnPersonClickListener(listener: OnPersonClickListener) {
        this.listener = listener
    }

    fun removeItem(personDeleted: PersonWithPhones) {
        val index = data.indexOfFirst { it.person.id == personDeleted.person.id }
        Log.d("Person", "Removing item at index $index")
        data.removeAt(index)
        notifyItemRemoved(index)
    }

    fun updateItem(personUpdated: PersonWithPhones?) {
        val index = data.indexOfFirst { it.person.id == personUpdated?.person?.id }
        if (index != -1) {
            data[index] = personUpdated!!
            notifyItemChanged(index)
        }
    }

    fun addItem(personInserted: PersonWithPhones?) {
        if (data.size == 0) {
            data.add(personInserted!!)
            notifyItemInserted(0)
            return
        }
        data.add(1, personInserted!!)
        notifyItemInserted(1)
    }

    class ViewHolder(private val binding: PersonItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PersonWithPhones, listener: OnPersonClickListener?) {
            if (item.phones.isEmpty()) {
                binding.lblPhone.text = binding.root.context.getString(R.string.no_phones)
            } else {
                binding.lblPhone.text = item.phones[0].number
            }

            binding.lblFullName.text = "${item.person.name} ${item.person.lastName}"
            binding.root.setOnClickListener {
                listener?.onPersonClick(item)
            }
            binding.btnDeletePerson.setOnClickListener {
                listener?.onPersonDeleteClick(item)
            }
        }
    }

    interface OnPersonClickListener {
        fun onPersonClick(person: PersonWithPhones)
        fun onPersonDeleteClick(person: PersonWithPhones)
    }
}

