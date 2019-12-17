package com.krzysztofsroga.librehome.ui.mylists

import androidx.lifecycle.ViewModel
import com.krzysztofsroga.librehome.R

class SwitchGroupViewModel : ViewModel() {
    val switchGroupList: List<OldSwitchGroup> = listOf(
        OldSwitchGroup("LEDS", "All led switches", R.drawable.led_strip),
        OldSwitchGroup("Christmas tree", "Christmas decorations", R.drawable.tree),
        OldSwitchGroup("My room", "Switches in my room", R.drawable.room),
        OldSwitchGroup("Heating", "All heating devices", R.drawable.heating)
    ) //TODO load list

}
