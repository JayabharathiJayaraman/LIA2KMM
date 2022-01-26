package se.mobileinteraction.jordbruksverketkmm

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}