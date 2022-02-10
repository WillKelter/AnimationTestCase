package com.willkelter.animationtest

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(private var data: Array<ButtonEntity>, val viewModel: MainActivityViewModel, private val listener:OnItemClickListener):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TYPE_SMALL = 1
    private val TYPE_BIG = 2


    inner class ClosedViewHolder(view: View) : RecyclerView.ViewHolder(view), OnClickListener {
        val icon: ImageView = view.findViewById(R.id.icon)
        init {
            view.setOnClickListener(this)
        }
        override fun onClick(p0: View?) {
           val position = adapterPosition
            listener.onClick(position)
        }

    }

    inner class OpenedViewHolder(view: View) : RecyclerView.ViewHolder(view), OnClickListener {
        val icon: ImageView = view.findViewById(R.id.icon)
        val label: TextView = view.findViewById(R.id.label)
        init {
            view.setOnClickListener(this)
        }
        override fun onClick(p0: View?) {
            val position = adapterPosition
            listener.onClick(position)
        }
    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == TYPE_SMALL){
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.button_closed_layout, viewGroup, false)

        return ClosedViewHolder(view)
        }
        else {
            val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.button_opened_layout, viewGroup, false)
            return OpenedViewHolder(view)
        }

    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == TYPE_SMALL) {
            (holder as ClosedViewHolder).icon.setImageResource(data[position].icon)
        } else {
            (holder as OpenedViewHolder).icon.setImageResource(data[position].icon)
            holder.label.text = data[position].label
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if(!viewModel.isOpened){
            TYPE_SMALL
        } else {
            TYPE_BIG
        }

    }


    override fun getItemCount() = data.size

    interface OnItemClickListener{
        fun onClick(position:Int)
    }
}

