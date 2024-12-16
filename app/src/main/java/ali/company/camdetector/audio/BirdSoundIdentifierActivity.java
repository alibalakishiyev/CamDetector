package ali.company.camdetector.audio;

import android.media.AudioRecord;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import org.tensorflow.lite.support.audio.TensorAudio;
import org.tensorflow.lite.support.label.Category;
import org.tensorflow.lite.task.audio.classifier.AudioClassifier;
import org.tensorflow.lite.task.audio.classifier.Classifications;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import ali.company.camdetector.helpers.AudioHelperActivity;

public class BirdSoundIdentifierActivity extends AudioHelperActivity {

    private String model = "my_birds_model.tflite";
    private TimerTask timerTask;
    private AudioRecord audioRecord;

    private AudioClassifier audioClassifier;
    private TensorAudio tensorAudio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Loading the model  from the assets folder
        try {
            audioClassifier = AudioClassifier.createFromFile(this, model);
        } catch (IOException e) {
            e.printStackTrace();
        }

        tensorAudio = audioClassifier.createInputTensorAudio();
    }

    @Override
    public void startRecording(View view) {
        super.startRecording(view);

        //showing the audio recorder specification
        TensorAudio.TensorAudioFormat format = audioClassifier.getRequiredTensorAudioFormat();
        String specs = "Number Of Channels: " + format.getChannels() + "\n"
                + "Sample Rate: " + format.getSampleRate();
        specstTextView.setText(specs);

        audioRecord = audioClassifier.createAudioRecord();
        audioRecord.startRecording();

        timerTask = new TimerTask() {
            @Override
            public void run() {
                tensorAudio.load(audioRecord);
                List<Classifications> output = audioClassifier.classify(tensorAudio);

                //Filtering out classifications with low probability
                List<Category> finalOutput = new ArrayList<>();
                for (Category category : output.get(0).getCategories()) {
                    if (category.getScore() > 0.3f && category.getLabel().equals("Bird")) {
                        finalOutput.add(category);
                    }
                }

                if(finalOutput.isEmpty()){
                    return;
                }

                finalOutput = new ArrayList<>();
                for (Category category : output.get(1).getCategories()) {
                    if (category.getScore() > 0.3f) {
                        finalOutput.add(category);
                    }
                }

                //Creating a multiline string with the filtered results
                StringBuilder outputStr = new StringBuilder();
                for (Category category : finalOutput) {
                    outputStr.append(category.getLabel())
                            .append(": ").append(category.getScore()).append("\n");
                }
                Log.d("Audio", "classify list size: " + finalOutput.size());

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        outputTextView.setText(outputStr.toString());
                    }
                });

            }
        };
        new Timer().scheduleAtFixedRate(timerTask, 1, 500);
    }

    @Override
    public void stopRecording(View view) {
        super.stopRecording(view);

        timerTask.cancel();
        audioRecord.stop();


    }
}