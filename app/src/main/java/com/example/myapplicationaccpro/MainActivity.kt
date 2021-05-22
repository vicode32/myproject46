@file:Suppress("unused")

package com.example.myapplicationaccpro

import android.annotation.SuppressLint
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlin.math.sqrt

class MainActivity : AppCompatActivity(), SensorEventListener {
    private lateinit var acc: TextView
    private lateinit var  sensorManager: SensorManager
    private lateinit var sensor: Sensor
    private var lastsaved = System.currentTimeMillis()
    private var gravity = FloatArray(3)
    private lateinit var qwe: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        acc = findViewById(R.id.acc)
        val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensorManager.registerListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_NORMAL)
        qwe = findViewById(R.id.qwe)

    }

    companion object{
        private  var ACC_MIN_DATA = 1000
    }


    @SuppressLint("SetTextI18n")
    override fun onSensorChanged(event: SensorEvent?) {
        if ((System.currentTimeMillis() - lastsaved) > 1000) {
            lastsaved = System.currentTimeMillis()

            val alpha: Float = 0.8f
            // use low-pass filter
            gravity[0] = alpha * gravity[0] - (1 - alpha) * event!!.values[0]
            gravity[1] = alpha * gravity[1] - (1 - alpha) * event.values[1]
            gravity[2] = alpha * gravity[2] - (1 - alpha) * event.values[2]
            // using high-pass filter
            val x = event.values[0] - gravity[0]
            val y = event.values[1] - gravity[1]
            val z = event.values[2] - gravity[2]
            val t = sqrt(x * x + y * y + z * z)

            acc.text = "x = $x" + "y = $y" + "z = $z"
            qwe.text = "rt = $t"

        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }
}

private fun SensorManager.registerListener(mainActivity: MainActivity, defaultSensor: Sensor?, sensorDelayNormal: Int) {

}
