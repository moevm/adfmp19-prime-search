package com.example.primenumber

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_game.*

class Game : AppCompatActivity() {

    fun openRecord(mode:String, level:String, record: Int) {
        val intent = Intent(this, RecordActivity::class.java)

        intent.putExtra("mode", mode)
        intent.putExtra("level", level)
        intent.putExtra("record", record)

        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val mode = getIntent().getStringExtra("mode")
        var level = getIntent().getStringExtra("level")

        var record = 0

        button_end.setOnClickListener {
            openRecord(mode, level, record)
        }




    }
}
