import com.mongodb.*

fun main(args: Array<String>) {
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
    ).insertElements()


    //get element
    val cursorMap = getMongoDB()
            .getCollection("requests")
            .getDBCursor(mapOf("array" to arrayOf(1, 2, 3)))

    val obj = createMongoObject(mapOf("array" to arrayOf(1, 2, 3)))

    val cursorObject = getMongoDB()
            .getCollection("requests")
            .getDBCursor(obj)

    val wtfMap = cursorMap.one().get("array")
    val wtfObject = cursorObject.one().get("array")

    val wtfArray = arrayOf(wtfMap)

    println()
}

private fun getMongoDB(): DB {
    return MongoClient("localhost", 27017)
            .getDB("api-testing")

}

private fun DB.getCollectionFromDB(collectionName: String): DBCollection {
    return this.getCollection(collectionName)
}

fun BasicDBObject.insertElements() {
    getMongoDB()
            .getCollectionFromDB("requests")
            .insert(this)
}

fun DBCollection.getDBCursor(query: BasicDBObject): DBCursor = this.find(query)

fun <T> DBCollection.getDBCursor(properties: Map<String, T>): DBCursor = this.find(createMongoObject(properties))

private fun <T> createMongoObject(properties: Map<String, T>): BasicDBObject {
    val obj = BasicDBObject()
    properties.forEach { (key, value) ->
        obj.append(key, value)
    }
    return obj
}