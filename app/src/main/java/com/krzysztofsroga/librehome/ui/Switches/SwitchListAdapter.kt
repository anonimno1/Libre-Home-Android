package com.krzysztofsroga.librehome.ui.Switches

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.Switch
import androidx.recyclerview.widget.RecyclerView
import com.krzysztofsroga.librehome.R
import kotlinx.android.synthetic.main.switch_entry.view.*

class SwitchListAdapter(private val lightSwitchList: List<LightSwitch>) :
    RecyclerView.Adapter<SwitchListAdapter.SwitchViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SwitchViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.switch_entry, parent, false)
        return SwitchViewHolder(itemView)
    }

    override fun getItemCount(): Int = lightSwitchList.size

    override fun onBindViewHolder(holder: SwitchViewHolder, position: Int) {
        val lightSwitch = lightSwitchList[position]
        holder.switch.text = lightSwitch.name
        holder.switch.isChecked = lightSwitch.state
        holder.switch.setOnCheckedChangeListener { _, isChecked ->
            holder.seekBar.isEnabled = isChecked
        }
        holder.seekBar.isEnabled = lightSwitch.state
        holder.seekBar.progress = 100 //TODO download this data
    }


    class SwitchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val switch: Switch = view.switchName
        val seekBar: SeekBar = view.switchSeekBar
    }
}