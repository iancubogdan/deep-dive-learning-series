package com.bogdaniancu.kotlin.referenceTutorial.classesandobjects

/**
 *  Interfaces in Kotlin can contain declarations of abstract methods, as well as method implementations. What makes
 *  them different from abstract classes is that interfaces cannot store state. They can have properties but these
 *  need to be abstract or to provide accessor implementations.
 *  An interface is defined using the keyword interface
 */

interface MyInterface {
    fun bar()
    fun foo() {
        //optional body
    }
}


class Child0 : MyInterface {
    override fun bar() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}

/**
 * Properties in Interfaces
 * You can declare properties in interfaces. A property declared in an interface can either be abstract, or it can
 * provide implementations for accessors. Properties declared in interfaces can't have backing fields, and therefore
 * accessors declared in interfaces can't reference them.
 */

interface MyInterface1 {
    val prop: Int //abstract

    val propertyWithImplementation: String
        get() = "foo"

    fun foo() {
        print(prop)
        print(propertyWithImplementation)
    }
}

class Child1 : MyInterface1 {
    override val prop: Int = 29
    override val propertyWithImplementation: String = "dsdf"
}

/**
 * Interfaces Inheritance
 * An interface can derive from other interfaces and thus both provide implementations for their members and declare
 * new functions and properties. Quite naturally, classes implementing such an interface are only required to define
 * the missing implementations:
 */
interface Named {
    val name: String
}

interface Person1 : Named {
    val firstName: String
    val lastName: String

    override val name: String get() = "$firstName $lastName"
}

data class Employee(
        // implementing 'name' is not required
        override val firstName: String,
        override val lastName: String
//        val position: Position
) : Person1


/**
 * Resolving overriding conflicts
 * When we declare many types in our supertype list, it may appear that we inherit more than one implementation of
 * the same method. For example
 */

interface A {
    fun foo() {
        print("A")
    }
    fun bar()
}

interface B {
    fun foo() {
        print("B")
    }
    fun bar() {
        print("bar")
    }
}

class C : A {
    override fun bar() {
        print("bar")
    }
}

class D: A, B {
    override fun foo() {
        super<A>.foo()
        super<B>.foo()
    }

    override fun bar() {
        super<B>.bar()
    }

}


fun main() {
    var child = Child1()

//    println(child.prop)
    child.foo()
}
