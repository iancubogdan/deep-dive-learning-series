package com.bogdaniancu.kotlin.referenceTutorial.basics

class ReturnsAndJumps {
    /**
     * Kotlin has three structural jump expressions:

        - return. By default returns from the nearest enclosing function or anonymous function.
        - break. Terminates the nearest enclosing loop.
        - continue. Proceeds to the next step of the nearest enclosing loop.
     * All of these expressions can be used as part of larger expressions:
     */

//    val s = person.name ?: return

    /**
     * Break and Continue Labels
     * Any expression in Kotlin may be marked with a label. Labels have the form of an identifier followed by the
     * @ sign, for example: abc@, fooBar@ are valid labels (see the grammar). To label an expression, we just put a
     * label in front of it
     */

    fun labelDemo() {
        loop@ for (i in 1..100) {
            // ...
        }

        //Now, we can qualify a break or a continue with a label:
        loop@ for (i in 1..100) {
            for (j in 1..100) {
                if (j == 3) break@loop
            }
        }
        //A break qualified with a label jumps to the execution point right after the loop marked with that label.
        // A continue proceeds to the next iteration of that loop.

    }

}