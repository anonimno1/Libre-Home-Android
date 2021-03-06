package com.krzysztofsroga.librehome.connection

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface DomoticzService {
    @Headers("Content-Type: application/json")
    @GET("json.htm?type=devices&filter=lights&used=true&order=Name")
    suspend fun getDevices(): OnlineSwitches.DomoticzResponseComponents

    @Headers("Content-Type: application/json")
    @GET("json.htm?type=scenes")
    suspend fun getGroups(): OnlineSwitches.DomoticzResponseComponents

    @Headers("Content-Type: application/json")
    @GET("json.htm?type=command&param=switchlight")
    suspend fun sendDeviceState(@Query("idx") lightSwitchId: Int?, @Query("switchcmd", encoded = true) switchCmd: String, @Query("level") level : Int? = null): OnlineSwitches.DomoticzResponse //TODO ID should not be null

    @Headers("Content-Type: application/json")
    @GET("json.htm?type=command&param=switchscene")
    suspend fun sendGroupState(@Query("idx") groupSceneId: Int?, @Query("switchcmd", encoded = true) switchCmd: String): OnlineSwitches.DomoticzResponse //TODO ID should not be null
}

enum class SwitchCmd(val command: String) {
    On("On"),
    Off("Off"),
    SetLevel("Set%20Level"),
}