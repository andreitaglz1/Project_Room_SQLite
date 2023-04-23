package com.andrea.roomsqlite

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andrea.roomsqlite.*
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private val newCiudadActivityRequestCode = 1
    private val ciudadViewModel: CiudadViewModel by viewModels {
        CiudadViewModelFactory((application as CiudadApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = CiudadListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, NewCiudadActivity::class.java)
            startActivityForResult(intent, newCiudadActivityRequestCode)
        }

        ciudadViewModel.allCiudad.observe(owner = this) { ciudad ->
            ciudad.let { adapter.submitList(it) }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        if (requestCode == newCiudadActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.getStringExtra(NewCiudadActivity.EXTRA_REPLY)?.let { reply ->
               val ciudad = Ciudad(1, "Granada", 1500)
                ciudadViewModel.insert(ciudad)
            }
        } else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG
            ).show()
        }
    }
}
