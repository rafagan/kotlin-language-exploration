import org.junit.Test
import kotlin.properties.Delegates

//17: Não quero que a variável name seja optional, mas também não sei com o que devo inicializá-la a priori
class DelegateLearn4 {
    class User {
        var name: String by Delegates.notNull()

        fun init(name: String) {
            this.name = name
        }
    }

    @Test
    fun testDelegate() {
        val user = User()
        user.init("Carl")
        println(user.name)
    }
}