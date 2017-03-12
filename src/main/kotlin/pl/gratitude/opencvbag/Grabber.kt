package pl.gratitude.opencvbag

import org.bytedeco.javacpp.opencv_core.*
import org.bytedeco.javacv.CanvasFrame
import org.bytedeco.javacv.OpenCVFrameConverter
import org.bytedeco.javacv.OpenCVFrameGrabber

/**
 * @author Sławomir Onyszko
 */
class Grabber(val title: String = "Grabber") {

    fun <T : IplImage> grabIt(function: (T: IplImage) -> T, deviceNumber: Int) {
        val grabber = OpenCVFrameGrabber(deviceNumber)
        val converter = OpenCVFrameConverter.ToIplImage()
        grabber.start()

        var frame = converter.convert(grabber.grab())
        val canvasFrame = CanvasFrame(title)
        canvasFrame.isResizable = false
        canvasFrame.setCanvasSize(frame.width(), frame.height())

        var image: IplImage? = null
        val memoryStorage = CvMemStorage.create()
        while (canvasFrame.isVisible && frame != null) {
            frame = converter.convert(grabber.grab())
            cvClearMemStorage(memoryStorage)
            image = function.invoke(frame)
            canvasFrame.showImage(converter.convert(image))
        }

        image.let(::cvReleaseImage)
        memoryStorage.let(::cvReleaseMemStorage)
        grabber.stop()
        canvasFrame.dispose()
    }

}