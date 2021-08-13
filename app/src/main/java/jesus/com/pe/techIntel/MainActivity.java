package jesus.com.pe.techIntel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnProblemas,btnPotencias,btnConversiones;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnPotencias=(Button) findViewById(R.id.btn_Potencias);
        btnConversiones=(Button) findViewById(R.id.btn_Conversiones);
        btnProblemas=(Button) findViewById(R.id.btn_GenerarDinero);

        btnProblemas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,GenerarProblema2AbueloActivity.class);
                startActivity(intent);
            }
        });
        btnConversiones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ConversionesActivity.class);
                startActivity(intent);
            }
        });
        btnPotencias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,PotenciasActivity.class);
                startActivity(intent);
            }
        });

    }
}