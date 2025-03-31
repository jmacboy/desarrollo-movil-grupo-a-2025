package com.example.practicaroom.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practicaroom.databinding.PhoneItemViewBinding
import com.example.practicaroom.db.models.Phone

class PhoneAdapter(
    private var data: MutableList<Phone>
) : RecyclerView.Adapter<PhoneAdapter.ViewHolder>() {
    private var listener: OnPhoneClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PhoneItemViewBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item, listener)
    }

    fun setData(newData: MutableList<Phone>) {
        this.data = newData
        notifyDataSetChanged()
    }

    fun setOnPhoneClickListener(listener: OnPhoneClickListener) {
        this.listener = listener
    }

    fun removeItem(phoneDeleted: Phone) {
        val index = data.indexOfFirst { it.id == phoneDeleted.id }
        Log.d("Phone", "Removing item at index $index")
        data.removeAt(index)
        notifyItemRemoved(index)
    }

    fun updateItem(phoneUpdated: Phone?) {
        val index = data.indexOfFirst { it.id == phoneUpdated?.id }
        if (index != -1) {
            data[index] = phoneUpdated!!
            notifyItemChanged(index)
        }
    }

    fun addItem(phoneInserted: Phone?) {
        if (data.size == 0) {
            data.add(phoneInserted!!)
            notifyItemInserted(0)
            return
        }
        data.add(1, phoneInserted!!)
        notifyItemInserted(1)
    }

    class ViewHolder(private val binding: PhoneItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Phone, listener: OnPhoneClickListener?) {
            binding.lblPhoneText.text = item.number
            binding.lblPhoneType.text = item.type
            binding.btnPhoneDelete.setOnClickListener {
                listener?.onPhoneDeleteClick(item)
            }
        }
    }

    interface OnPhoneClickListener {
        fun onPhoneDeleteClick(phone: Phone)
    }
}
