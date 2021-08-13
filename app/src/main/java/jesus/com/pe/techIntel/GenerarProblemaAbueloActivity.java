package jesus.com.pe.techIntel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

public class GenerarProblemaAbueloActivity extends AppCompatActivity {

    TextView problemaTxv;
    RadioButton alter1,alter2,alter3;
    Button aceptar;
    String id1,id2,id3,respCorrec;
    String url="http://51.222.31.68:5000/api/ejercicios/generaroDinero2";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generar_problema_abuelo);


    }
}