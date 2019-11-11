package com.bogdaniancu.kotlin.referenceTutorial.classesandobjects

/**
 * Extensions
 * Kotlin provides the ability to extend a class with new functionality without having to inherit from the class or
 * use design patterns such as Decorator. This is done via special declarations called extensions. For example, you can
 * write new functions for a class from a third-party library that you can't modify. Such functions are available for
 * calling in the usual way as if they were methods of the original class. This mechanism is called extension functions.
 * There are also extension properties that let you define new properties for existing classes.
 */

/**
 *
 * Extension functions
 * To declare an extension function, we need to prefix its name with a receiver type, i.e. the type being extended.
 * The following adds a swap function to MutableList<Int>:
 */

fun MutableList<Int>.swap(index1: Int, index2: Int) {
    val tmp = this[index1] // 'this' corresponds to the list
    this[index1] = this[index2]
    this[index2] = tmp
}


/**
 * The this keyword inside an extension function corresponds to the receiver object (the one that is passed before
 * the dot). Now, we can call such a function on any MutableList<Int>:
 */
fun demo() {
    val list = mutableListOf(1, 2, 3)
    list.swap(0, 2) // 'this' inside 'swap()' will hold the value of 'list'
}

/**
 * Of course, this function makes sense for any MutableList<T>, and we can make it generic:
 */

fun <T> MutableList<T>.swapGen(index1: Int, index2: Int) {
    val tmp = this[index1] // 'this' corresponds to the list
    this[index1] = this[index2]
    this[index2] = tmp
}

/**
 * We declare the generic type parameter before the function name for it to be available in the receiver
 * type expression. See Generic functions.
 */