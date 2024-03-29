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

class  Context {

}

class AttributeSet{

}

open class View (ctx: Context){
//    constructor(ctx: Context)
    constructor(ctx: Context, attrs: AttributeSet) :this(ctx) {
    }
}

class MyView : View {
    constructor(ctx: Context) : super(ctx) {
    }
    constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs) {
    }
}

/**
 * Overriding methods
 *
 * Kotlin requires explicit modifiers for overridable members (we call them open) and for overrides:
 */

open class Shape {
    open fun draw() {}
    fun fill() {}
}

class Circle() : Shape(){
    override fun draw() {}
}

/**
 * The override modifier is required for Circle.draw(). If it were missing, the compiler would complain. If there is
 * no open modifier on a function, like Shape.fill(), declaring a method with the same signature in a subclass is
 * illegal, either with override or without it. The open modifier has no effect when added on members of a final class
 * (i.e.. a class with no open modifier).

 * A member marked override is itself open, i.e. it may be overridden in subclasses. If you want to prohibit
 * re-overriding, use final:
 */

open class Rectangle() : Shape() {
    final override fun draw() { /*...*/ }
}

/**
 * Overriding properties
 *
 * Overriding properties works in a similar way to overriding methods; properties declared on a superclass that are
 * then redeclared on a derived class must be prefaced with override, and they must have a compatible type. Each
 * declared property can be overridden by a property with an initializer or by a property with a get method.
 */

open class Parent {
    open val someValue: Int = 0
}

class Child : Parent(){
    override val someValue = 3
}

/**
 * You can also override a val property with a var property, but not vice versa. This is allowed because a val property
 * essentially declares a get method, and overriding it as a var additionally declares a set method in the derived class.
 *
 * Note that you can use the override keyword as part of the property declaration in a primary constructor.
 */

interface Shape1 {
    val vertexCount : Int
}

class Rectangle1(override val vertexCount: Int = 4) :Shape1 {
}

class Polygon : Shape1 {
    override var vertexCount: Int = 0

}

/**
 * Derived class initialization order
 *
 * During construction of a new instance of a derived class, the base class initialization is done as the first step
 * (preceded only by evaluation of the arguments for the base class constructor) and thus happens before the
 * initialization logic of the derived class is run.
 */

open class Base1(val name: String) {
    init {
        println("Initializing Base")
    }
    open val size: Int = name.length.also {
        println("Initializing size in Base: $it")
    }
}

class Derived1(name: String, val lastName: String) : Base1(name.capitalize().also { println("Argument for Base: $it")  }){
    init {
        println("Initializing Derived")
    }
    override val size : Int = (super.size + lastName.length).also { println("Initializing size in Derived: $it") }
}

/**
 * It means that, by the time of the base class constructor execution, the properties declared or overridden in the
 * derived class are not yet initialized. If any of those properties are used in the base class initialization logic
 * (either directly or indirectly, through another overridden open member implementation), it may lead to incorrect
 * behavior or a runtime failure. When designing a base class, you should therefore avoid using open members in the
 * constructors, property initializers, and init blocks.
 */

/**
 * Calling the superclass implementation
 *
 * Code in a derived class can call its superclass functions and property accessors implementations using the
 * super keyword:
 */

open class Rectangle2 {
    open fun draw() { println("Drawing a rectangle") }
    open val borderColor: String get() = "black"
}

class FilledRectangle: Rectangle2() {
    override fun draw() {
        super.draw()
        println("Filling the rectangle")
    }
    val fillColor : String = super.borderColor
}

/**
 * Inside an inner class, accessing the superclass of the outer class is done with the super keyword qualified with
 * the outer class name: super@Outer:
 */

class FilledRectangle2: Rectangle2() {
    override fun draw() { /* ... */ }
    override val borderColor: String get() = "black"

    inner class Filler {
        fun fill() {
        }
        fun drawAndFill() {
            super@FilledRectangle2.draw()
            fill()
            println("Drawn a filled rectangle with color ${super@FilledRectangle2.borderColor}")
            // Uses Rectangle's implementation of borderColor's get()
        }
    }
}

/**
 *  Overriding rules
 *
 *  In Kotlin, implementation inheritance is regulated by the following rule: if a class inherits many implementations
 *  of the same member from its immediate superclasses, it must override this member and provide its own implementation
 *  (perhaps, using one of the inherited ones). To denote the supertype from which the inherited implementation is
 *  taken, we use super qualified by the supertype name in angle brackets, e.g. super<Base>:
 */

open class Rectangle3 {
    open fun draw() { /* ... */ }
}

interface Polygon3 {
    fun draw() { /* ... */ } // interface members are 'open' by default
}

class Square3 : Rectangle3(), Polygon3 {
    override fun draw() {
        super<Rectangle3>.draw()
        super<Polygon3>.draw()
    }
}

/**
 * It's fine to inherit from both Rectangle and Polygon, but both of them have their implementations of draw(), so we
 * have to override draw() in Square and provide its own implementation that eliminates the ambiguity.
 */


/** Abstract classes
 *
 * A class and some of its members may be declared abstract. An abstract member does not have an implementation in its
 * class. Note that we do not need to annotate an abstract class or function with open – it goes without saying.
 *
 * We can override a non-abstract open member with an abstract one
 */

open class Polygon4 {
    open fun draw() {}
}

abstract class Rectangle4 : Polygon4() {
    override abstract fun draw()
}

/**
 * Companion objects
 * If you need to write a function that can be called without having a class instance but needs access to the internals
 * of a class (for example, a factory method), you can write it as a member of an object declaration inside that class.
 *
 * Even more specifically, if you declare a companion object inside your class, you'll be able to call its members
 * using only the class name as a qualifier.
 */


fun main() {
//    println("Hello World")
//    val orderDemo = InitOrderDemo("first")
//    val constructors = Constructors(2)

//    val base1 = Base1("value")
    val derived1 = Derived1("john", "brown")
}

