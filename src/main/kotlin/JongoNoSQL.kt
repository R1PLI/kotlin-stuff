import com.mongodb.MongoClient
import org.jongo.Find
import org.jongo.FindOne
import org.jongo.Jongo
import org.jongo.MongoCursor


data class Books(val id: Int = -1, val title: String = "", val author: String = "")

fun <T> FindOne.As(clazz: Class<T>): T? {
    return this.`as`(clazz)
}

fun <T>  Find.As(clazz: Class<T>): MongoCursor<T>? {
    return this.`as`(clazz)
}

fun main(args: Array<String>) {
    val db = MongoClient().getDB("api-testing")

    val jongo = Jongo(db)

    val books = jongo.getCollection("requests")

    val all = books.find("{author: 'King'}").As(Books::class.java)
    val one = books.findOne("{author: 'Yes'}").As(Books::class.java)
    all?.forEach { println(it) }
    println(one)
}
