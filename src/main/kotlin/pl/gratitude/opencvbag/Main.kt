package pl.gratitude.opencvbag

import org.bytedeco.javacpp.opencv_core.*
import org.bytedeco.javacpp.opencv_imgproc.*
import pl.gratitude.opencvbag.grabber.IplImageGrabber
import pl.gratitude.opencvbag.grabber.MatGrabber

/**
 * @author Sławomir Onyszko
 */
fun main(args: Array<String>) {
    matGrabber()
}

fun iplImageGrabber() {
    val grabber = IplImageGrabber()

    val threshold: Double = 20.0
    val apertureSize: Int = 3

    grabber.grab {
        val image: IplImage = IplImage.create(it.width(), it.height(), IPL_DEPTH_8U, 1) // create new image for gray scale converting
        cvCvtColor(it, image, CV_BGR2GRAY) // convert image to gray scale
        cvCanny(image, image, threshold, (threshold * 3), apertureSize) // apply canny
        image // pass image for display
    }
}

fun matGrabber() {
    val grabber = MatGrabber()

    val threshold: Double = 20.0
    val apertureSize: Int = 3

    grabber.grab {
        val image = Mat(it.size().width(), it.size().height(), IPL_DEPTH_8U, 1) // create new image for gray scale converting
        cvtColor(it, image, CV_BGR2GRAY) // convert image to gray scale
        Canny(image, image, threshold, (threshold * 2), apertureSize, true) // apply canny
        image // pass image for display
    }
}
