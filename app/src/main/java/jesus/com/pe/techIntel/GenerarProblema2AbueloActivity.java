package jesus.com.pe.techIntel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Date;

import okhttp3.Response;


public class GenerarProblema2AbueloActivity extends AppCompatActivity {

    TextView problemaTxvv;
    RadioButton alter1,alter2,alter3;
    Button aceptar;
    String id1,id2,id3,respCorrec;
    String url="http://51.222.31.68:5000/api/ejercicios/generadorDinero";

    //Contador
    TextView tiempotxt;
    Date tiempoInicio,fechaFin;
    boolean termino;
    Runnable contador=new Runnable() {
        @Override
        public void run() {
            fechaFin=new Date();
            Long difFecha=fechaFin.getTime()-tiempoInicio.getTime();
            tiempotxt.setText("tiempo : "+difFecha/1000+" segundos");
            if(termino==false) {
                new Handler().postDelayed(contador, 500);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generar_problema2_abuelo);

        tiempotxt=findViewById(R.id.txtContador);
        problemaTxvv=(TextView) findViewById(R.id.txv_problema2);
        alter1=(RadioButton) findViewById(R.id.rbt_alter1);
        alter2=(RadioButton) findViewById(R.id.rbt_alter2);
        alter3=(RadioButton) findViewById(R.id.rbt_alter3);
        aceptar=(Button) findViewById(R.id.btn_aceptar2);


        //Inicio Contador
        termino=false;
        tiempotxt.setText("Tiempo: 0 segundos ");
        new Handler().postDelayed(contador,500);
        //Fin Contador


        AndroidNetworking.get(url).build().getAsJSONObject(new JSONObjectRequestListener() {
            @Override
            public void onResponse(JSONObject response) {

                tiempoInicio=new Date();

                String problema = response.optString("problema");
                String respuesta = response.optString("respuest_correcta");


                JSONArray jsonArrayAlternativas=response.optJSONArray("alternativas");
                JSONObject jsonalter1=jsonArrayAlternativas.optJSONObject(0);
                JSONObject jsonalter2=jsonArrayAlternativas.optJSONObject(1);
                JSONObject jsonalter3=jsonArrayAlternativas.optJSONObject(2);


                String idalter1=jsonalter1.optString("id");
                String idalter2=jsonalter2.optString("id");
                String idalter3=jsonalter3.optString("id");

                String alternativa1=jsonalter1.optString("alternativa");
                String alternativa2=jsonalter2.optString("alternativa");
                String alternativa3=jsonalter3.optString("alternativa");

                alter1.setText(alternativa1);
                alter2.setText(alternativa2);
                alter3.setText(alternativa3);

                id1=idalter1;
                id2=idalter2;
                id3=idalter3;

                respCorrec=respuesta;
                problemaTxvv.setText(problema);

            }
            @Override
            public void onError(ANError anError) {
                Toast.makeText(GenerarProblema2AbueloActivity.this, "Ocurrio un error contactese con el administrador", Toast.LENGTH_SHORT).show();
            }
        });

        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Contador inicio
                fechaFin=new Date();
                Long difFecha=fechaFin.getTime()-tiempoInicio.getTime();
                tiempotxt.setText("tiempo : "+difFecha/1000+" segundos");
                //Contador fin

                String respSeleccionada="";

                if(alter1.isChecked()){
                    respSeleccionada=id1;
                }
                if(alter2.isChecked()){
                    respSeleccionada=id2;
                }
                if(alter3.isChecked()){
                    respSeleccionada=id3;
                }
                if(respSeleccionada.equals(respCorrec)){
                    Toast.makeText(GenerarProblema2AbueloActivity.this, "Correcto!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(GenerarProblema2AbueloActivity.this, "Incorrecto", Toast.LENGTH_SHORT).show();
                }
                aceptar.setEnabled(true);

                termino=true;
            }
        });

    }
}