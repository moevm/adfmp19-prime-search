package com.example.primenumber

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_record.*
import java.io.*
import java.util.*

class RecordActivity : AppCompatActivity() {

    fun openTableRecord(name: String) {
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

        val nameUser = "Игрок" + Random().nextInt(1000).toString()
        et_name.hint = nameUser

        try {
            val recordOfRecord = "$nameUser $record\n"
            val recordFile = "record"+mode+level+".txt"
            val fOut = openFileOutput(
                recordFile,
                Context.MODE_APPEND
            )

            val osw = OutputStreamWriter(fOut)

            osw.write(recordOfRecord)

            osw.flush()
            osw.close()

            Log.d("QQQ", "success = $recordOfRecord")
            Log.d("QQQ", "success = $recordFile")
        } catch (ioe: IOException) {
            ioe.printStackTrace()
        }

        button_end.setOnClickListener {
            openTableRecord("")
        }

        button_save.setOnClickListener {
            val name = et_name.text.toString()
            if (name != "") {
                //сохранение результата
                openTableRecord(name)
            } else {
                if (et_name.hint.toString() != "") {
                    openTableRecord(et_name.hint.toString())
                } else {
                    et_name.setBackgroundColor(getResources().getColor(R.color.colorAccent))
                }
            }
        }

    }
}
