package cl3
import org.junit.Test

//67: Sobrescrita de extensões com herança
open class D {
}

class D1 : D() {
}

open class C {
    open fun D.foo() {
        println("D.foo in C")
    }

    open fun D1.foo() {
        println("D1.foo in C")
    }

    fun caller(d: D) {
        d.foo()   // call the extension function
    }
}

class C1 : C() {
    override fun D.foo() {
        println("D.foo in C1")
    }

    override fun D1.foo() {
        println("D1.foo in C1")
    }
}

//68: Sealed classes: São uma espécie de ENUMs avançados, que suportam vários tipos internos
sealed class Expr {
    class Const(val number: Double) : Expr()
    class Sum(val e1: Expr, val e2: Expr) : Expr()
    object NotANumber : Expr()
}

class Teste {
    @Test
    fun testExtensionInheritance() {
        C().caller(D())   // prints "D.foo in C"
        C1().caller(D())  // prints "D.foo in C1" - dispatch receiver is resolved virtually
        C().caller(D1())  // prints "D.foo in C" - extension receiver is resolved statically
    }

    @Test
    fun testSealedClasses() {
        //A vantagem do uso está em uma expressão com when
        fun eval(expr: Expr): Double = when(expr) {
            is Expr.Const -> expr.number
            is Expr.Sum -> eval(expr.e1) + eval(expr.e2)
            Expr.NotANumber -> Double.NaN
        // the `else` clause is not required because we've covered all the cases
        }
        val x = eval(Expr.Sum(Expr.Const(2.0), Expr.Const(2.0)))
        println(x)
    }
}