package com.example.ch12_platform_channel

import io.flutter.embedding.android.FlutterActivity
import io.flutter.plugin.common.MethodChannel
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugins.GeneratedPluginRegistrant

class MainActivity: FlutterActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        GeneratedPluginRegistrant.registerWith(FlutterEngine(this))

        val deviceInfoChannel = MethodChannel(flutterEngine?.dartExecutor?.binaryMessenger, "com.example.ch12_platform_channel/deviceinfo")
        deviceInfoChannel.setMethodCallHandler { call, result ->
            if(call.method == "getDeviceInfo"){
                val deviceInfo = getDeviceInfo()
                result.success(deviceInfo)
            }else{
                result.notImplemented()
            }
        }

    }

    private fun getDeviceInfo(): String {
        return ("\nDevice: "+Build.DEVICE
                +"\nManufacturer: "+Build.MANUFACTURER
                +"\nModel: "+Build.MODEL
                +"\nProduct: "+Build.PRODUCT
                +"\nVersion Release: "+Build.VERSION.RELEASE
                +"\nVersion SDK: "+Build.VERSION.SDK_INT
                +"\nFingerprint: "+Build.FINGERPRINT)
    }
}
