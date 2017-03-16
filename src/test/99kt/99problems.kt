import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.util.*

// 1- Localize o último elemento de uma lista.
fun <T> last(list: List<T>): T? =
        if(list.isNotEmpty()) list[list.lastIndex]
        else null

// 2- Encontre o penúltimo elemento de uma lista.
fun <T> penultimate(list: List<T>): T? =
        if(list.isNotEmpty() && list.size > 1) list[list.lastIndex - 1]
        else null

// 3- Localize o N-ésimo elemento de uma lista.
tailrec fun <T> nth(list: List<T>, element: T, length: Int = 0): Int? =
        if(list.isEmpty()) null
        else if(list.first() == element) length
        else nth(list.drop(1), element, length + 1)

tailrec fun <T> nth2(list: List<T>, index: Int): T? =
        if(index < 0 || list.lastIndex - index < 0) null
        else if(index == 0) list.first()
        else nth2(list.drop(1), index - 1)

// 4- Calcule o número de elementos de uma lista.
fun <T> length(list: List<T>): Int = list.sumBy { 1 }

fun <T> lengthRec(list: List<T>): Int =
        if(list.isEmpty()) 0 else 1 + lengthRec(list.drop(1))

// 5- Inverta uma lista
fun <T> reverse(list: List<T>): List<T> {
    val result = LinkedList<T>()
    list.forEach { result.addFirst(it) }
    return result
}

fun <T> reverseRec(list: List<T>): List<T> =
        if(list.size > 1) reverseRec(list.drop(1)) + list.first() else list

class Exercises {
    @Test
    fun test1() {
        assertEquals(last(listOf(1, 2, 3, 4, 5, 6, 7, 8)), 8)
        assertEquals(last(listOf(1)), 1)
        assertEquals(last(emptyList<Int>()), null)
    }

    @Test
    fun test2() {
        assertEquals(penultimate(listOf(1, 2, 3, 4, 5, 6, 7, 8)), 7)
        assertEquals(penultimate(listOf(1)), null)
        assertEquals(penultimate(emptyList<Int>()), null)
    }

    @Test
    fun test3() {
        assertEquals(nth(listOf(1, 1, 2, 3, 5, 8), 2), 2)
        assertEquals(nth(listOf(1, 1, 2, 3, 5, 8), 5), 4)
        assertEquals(nth(listOf(1, 1, 2, 3, 5, 8), 4), null)

        assertEquals(nth2(listOf(1, 1, 2, 3, 5, 8), 2), 2)
        assertEquals(nth2(listOf(1, 1, 2, 3, 5, 8), 5), 8)
        assertEquals(nth2(listOf(1, 1, 2, 3, 5, 8), 4), 5)
        assertEquals(nth2(listOf(1, 1, 2, 3, 5, 8), -1), null)
        assertEquals(nth2(listOf(1, 1, 2, 3, 5, 8), 20), null)
    }

    @Test
    fun test4() {
        assertEquals(length(listOf(1, 1, 2, 3)), 4)
        assertEquals(length(listOf(1, 1, 2, 3, 5)), 5)
        assertEquals(length(listOf(1, 1, 2, 3, 5, 6)), 6)
        assertEquals(length(emptyList<Int>()), 0)
    }

    @Test
    fun test5() {
        assertEquals(reverseRec(listOf(1, 1, 2, 3, 5, 8)), listOf(8, 5, 3, 2, 1, 1))
    }
}