# OpenCV Bag

Project that contains OpenCV (JavaCV) samples written in Kotlin.

## Samples:

### Canny edges detection

![Canny](https://github.com/MrOnyszko/opencv-bag/blob/master/art/canny.jpeg)

Using IplImageGrabber class:

```kotlin

    val grabber = IplImageGrabber()

    val threshold: Double = 20.0
    val apertureSize: Int = 3

    grabber.grab {
        val image: IplImage = IplImage.create(it.width(), it.height(), IPL_DEPTH_8U, 1) // create new image for gray scale converting
        cvCvtColor(it, image, CV_BGR2GRAY) // convert image to gray scale
        cvCanny(image, image, threshold, (threshold * 3), apertureSize) // apply canny
        image // pass image for display
    }

```

Using MatGrabber class:

```kotlin

    val grabber = MatGrabber()

    val threshold: Double = 20.0
    val apertureSize: Int = 3

    grabber.grab {
        val image = Mat(it.size().width(), it.size().height(), IPL_DEPTH_8U, 1) // create new image for gray scale converting
        cvtColor(it, image, CV_BGR2GRAY) // convert image to gray scale
        Canny(image, image, threshold, (threshold * 2), apertureSize, true) // apply canny
        image // pass image for display
    }

```

## License

   Copyright [2017] [Sławomir Onyszko]

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.