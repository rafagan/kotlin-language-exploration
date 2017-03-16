
import org.junit.Test
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*
import java.util.concurrent.locks.Lock
import javax.swing.tree.TreeNode

class KotlinLearn {
    //3: Retorno de função void
    @Test
    fun returnVoid() {
        println("Aqui, um exemplo de retorno void")
    }
    @Test
    fun kotlinTest(): Unit {
        println("Explicitamente, o retorno é Unit")
    }

    //4: foreach e declaração inline de arrays
    @Test
    fun foreach() {
        val args = emptyArray<String>()
        for (name in args)
            println("Hello, $name!")

        val args2 = arrayOf(1, 2, 3)
        for (name in args2)
            println("Hello, $name!")

        val args3 = Array(5, { i -> (i * i).toString() })
        for (name in args3)
            println("Hello, $name!")
    }

    //5: O switch do Kotlin é bem poderoso. Ele pode inicializar variáveis igual a operadores ternários.
    @Test
    fun switchWhen() {
        val language = "PT-PT"
        println(when (language) {
            "EN" -> "Hello!"
            "FR" -> "Salut!"
            "IT" -> "Ciao!"
            "PT-BR", "PT-PT" -> "Olá"
            else -> "Sorry, I can't greet you in $language yet"
        })
    }

    //6: Observe a classe dentro da função, observe a forma como é declarado o construtor
    @Test
    fun classExample() {
        class Greeter(val name: String) {
            fun greet() {
                println("Hello, $name");
            }
        }

        Greeter("Teste").greet()
    }

    //7: Perceba que as condicionais podem ser operadores ternários
    @Test
    fun testOneLineF() {
        println(oneLineMaxValueFunction(1.0, 2.0))
    }
    fun oneLineMaxValueFunction(a : Double, b : Double) = if (a > b) a else b

    //8: Valor default
    @Test
    fun testFunc() {
        show("T")
    }
    fun show (teste : String, msg : String = "Hello World"){
        println("$msg")
    }

    //9: Any
    @Test
    fun testAny() {
        println(getStringLength("hisudfhiu"))
    }
    fun getStringLength(obj: Any) : Int? {
        if (obj is String)
            return obj.length // no cast to String is needed
        return null
    }

    //10: Intervalos
    @Test
    fun intervals() {
        val x = Integer.parseInt("10")
        //Check if a number lies within a range:
        val y = 10
        if (x in 1..y - 1)
            println("OK")

        //Iterate over a range:
        for (a in 1..5)
            print("$a ")

        //Check if a number is out of range:
        println()
        val array = arrayListOf<String>()
        array.add("aaa")
        array.add("bbb")
        array.add("ccc")

        //If com intervalos
        if (x !in 0..array.size - 1)
            println("Out: array has only ${array.size} elements. x = $x")

        //Check if a collection contains an object:
        if ("aaa" in array) // collection.contains(obj) is called
            println("Yes: array contains aaa")

        if ("ddd" in array) // collection.contains(obj) is called
            println("Yes: array contains ddd")
        else
            println("No: array doesn't contains ddd")
    }

    //11: Múltiplos retornos. O uso do operator obriga ao nome ser component
    @Test
    fun genericType() {
        val pair = Pair(1, "one")
        val (num, name) = pair //LINHA MAIS IMPORTANTE
        println("num = $num, name = $name")

        //Observe que o construtor é colocado na inicialização da classe
        class Pair<K, V>(val first: K, val second: V) {
            //component1: Primeiro a ser retornado
            operator fun component1(): K {
                return first
            }

            //component2: Segundo a ser retornado
            operator fun component2(): V {
                return second
            }
        }
    }

    //12: Data classes: geram getters, setters, equals, hashcode e tostring
    @Test
    fun dataClass() {
        data class User(val name: String, val id: Int)

        fun getUser(): User {
            return User("Alex", 1)
        }

        //Repare no acesso às propriedades
        val user = getUser()
        println("name = ${user.name}, id = ${user.id}")

        // or
        val (name, id) = getUser()
        println("name = $name, id = $id")

        // or
        println("name = ${getUser().component1()}, id = ${getUser().component2()}")


        val firstUser = User("Alex", 1)
        println(firstUser) // toString()

        val secondUser = User("Alex", 1)
        val thirdUser = User("Max", 2)

        println("user == secondUser: ${firstUser == secondUser}")
        println("user == thirdUser: ${firstUser == thirdUser}")
    }

