package com.example.primenumber

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    fun change() {
        button_time_easy.setText(R.string.easy)
        button_speed_average.setText(R.string.average)
        button_endless_difficult.setText(R.string.difficult)
    }

    fun openGame(mode:String, level:String) {
        val intent = Intent(this, Game::class.java)

        intent.putExtra("mode", mode)
        intent.putExtra("level", level)

        startActivity(intent)
    }

    fun openStatistics() {
        val intent = Intent(this, TableRecordActivity::class.java)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var mode = "none"
        var level = "none"

        button_time_easy.setOnClickListener {
            if (mode == "none"){
                //меняем надписи на кнопках
                mode = "time"
                change()
            } else {
                //открываем новую форму и посылаем туда данные
                level = "easy"
                openGame(mode, level)
            }
        }

        button_speed_average.setOnClickListener {
            if (mode == "none"){
                //меняем надписи на кнопках
                mode = "speed"
                change()
            } else {
                //открываем новую форму и посылаем туда данные
                level = "average"
                openGame(mode, level)
            }
        }

        button_endless_difficult.setOnClickListener {
            if (mode == "none"){
                //открываем активити с игрой
                mode = "endless"
                openGame(mode, level)
            } else {
                //открываем новую форму и посылаем туда данные
                level = "difficult"
                openGame(mode, level)
            }
        }

        button_statistics.setOnClickListener {
            openStatistics()
        }

    }
}
