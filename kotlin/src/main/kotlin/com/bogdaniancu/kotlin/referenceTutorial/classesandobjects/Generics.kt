package com.bogdaniancu.kotlin.referenceTutorial.classesandobjects

import kotlin.Array


/**
 * https://kotlinlang.org/docs/reference/generics.html
 */

/**
 * As in Java, classes in Kotlin may have type parameters:
 */

class Box<T>(t: T) {
    var value = t
}
//In general, to create an instance of such a class, we need to provide the type arguments:
val box: Box<Int> = Box<Int>(1)

//But if the parameters may be inferred, e.g. from the constructor arguments or by some other means, one is allowed
// to omit the type arguments:
val box2 = Box(1) // 1 has type Int, so the compiler figures out that we are talking about Box<Int>

/**
 * Declaration-site variance
 *
 * we can annotate the type parameter T of Source to make sure that it is only returned (produced) from members of
 * Source<T>, and never consumed. To do this we provide the out modifier:
 *
 */

interface Source<out T> {
    fun nextT(): T
}

fun demo(strs: Source<String>) {
    val objects: Source<Any> = strs // This is OK, since T is an out-parameter
}

/**
 * The general rule is: when a type parameter T of a class C is declared out, it may occur only in out-position in the
 * members of C, but in return C<Base> can safely be a supertype of C<Derived>.
 *
 * In "clever words" they say that the class C is covariant in the parameter T, or that T is a covariant type parameter.
 * You can think of C as being a producer of T's, and NOT a consumer of T's.
 *
 * The out modifier is called a variance annotation, and since it is provided at the type parameter declaration site,
 * we talk about declaration-site variance. This is in contrast with Java's use-site variance where wildcards in the
 * type usages make the types covariant.
 *
 * In addition to out, Kotlin provides a complementary variance annotation: in. It makes a type parameter
 * contravariant: it can only be consumed and never produced. A good example of a contravariant type is Comparable:
 */

interface Comparable<in T> {
    operator fun compareTo(other: T): Int
}

fun demo2(x: Comparable<Number>) {
    x.compareTo(1.0) // 1.0 has type Double, which is a subtype of Number
    // Thus, we can assign x to a variable of type Comparable<Double>
    val y: Comparable<Double> = x //OK

}

/**
 * Type projections
 *
 * Use-site variance: Type projections
 * It is very convenient to declare a type parameter T as out and avoid trouble with subtyping on the use site, but
 * some classes can't actually be restricted to only return T's! A good example of this is Array:
 */

class Array<T>(val size: Int) {
    val array = Array<T>(12)
    fun get(index: Int): T {
        return array.get(index)
    }
    fun set(index: Int, value: T) {  }
}

fun copy(from: Array<Any>, to: Array<Any>) {
    assert(from.size == to.size)
    for(i in from.indices) {
        to[i] = from[i]
    }
}

//This function is supposed to copy items from one array to another. Let's try to apply it in practice:
fun demo3() {
    val ints: Array<Int> = arrayOf(1, 2, 3)
    val any = Array<Any>(3) { "" }
//    copy(ints, any)
//          ^ type is Array<Int> but Array<Any> was expected
}

/**
 * Here we run into the same familiar problem: Array<T> is invariant in T, thus neither of Array<Int> and Array<Any>
 * is a subtype of the other. Why? Again, because copy might be doing bad things, i.e. it might attempt to write,
 * say, a String to from, and if we actually passed an array of Int there, a ClassCastException would have been
 * thrown sometime later.
 *
 * Then, the only thing we want to ensure is that copy() does not do any bad things. We want to prohibit it from
 * writing to from, and we can:
 */

fun copy2(from: Array<out Any>, to: Array<Any>) {
    assert(from.size == to.size)
    for(i in from.indices) {
        to[i] = from[i]
    }
}

fun demo4() {
    val ints: Array<Int> = arrayOf(1, 2, 3)
    val any = Array<Any>(3) { "" }
    copy2(ints, any)
}

/**
 *
 * What has happened here is called type projection: we said that from is not simply an array, but a restricted
 * (projected) one: we can only call those methods that return the type parameter T, in this case it means that we
 * can only call get(). This is our approach to use-site variance, and corresponds to Java's Array<? extends Object>,
 * but in a slightly simpler way.
 * You can project a type with in as well:
 * Array<in String> corresponds to Java's Array<? super String>, i.e. you can pass an array of CharSequence or an
 * array of Object to the fill() function.
 */

fun fill(dest: Array<in String>, value: String) {}

/**
 *
 * Generic functions
 * Not only classes can have type parameters. Functions can, too. Type parameters are placed before the name of the
 * function:
 */

fun <T> singletonList(item: T): List<T> {
    return List<T>(2, {item})
}

fun <T> T.basicToString(): String {  // extension function
    return "something"
}

/**
 * To call a generic function, specify the type arguments at the call site after the name of the function:
 *
 * Type arguments can be omitted if they can be inferred from the context, so the following example works as well:
 */

val l = singletonList<Int>(1)
val l2 = singletonList(1)

