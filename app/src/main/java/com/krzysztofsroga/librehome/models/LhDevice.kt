package com.krzysztofsroga.librehome.models

import com.krzysztofsroga.librehome.R
import com.krzysztofsroga.librehome.connection.DomoticzService

sealed class LhDevice(id: Int, name: String) : LhAbstractDevice(id, name) {

    class LhSimpleSwitch(id: Int, name: String, override var enabled: Boolean) : LhDevice(id, name), Switchable {
        override val icon: Int = R.drawable.light

        override suspend fun sendState(service: DomoticzService) {
            service.sendDeviceState(id, if (enabled) "On" else "Off")
        }
    }

    class LhPushButton(id: Int, name: String) : LhDevice(id, name), HasButton {
        override val icon: Int = R.drawable.icons8_push_button

        override suspend fun sendState(service: DomoticzService) {
            service.sendDeviceState(id, "On")
        }
    }

    class LhDimmableSwitch(id: Int, name: String, override var enabled: Boolean, override var dim: Int) : LhDevice(id, name), Switchable, Dimmable {
        override val icon: Int = R.drawable.light_dim

        override suspend fun sendState(service: DomoticzService) {
            service.sendDeviceState(id, if (enabled) "Set%20Level" else "Off", dim)
        }
    }

    class LhSelectorSwitch(id: Int, name: String, override var enabled: Boolean, var dim: Int, var levels: List<String>) : LhDevice(id, name), Switchable {
        override val icon: Int = R.drawable.ic_format_list_bulleted_black_24dp

        var selectedId
            get() = dim / 10
            set(value) {
                dim = value * 10
            }

        override suspend fun sendState(service: DomoticzService) {
            service.sendDeviceState(id, if (enabled) "Set%20Level" else "Off", dim)
        }
    }

    class LhBlindsPercentage(id: Int, name: String, override var dim: Int) : LhDevice(id, name), Dimmable, SimpleName {

        override val icon: Int = R.drawable.icons8_jalousie

        override suspend fun sendState(service: DomoticzService) {
            service.sendDeviceState(id, "Set%20Level", dim)
        }
    }

    class LhUnsupported(id: Int, name: String?, override val typeName: String?) : LhDevice(id, name ?: "Unnamed"), Unsupported {
        override val icon: Int = R.drawable.ic_report_problem_black_24dp

        override suspend fun sendState(service: DomoticzService) {}
    }
}