    //13: Foreach em dicionários
    @Test
    fun travessingMap() {
        val map = hashMapOf<String, Int>()
        map.put("one", 1)
        map.put("two", 2)

        for ((key, value) in map) {
            println("key = $key, value = $value")
        }
    }

    //19: Filtros
    @Test
    fun testFilter() {
        fun isOdd(x: Int) = x % 2 != 0 //predicado
        val numbers = listOf(1, 2, 3) //<algo>Of
        println(numbers.filter(::isOdd)) //Filtro
    }

    //20: Filtros compostos
    @Test
    fun testComposeFilter() {
        fun isOdd(x: Int) = x % 2 != 0
        fun length(s: String) = s.length
        fun <A, B, C> compose(f: (B) -> C, g: (A) -> B): (A) -> C {
            return { x -> f(g(x)) }
        }

        val oddLength = compose(::isOdd, ::length)
        val strings = listOf("a", "ab", "abc")
        println(strings.filter(oddLength))
    }

    //21: Mudar ordem dos parâmetros na entrada da função
    @Test
    fun changeParametersOrder() {
        fun subNumbers(first: Int, second : Int): Int = first - second
        println(subNumbers(second = 10, first = 20));
    }

    //22: Varargs (múltiplos parâmetros de entrada)
    @Test
    fun varargsExample() {
        fun addNumbers(vararg params : Int) : Int {
            var sum = 0
            for(v in params)
                sum += v
            return sum
        }
        println(addNumbers(1,2,3,4,5))
    }

    //23: Parâmetro pre inicializado
    @Test
    fun funParamIni() {
        fun addTwoNumbers(v1 : Int, v2 : Int = 0) : Int {
            return v1 + v2
        }
        println(addTwoNumbers(10))
    }

    //24: Extensions. Obs: Extensões não assumem comportamento de herança
    @Test
    fun testExtensions() {
        fun String.changeChar(c1 : Char, c2 : Char) : String {
            val str = StringBuilder()
            for(c in this)
                if(c == c1)
                    str.append(c2)
                else
                    str.append(c)
            return str.toString()
        }

        println("hahahaha".changeChar('a', 'e'))
    }

    //25: Tratando nulls
    @Test
    fun testOptional() {
        val v1 : Int? = null
        val v2 = 20

        println(v1 ?: 2 + v2)
    }

    //26: Cast automático
    @Test
    fun automaticCast() {
        fun func(value : Any) : Any? {
            if(value is String)
                return value.length //Olha o cast automático
            else if(value is Int && value in 1..100) // Intervalo no if
                return 50
            else if(value !is String) //Só pra treinar sintaxe
                return value

            return null //Nunca será
        }

        println(func("10"))
        println(func(10))
    }

    //27: For incrementado em 2
    @Test
    fun forIncr() {
        for (i in 1..100 step 2)
            print(i)
    }

    //28: Notação infixa
    @Test
    fun infixNotation() {
        infix fun Int.sum(x1 : Int) : Int {
            return this + x1
        }

        println(2 sum 3)
    }

    //29: Objects (annonymous inner classes)
    interface B {}
    @Test
    fun objects() {
        //Cria um objeto ad-hoc
        val adHoc = object {
            var x: Int = 0
            var y: Int = 0
        }
        print(adHoc.x + adHoc.y)

        //Cria uma implementação anônima de uma interface
//        window.addMouseListener(object : MouseAdapter() {
//            override fun mouseClicked(e: MouseEvent) {
//                // ...
//            }
//
//            override fun mouseEntered(e: MouseEvent) {
//                // ...
//            }
//        })

        open class A(x: Int) {
            public open val y: Int = x
        }

        //Observe a herança de duas classes ad-hoc
        val ab: A = object : A(1), B {
            override val y = 15
        }
    }

    //30: Exemplo com HashMap
    @Test
    fun hashMapCompleteExample() {
        fun findPairless(a: IntArray): Int {
            val map = HashMap<Int, Int>()
            for(v in a)
                map[v] = map[v] ?: 0 + 1

            for((k, v) in map)
                if(map[k] == 1)
                    return v

            return 0
        }

        findPairless(intArrayOf(1, 2, 3, 4, 5, 6, 7))
    }

