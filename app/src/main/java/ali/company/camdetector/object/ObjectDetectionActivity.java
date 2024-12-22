package ali.company.camdetector.object;

import android.graphics.Bitmap;
import android.os.Bundle;

import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.objects.DetectedObject;
import com.google.mlkit.vision.objects.ObjectDetection;
import com.google.mlkit.vision.objects.ObjectDetector;
import com.google.mlkit.vision.objects.defaults.ObjectDetectorOptions;

import java.util.ArrayList;
import java.util.List;

import ali.company.camdetector.helpers.BoxWithText;
import ali.company.camdetector.helpers.CDImageHelperActivity;

public class ObjectDetectionActivity extends CDImageHelperActivity {

    private ObjectDetector objectDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Multiple object detection in static images
        ObjectDetectorOptions options =
                new ObjectDetectorOptions.Builder()
                        .setDetectorMode(ObjectDetectorOptions.SINGLE_IMAGE_MODE)
                        .enableMultipleObjects()
                        .enableClassification()
                        .build();

        objectDetector = ObjectDetection.getClient(options);
    }

    @Override
    protected void runDetection(Bitmap bitmap) {
        InputImage image = InputImage.fromBitmap(bitmap, 0);
        objectDetector.process(image)
                .addOnSuccessListener(
                        detectedObjects -> {
                            // Task completed successfully
                            StringBuilder sb = new StringBuilder();
                            List<BoxWithText> list = new ArrayList<>();
                            for (DetectedObject object : detectedObjects) {
                                for (DetectedObject.Label label : object.getLabels()) {
                                    sb.append(label.getText()).append(" : ")
                                            .append(label.getConfidence()).append("\n");
                                }
                                if (!object.getLabels().isEmpty()) {
                                    list.add(new BoxWithText(object.getBoundingBox(), object.getLabels().get(0).getText()));
                                } else {
                                    list.add(new BoxWithText(object.getBoundingBox(), "Unknown"));
                                }
                            }
                            getInputImageView().setImageBitmap(drawDetectionResult(bitmap, list));
                            if (detectedObjects.isEmpty()) {
                                getOutputTextView().setText("Could not detect!!");
                            } else {
                                getOutputTextView().setText(sb.toString());
                            }
                        })
                .addOnFailureListener(
                        e -> {
                            // Task failed with an exception
                            // ...
                            e.printStackTrace();
                        });
    }
}