package com.bogdaniancu.kotlin.referenceTutorial.classesandobjects

/**
The class declaration consists of the class name, the class header (specifying its type parameters, the primary
constructor etc.) and the class body, surrounded by curly braces. Both the header and the body are optional; if the
class has no body, curly braces can be omitted.
 **/

class Empty

/**
 * Constructors
 * A class in Kotlin can have a primary constructor and one or more secondary constructors. The primary constructor is
 * part of the class header: it goes after the class name (and optional type parameters).
 **/
class Person constructor(firstName: String) {
}
/**if the primary constructor does not have any annotations or visibility modifiers, the constructor
 * keyword can be omitted:
 */
class Person2 (firstName: String) {
}

/**
 * The primary constructor cannot contain any code. Initialization code can be placed in initializer blocks, which are
 * prefixed with the init keyword.

During an instance initialization, the initializer blocks are executed in the same order as they appear in the class
body, interleaved with the property initializers:
 */

class InitOrderDemo(name: String) {
    val firstProperty = "First property: $name".also(::println)
    init {
        println("First initializer block that prints ${name}")
    }
    val secondProperty = "Second property: ${name.length}".also(::println)
    init {
        println("Second initializer block that prints ${name.length}")
    }
}

/**
 * Note that parameters of the primary constructor can be used in the initializer blocks. They can also be used in
 * property initializers declared in the class body:
 */
class Customer(name: String) {
    val customerKey = name.toUpperCase()
}

/**
 * In fact, for declaring properties and initializing them from the primary constructor, Kotlin has a concise syntax:
 */

class Person3(val firstName: String, val lastName: String, var age: Int) {
}

/**
 * If the constructor has annotations or visibility modifiers, the constructor keyword is required, and the modifiers
 * go before it:
 */
//class Customer2 public @Inject constructor(name: String)


/**
 * Secondary constructors
 *
 * The class can also declare secondary constructors, which are prefixed with constructor:
 */

class Person4 {
    var children: MutableList<Person4> = mutableListOf()
    constructor(parent: Person4) {
        parent.children.add(this)
    }
}

/**
 * If the class has a primary constructor, each secondary constructor needs to delegate to the primary constructor,
 * either directly or indirectly through another secondary constructor(s). Delegation to another constructor of the
 * same class is done using the this keyword:
 */

class Person5 (val name: String){
    var children: MutableList<Person5> = mutableListOf()
    constructor(name: String, parent: Person5) : this(name) {
        parent.children.add(this)
    }
}

/**
 * Note that code in initializer blocks effectively becomes part of the primary constructor. Delegation to the primary
 * constructor happens as the first statement of a secondary constructor, so the code in all initializer blocks is
 * executed before the secondary constructor body. Even if the class has no primary constructor, the delegation still
 * happens implicitly, and the initializer blocks are still executed:
 */
class Constructors {
    init {
        println("Init block")
    }

    constructor(i: Int) {
        println("Constructor")
    }
}

/**
 * If a non-abstract class does not declare any constructors (primary or secondary), it will have a generated primary
 * constructor with no arguments. The visibility of the constructor will be public. If you do not want your class to
 * have a public constructor, you need to declare an empty primary constructor with non-default visibility:
 */
class DontCreateMe private constructor () { /*...*/ }


/**
 *NOTE: On the JVM, if all of the parameters of the primary constructor have default values, the compiler will generate
 * an additional parameterless constructor which will use the default values. This makes it easier to use Kotlin with
 * libraries such as Jackson or JPA that create class instances through parameterless constructors.
 */
class Customer3(val customerName: String = "")

/**
 * Creating instances of classes
 *
 * To create an instance of a class, we call the constructor as if it were a regular function:
 * Note that Kotlin does not have a new keyword.
 * Creating instances of nested, inner and anonymous inner classes is described in Nested classes.
 */


val person5 = Person5("someName")
val person5_2 = Person5("someName", person5)

/**
 * Classes can contain:

        Constructors and initializer blocks
        Functions
        Properties
        Nested and Inner Classes
        Object Declarations
 */

/**
 * Inheritance
 *
 * All classes in Kotlin have a common superclass Any, that is the default superclass for a class with no
 * supertypes declared
 * Any has three methods: equals(), hashCode() and toString(). Thus, they are defined for all Kotlin classes.
 */
class Example // Implicitly inherits from Any

/**
 * To declare an explicit supertype, place the type after a colon in the class header:
 * If the derived class has a primary constructor, the base class can (and must) be initialized right there,
 * using the parameters of the primary constructor.
 */

open class Base(p: Int)

class Derived(p: Int) : Base(p)

/**
 * If the derived class has no primary constructor, then each secondary constructor has to initialize the base type
 * using the super keyword, or to delegate to another constructor which does that. Note that in this case different
 * secondary constructors can call different constructors of the base type:
 */

fun main() {
    println("Hello World")
    val orderDemo = InitOrderDemo("first")
    val constructors = Constructors(2)
}