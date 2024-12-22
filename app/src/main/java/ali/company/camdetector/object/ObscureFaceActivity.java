package ali.company.camdetector.object;

import androidx.camera.core.CameraSelector;


import ali.company.camdetector.helpers.CMVideoHelperActivity;
import ali.company.camdetector.helpers.vision.VisionBaseProcessor;
import ali.company.camdetector.helpers.vision.obscure.ObscureFaceProcessor;
import ali.company.camdetector.helpers.vision.obscure.ObscureType;

public class ObscureFaceActivity extends CMVideoHelperActivity {

    private ObscureFaceProcessor obscureFaceProcessor;

    @Override
    protected VisionBaseProcessor setProcessor() {
        obscureFaceProcessor = new ObscureFaceProcessor(graphicOverlay);
        obscureFaceProcessor.setObscureType(ObscureType.TRANSLUCENT);
        return obscureFaceProcessor;
    }

    @Override
    protected int getLensFacing() {
        return CameraSelector.LENS_FACING_FRONT;
    }
}
