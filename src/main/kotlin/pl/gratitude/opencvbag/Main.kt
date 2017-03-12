package pl.gratitude.opencvbag

import org.bytedeco.javacpp.opencv_core.IPL_DEPTH_8U
import org.bytedeco.javacpp.opencv_core.IplImage
import org.bytedeco.javacpp.opencv_imgproc.*

/**
 * @author Sławomir Onyszko
 */
fun main(args: Array<String>) {
    val grabber = Grabber()

    val threshold: Double = 40.0
    val apertureSize: Int = 3

    grabber.grabIt(function = {
        val image: IplImage = IplImage.create(it.width(), it.height(), IPL_DEPTH_8U, 1) // create new image for gray scale converting
        cvCvtColor(it, image, CV_BGR2GRAY) // convert image to gray scale
        cvCanny(image, image, threshold, (threshold * 3), apertureSize) // apply canny
        image // return image for display
    }, deviceNumber = 1)
}
