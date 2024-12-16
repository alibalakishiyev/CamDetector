package ali.company.camdetector.text;

import android.os.Bundle;
import android.util.Log;

import org.tensorflow.lite.support.label.Category;
import org.tensorflow.lite.task.text.nlclassifier.NLClassifier;

import java.io.IOException;
import java.util.List;

import ali.company.camdetector.helpers.TextHelperActivity;

public class SpamDetectionActivity extends TextHelperActivity {

    private static final  String MODEL_PATH = "model_spam.tflite";
    private NLClassifier classifier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            classifier = NLClassifier.createFromFile(this , MODEL_PATH);
        } catch (IOException e) {
            Log.e(SpamDetectionActivity.class.getSimpleName(),e.getMessage());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        classifier.close();
    }

    @Override
    protected void runClassification(String comment) {

        List<Category> apiResults = classifier.classify(comment);
        float score = apiResults.get(1).getScore();
        if (score > 0.8f){
            outputTextView.setText("Detected as spam.\nSpam score: "+score);
        }else {
            outputTextView.setText("Not detected as spam.\nSpam score: " + score );
        }
    }


}
