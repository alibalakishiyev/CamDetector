package ali.company.camdetector.helpers;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import ali.company.camdetector.R;

public class TextHelperActivity extends AppCompatActivity {


    protected EditText inputEditText;
    protected TextView outputTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_text_helper);

        inputEditText = findViewById(R.id.editTextComment);
        outputTextView = findViewById(R.id.textViewOutputSpam);



    }

    public void onPostComment(View view){
        runClassification(inputEditText.getText().toString());

    }

    protected void runClassification(String comment){

    }
}