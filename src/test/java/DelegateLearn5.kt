import org.junit.Test

//18: Construtor a partir de dicionários
class DelegateLearn5 {
    class User(val map: Map<String, Any?>) {
        val name: String by map
        val age: Int     by map
    }

    @Test
    fun testDelegate() {
        val user = User(mapOf(
                "name" to "John Doe",
                "age"  to 25
        ))

        println("name = ${user.name}, age = ${user.age}")
    }
}