package com.example.primenumber

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_game.*
import android.widget.ArrayAdapter
import android.widget.GridView

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

        //изменение информации
        when(mode) {
            R.string.game_on_time.toString() -> {
                //запускаем таймер
            }
            R.string.game_on_speed.toString()-> {
                //считаем 30 чисел
                tv_info.setText("0/30")
            }
            R.string.endless_mode.toString() -> {
                //считаем баллы за правильные ответы и увеличиваем уровень
                tv_info.setText("$record")
            }
        }


        button_end.setOnClickListener {
            openRecord(mode, level, record)
        }

        var example = arrayOf(0, 0, 0)

        //настрока чекбоксов
        var prime_number = arrayOf(2, 3, 5, 7, 11, 13)
        var numColumns = 1
        when(level) {
            "easy" -> { numColumns = 3
                example = arrayOf(23, 100, 55)
                record = 20
            }
            "average" -> {prime_number = arrayOf(2, 3, 5, 7, 11, 13, 17, 19)
                numColumns = 4
                example = arrayOf(307, 445, 230)
                record = 30
            }
            "difficult" -> {prime_number = arrayOf(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37)
                numColumns = 4
                example = arrayOf(971, 504, 1200)
                record = 40
            }
        }

        val adapter = ArrayAdapter<Int>(this, android.R.layout.simple_list_item_multiple_choice, prime_number)
        gridView.adapter = adapter
        gridView.numColumns = numColumns
        gridView.choiceMode = GridView.CHOICE_MODE_MULTIPLE
        gridView.gravity = GridView.TEXT_ALIGNMENT_CENTER

        var visiblePrime = false
        button_composite.setOnClickListener {
            //перед показом простых чисел нужно проверить правильность ответа
            if (!visiblePrime) {
                //показываем простые числа
                visiblePrime = true
                gridView.visibility = View.VISIBLE
            } else {
                //скрываем простые числа и обрабатываем введенные данные
                visiblePrime = false
                gridView.visibility = View.INVISIBLE

                tv_number.setText(example[2].toString())
            }
        }

        button_prime.setOnClickListener {
            tv_number.setText(example[1].toString())
        }


        tv_number.setText(example[0].toString())



    }
}
