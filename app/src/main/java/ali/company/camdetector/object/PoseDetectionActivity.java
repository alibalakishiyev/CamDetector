package ali.company.camdetector.object;

import android.os.Bundle;

import com.google.mlkit.vision.pose.accurate.AccuratePoseDetectorOptions;

import ali.company.camdetector.helpers.CMVideoHelperActivity;
import ali.company.camdetector.helpers.vision.VisionBaseProcessor;
import ali.company.camdetector.helpers.vision.posedetector.PoseDetectorProcessor;

public class PoseDetectionActivity extends CMVideoHelperActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected VisionBaseProcessor setProcessor() {
        AccuratePoseDetectorOptions options = new AccuratePoseDetectorOptions.Builder()
                .setDetectorMode(AccuratePoseDetectorOptions.STREAM_MODE)
                .build();
        return new PoseDetectorProcessor(
                options,
                true,
                false,
                false,
                false,
                true,
                this,
                graphicOverlay,
                previewView
        );
    }
}