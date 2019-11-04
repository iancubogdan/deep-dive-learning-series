package com.bogdaniancu.kotlin.referenceTutorial.classesandobjects

/**
 * Declaring Properties
 * Properties in Kotlin classes can be declared either as mutable using the var keyword, or as read-only
 * using the val keyword.
 */

class Address {
    var name: String = "Holmes, Sherlock"
    var street: String = "Baker"
    var city: String = "London"
    var state: String? = null
    var zip: String = "123456"
}

/**
 * To use a property, simply refer to it by name:
 */

fun copyAddress(address: Address): Address {
    val result = Address() //there's no 'new' keyword in Kotlin
    result.name = address.name // accessors are called
    result.street = address.street

    return result
}

/**
 * Getters and Setters
 *
 * The full syntax for declaring a property is
 *
 * var <propertyName>[: <PropertyType>] [= <property_initializer>]
 * [<getter>]
 * [<setter>]
 *
 * The initializer, getter and setter are optional. Property type is optional if it can be inferred from the
 * initializer (or from the getter return type, as shown below).
 */

// var allByDefault: Int? // error: explicit initializer required, default getter and setter implied
var initialized = 1 // has type Int, default getter and setter

/**
 * The full syntax of a read-only property declaration differs from a mutable one in two ways: it starts with val
 * instead of var and does not allow a setter:
*/

//val simple: Int? //has type Int, default getter, must be initialized in constructor
val inferredType = 1 // has type Int and a default getter


class Demo {
    val size = 1


    /**
     * We can define custom accessors for a property. If we define a custom getter, it will be called every time we access
     * the property (this allows us to implement a computed property). Here's an example of a custom getter:
     */
    val isEmpty: Boolean
        get() = this.size ==0

    /**
     * Since Kotlin 1.1, you can omit the property type if it can be inferred from the getter:
     */
    val isEmpty2 get() = this.size == 0

    /**
     * If we define a custom setter, it will be called every time we assign a value to the property. A custom setter
     * looks like this:
     */
    var StringRepresentation: String
        get() = this.toString()
        set(value) { // By convention, the name of the setter parameter is value, but you can choose a different name if you prefer.
            setDataFromString(value) // parses the string and assigns values to other properties
        }

    private fun setDataFromString(value: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

/**
 * If you need to change the visibility of an accessor or to annotate it, but don't need to change the default
 * implementation, you can define the accessor without defining its body:
 */

var setterVisibility: String = "abs"
    private set //The setter is private and has the default implementation

var setterWithAnnotation: Any? = null
    @Inject set //annotate the setter with Inject

annotation class Inject


/**
 * Backing Fields
 *
 * Fields cannot be declared directly in Kotlin classes. However, when a property needs a backing field, Kotlin
 * provides it automatically. This backing field can be referenced in the accessors using the field identifier:
 */

var counter = 0 // Note: the initializer assigns the backing field directly
    set(value) {
        if (value >= 0) field = value
    }
/**
 * The field identifier can only be used in the accessors of the property.
 * A backing field will be generated for a property if it uses the default implementation of at least one of the
 * accessors, or if a custom accessor references it through the field identifier.
 * For example, in the following case there will be no backing field:
 */

//val isEmpty: Boolean
//    get() = this.size ==0


/**
 * Backing Properties
 * If you want to do something that does not fit into this "implicit backing field" scheme, you can always fall back
 * to having a backing property:
 */

private var _table : Map<String, Int>? = null
public val table: Map<String, Int>
    get() {
        if(_table == null) {
            _table = HashMap()
        }
        return _table ?: throw AssertionError("Set to null by another thread")
    }

/**
 *  Compile-Time Constants
 *  Properties the value of which is known at compile time can be marked as compile time constants using the const modifier. Such properties need to fulfil the following requirements:

        - Top-level, or member of an object declaration or a companion object.
        - Initialized with a value of type String or a primitive type
        - No custom getter
        - Such properties can be used in annotations:
 */
const val SUBSYSTEM_DEPRECATED: String = "This subsystem is deprecated"


/**
 * Late-Initialized Properties and Variables
 *
 * Normally, properties declared as having a non-null type must be initialized in the constructor. However, fairly
 * often this is not convenient. For example, properties can be initialized through dependency injection, or in the
 * setup method of a unit test. In this case, you cannot supply a non-null initializer in the constructor, but you
 * still want to avoid null checks when referencing the property inside the body of a class.
 *
 * To handle this case, you can mark the property with the lateinit modifier:
 */

class TestSubject

public class MyTest {
    lateinit var subject: TestSubject

    @Inject fun setup() {
        subject = TestSubject()
    }
}

/**
 * The modifier can be used on var properties declared inside the body of a class (not in the primary constructor, and
 * only when the property does not have a custom getter or setter) and, since Kotlin 1.2, for top-level properties and
 * local variables. The type of the property or variable must be non-null, and it must not be a primitive type.
 *
 * Accessing a lateinit property before it has been initialized throws a special exception that clearly identifies the
 * property being accessed and the fact that it hasn't been initialized.
 *
 * Checking whether a lateinit var is initialized (since 1.2)
 * To check whether a lateinit var has already been initialized, use .isInitialized on the reference to that property:
 */



