package delegate

import java.beans.PropertyChangeListener


class Persona(
        val name: String, age: Int, salary: Int
) : PropertyChangeAware() {
    var age: Int by ObservableProperty(age, changeSupport)
    var salary: Int by ObservableProperty(salary, changeSupport)
}


fun main(args: Array<String>) {
    val p = Persona("Alexander", 22, 750)
    p.addPropertyChangeListener(
            PropertyChangeListener { event ->
                println("Property ${event.propertyName} changed " +
                        "from ${event.oldValue} to ${event.newValue}")
            }
    )
    println(p.name)
    p.age = 23
    p.salary = 751
}