    //31: Lambda como parâmetro
    @Test
    fun lambdaAsParam() {
        fun receiveLambda(name : String = "no name", lambda: (String, Int) -> Int) {
            println(lambda(name, 2222))
        }

        receiveLambda { name, teste ->
            println(name)
            println(teste)
            15 //O último valor verdadeiro é o retornado
        }


        fun receiveLambda2(name : String = "no name", lambda: (String) -> Int) {
            println(lambda(name))
        }

        //Veja it
        receiveLambda2 {
            println(it)
            15 //O último valor verdadeiro é o retornado
        }
    }

    //32: Se não for nulo, executa
    @Test
    fun executeIfNotNull() {
        val data = "ededed"
        data?.let {
            println("not null") // execute this block if not null
        }
    }

    //33: Try with resources
    @Test
    fun tryWithResources() {
        val stream = Files.newInputStream(Paths.get("/some/file.txt"))
        stream.buffered().reader().use { reader ->
            println(reader.readText())
        }
    }

    //34: Call with multiple times
    @Test
    fun withTest() {
        class Turtle {
            fun penDown() {
                println("penDown")}
            fun penUp() {
                println("penUp")
            }
            fun turn(degrees: Double) {
                println("turn $degrees degrees")
            }
            fun forward(pixels: Double) {
                println("turn $pixels pixels")
            }
        }

        val myTurtle = Turtle()
        with(myTurtle) { //draw a 100 pix square
            penDown()
            for(i in 1..4) {
                forward(100.0)
                turn(90.0)
            }
            penUp()
        }
    }

    //35: Comparar com === retorna verdadeiro quando os objetos são exatamente os mesmos
    @Test
    fun compareTypesLikeJS() {
        data class Teste(val v1 : Int, val v2 : String)
        val a = Teste(10, "20")
        val b = Teste(10, "20")

        println(a == b)
        println(a === b)
        println(a === a)
    }

    //36: String multi-linha
    @Test
    fun multilineString() {
        val text = """
            |Tell me and I forget.
            Teach me and I remember.
            |Involve me and I learn.
            |(Benjamin Franklin)
            """.trimMargin()
        println(text)
    }

    //37: Indices no for
    @Test
    fun forWithIndexes() {
        val array = arrayOf(1,2,3,4,5)
        for ((index, value) in array.withIndex()) {
            println("the element at $index is $value")
        }
    }

    //38: For with labels
    @Test
    fun forWithLabels() {
        loop@ for (i in 1..100) {
            for (j in 1..100) {
                if (j == 30 && i == 20) {
                    println("$i $j")
                    break@loop
                }
            }
        }
    }

    //39: Returns in lambdas
    @Test
    fun returnFromLambda() {
        val ints = arrayOf(2,3,4,5,0,6,7)

        fun foo() {
            ints.forEach lit@ {
                if (it == 0) return@lit
                print(it)
            }

            ints.forEach {
                if (it == 0) return@forEach
                print(it)
            }

            ints.forEach(fun(value: Int) {
                if (value == 0) return
                print(value)
            })
        }

        foo()
    }

    //40: Chamar parâmetros na ordem diferente
    @Test
    fun intercambiateParams() {
        fun reformat(str: String,
                     normalizeCase: Boolean = true,
                     upperCaseFirstLetter: Boolean = true,
                     divideByCamelHumps: Boolean = false,
                     wordSeparator: Char = ' ') {
            println(str)
            println(normalizeCase)
            println(upperCaseFirstLetter)
            println(divideByCamelHumps)
            println(wordSeparator)
        }

        reformat("asdasd",
                normalizeCase = true,
                upperCaseFirstLetter = true,
                divideByCamelHumps = false,
                wordSeparator = '_'
        )

        reformat("asdasd", wordSeparator = '_')
    }

    //41: Tailrec: gera um código estruturado a partir de um código com muita recursividade, evitando stackoverflow
    @Test
    fun testTailrec() {
        tailrec fun findFixPoint(x: Double = 1.0): Double
                = if (x == Math.cos(x)) x else findFixPoint(Math.cos(x))
        println(findFixPoint())

        fun findFixPointLiteral(): Double {
            var x = 1.0
            while (true) {
                val y = Math.cos(x)
                if (x == y) return y
                x = y
            }
        }
    }

    //42: Sintaxes lambda
    @Test
    fun testInnerMethodsAndLambda() {
        val sum1 = { x: Int, y: Int -> x + y }
        val sum2 : (Int, Int) -> Int = { x, y -> x + y }
        var sum3 : Int.(other: Int) -> Int
    }

