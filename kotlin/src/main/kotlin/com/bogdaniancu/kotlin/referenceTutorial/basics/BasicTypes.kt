package com.bogdaniancu.kotlin.referenceTutorial.basics

class BasicTypes {
    /**
     * In Kotlin, everything is an object in the sense that we can call member functions and properties on any variable.
     * Some of the types can have a special internal representation - for example, numbers, characters and booleans can
     * be represented as primitive values at runtime - but to the user they look like ordinary classes. In this section
     * we describe the basic types used in Kotlin: numbers, characters, booleans, arrays, and strings.
     */

    //Numbers

    /**
     * All variables initialized with integer values not exceeding the maximum value of Int have the inferred type Int.
     * If the initial value exceeds this value, then the type is Long. To specify the Long value explicitly, append the
     * suffix l or L to the value.
     */

    val one = 1 // Int
    val threeBillion = 3000000000 // Long
    val oneLong = 1L // Long
    val oneByte: Byte = 1
}