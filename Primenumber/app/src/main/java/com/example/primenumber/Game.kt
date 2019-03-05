package com.example.primenumber

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_game.*
import android.widget.ArrayAdapter
import android.widget.Button
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
                tv_info.text = "1/5"
            }
            R.string.endless_mode.toString() -> {
                //считаем баллы за правильные ответы и увеличиваем уровень
                tv_info.text = "$record"
            }
        }


        button_end.setOnClickListener {
            openRecord(mode, level, record)
        }

        var lower = 0
        var upper = 0

        //настройка чекбоксов
        var prime_number = arrayOf(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37)
        var numColumns = 4
        when(level) {
            "easy" -> {
                lower = 40
                upper = 200
            }
            "average" -> {
                lower = 200
                upper = 500
            }
            "difficult" -> {
                lower = 500
                upper = 2000
            }
        }

        when (mode) {
            "time" -> {

            }
            "speed" -> {
                button_end.visibility = Button.INVISIBLE
                tv_info.setText("1/5")
                tv_record.setText("0")
            }
            "endless" -> {

            }
        }

        val adapter = ArrayAdapter<Int>(this, R.layout.table_item, prime_number)
        gridView.adapter = adapter
        gridView.numColumns = numColumns
        gridView.choiceMode = GridView.CHOICE_MODE_MULTIPLE
        gridView.gravity = GridView.TEXT_ALIGNMENT_CENTER

        var visiblePrime = false
        var count = 1
        button_composite.setOnClickListener {
            //перед показом простых чисел нужно проверить правильность ответа
            if (!visiblePrime) {
                if (!isPrime(tv_number.text.toString().toInt())) {
                    //показываем простые числа
                    visiblePrime = true
                    gridView.visibility = View.VISIBLE
                    record += 5

                    button_prime.visibility = Button.INVISIBLE
                    button_composite.text = "Ок"
                } else {
                    when (mode) {
                        "time" -> {
                            tv_number.text = generation(lower, upper).toString()
                        }
                        "speed" -> {
                            if (count < 5) {
                                count++
                                tv_record.text = record.toString()
                                tv_info.text = "$count/5"
                                tv_number.text = generation(lower, upper).toString()
                            } else {
                                openRecord(mode, level, record)
                            }
                        }
                        "endless" -> {
                            tv_number.text = generation(lower, upper).toString()
                        }
                    }
                }
            } else {
                //скрываем простые числа и обрабатываем введенные данные
                with(gridView.checkedItemPositions) {
                    for (i in 0 until size()) {
                        val key = keyAt(i)
                        val value = this[key]
                        if (value) {
                            //проверка
                            if (tv_number.text.toString().toInt() % prime_number[key] == 0) {
                                record += 2
                            } else {
                                record -= 1
                            }
                        }
                    }
                }

                gridView.clearChoices()

                visiblePrime = false
                gridView.visibility = View.INVISIBLE
                button_prime.visibility = Button.VISIBLE
                button_composite.setText(R.string.composite)

                when (mode) {
                    "time" -> {
                        tv_number.text = generation(lower, upper).toString()
                    }
                    "speed" -> {
                        if (count < 5) {
                            count++
                            tv_record.text = record.toString()
                            tv_info.text = "$count/5"
                            tv_number.text = generation(lower, upper).toString()
                        } else {
                            openRecord(mode, level, record)
                        }
                    }
                    "endless" -> {
                        tv_number.text = generation(lower, upper).toString()
                    }
                }
            }
        }

        button_prime.setOnClickListener {
            when (mode) {
                "time" -> {
                    tv_number.text = generation(lower, upper).toString()
                }
                "speed" -> {
                    val number = tv_number.text.toString().toInt()
                    if (isPrime(number)) {
                        record += 5
                    }
                    if (count < 5) {
                        count++
                        tv_record.text = record.toString()
                        tv_info.text = "$count/5"
                        tv_number.text = generation(lower, upper).toString()
                    } else {
                        openRecord(mode, level, record)
                    }
                }
                "endless" -> {
                    tv_number.text = generation(lower, upper).toString()
                }
            }
        }


        tv_number.text = generation(lower, upper).toString()

    }
}