    //43: Reescrever invoke permite chamar dessa forma
    @Test
    fun testInvoke() {
        operator fun Int.invoke() { println(this) }
        1() //huh?..
    }

    //44: Destructuring declarations
    @Test
    fun testDestructuring() {
        data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int)
        fun function(year: Int, month: Int, dayOfMonth: Int): MyDate {
            return MyDate(year, month, dayOfMonth)
        }

        fun isLeapDay(date: MyDate): Boolean {
            val (year, month, dayOfMonth) = date

            // 29 February of a leap year
            return year % 4 == 0 && month == 2 && dayOfMonth == 29
        }
    }

    //45: Retornos em lambdas: O retorno normal pertence à função
    @Test
    fun returnInLambdas() {
        val ints = intArrayOf(1,2,3,4,5)
        ints.forEach lit@ {
            if (it == 0) return@lit
            print(it)
        }

        //Outra possibilidade seria encarar o lâmbda como uma função
        ints.forEach(fun(value: Int) {
            if (value == 0) return
            print(value)
        })
    }

    //46: Funções inline: Otimizam o código, principalmente closures
    inline fun <T> lock(lock: Lock, body: () -> T): T? {
        return null
    }
    //noinline vai identificar elementos que não devem ser colocados por extenso
    inline fun foo(inlined: () -> Unit, noinline notInlined: () -> Unit) {
        // ...
    }

    //47: Apply TODO
    fun arrayOfMinusOnes(size: Int): IntArray {
        return IntArray(size).apply { fill(-1) }
    }

    //48: Tipos genéricos em funções
    @Test
    fun testGenerics() {
        fun <T> singletonList(item: T): List<T> {
            return listOf(item)
        }

        println(singletonList(1))
        println(singletonList("1"))
    }

    //49: É possível extender qualquer classe com Any
    @Test
    fun extensionAny() {
        fun Any?.toString2(): String {
            if (this == null) return "null"
            return toString()
        }

        println(1.toString2())
    }

    //62: Funções inline com retorno permitido dentro do lâmbda
    inline fun f(crossinline body: () -> Unit) {
        val f = Runnable { body() }
    }

    //63: Reitified (passar tipo de um generic como parâmetro)
    inline fun <reified T> TreeNode.findParentOfType(): T? {
        var p = parent
        while (p != null && p !is T) {
            p = p?.parent
        }
        return p as T
    }

    //69: Se algo nao for nulo, e você desejar executar uma operação, use let
    @Test
    fun testLet() {
        val x: Int? = null
        x?.let { println(it) }
    }

    //70: Reflections
    @Test
    fun testReflections() {
        //Reflection de classes
        val c = MyClass::class
        println(c)

        //Reflection de funções
        fun isOdd(x: Int) = x % 2 != 0
        val numbers = listOf(1, 2, 3)
        println(numbers.filter(::isOdd)) // prints [1, 3]

        //Salvando reflection em uma variável
        val predicate: (Int) -> Boolean = ::isOdd

        //Perceba que a sintaxe de tipos de funções é (<tipo>)
        fun <A, B, C> compose(f: (B) -> C, g: (A) -> B): (A) -> C {
            return { x -> f(g(x)) }
        }
        fun length(s: String) = s.length
        val oddLength = compose(::isOdd, ::length)
        val strings = listOf("a", "ab", "abc")
        println(strings.filter(oddLength)) // Prints "[a, abc]"

        //Reflection de propriedades
        class A(val p: Int)
        val prop = A::p
        println(prop.get(A(1))) // prints "1"

        //Construtores podem ser usados com reflection de maneira idêntica à funções que retornam instância da classe
        class Foo
        fun function(factory : () -> Foo) {
            val x : Foo = factory()
        }
        function(::Foo)
    }

    //71: Annotations
    //Possuem como objetivo adicionar metadados ao código (pré processamento)
    @Test
    fun testAnnotations() {
        //Exemplo de annotation
        annotation class Special(val why: String)
        @Special("example") class Foo {}
    }

    //72: Manipulação avançada de coleções
    @Test
    fun testCollections() {
        println(
                listOf(1, 2, 3, 4).fold(1, {
                    partProduct, element -> element * partProduct
                }) == 24
        )
    }

    //73???: Nomes com caracteres diferentes para funções
    fun `olha para este nome de função`() {
        `olha para este nome de função`()
    }
}