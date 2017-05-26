package pl.gratitude.opencvbag.grabber

import org.bytedeco.javacpp.opencv_core.*
import org.bytedeco.javacpp.opencv_core.CvMemStorage.create
import org.bytedeco.javacv.CanvasFrame
import org.bytedeco.javacv.OpenCVFrameConverter
import org.bytedeco.javacv.OpenCVFrameGrabber

/**
 * @author Sławomir Onyszko
 */
class MatGrabber(private val title: String = "Grabber") {

    fun <T : Mat> grab(deviceNumber: Int = 0, function: (T: Mat) -> T) {
        val grabber = OpenCVFrameGrabber(deviceNumber)
        val converter = OpenCVFrameConverter.ToMat()
        grabber.start()

        var frame: Mat = converter.convert(grabber.grab())
        val canvasFrame = CanvasFrame(title)

        canvasFrame.isResizable = false
        canvasFrame.setCanvasSize(frame.size().width(), frame.size().height())

        var image: Mat? = null
        val memoryStorage = create()

        while (canvasFrame.isVisible && !frame.isNull) {
            frame = converter.convert(grabber.grab())
            cvClearMemStorage(memoryStorage)
            image = function.invoke(frame)
            canvasFrame.showImage(converter.convert(image))
        }

        grabber.stop()
        canvasFrame.dispose()
        image?.release()
        memoryStorage.let(::cvReleaseMemStorage)
    }

}
