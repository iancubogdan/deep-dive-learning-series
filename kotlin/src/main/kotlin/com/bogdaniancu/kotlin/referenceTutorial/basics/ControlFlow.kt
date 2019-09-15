package com.bogdaniancu.kotlin.referenceTutorial.basics

class ControlFlow {


    /**
     * If Expression
     *
     * In Kotlin, if is an expression, i.e. it returns a value. Therefore there is no ternary operator
     * (condition ? then : else), because ordinary if works fine in this role.
     */
    fun ifDemo() {
        val a = 2
        val b = 2
        //traditional usage
        var max = a
        if (a < b) max = b

        //with else
        var max2: Int
        if (a > b) {
            max = a
        } else {
            max = b
        }

        //as expression
        val max3 = if (a > b) a else b

        //if branches can be blocks and the last expression is the value of a block
        val max4 = if (a > b) {
            print("Choose a")
            a
        } else {
            print("Choose b")
            b
        }
    }

    /**
     * When expression
     * when replaces the switch operator of C-like languages. In the simples form it looks like this
     */

    fun whenDemo1() {
        var x : Int = 3

        when (x) {
            1 -> print("x == 1")
            2 -> print("x == 2")
            else -> {
                print("x is neither 1 nor 2")
            }
        }
    }
}
