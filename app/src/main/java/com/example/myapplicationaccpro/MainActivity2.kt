package com.example.myapplicationaccpro
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.sqrt
class MainActivity2 : AppCompatActivity(), SensorEventListener {
    private var  sensorManager: SensorManager? = null
    private lateinit var sensor: Sensor
    private var acceleration = 0f
    private var lastacceleration = 0f
    private var currentaccceleration = 0f
    private lateinit var ert: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensorManager.registerListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_NORMAL)
        acceleration = 10f
        currentaccceleration = SensorManager.GRAVITY_EARTH
        lastacceleration = SensorManager.GRAVITY_EARTH
        ert = findViewById(R.id.ert)
    }
    override fun onSensorChanged(event: SensorEvent?) {
        val x = event!!.values[0]
        val y = event.values[1]
        val z = event.values[2]
        val t = sqrt(x * x + y *y + z * z)
        lastacceleration = currentaccceleration
        currentaccceleration = sqrt(x * x + y * y + z * z)
        val delta : Float = currentaccceleration - lastacceleration
        acceleration = acceleration * 0.9f + delta
        if (acceleration > 5) {
            ert.text = "rt = $t"
        }
    }
    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }
    override fun onResume() {
        sensorManager?.registerListener(this,sensorManager?.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_NORMAL)
        super.onResume()
    }
    override fun onPause() {
        sensorManager?.unregisterListener(this,Sensor.TYPE_ACCELEROMETER)
        super.onPause()
    }
}
private fun SensorManager.unregisterListener(mainActivity2: MainActivity2, typeAccelerometer: Int) {

}
private fun SensorManager.registerListener(mainActivity2: MainActivity2, defaultSensor: Sensor?, sensorDelayNormal: Int) {
}
