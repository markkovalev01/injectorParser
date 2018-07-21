import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import java.io.File

class Parser(htmlFile: File) {
    val document: Document;

    init {
        document = Jsoup.parse(htmlFile, "UTF-8");
    }

    public fun hasInject(): Boolean {
        var inj: Elements = document.select("inj");
        if (inj.isEmpty()) {
            return false;
        }
        return true;
    }
}

fun main(args: Array<String>) {
    val parser = Parser(File("test.html"));
    println(parser.hasInject());
    val parser1 = Parser(File("test2.htm"));
    println(parser1.hasInject());
}