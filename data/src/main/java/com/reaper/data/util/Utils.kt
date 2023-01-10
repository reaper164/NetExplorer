package com.reaper.data.util

import android.util.Log
import java.io.IOException
import java.net.InetAddress
import java.net.InetSocketAddress
import java.net.Socket

object Utils {


    fun pingInetAddress (address: String) : Boolean {
        val inet = InetAddress.getByName(address)
        Log.e("Utils","Sending Ping Request to $address")
        return if(inet.isReachable(5000)){
            Log.e("Utils","Host is reachable")
            true
        }else{
            Log.e("Utils","Host is NOT reachable")
            false
        }
    }

    fun pingHost(host: String, port: Int, timeout: Int): Boolean {
        try {
            Socket().use { socket ->
                socket.connect(InetSocketAddress(host, port), timeout)
                Log.e("Utils","Host is reachable")
                return true
            }
        } catch (e: IOException) {
            Log.e("Utils","Host is NOT reachable")
            return false // Either timeout or unreachable or failed DNS lookup.
        }
    }


}