import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import java.io.File
import java.io.FileFilter
import java.io.FilenameFilter

class Parser(htmlFile: File) {
    val text: String;
    val regular: Regex;

    init {
        text = htmlFile.readText(Charsets.UTF_8);
        regular = Regex("<inj name=|injector.bgpu")
    }

    public fun hasInject(): Boolean {
        return regular.containsMatchIn(text);
    }
}

class InjectorFinder(rootPass: String) {
    val root: File;
    val listFiles: ArrayList<String>;

    init {
        root = File(rootPass);
        listFiles = ArrayList<String>();
    }

//    public fun test() {
//        println("yes");
//        for (i in 0..10) {
//            println(2);
//            launch {
////                delay(2000);
//                println("1");
//                listFiles.add("i ${i}");}
//            Thread.sleep(100)
//            }
//        }

    public fun searchInject() {
        if (root.isDirectory) {
            root
                    .walk()
                    .filter { it -> Regex("txt|html|htm|jsp|frag").containsMatchIn(it.extension) }
                    .forEach {
                            if (Parser(it).hasInject()) {
                                listFiles.add(it.absolutePath)
                            }
                    }
        }
    }

    public fun showInjFiles() {
        listFiles.forEach { println(it) }
    }

}

fun main(args: Array<String>) {
    val start = System.currentTimeMillis();
    val finder = InjectorFinder("C:\\Users")
    finder.searchInject()
    finder.showInjFiles()
    println(System.currentTimeMillis() - start);
}