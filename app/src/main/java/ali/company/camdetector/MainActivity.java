package ali.company.camdetector;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import ali.company.camdetector.audio.AudioClassificationActivity;
import ali.company.camdetector.audio.BirdSoundIdentifierActivity;
import ali.company.camdetector.helpers.AudioHelperActivity;
import ali.company.camdetector.helpers.ImageHelperActivity;
import ali.company.camdetector.helpers.TextHelperActivity;
import ali.company.camdetector.image.FaceDetectionActivity;
import ali.company.camdetector.image.FlowerIdentificationActivity;
import ali.company.camdetector.image.ObjectDetectionActivity;
import ali.company.camdetector.image.imageClassificationActivity;
import ali.company.camdetector.text.SpamDetectionActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
    }
    public void onGoToImageActivity (View view){

        Intent intent = new Intent(this, imageClassificationActivity.class);
        startActivity(intent);
    }

    public void onGoToFlowerIdentification(View view){

        Intent intent = new Intent(this, FlowerIdentificationActivity.class);
        startActivity(intent);
    }
    public void onGoToObjectDetection(View view){

        Intent intent = new Intent(this, ObjectDetectionActivity.class);
        startActivity(intent);
    }

    public void onGoToFaceDetection(View view){

        Intent intent = new Intent(this, FaceDetectionActivity.class);
        startActivity(intent);
    }
    public void onGoToAudioClassification(View view){

        Intent intent = new Intent(this, AudioClassificationActivity.class);
        startActivity(intent);
    }
    public void onGoToBirdSoundIdentifier(View view){

        Intent intent = new Intent(this, BirdSoundIdentifierActivity.class);
        startActivity(intent);
    }
    public void onGoToSpamDetection(View view){

        Intent intent = new Intent(this, SpamDetectionActivity.class);
        startActivity(intent);
    }
}