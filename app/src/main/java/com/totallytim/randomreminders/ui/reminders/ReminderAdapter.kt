package com.totallytim.randomreminders.ui.reminders

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.totallytim.randomreminders.R
import com.totallytim.randomreminders.TextItemViewHolder
import com.totallytim.randomreminders.database.Reminder

class ReminderAdapter: RecyclerView.Adapter<TextItemViewHolder>() {

    var data = listOf<Reminder>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(R.layout.reminder_list_item, parent, false) as TextView
        return TextItemViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
        val item = data[position]
        holder.textView.text = item.name
    }
}