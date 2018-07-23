import java.io.BufferedReader
import java.io.File
import java.io.InputStream

fun main(args: Array<String>) {
    val start = System.currentTimeMillis()
    val regular = Regex("<inj name=")
    val file = File("Тупичок Гоблина (Goblin). Переводы кино. Студия полный Пэ. Божья Искра. Синий Фил..html")
//    val lines = file.readLines()
//    lines.forEach{if(regular.containsMatchIn(it)){
//        println("yes")
//        return}}
    val text = file.readText(Charsets.UTF_8);
    if(regular.containsMatchIn(text)){
        println("yes")
    }
    println(System.currentTimeMillis()-start)
}