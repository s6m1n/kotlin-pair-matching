package pairmatching.model

class Crew(
    private val course: Course,
    private val name: String,
){
    fun printInfo(){
        println("Course: ${course}, Name: ${name}")
    }
}