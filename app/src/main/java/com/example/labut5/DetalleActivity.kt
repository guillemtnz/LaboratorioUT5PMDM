package com.example.labut5

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetalleActivity : AppCompatActivity() {

    private val tag = "DetalleActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle)

        val nombre = intent.getStringExtra("EXTRA_NOMBRE") ?: "Desconocido"
        val peligro = intent.getStringExtra("EXTRA_PELIGRO") ?: "Desconocido"

        findViewById<TextView>(R.id.tvDetalleNombre).text = nombre
        findViewById<TextView>(R.id.tvDetallePeligro).text =
            getString(R.string.peligro_label, peligro)
    }

    override fun onStart() {
        super.onStart()
        Log.d(tag, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(tag, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(tag, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(tag, "onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(tag, "onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(tag, "onDestroy")
    }
}
