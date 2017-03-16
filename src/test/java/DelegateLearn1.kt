import org.junit.Test
import kotlin.reflect.KProperty

//14: Observe que o delegate propõe implementações de métodos para uma dada classe
class DelegateLearn1 {
    class Example {
        init {
            println("Estou inicializando a classe aqui")
        }

        //Delegate: Sei o que devo fazer, mas não sei como fazer. Preciso que alguém me ensine.
        var p: String by Delegate() //O DETALHE ESTÁ AQUI

        override fun toString() = "Example Class"
    }

    class Delegate() {
        //Sintaxe dos getters e setters para properties usando delegate
        operator fun getValue(thisRef: Any?, prop: KProperty<*>): String {
            return "$thisRef, thank you for delegating '${prop.name}' to me!"
        }

        operator fun setValue(thisRef: Any?, prop: KProperty<*>, value: String) {
            println("$value has been assigned to ${prop.name} in $thisRef")
        }
    }

    @Test
    fun testDelegate() {
        val e = Example()
        println(e.p)
        e.p = "NEW"
    }
}