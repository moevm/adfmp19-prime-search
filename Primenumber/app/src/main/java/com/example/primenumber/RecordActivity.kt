package com.example.primenumber

import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.util.Log
import kotlinx.android.synthetic.main.activity_record.*
import java.io.*
import java.util.*
import java.util.stream.Collectors

class RecordActivity : AppCompatActivity() {

    fun openTableRecord(name: String, mode: String, level: String, record: Int) {

        var level1 = "none"
        if (mode == "endless") {
            level1 = ""
        } else {
            level1 = level
        }

        try {
            val recordOfRecord = "$name $record\n"
            val recordFile = "record"+mode+level1+".txt"
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

        val intent = Intent(this, TableRecordActivity::class.java)
        when (mode) {
            "time" ->    intent.putExtra("mode", "time")
            "speed" ->    intent.putExtra("mode", "speed")
            else -> intent.putExtra("mode", "endless")
        }
        Log.d("QQQ", level + "це левел")
        when (level) {
            "easy" ->    intent.putExtra("level", "easy")
            "average" ->    intent.putExtra("level", "average")
            "hard" -> intent.putExtra("level", "hard")
            else -> intent.putExtra("level", "")
        }

        startActivity(intent)
    }

    fun openTableRecordEnd() {
        val intent = Intent(this, TableRecordActivity::class.java)
        startActivity(intent)
    }


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record)

        val mode = getIntent().getStringExtra("mode")
        val level = getIntent().getStringExtra("level")
        val record = getIntent().getIntExtra("record", 0)

        var level1 = "none"
        if (mode == "endless") {
            level1 = ""
        } else {
            level1 = level
        }

        val recordFile = "record"+mode+level1+".txt"
        var recordList = ArrayList<String>()
        var loser : Int = -1
        try {
            val contextWrapper = android.content.ContextWrapper(this)

            val fIn = contextWrapper.openFileInput(recordFile)

            val isr = InputStreamReader(fIn)

            val scanner = Scanner(isr)

            while (scanner.hasNextLine()) {
                recordList.add(scanner.nextLine() + "\n")
            }

            recordList = ArrayList(recordList.stream().map { t ->
                val split = t.split(" ")
                Log.d("QQQ", split[split.size - 1].trim())
                Pair<Int, String>(split[split.size - 1].trim().toInt(),t)
            }.sorted { p0, p1 -> -p0.first.compareTo(p1.first) }
                .map { p0 -> p0.second }.collect(Collectors.toList()).toList().take(10))

            Log.d("QQQ", "success = ${recordList.size}")
            val topPlayerRecord = recordList[0]
            tv_top.setText("1-ое место: $topPlayerRecord")
            if (recordList.size >= 10) {
                loser = recordList[9].split(" ")[recordList[9].split(" ").size - 1].trim().toInt()
            }
        } catch (ioe: IOException) {
            ioe.printStackTrace()
        }

        Log.d("QQQ", "it's loser message $loser")

        if (mode != "endless") {
            tv_record.setText("Ваш рекорд: $record/50")
        } else {
            tv_record.setText("Ваш рекорд: $record")
        }

        val nameUser = "Игрок" + Random().nextInt(1000).toString()
        et_name.hint = nameUser

        button_end.setOnClickListener {
            openTableRecordEnd()
        }

        button_save.setOnClickListener {
            val name = et_name.text.toString()
            if (name != "") {
                openTableRecord(name, mode, level, record)
            } else {
                if (et_name.hint.toString() != "") {
                    openTableRecord(et_name.hint.toString(), mode, level, record)
                } else {
                    et_name.setBackgroundColor(getResources().getColor(R.color.colorAccent))
                }
            }
        }

    }
}
