import com.mongodb.MongoClient
import org.jongo.Jongo


data class Books(val id: Int, val title: String, val author: String) {
    constructor() : this(-1, "", "")
}


fun main(args: Array<String>) {
    val db = MongoClient().getDB("api-testing")

    val jongo = Jongo(db)

    val books = jongo.getCollection("requests")

    val all = books.find("{author: 'King'}").`as`(Books::class.java)
    val one = books.findOne("{author: 'Yes'}").`as`(Books::class.java)
    all.forEach { println(it) }
    println(one)
}
