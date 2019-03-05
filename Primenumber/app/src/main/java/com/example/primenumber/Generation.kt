package com.example.primenumber

import android.util.Log
import java.util.Random

fun random(from:Int, to:Int) : Int {
    return Random().nextInt(to-from)+from
}

fun isPrime(number:Int):Boolean {
    var prime = true
    for (i in 2..Math.sqrt(number.toDouble()).toInt()+1){
        if (number % i == 0) {
            prime = false
        }
    }
    return prime
}


// возможно нужно добавить тут проверку на то, было ли уже это число или нет
// если есть и -1, и +1, то всегда выдает с минусом
fun randomPrime(from:Int, to:Int) : Int {
    var isPrime = false
    var prime = 0
    while (!isPrime) {
        var n = random(Math.ceil(from.toDouble() / 6).toInt(), Math.floor(to.toDouble() / 6).toInt())
        when {
            isPrime(6*n-1) -> {
                prime = 6*n-1
                isPrime = true
            }
            isPrime(6*n+1) -> {
                prime = 6*n+1
                isPrime = true
            }
        }
    }
    return prime
}

//добавить проверку на то, что этого числа ещё не было
fun generation(lower:Int, upper:Int) : Int {
    val primeOrComposite = random(0, 2) == 0
    //если 0, то генерируем простое число
    //если 1, то составное
    var number = 0
    if (primeOrComposite) {
           number = randomPrime(lower, upper)
    } else {
        var composite = false
        while (!composite) {
            number = random(lower, upper)
            composite = !isPrime(number)
        }
    }
    Log.d("Generation", "primeOrComposite = $primeOrComposite     number = $number")

    return number
}