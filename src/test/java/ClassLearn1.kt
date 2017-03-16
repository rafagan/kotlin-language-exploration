import org.junit.Test

//50: Construtor primário explicito
class Person1 constructor(firstName: String) {
    //FirstName é uma propriedade
}

//51: Declaração equivalente à de cima
class Person2(firstName: String) {
}

//52: Código de inicialização da classe, chamado após o construtor:
class Customer(name: String) {
    init {
        println("Customer initialized with value $name")
    }

    //Também podem ser chamados no corpo da classe
    val customerKey = name.toUpperCase()

    constructor(name: String, lastName: String) : this(name) {
        println("sadasdasd")
    }
}

//53: Podemos configurar se as propriedades possuirão getters e setters utilizando val e var
class Person3(val firstName: String, val lastName: String, var age: Int) {
    // ...
}

//54: Construtor privado (pode ser instanciada por métodos fábrica)
class DontCreateMe private constructor () {
}

// 55: A herança pode ser configurada no Kotlin com a palavra open, seguindo o pattern: Se não irá ter herança, proíba
// Por padrão, toda classe kotlin é final
// Referencia-se o pai com super
open class Base(p: Int) {
    open fun method() {

    }
}
class Derived(p: Int) : Base(p) {
    override fun method() {

    }
}

//56: Sobrescrevendo propriedades
open class Foo {
    open val x: Int get() { return 0 }
}
class Bar1(override val x: Int) : Foo() {

}

//57: Herança múltipla: Obriga a sobrescrita do método da classe pai
private open class A {
    open fun f() { print("A") }
    fun a() { print("a") }
}
private interface B {
    fun f() { print("B") } // interface members are 'open' by default
    fun b() { print("b") }
}
private class C() : A(), B {
    // The compiler requires f() to be overridden:
    override fun f() {
        super<A>.f() // call to A.f()
        super<B>.f() // call to B.f()
    }
}

// 58: Classe e métodos abstratos
open class OtherBase {
    open fun f() {}
}
abstract class OtherDerived : OtherBase() {
    override abstract fun f()
}

class Teste() {
    @Test
    fun testConstruction() {
        Customer("", "")
    }
}