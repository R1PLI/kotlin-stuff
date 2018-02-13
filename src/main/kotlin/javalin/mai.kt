package javalin

import io.javalin.ApiBuilder.*
import io.javalin.Javalin

data class User(val name: String, val email: String, val id: Int)


fun main(args: Array<String>) {
    val userDao = UserDao()

    val app = Javalin.create().apply {
        port(7000)
        exception(Exception::class.java) { e, _ -> e.printStackTrace() }
        error(404) { ctx ->
            ctx.json("not found")
        }
    }.start()

    app.routes {
        get("/") { it.result("Hello world!") }
        get("/users") { it.json(userDao.users) }
        get("/users/:id") {
            it.json(userDao.findById(
                    it.param("id")!!.toInt())!!
            )
        }
        get("/users/email/:email") { it.json(userDao.findByEmail(it.param("email")!!)!!) }

        patch("/users/update/:id") {
            val user = it.bodyAsClass(User::class.java)
            userDao.update(
                    id = it.param("id")!!.toInt(),
                    user = user
            )
            it.status(204)
        }
        delete("/users/delete/:id") {
            userDao.delete(it.param("id")!!.toInt())
            it.status(204)
        }
    }
}