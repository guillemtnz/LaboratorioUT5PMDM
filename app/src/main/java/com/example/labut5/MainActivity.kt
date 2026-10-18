package com.example.labut5

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.ListView
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private val tag = "MainActivity"

    private lateinit var etapas: List<Etapa>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Evitar que la barra tape el contenido
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.displayCutout())
            val actionBar = supportActionBar?.height ?: 0
            v.setPadding(bars.left, bars.top + actionBar, bars.right, bars.bottom)
            insets
        }

        val lvEtapas = findViewById<ListView>(R.id.lvEtapas)

        etapas = listOf(
            Etapa("La Comarca", "Baja", android.R.drawable.ic_menu_myplaces),
            Etapa("Rivendell", "Baja", android.R.drawable.ic_menu_compass),
            Etapa("Minas de Moria", "Alta", android.R.drawable.ic_menu_mapmode),
            Etapa("Lothlórien", "Media", android.R.drawable.ic_menu_gallery),
            Etapa("Emyn Muil", "Media", android.R.drawable.ic_menu_directions),
            Etapa("Mordor", "Extrema", android.R.drawable.ic_dialog_alert)
        )

        lvEtapas.adapter = EtapaAdapter(this, etapas)

        // Menú contextual
        registerForContextMenu(lvEtapas)

        // Menú emergente
        findViewById<Button>(R.id.btnAccionesMarcha).setOnClickListener { viewAncla ->
            val popup = PopupMenu(this, viewAncla)
            popup.menuInflater.inflate(R.menu.menu_popup_marcha, popup.menu)
            popup.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.pop_acelerar -> {
                        Toast.makeText(this, R.string.toast_acelerar, Toast.LENGTH_SHORT).show()
                        true
                    }
                    R.id.pop_acampar -> {
                        Toast.makeText(this, R.string.toast_acampar, Toast.LENGTH_SHORT).show()
                        true
                    }
                    else -> false
                }
            }
            popup.show()
        }
    }

    // Menú de opciones
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_principal, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.opc_abandonar -> {
                finishAffinity()
                true
            }
            R.id.opc_reiniciar -> {
                Toast.makeText(this, R.string.toast_reiniciar, Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // Menú contextual
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.menu_contextual_etapa, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val etapa = etapas[info.position]

        return when (item.itemId) {
            R.id.ctx_inspeccionar -> {
                val intent = Intent(this, DetalleActivity::class.java).apply {
                    putExtra("EXTRA_NOMBRE", etapa.nombre)
                    putExtra("EXTRA_PELIGRO", etapa.peligro)
                }
                startActivity(intent)
                true
            }
            else -> super.onContextItemSelected(item)
        }
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
