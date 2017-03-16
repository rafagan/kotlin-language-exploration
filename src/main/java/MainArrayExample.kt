
//2: Main e array
fun main(args: Array<String>) {
    //Observe que a sintaxe dos parâmetros é semelhante à dos diagramas UML
    if (args.size == 0) {
        println("Please provide a name as a command-line argument")
        return
    }

    //O operador ${} possibilita inserir variáveis como string dentro do texto
    println("Hello, ${args[0]}!")
}