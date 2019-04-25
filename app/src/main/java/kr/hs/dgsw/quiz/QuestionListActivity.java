package kr.hs.dgsw.quiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static kr.hs.dgsw.quiz.util.KeyboardUtils.hideKeyboard;

public class QuestionListActivity extends AppCompatActivity {

    private String password = "1234";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_list);
    }

    public void onConfirm(View v) {
        EditText password = findViewById(R.id.editTextPassword);

        hideKeyboard(this);

        if (this.password.equals(password.getText().toString())) {
            findViewById(R.id.layoutPassword).setVisibility(View.GONE);
        }
        else {
            Toast.makeText(this, "비밀번호가 틀렸습니다.", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
