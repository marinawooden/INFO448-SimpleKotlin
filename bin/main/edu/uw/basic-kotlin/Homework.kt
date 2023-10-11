package edu.uw.basickotlin

class Library {
    // This is just here as a placeholder, to make sure tests run and pass
    // before you add any code
    fun someLibraryMethod(): Boolean {
        return true
    }
}

// write a "whenFn" that takes an arg of type "Any" and returns a String
fun whenFn(arg: Any): String {
    return when (arg) {
        "Hello" -> "world"
        is String -> "Say what?"
        0 -> "zero"
        1 -> "one"
        is Int -> {
            if (arg in 2..10) {
                "low number"
            } else {
                "a number"
            }
        }
        else -> "I don't understand"
    }
}

// write an "add" function that takes two Ints, returns an Int, and adds the values
fun add(x: Int, y: Int): Int {
    return x + y
}

// write a "sub" function that takes two Ints, returns an Int, and subtracts the values
fun sub(x: Int, y: Int): Int {
    return x - y
}

// write a "mathOp" function that takes two Ints and a function (that takes two Ints and returns an Int), returns an Int, and applies the passed-in-function to the arguments
fun mathOp(x: Int, y: Int, fnc: (Int, Int) -> Int): Int {
    return fnc(x, y)
}

// write a class "Person" with first name, last name and age
class Person(val firstName: String, val lastName: String, val age: Int) {
    // Additional methods and properties can be added here
    private val _debugString: String = "[Person firstName:$firstName lastName:$lastName age:$age]"
    
    val debugString: String
        get() = _debugString
}

// write a class "Money" with amount and currency, and define a convert() method and the "+" operator
class Money(val amount: Int, val currency: String) {
    init {
        require(amount > 0) {
            "Invalid amount- must be greater than 0"
        }

        require(currency in setOf("USD", "EUR", "CAN", "GBP")) {
            "Invalid currency - must be USD, EUR, CAN, or GBP"
        }
    }

    fun convert(targetCurrency: String): Money {
        require(targetCurrency in setOf("USD", "EUR", "CAN", "GBP")) {
            "Invalid target currency"
        }
        
        val conversionRate = when (currency) {
            "USD" -> when (targetCurrency) {
                "EUR" -> 3.0 / 2.0
                "CAN" -> 5.0 / 4.0
                "GBP" -> 1.0 / 2.0
                else -> 1.0
            }
            "EUR" -> when (targetCurrency) {
                "USD" -> 2.0 / 3.0
                "CAN" -> 5.0 / 6.0
                "GBP" -> 1.0 / 3.0
                else -> 1.0
            }
            "CAN" -> when (targetCurrency) {
                "USD" -> 4.0 / 5.0
                "EUR" -> 6.0 / 5.0
                "GBP" -> 2.0 / 5.0
                else -> 1.0
            }
            "GBP" -> when (targetCurrency) {
                "USD" -> 2.0
                "EUR" -> 3.0
                "CAN" -> 5.0
                else -> 1.0
            }
            else -> 1.0
        }

        val convertedAmount = (amount * conversionRate).toInt()
        return Money(convertedAmount, targetCurrency)
    }

    operator fun plus(curr2: Money): Money {
        return Money(amount + (curr2.convert(currency)).amount, currency)
    }
}