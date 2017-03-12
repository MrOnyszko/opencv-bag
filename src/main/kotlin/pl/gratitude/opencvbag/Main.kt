package pl.gratitude.opencvbag

/**
 * @author Sławomir Onyszko
 */
fun main(args: Array<String>) {
    val grabber = Grabber()
    grabber.grabIt({ it }, 1)
}
