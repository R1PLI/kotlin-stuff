import com.mongodb.BasicDBObject
import com.mongodb.DB
import com.mongodb.DBCollection
import com.mongodb.MongoClient

fun main(args: Array<String>) {
    formMongoObject(mapOf("id" to "18.0", "title" to "faust1", "author" to "Gete2"))
            .insertElements()

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

private fun formMongoObject(properties: Map<String, String>): BasicDBObject {
    val obj = BasicDBObject()
    properties.forEach { (key, value) ->
        obj.append(key, value)
    }

    return obj
}