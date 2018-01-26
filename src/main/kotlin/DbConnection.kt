import com.mongodb.*

fun main(args: Array<String>) {
    val mongoCollection = setupMongoConnection("localhost", 27017)
            .getMongoDb("api-testing")
            .getCollection("requests")

    mongoCollection.insertElements(
            createMongoObject(
                    mapOf(
                            "id" to "18.0",
                            "title" to "faust1",
                            "author" to "Gete2",
                            "array" to arrayOf(1, 2, 3),
                            "new Object" to createMongoObject(
                                    mapOf(
                                            "inside object id" to "20",
                                            "inside object title" to "Some Title"
                                    )
                            )
                    )
            )
    )

    //get element using map
    val cursorMap = mongoCollection.getDBCursor(mapOf("array" to arrayOf(1, 2, 3)))

    //get element using object
    val obj = createMongoObject(mapOf("array" to arrayOf(1, 2, 3)))
    val cursorObject = mongoCollection
            .getDBCursor(obj)

}

private fun setupMongoConnection(host: String, port: Int): MongoClient = MongoClient(host, port)

private fun MongoClient.getMongoDb(dbname: String): DB = getDB(dbname)

fun DBCollection.getDBCursor(query: BasicDBObject): DBCursor? = this.find(query)

fun <T> DBCollection.getDBCursor(properties: Map<String, T>): DBCursor? = this.find(createMongoObject(properties))

fun DBCollection.insertElements(elements: BasicDBObject): WriteResult = this.insert(elements)

private fun <T> createMongoObject(properties: Map<String, T>): BasicDBObject {
    val obj = BasicDBObject()
    properties.forEach { (key, value) ->
        obj.append(key, value)
    }
    return obj
}