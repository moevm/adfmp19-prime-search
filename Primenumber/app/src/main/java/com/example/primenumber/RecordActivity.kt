package com.example.primenumber

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_record.*

class RecordActivity : AppCompatActivity() {

    fun openTableRecord() {
        val intent = Intent(this, TableRecordActivity::class.java)
        startActivity(intent)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record)

        val mode = getIntent().getStringExtra("mode")
        val level = getIntent().getStringExtra("level")
        val record = getIntent().getIntExtra("record", 0)

        tv_record.setText("Ваш рекорд: $record")

        button_end.setOnClickListener {
            openTableRecord()
        }

        button_save.setOnClickListener {
            val name = et_name.text.toString()
            if (name != "") {
                //сохранение результата
                openTableRecord()
            } else {
                et_name.setBackgroundColor(getResources().getColor(R.color.colorAccent))
            }
        }

    }
}
