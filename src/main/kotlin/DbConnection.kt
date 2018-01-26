import com.mongodb.*

fun main(args: Array<String>) {
    //create connection and return collection from database
    val mongoCollection = setupMongoConnection("localhost", 27017)
            .getDB("api-testing")
            .getCollection("requests")

    //insert element into db
    mongoCollection.insertElements(
            createMongoObject(
                    mapOf(
                            "id" to "18.0",
                            "title" to "faust1",
                            "author" to "Gete2",
                            "array" to arrayOf(1, createMongoObject(mapOf("object id inside of array" to "20")), 3),
                            "new Object" to createMongoObject(
                                    mapOf(
                                            "inside object id" to "20",
                                            "inside object title" to "Some Title"
                                    )
                            )
                    )
            )
    )

    //create db cursor with use of Map
    val cursorMap = mongoCollection.getDBCursor(mapOf("array" to arrayOf(1, createMongoObject(mapOf("object id inside of array" to "20")), 3)))

    //create db cursor using createMongoObject method
    val cursorObject = mongoCollection.getDBCursor(createMongoObject(mapOf("no" to "no")))

    //tries to get object from db, gets error message
    println(cursorObject?.getDBObject() ?: "No such object in db")

    //tries to get object from db, gets object
    println(cursorMap?.getDBObject()?.get("array") ?: "No such object in db")

    //tries to get element from object in db, gets elements
    println(cursorMap?.getElement("array") ?: "No such element in database")

    //tries to get all elements in collection, gets elements collections
    cursorMap?.getAllElements()?.forEach { println(it) }

    //tries to get limited number of elements in collection, gets elements with fixed size
    cursorMap?.getAllElementsWithLimit(4)?.forEach { println(it) }
}

private fun setupMongoConnection(host: String, port: Int): MongoClient = MongoClient(host, port)

fun DBCollection.getDBCursor(query: BasicDBObject): DBCursor? = this.find(query)

fun <T> DBCollection.getDBCursor(properties: Map<String, T>): DBCursor? = this.find(createMongoObject(properties))

fun DBCursor.getDBObject(): DBObject? {
    return this.one()
}

fun DBCursor.getElement(parameter: String): Any? {
    return this.one().get(parameter)
}

fun DBCursor.getAllElements(): List<DBObject>? {
    var elements = emptyList<DBObject>()
    this.use {
        while (this.hasNext()) {
            elements += this.next()
        }
    }
    return elements
}

fun DBCursor.getAllElementsWithLimit(limit: Int): List<DBObject>? {
    var elements = emptyList<DBObject>()
    this.use {
        while (this.hasNext() && elements.size < limit) {
            elements += this.next()
        }
    }
    return elements
}

fun DBCollection.insertElements(elements: BasicDBObject): WriteResult = this.insert(elements)

private fun <T> createMongoObject(properties: Map<String, T>): BasicDBObject {
    val obj = BasicDBObject()
    properties.forEach { (key, value) ->
        obj.append(key, value)
    }
    return obj
}