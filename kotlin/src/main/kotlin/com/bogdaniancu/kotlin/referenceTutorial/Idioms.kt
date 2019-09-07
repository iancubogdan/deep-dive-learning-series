package com.bogdaniancu.kotlin.referenceTutorial

import com.google.gson.Gson
import com.google.gson.JsonElement
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

class Idioms {


    //Creating DTOs
    data class Customer(val name: String, val email: String)

    /**
     * provides a Customer class with the following functionality:

        getters (and setters in case of vars) for all properties
        equals()
        hashCode()
        toString()
        copy()
        component1(), component2(), …, for all properties (see Data classes)
     */

    //Default values for function parameters
    fun foo(a: Int = 0, b: String = "") {

    }

    //Filtering a list
    val list = listOf<Int>(23, 32, -32)
    val positives = list.filter {x -> x > 0}
    val positives2 = list.filter { it > 0 }


    //Checking element presence in a collection.
   fun checkCollection () {
       val list = listOf<Int>(23, 32, -32)
       if (32 in list) {
           //do something
       }
   }

    fun stringInterpolation () {
        val name = "John"
        println("Name $name")
    }

    fun instanceChecks () {
//        when (x) {
//            is Foo -> ...
//            is Bar -> ...
//            else   -> ...
//        }
    }

    fun traversingMap () {
        val map = mapOf("1" to 1, "2" to 2)
        for ((k, v) in map) {
            print("$k -> $v")
        }
    }

    fun usingRanges() {
        for (i in 1..100) {
        }  // closed range: includes 100
        for (i in 1 until 100) {
        } // half-open range: does not include 100
        for (x in 2..10 step 2) {
        }
        for (x in 10 downTo 1) {
        }
        val x = 2
        if (x in 1..10) {

        }
    }

    fun foo () {
        //read only list
        val list = listOf("a", "b", "c")
        //read only map
        val map = mapOf("a" to 1, "b" to 2, "c" to 3)

        val mutableMap = mutableMapOf("a" to 1, "b" to 2, "c" to 3)

        //accessing a map
        println(map["key"])
        mutableMap["key"] = 3

        //lazy property
//        val p : String by lazy {
//
//        }
    }

    //extension functions
    fun String.spaceToCamelCase() {

    }

    fun demo() {
        "Convert this to camelcase".spaceToCamelCase()
    }

    //creating a singleton
    object Resource {
        val name = "Name"
    }

    fun demo2() {
        val files = File("Test").listFiles()
        //if not null shorthand
        println(files?.size)

        //if not null and else shorthand
        println(files?.size ?: "empty")

        //executing a statement if null
        val values = listOf("bla")
        val email = values[1] ?: throw IllegalStateException("Something is missing")

        val mainValue = values.firstOrNull() ?: ""

        //execute if not null
        val value : Int?
        value = 42
        value?.let {

        }

        //map nullable value if not null
        val mapped = value?.let {
            transformValue(it)
        } ?: 32 //default value
    }

    fun transformValue(value: Int) {

    }

    //return on when statemnt
    fun transform(color: String): Int {
        return when(color) {
            "Red" -> 0
            "Green" -> 1
            "Blue" -> 2
            else -> throw IllegalArgumentException("Invalid color param value")
        }
    }

    //try/catch
    fun test() {
        val result = try {
            transform("Orange")
        } catch (e : IllegalArgumentException) {
            throw IllegalStateException(e)
        }
        // working with result
    }

    //if expression
    fun foo(param: Int) {
        val result = if (param == 1) {
            "one"
        } else if (param == 2) {
            "two"
        } else {
            "three"
        }
    }

    //Builder-style usage of methods that return Unit
    fun arrayOfMinusOnes(size: Int): IntArray {
        return IntArray(size).apply { fill(-1) }
    }

    //single-expression functions
    fun theAnswer() = 42
    //equivalent to
    fun theAnswerEq(): Int {
        return 42
    }

    fun transform2(color: String): Int = when (color) {
        "Red" -> 0
        "Green" -> 1
        "Blue" -> 2
        else -> throw IllegalArgumentException("Invalid color param value")
    }

    //Calling multiple methods on an object instance (with)

    class Turtle {
        fun penDown() {}
        fun penUp() {}
        fun turn(degrees: Double) {}
        fun forward(pixels: Double) {}
    }

    fun demo3() {
        val myTurtle = Turtle()
        with(myTurtle) {
            penDown()
            for(i in 1..4) {
                forward(100.0)
                turn(90.0)
            }
            penUp()
        }

    }

    //try with resources =>use

    fun demo4() {
        val stream = Files.newInputStream(Paths.get("/some/"))
        stream.buffered().reader().use { reader ->
            println(reader.readText())
        }
    }

    //Convenient form for a generic function that requires the generic type information

    //  public final class Gson {
    //     ...
    //     public <T> T fromJson(JsonElement json, Class<T> classOfT) throws JsonSyntaxException {
    //     ...

    inline fun <reified T: Any> Gson.fromJson(json: JsonElement): T = this.fromJson(json, T::class.java)



    //consuming a nullable Boolean //weird one?!
    fun demo5() {
        val b: Boolean? = true
        if (b == true) {

        } else {

        }
    }


    //swapping two variables
    fun demo6() {
        var a = 1
        var b = 2
        a = b.also { b = a }
    }
}