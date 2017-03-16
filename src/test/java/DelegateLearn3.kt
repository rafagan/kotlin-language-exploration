import org.junit.Test
import kotlin.properties.Delegates

//16: Possui histórico do valor antigo e do novo
class DelegateLearn3 {
    class User {
        var name: String by Delegates.observable("no name") {
            d, old, new -> println("$old - $new")
        }

        //Exemplo getter e setter
        var lala: String = ""
            get() {
                return field
            }
            set(value) {
                field = value
            }
    }

    @Test
    fun testDelegate() {
        val user = User()
        user.name = "Carl"
        user.name = "Ronaldo"
    }
}