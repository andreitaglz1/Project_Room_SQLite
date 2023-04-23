package com.andrea.roomsqlite

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import com.andrea.roomsqlite.databinding.ActivityNewCiudadBinding

class NewCiudadActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewCiudadBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_ciudad)

        val editCiudadView = binding.editCiudad

        val button = binding.buttonSave
        button.setOnClickListener {
            val replyIntent = Intent()
            if(TextUtils.isEmpty(editCiudadView.text)){
                setResult(Activity.RESULT_CANCELED, replyIntent)
            }else{
                val ciudad = editCiudadView.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, ciudad)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object{
        const val EXTRA_REPLY = "com.andrea.ciudadlistsql.REPLY"
    }
}