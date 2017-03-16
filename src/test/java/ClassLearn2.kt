import org.junit.Test
import java.util.*

//Properties - sintaxe:
//var <propertyName>: <PropertyType> [= <property_initializer>]
//[<getter>]
//[<setter>]

//59: Singletons
object DataProviderManager {
    //Constantes disponíveis em tempo de compilação
    const val SUBSYSTEM_DEPRECATED: String = "This subsystem is deprecated"

    //Para acessar o valor da property internamente, use field
    var stringRepresentation: String = "Frango"
        get() = field
        set(value) {
            field = value
        }

    private var _table: Map<String, Int>? = null
    val table: Map<String, Int>
        get() {
            if (_table == null)
                _table = HashMap() // Type parameters are inferred
            return _table ?: throw AssertionError("Set to null by another thread")
        }
}

//60: Testando lateinit
class PropertyLateInit() {
    lateinit var subject: Base

    @Test
    fun testLateInit() {
        subject.method()
    }
}

//61: Companion: Valores estáticos dentro da classe
class MyClass {
    companion object Factory {
        fun create(): MyClass = MyClass()
    }
}

//64: Para acessar variáveis de uma classe externa dentro da classe aninhada, usar inner
class Outer {
    private val bar: Int = 1
    inner class Inner {
        fun foo() = bar
    }
}
val demo = Outer().Inner().foo() // == 1

//65: enums
enum class Color(val rgb: Int) {
    RED(0xFF0000),
    GREEN(0x00FF00),
    BLUE(0x0000FF)
}

//66: Extensões como membros
//Extension receiver
class D {
    fun bar() { }
}
//Dispatch receiver
open class E {
    fun baz() { }

    //Observe que posso extender D, de forma a acessar seus métodos juntamente com os meus
    open fun D.foo() {
        bar()   // calls D.bar
        baz()   // calls C.baz
        //this@C.toString() //Use algo assim para chamar métodos quando o nome for igual nas duas classes
    }

    fun caller(d: D) {
        d.foo()   // call the extension function
    }
}

class Teste2() {
    @Test
    fun testarSingletonEPropriedades() {
        println(DataProviderManager.stringRepresentation)
        DataProviderManager.stringRepresentation = "Requeijão cremoso"
        println(DataProviderManager.stringRepresentation)
    }

    @Test
    fun testCompanion() {
        val instance = MyClass.create()
    }
}