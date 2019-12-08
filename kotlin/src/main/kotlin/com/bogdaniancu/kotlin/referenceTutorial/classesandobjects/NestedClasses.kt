package com.bogdaniancu.kotlin.referenceTutorial.classesandobjects

import java.awt.event.ActionListener

/**
 * Nested and Inner Classes
 *
 * Classes can be nested in other classes:
 */

class Outer2 {
    private val bar2: Int = 1
    class Nested2 {
        fun foo() = 2
//        fun foo() = bar2
    }
}
val demo = Outer2.Nested2().foo()

/**
 * A nested class marked as inner can access the members of its outer class. Inner classes carry a reference to an
 * object of an outer class:
 */

class Outer3 {
    private val bar: Int = 1
    inner class Inner3 {
        fun foo() = bar
    }
}

val demo2 = Outer3().Inner3().foo()

/**
 * Anonymous inner classes
 *
 * Anonymous inner class instances are created using an object expression:
 *
 * Note: on the JVM, if the object is an instance of a functional Java interface (i.e. a Java interface with a
 * single abstract method), you can create it using a lambda expression prefixed with the type of the interface:
 */

/**
    window.addMouseListener(object : MouseAdapter() {

        override fun mouseClicked(e: MouseEvent) { ... }

        override fun mouseEntered(e: MouseEvent) { ... }
    })
*/

val listener = ActionListener { println("clicked") }