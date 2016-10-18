package edu.tecii.android.intereses;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {

    @Bind(R.id.inputCap)
    EditText inputCap;
    @Bind(R.id.inputPercentage)
    EditText inputPercentage;
    @Bind(R.id.inputPeriodos)
    EditText inputPeriodos;
    @Bind(R.id.btnCal)
    Button btnCal;
    @Bind(R.id.txtRes)
    TextView txtRes;
    @Bind(R.id.txtRes1)
    TextView txtRes1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnCal)
    public void handleClickSubmit() {
        hideKeyboard();
        String capital = inputCap.getText().toString().trim();
        String tasa = inputPercentage.getText().toString().trim();
        String periodo = inputPeriodos.getText().toString().trim();


        if (!capital.isEmpty()) {
            double n1 = Double.parseDouble(capital);
            double n2 = Double.parseDouble(tasa);
            double n3 = Double.parseDouble(periodo);

            double monto = n1*Math.pow((1+n2/100),n3);
            double intereses = monto-n1;

            String resu = "El monto total es: "+String.format("%8.2f\n",monto);
            String resu1 = "El interes a pagar es: "+String.format("%8.2f\n",intereses);
            txtRes.setVisibility(View.VISIBLE);
            txtRes.setText(resu);
            txtRes1.setVisibility(View.VISIBLE);
            txtRes1.setText(resu1);
        }
    }

    private void hideKeyboard() {
        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        try {
            inputManager.hideSoftInputFromInputMethod(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_IMPLICIT_ONLY);
        } catch (NullPointerException npe) {
            Log.e(getLocalClassName(), Log.getStackTraceString(npe));
        }
    }
}
