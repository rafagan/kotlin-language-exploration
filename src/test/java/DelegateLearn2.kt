import org.junit.Test

//15: Na primeira chamada, inicializa. As demais só retorna.
class DelegateLearn2 {
    class LazySample {
        val lazy: String by lazy {
            println("computed hahaha")
            println("computed auehauehuaehua")
            println("computed ashjdasdhausydhua")
            println("computed kkkkkkkk")
            "my lazy" //A última linha é o retorno, e é a única chamada depois da primeira vez
        }
    }

    @Test
    fun testDelegate() {
        val sample = LazySample()
        println("lazy = ${sample.lazy}")
        println("lazy = ${sample.lazy}")
    }
}