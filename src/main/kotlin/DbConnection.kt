import com.mongodb.BasicDBObject
import com.mongodb.DB
import com.mongodb.DBCollection
import com.mongodb.MongoClient

fun main(args: Array<String>) {
    createMongoObject(
            mapOf(
                    "id" to "18.0",
                    "title" to "faust1",
                    "author" to "Gete2",
                    "array" to arrayOf(1,2,3),
                    "new Object" to createMongoObject(
                            mapOf(
                                    "inside object id" to "20",
                                    "inside object title" to "Some Title"
                            )
                    )
            )
    ).insertElements()

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

private fun <T> createMongoObject(properties: Map<String, T>): BasicDBObject {
    val obj = BasicDBObject()
    properties.forEach { (key, value) ->
        obj.append(key, value)
    }
    return obj
}