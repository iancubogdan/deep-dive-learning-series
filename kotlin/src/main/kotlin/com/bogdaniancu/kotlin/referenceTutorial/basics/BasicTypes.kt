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

    /**
     * For floating-point numbers, Kotlin provides types Float and Double. According to the IEEE 754 standard, floating
     * point types differ by their decimal place, that is, how many decimal digits they can store. Float reflects the
     * IEEE 754 single precision, while Double provides double precision.
     * For variables initialized with fractional numbers, the compiler infers the Double type. To explicitly specify
     * the Float type for a value, add the suffix f or F. If such a value contains more that 6-7 decimal digits, it
     * will be rounded.
     */

    val pi = 3.14 // Double
    val e = 2.7182818284 // Double
    val eFloat = 2.7182818284f // Float, actual value is 2.7182817

    /**
     * Note that unlike some other languages, there are no implicit widening conversions for numbers in Kotlin. For
     * example, a function with a Double parameter can be called only on Double values, but not Float, Int, or other
     * numeric values.
     */
    fun main() {
        fun printDouble(d: Double) { print(d) }

        val i = 1
        val d = 1.1
        val f = 1.1f

        printDouble(d)
//    printDouble(i) // Error: Type mismatch
//    printDouble(f) // Error: Type mismatch
    }

    //You can use underscores to make number constants more readable:
    val oneMillion = 1_000_000
    val creditCardNumber = 1234_5678_9012_3456L
    val socialSecurityNumber = 999_99_9999L
    val hexBytes = 0xFF_EC_DE_5E
    val bytes = 0b11010010_01101001_10010100_10010010


    /**
     * On the Java platform, numbers are physically stored as JVM primitive types, unless we need a nullable number
     * reference (e.g. Int?) or generics are involved. In the latter cases numbers are boxed.
    */

    //Note that boxing of numbers does not necessarily preserve identity:
    val a : Int = 10000
    //println(a === a) //prints 'true'
    val boxedA: Int? = a
    val anotherBoxedA: Int? = a
    //println(boxedA === anotherBoxedA) // !!!Prints 'false'!!!

    //On the other hand, it preserves equality:
    val a1: Int = 10000
//    println(a1 == a1) // Prints 'true'
    val boxedA1: Int? = a
    val anotherBoxedA1: Int? = a
//    println(boxedA1 == anotherBoxedA1) // Prints 'true'


    //Arrays
    /**
     * Arrays in Kotlin are represented by the Array class, that has get and set functions (that turn into [] by
     * operator overloading conventions), and size property, along with a few other useful member functions:
     */
//    class Array<T> private constructor() {
//        val size: Int
//        operator fun get(index: Int): T
//        operator fun set(index: Int, value: T): Unit
//
//        operator fun iterator(): Iterator<T>
//        // ...
//    }

    //raw string, does not support escaping
    val text = """
    for (c in "foo")
        print(c)
    """

    val text2 = """
    |Tell me and I forget.
    |Teach me and I remember.
    |Involve me and I learn.
    |(Benjamin Franklin)
    """.trimMargin()


    //String templates
    fun demo6() {
        val i = 10
        println("i = $i") // prints "i = 10"

        val s = "abc"
        println("$s.length is ${s.length}") // prints "abc.length is 3"
    }

    //resolving import clash
//    import org.example.Message // Message is accessible
//    import org.test.Message as testMessage // testMessage stands for 'org.test.Message'

}