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
import android.widget.TextView
import java.util.*

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
        when (mode) {
            "time" -> {

            }
            "speed" -> {
                button_end.visibility = Button.INVISIBLE
                tv_info.setText("1/5")
                tv_record.setText(record.toString())
            }
            "endless" -> {
                tv_record.setText(record.toString())
                tv_info.visibility = TextView.GONE
                level = "easy"
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
                Log.d("QQQ", "$level $lower $upper")
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
        Log.d("QQQ", "$level $lower $upper")

        tv_number.text = generation(lower, upper).toString()

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
                    record += 2

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
                            tv_record.text = record.toString()
                        }
                    }
                }
            } else {
                //скрываем простые числа и обрабатываем введенные данные
                var div = emptyArray<Int>()
                for (el in prime_number) {
                    if (tv_number.text.toString().toInt() % el == 0) {
                        div += el
                    }
                }
                var correct = 0
                var uncorrect = 0
                with(gridView.checkedItemPositions) {
                    for (i in 0 until size()) {
                        val key = keyAt(i)
                        val value = this[key]
                        if (value) {
                            //проверка
                            if (prime_number[key] in div) {
                                correct++
                            } else {
                                uncorrect++
                            }
                        }
                    }
                }

                uncorrect = Math.ceil(8 * uncorrect.toDouble() / (correct + uncorrect).toDouble()).toInt()
                correct = Math.ceil(8 * correct.toDouble() / (div.size).toDouble()).toInt()

                record += (correct - uncorrect)

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
                        if (level == "easy" && record > 200) {
                            level = "average"
                        }
                        if (level == "average" && record > 500) {
                            level = "difficult"
                        }
                        if (level == "average" && record < 200) {
                            level = "easy"
                        }
                        if (level == "difficult" && record < 500) {
                            level = "average"
                        }
                        tv_number.text = generation(lower, upper).toString()
                        tv_record.text = record.toString()
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
                        record += 10
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
                    val number = tv_number.text.toString().toInt()
                    if (isPrime(number)) {
                        record += 10
                    }
                    if (level == "easy" && record > 200) {
                        level = "average"
                    }
                    if (level == "average" && record > 500) {
                        level = "difficult"
                    }
                    if (level == "average" && record < 200) {
                        level = "easy"
                    }
                    if (level == "difficult" && record < 500) {
                        level = "average"
                    }
                    tv_number.text = generation(lower, upper).toString()
                    tv_record.text = record.toString()
                }
            }
        }

    }
}
