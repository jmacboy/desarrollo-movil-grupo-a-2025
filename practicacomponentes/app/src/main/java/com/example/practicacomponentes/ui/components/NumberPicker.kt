package com.example.practicacomponentes.ui.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.practicacomponentes.R
import com.example.practicacomponentes.databinding.NumberPickerLayoutBinding

class NumberPicker(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs) {
    private val binding: NumberPickerLayoutBinding
    private var onValueChangedListener: ((value: Int) -> Unit)? = null

    //    private var onValueChangedListenerInterface: OnValueChangedListener? = null
    var value: Int = 0
        set(value) {
            field = value
            onValueChangedListener?.invoke(value)
            reloadScreen()
        }

    init {
        val inflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = NumberPickerLayoutBinding.inflate(inflater, this, true)
        setupEventListeners()
        readXmlAttrs(attrs)
    }

    private fun readXmlAttrs(attrs: AttributeSet?) {
        if (attrs == null) {
            return
        }
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.NumberPicker,
            0, 0
        ).apply {
            try {
                value = getInteger(R.styleable.NumberPicker_initialValue, 0)
                reloadScreen()
            } finally {
                recycle()
            }
        }
    }

    private fun setupEventListeners() {
        binding.btnPlus.setOnClickListener {
            value++
            onValueChangedListener?.invoke(value)
//            onValueChangedListenerInterface?.onValueChanged(value)
            reloadScreen()
        }
        binding.btnMinus.setOnClickListener {
            value--
            onValueChangedListener?.invoke(value)
//            onValueChangedListenerInterface?.onValueChanged(value)
            reloadScreen()
        }
    }

    private fun reloadScreen() {
        binding.lblValue.text = value.toString()
    }

    fun setOnValueChangedListener(listener: (value: Int) -> Unit) {
        onValueChangedListener = listener
    }

//    interface OnValueChangedListener {
//        fun onValueChanged(value: Int)
//    }
}