/*
 * Copyright 2020 Google LLC. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.mlkit_pose.kotlin.posedetector

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.mlkit_pose.GraphicOverlay
import com.google.common.primitives.Ints
import com.example.mlkit_pose.GraphicOverlay.Graphic
import com.example.mlkit_pose.R
import com.google.mlkit.vision.pose.Pose
import com.google.mlkit.vision.pose.PoseLandmark
import java.lang.Math.max
import java.lang.Math.min
import java.util.Locale
import kotlin.math.atan2

/** Draw the detected pose in preview.  */
class PoseGraphic internal constructor(
    overlay: GraphicOverlay,
    private val pose: Pose,
    private val showInFrameLikelihood: Boolean,
    private val visualizeZ: Boolean,
    private val rescaleZForVisualization: Boolean,
    private val poseClassification: List<String>,
    private val exName: String?) : GraphicOverlay.Graphic(overlay) {
        private var zMin = java.lang.Float.MAX_VALUE
        private var zMax = java.lang.Float.MIN_VALUE
        private val classificationTextPaint: Paint
        private val leftPaint: Paint
        private val rightPaint: Paint
        private val whitePaint: Paint
        private val wrongPaint: Paint
        private val DownPaint: Paint
        private val UpPaint: Paint
        init {
            classificationTextPaint = Paint()
            classificationTextPaint.color = Color.WHITE
            classificationTextPaint.textSize = POSE_CLASSIFICATION_TEXT_SIZE
            classificationTextPaint.setShadowLayer(5.0f, 0f, 0f, Color.BLACK)

            whitePaint = Paint()
            whitePaint.strokeWidth = STROKE_WIDTH
            whitePaint.color = Color.WHITE
            whitePaint.textSize = IN_FRAME_LIKELIHOOD_TEXT_SIZE
            leftPaint = Paint()
            leftPaint.strokeWidth = STROKE_WIDTH
            leftPaint.color = Color.BLUE
            rightPaint = Paint()
            rightPaint.strokeWidth = STROKE_WIDTH
            rightPaint.color = Color.YELLOW
            wrongPaint = Paint()
            wrongPaint.strokeWidth = STROKE_WIDTH
            wrongPaint.color = Color.RED
            DownPaint = Paint()
            DownPaint.strokeWidth = STROKE_WIDTH
            DownPaint.color = Color.GREEN
            UpPaint = Paint()
            UpPaint.strokeWidth = STROKE_WIDTH
            UpPaint.color = Color.MAGENTA

        }
    fun getAngle(firstPoint: PoseLandmark, midPoint: PoseLandmark, lastPoint: PoseLandmark): Double {
        var result = Math.toDegrees(
            (atan2(lastPoint.getPosition().y - midPoint.getPosition().y,
                lastPoint.getPosition().x - midPoint.getPosition().x)
                    - atan2(firstPoint.getPosition().y - midPoint.getPosition().y,
                firstPoint.getPosition().x - midPoint.getPosition().x)).toDouble()
        )
        result = Math.abs(result) // Angle should never be negative
        if (result > 180) {
            result = 360.0 - result // Always get the acute representation of the angle
        }
        return result
    }
    override fun draw(canvas: Canvas) {
        val landmarks = pose.allPoseLandmarks
        if (landmarks.isEmpty()) {
            return
        }

        // Draw pose classification text.
        val classificationX = POSE_CLASSIFICATION_TEXT_SIZE * 0.5f
        for (i in poseClassification.indices) {
            val classificationY = canvas.height - (
                    POSE_CLASSIFICATION_TEXT_SIZE * 1.5f * (poseClassification.size - i).toFloat()
                    )
            canvas.drawText(
                poseClassification[i],
                classificationX,
                classificationY,
                classificationTextPaint
            )
        }

        // Draw all the points
        for (landmark in landmarks) {
            drawPoint(canvas, landmark, whitePaint)
            if (visualizeZ && rescaleZForVisualization) {
                zMin = min(zMin, landmark.position3D.z)
                zMax = max(zMax, landmark.position3D.z)
            }
        }

        val leftShoulder = pose.getPoseLandmark(PoseLandmark.LEFT_SHOULDER)
        val rightShoulder = pose.getPoseLandmark(PoseLandmark.RIGHT_SHOULDER)
        val leftElbow = pose.getPoseLandmark(PoseLandmark.LEFT_ELBOW)
        val rightElbow = pose.getPoseLandmark(PoseLandmark.RIGHT_ELBOW)
        val leftWrist = pose.getPoseLandmark(PoseLandmark.LEFT_WRIST)
        val rightWrist = pose.getPoseLandmark(PoseLandmark.RIGHT_WRIST)
        val leftHip = pose.getPoseLandmark(PoseLandmark.LEFT_HIP)
        val rightHip = pose.getPoseLandmark(PoseLandmark.RIGHT_HIP)
        val leftKnee = pose.getPoseLandmark(PoseLandmark.LEFT_KNEE)
        val rightKnee = pose.getPoseLandmark(PoseLandmark.RIGHT_KNEE)
        val leftAnkle = pose.getPoseLandmark(PoseLandmark.LEFT_ANKLE)
        val rightAnkle = pose.getPoseLandmark(PoseLandmark.RIGHT_ANKLE)

        val leftPinky = pose.getPoseLandmark(PoseLandmark.LEFT_PINKY)
        val rightPinky = pose.getPoseLandmark(PoseLandmark.RIGHT_PINKY)
        val leftIndex = pose.getPoseLandmark(PoseLandmark.LEFT_INDEX)
        val rightIndex = pose.getPoseLandmark(PoseLandmark.RIGHT_INDEX)
        val leftThumb = pose.getPoseLandmark(PoseLandmark.LEFT_THUMB)
        val rightThumb = pose.getPoseLandmark(PoseLandmark.RIGHT_THUMB)
        val leftHeel = pose.getPoseLandmark(PoseLandmark.LEFT_HEEL)
        val rightHeel = pose.getPoseLandmark(PoseLandmark.RIGHT_HEEL)
        val leftFootIndex = pose.getPoseLandmark(PoseLandmark.LEFT_FOOT_INDEX)
        val rightFootIndex = pose.getPoseLandmark(PoseLandmark.RIGHT_FOOT_INDEX)

        /* Get Angle */

        // Hip angle
        val rightHipAngle = getAngle(rightShoulder,rightHip,rightKnee)
        val leftHipAngle = getAngle(leftShoulder,leftHip,leftKnee)
        // Shoulder angle
        val rightShoulderAngle = getAngle(rightHip,rightShoulder,rightElbow)
        val leftShoulderAngle = getAngle(leftHip,leftShoulder,leftElbow)
        // Elbow Angle
        val rightElbowAngle = getAngle(rightShoulder,rightElbow,rightWrist)
        val leftElbowAngle = getAngle(leftShoulder,leftElbow,leftWrist)
        // Knee Angle
        val rightKneeAngle = getAngle(rightHip,rightKnee,rightAnkle)
        val leftKneeAngle = getAngle(leftHip,leftKnee,leftAnkle)

        Log.d("ANGLE","=================\n"+"Right_HipAngle : "+rightHipAngle.toString()+"\nLeft_HipAngle : "+leftHipAngle.toString()+
            "\nRight_ShoulderAngle : "+rightShoulderAngle.toString()+"\nLeft_ShoulderAngle : "+leftShoulderAngle.toString()+"\nRight_ElbowAngle : "+rightElbowAngle.toString()
            +"\nLeft_ElbowAngle : "+leftElbowAngle.toString()+"\nRight_KneeAngle : "+rightKneeAngle.toString()+"\nLeft_KneeAngle : "+leftKneeAngle.toString()
        )


        // Left body
        drawLine(canvas, leftShoulder, rightShoulder, whitePaint)
        drawLine(canvas, leftHip, rightHip, whitePaint)
        drawLine(canvas, leftShoulder, leftElbow, leftPaint)
        drawLine(canvas, leftElbow, leftWrist, leftPaint)
        drawLine(canvas, leftShoulder, leftHip, leftPaint)
        drawLine(canvas, leftHip, leftKnee, leftPaint)
        drawLine(canvas, leftKnee, leftAnkle, leftPaint)
        drawLine(canvas, leftWrist, leftThumb, leftPaint)
        drawLine(canvas, leftWrist, leftPinky, leftPaint)
        drawLine(canvas, leftWrist, leftIndex, leftPaint)
        drawLine(canvas, leftIndex, leftPinky, leftPaint)
        drawLine(canvas, leftAnkle, leftHeel, leftPaint)
        drawLine(canvas, leftHeel, leftFootIndex, leftPaint)

        // Right body
        drawLine(canvas, rightShoulder, rightElbow, rightPaint)
        drawLine(canvas, rightElbow, rightWrist, rightPaint)
        drawLine(canvas, rightShoulder, rightHip, rightPaint)
        drawLine(canvas, rightHip, rightKnee, rightPaint)
        drawLine(canvas, rightKnee, rightAnkle, rightPaint)
        drawLine(canvas, rightWrist, rightThumb, rightPaint)
        drawLine(canvas, rightWrist, rightPinky, rightPaint)
        drawLine(canvas, rightWrist, rightIndex, rightPaint)
        drawLine(canvas, rightIndex, rightPinky, rightPaint)
        drawLine(canvas, rightAnkle, rightHeel, rightPaint)
        drawLine(canvas, rightHeel, rightFootIndex, rightPaint)

        //77 ~ 99 / 160 ~ 181
        if ((77.0 < rightElbowAngle) && (99.0 > rightElbowAngle)){
            drawLine(canvas, rightShoulder, rightElbow, DownPaint)
            drawLine(canvas, rightElbow, rightWrist, DownPaint)
        }
        else if ((160.0 < rightElbowAngle) && (181.0 > rightElbowAngle)) {
            drawLine(canvas, rightShoulder, rightElbow, UpPaint)
            drawLine(canvas, rightElbow, rightWrist, UpPaint)
        }
        else {
            drawLine(canvas, rightShoulder, rightElbow, wrongPaint)
            drawLine(canvas, rightElbow, rightWrist, wrongPaint)
        }




        // Draw inFrameLikelihood for all points
        if (showInFrameLikelihood) {
            for (landmark in landmarks) {
                canvas.drawText(
                    String.format(Locale.US, "%.2f", landmark.inFrameLikelihood),
                    translateX(landmark.position.x),
                    translateY(landmark.position.y),
                    whitePaint
                )
            }
        }
    }

    internal fun drawPoint(canvas: Canvas, landmark: PoseLandmark, paint: Paint) {
        val point = landmark.position
        canvas.drawCircle(translateX(point.x), translateY(point.y), DOT_RADIUS, paint)
    }

    internal fun drawLine(
        canvas: Canvas,
        startLandmark: PoseLandmark?,
        endLandmark: PoseLandmark?,
        paint: Paint
    ) {
        // When visualizeZ is true, sets up the paint to draw body line in different colors based on
        // their z values.

        if (visualizeZ) {
            val start = startLandmark!!.position3D
            val end = endLandmark!!.position3D

            // Gets the range of z value.
            val zLowerBoundInScreenPixel: Float
            val zUpperBoundInScreenPixel: Float

            if (rescaleZForVisualization) {
                zLowerBoundInScreenPixel = min(-0.001f, scale(zMin))
                zUpperBoundInScreenPixel = max(0.001f, scale(zMax))
            } else {
                // By default, assume the range of z value in screen pixel is [-canvasWidth, canvasWidth].
                val defaultRangeFactor = 1f
                zLowerBoundInScreenPixel = -defaultRangeFactor * canvas.width
                zUpperBoundInScreenPixel = defaultRangeFactor * canvas.width
            }

            // Gets average z for the current body line
            val avgZInImagePixel = (start.z + end.z) / 2
            val zInScreenPixel = scale(avgZInImagePixel)

            if (zInScreenPixel < 0) {
                // Sets up the paint to draw the body line in red if it is in front of the z origin.
                // Maps values within [zLowerBoundInScreenPixel, 0) to [255, 0) and use it to control the
                // color. The larger the value is, the more red it will be.
                var v = (zInScreenPixel / zLowerBoundInScreenPixel * 255).toInt()
                v = Ints.constrainToRange(v, 0, 255)
                paint.setARGB(255, 255, 255 - v, 255 - v)
            } else {
                // Sets up the paint to draw the body line in blue if it is behind the z origin.
                // Maps values within [0, zUpperBoundInScreenPixel] to [0, 255] and use it to control the
                // color. The larger the value is, the more blue it will be.
                var v = (zInScreenPixel / zUpperBoundInScreenPixel * 255).toInt()
                v = Ints.constrainToRange(v, 0, 255)
                paint.setARGB(255, 255 - v, 255 - v, 255)
            }

            canvas.drawLine(
                translateX(start.x),
                translateY(start.y),
                translateX(end.x),
                translateY(end.y),
                paint
            )
        } else {
            val start = startLandmark!!.position
            val end = endLandmark!!.position
            canvas.drawLine(
                translateX(start.x), translateY(start.y), translateX(end.x), translateY(end.y), paint
            )
            //Log.d("COORDINATE",
            //    "startX : "+translateX(start.x).toString()+"\n"+"startY : "+translateY(start.y).toString()+"\n"
            //    +"endX : "+translateXsub(end.x).toString()+"\n"+"enY : "+translateY(end.y).toString()+"\n==============================")
        }
    }

    companion object {
        private val DOT_RADIUS = 8.0f
        private val IN_FRAME_LIKELIHOOD_TEXT_SIZE = 30.0f
        private val STROKE_WIDTH = 10.0f
        private val POSE_CLASSIFICATION_TEXT_SIZE = 60.0f

    }
}
