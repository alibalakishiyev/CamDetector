package ali.company.camdetector.object;

import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.os.Bundle;

import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.face.Face;
import com.google.mlkit.vision.face.FaceDetection;
import com.google.mlkit.vision.face.FaceDetector;
import com.google.mlkit.vision.face.FaceDetectorOptions;

import java.util.ArrayList;
import java.util.List;

import ali.company.camdetector.helpers.BoxWithText;
import ali.company.camdetector.helpers.CDImageHelperActivity;

public class FaceDetectionActivity extends CDImageHelperActivity{

    private FaceDetector faceDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // High-accuracy landmark detection and face classification
        FaceDetectorOptions highAccuracyOpts =
                new FaceDetectorOptions.Builder()
                        .setPerformanceMode(FaceDetectorOptions.PERFORMANCE_MODE_ACCURATE)
                        .enableTracking()
                        .build();

        faceDetector = FaceDetection.getClient(highAccuracyOpts);
    }

    @Override
    protected void runDetection(Bitmap bitmap) {
        Bitmap finalBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        InputImage image = InputImage.fromBitmap(finalBitmap, 0);

        faceDetector.process(image)
                .addOnFailureListener(error -> {
                    error.printStackTrace();
                })
                .addOnSuccessListener(faces -> {
                    if (faces.isEmpty()) {
                        getOutputTextView().setText("No faces detected");
                    } else {
                        getOutputTextView().setText(String.format("%d faces detected", faces.size()));
                        List<BoxWithText> boxes = new ArrayList();
                        for (Face face : faces) {
                            boxes.add(new BoxWithText(face.getBoundingBox(), face.getTrackingId() + ""));
                        }
                        getInputImageView().setImageBitmap(drawDetectionResult(finalBitmap, boxes));
                    }
                });
    }
}