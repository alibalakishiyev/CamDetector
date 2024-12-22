package ali.company.camdetector.object;

import android.os.Bundle;

import ali.company.camdetector.helpers.CMVideoHelperActivity;
import ali.company.camdetector.helpers.vision.VisionBaseProcessor;
import ali.company.camdetector.helpers.vision.drowsiness.FaceDrowsinessDetectorProcessor;


public class DriverDrowsinessDetectionActivity extends CMVideoHelperActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected VisionBaseProcessor setProcessor() {
        return new FaceDrowsinessDetectorProcessor(graphicOverlay);
    }
}