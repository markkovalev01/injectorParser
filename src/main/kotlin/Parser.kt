import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import java.io.*
import java.util.*

class Parser(htmlFile: File, pattern:String) {
    val text: String;
    val regular: Regex;


    init {
        text = htmlFile.readText(Charsets.UTF_8);
        // "<inj name=|injector.bgpu"
        regular = Regex(pattern)

    }

    public fun hasInject(): Boolean {
        return regular.containsMatchIn(text);
    }
}

class InjectorFinder(rootPass: String, pattern: String, extension:String) {
    val root: File;
    val pattern:String
    val extension:String
    val listFiles: ArrayList<String>;

    init {
        root = File(rootPass);
        this.pattern = pattern
        this.extension = extension
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
            if(extension!=""){
            root
                    .walk()
                    // "txt|htm|jsp$|frag$"
                    .filter { it -> Regex(extension).containsMatchIn(it.extension) }
                    .forEach {
                            if (Parser(it, pattern).hasInject()) {
                                listFiles.add(it.absolutePath)
                            }
                    }
        }else{
                print("yes")
                root
                        .walk()
                        // "txt|htm|jsp$|frag$"
//                    .filter { it -> Regex(extension).containsMatchIn(it.extension) }
                        .forEach {
                            if (it.isFile && Parser(it, pattern).hasInject()) {
                                val file = File(it.absolutePath+".docx")
                                it.renameTo(file)
//                            listFiles.add(it.absolutePath)
                            }
                        }
            }
        }

    }

    public fun showInjFiles() {
//       val file:File = File("out.txt")
//        if(!file.exists()){
//            file.createNewFile()
//        }
//        val bw:BufferedWriter = BufferedWriter(BufferedOutputStream(file))
        listFiles.forEach { println(it) }
    }

}

fun main(args: Array<String>) {
    val start = System.currentTimeMillis();
    val finder = InjectorFinder("E:\\storage","Content_Types..xml","")
    finder.searchInject()
    finder.showInjFiles()
    println(System.currentTimeMillis() - start);
}