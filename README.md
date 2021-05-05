# IT's SMU-TEAM Pose Detection

<img src="https://img.shields.io/badge/minSdk-21-green"><img src="https://img.shields.io/badge/jdk-1.8-red"><img src="https://img.shields.io/badge/Android-4.1.3-yellow">

This repository currently implemented the Project Core Function using [MLKit Pose Detection](https://developers.google.com/ml-kit/vision/pose-detection).

MLKit is a mobile SDK that brings Google's on-device machine learning expertise to Android and iOS apps.

This API run on-device, allowing for real-time use cases where you want to process a live camera stream for example.

> [Google MLKit Overview](https://developers.google.com/ml-kit/guides)



### Requirements

* Android Studio 4.1.3
* kotlin-version 1.4.21
* gradle 4.1.3
* minSdkVersion 21 (Min API 21)
  targetSdkVersion 30 
* jdk 1.8 (jvmTarget 1.8)

### File Structure

📦mlkit_pose
 ┣ 📂kotlin
 ┃ ┣ 📂posedetector
 ┃ ┃ ┣ 📂classification
 ┃ ┃ ┃ ┣ 📜ClassificationResult.java
 ┃ ┃ ┃ ┣ 📜EMASmoothing.java
 ┃ ┃ ┃ ┣ 📜PoseClassifier.java
 ┃ ┃ ┃ ┣ 📜PoseClassifierProcessor.java
 ┃ ┃ ┃ ┣ 📜PoseEmbedding.java
 ┃ ┃ ┃ ┣ 📜PoseSample.java
 ┃ ┃ ┃ ┣ 📜RepetitionCounter.java
 ┃ ┃ ┃ ┗ 📜Utils.java
 ┃ ┃ ┣ 📜PoseDetectorProcessor.kt
 ┃ ┃ ┗ 📜**PoseGraphic.kt**
 ┃ ┣ 📜**CameraXLivePreviewActivity.kt**
 ┃ ┣ 📜ChooserActivity.kt
 ┃ ┣ 📜LivePreviewActivity.kt
 ┃ ┗ 📜VisionProcessorBase.kt
 ┣ 📂preference
 ┃ ┣ 📜CameraXLivePreviewPreferenceFragment.java
 ┃ ┣ 📜LivePreviewPreferenceFragment.java
 ┃ ┣ 📜PreferenceUtils.java
 ┃ ┣ 📜SettingsActivity.java
 ┃ ┗ 📜StillImagePreferenceFragment.java
 ┣ 📜BitmapUtils.java
 ┣ 📜CameraImageGraphic.java
 ┣ 📜CameraSource.java
 ┣ 📜CameraSourcePreview.java
 ┣ 📜CameraXViewModel.java
 ┣ 📜FrameMetadata.java
 ┣ 📜GraphicOverlay.java
 ┣ 📜InferenceInfoGraphic.java
 ┣ 📜ScopedExecutor.java
 ┗ 📜VisionImageProcessor.java

### Usage

Connect your Android phone to Computer, and just run.



### Demo

> preparing....



### LICENSE

Apache License 2.0







