package com.example.jquesadaabeijon.simondice;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int secuency=0;
    int turns=0;
    int check=0;
    int[] colors = new int[50];
    boolean correct=false;
    int points=0;

    Button btnRed;
    Button btnGreen;
    Button btnBlue;
    Button btnYellow;

    TextView totalPoints;

    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRed = (Button)findViewById(R.id.buttonRed);
        btnGreen = (Button)findViewById(R.id.buttonGreen);
        btnBlue = (Button)findViewById(R.id.buttonBlue);
        btnYellow = (Button)findViewById(R.id.buttonYellow);

        totalPoints = (TextView)findViewById(R.id.puntuacion);

        totalPoints.setText(""+points);

        sharedPref = this.getPreferences(Context.MODE_PRIVATE);

        btnRed.setBackgroundColor(Color.RED);
        btnRed.setAlpha(0.5f);
        btnGreen.setBackgroundColor(Color.GREEN);
        btnGreen.setAlpha(0.5f);
        btnBlue.setBackgroundColor(Color.BLUE);
        btnBlue.setAlpha(0.5f);
        btnYellow.setBackgroundColor(Color.YELLOW);
        btnYellow.setAlpha(0.5f);

        findViewById(R.id.nueva).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newGame();
            }
        });

        findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                secuencyGenerate();

            }
        });
        btnRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                colorCheck(Color.RED);
                iluminarBoton(btnRed);
            }
        });
        btnGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                colorCheck(Color.GREEN);
                iluminarBoton(btnGreen);
            }
        });
        btnBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                colorCheck(Color.BLUE);
                iluminarBoton(btnBlue);
            }
        });
        btnYellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                colorCheck(Color.YELLOW);
                iluminarBoton(btnYellow);
            }
        });

    }

    public void newGame(){
        turns=0;
        points=0;
        totalPoints.setText(""+points);
        Button btnStart=(Button)findViewById(R.id.start);
        btnStart.setEnabled(true);
        TextView t= (TextView) findViewById(R.id.turno);
        t.setText("Turno: "+turns);
    }

    public void secuencyGenerate(){
        Random rd=new Random();
        int[] coloresDisponibles={Color.RED,Color.BLUE,Color.GREEN,Color.YELLOW};
        colors[turns]=coloresDisponibles[rd.nextInt(4)];
        TextView t= (TextView) findViewById(R.id.turno);
        t.setText("Turno: "+(turns+1));
        turns++;
        correct=true;
        check=0;
    }

    public void colorCheck(int color){
        Button btnStart=(Button)findViewById(R.id.start);
        final TextView loser = (TextView)findViewById(R.id.loser);
        if(color==colors[check]){
            check++;
            points+=5;
            totalPoints.setText(""+points);
        }else{
            correct=false;
        }

        if(check==turns && correct){
            points+=turns*2;
            totalPoints.setText(""+points);
            btnStart.setEnabled(true);
            btnRed.setEnabled(false);
            btnGreen.setEnabled(false);
            btnBlue.setEnabled(false);
            btnYellow.setEnabled(false);
        }
        if(!correct && turns>0){
            loser.postDelayed(new Runnable() {
                @Override
                public void run() {
                    btnRed.setVisibility(View.INVISIBLE);
                    btnGreen.setVisibility(View.INVISIBLE);
                    btnBlue.setVisibility(View.INVISIBLE);
                    btnYellow.setVisibility(View.INVISIBLE);
                    TextView t= (TextView) findViewById(R.id.turno);
                    t.setVisibility(View.INVISIBLE);
                    loser.setBackgroundColor(Color.WHITE);
                    loser.setText("HAS PERDIDO");
                    loser.setScaleX(3f);
                    loser.setScaleY(3f);
                }
            },50);
            loser.postDelayed(new Runnable() {
                @Override
                public void run() {
                    loser.setText("");
                    loser.setScaleX(1f);
                    loser.setScaleY(1f);
                    loser.setBackgroundColor(Color.GRAY);
                    TextView t= (TextView) findViewById(R.id.turno);
                    t.setText("Turno: 0");
                    botonAzul.setVisibility(View.VISIBLE);
                    botonRojo.setVisibility(View.VISIBLE);
                    botonAmarillo.setVisibility(View.VISIBLE);
                    botonVerde.setVisibility(View.VISIBLE);
                    t.setVisibility(View.VISIBLE);
                }
            },1500);
            if(record<puntuacion){
                record=puntuacion;
                record_valor.setText(""+record);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt(getString(R.string.saved_high_score), record);
                editor.commit();

            }
            puntuacion=0;
            puntos_valor.setText(""+puntuacion);
            nuevaPartida();
            botonStart.setEnabled(true);
        }
    }

    public void iluminarBoton(Button boton){
        final Button b=boton;
        b.postDelayed(new Runnable() {
            @Override
            public void run() {
                b.setAlpha(0.5f);
            }
        },0);
        b.postDelayed(new Runnable() {
            @Override
            public void run() {
                b.setAlpha(0.75f);
            }
        },50);
        b.postDelayed(new Runnable() {
            @Override
            public void run() {
                b.setAlpha(1f);
            }
        },100);
        b.postDelayed(new Runnable() {
            @Override
            public void run() {
                b.setAlpha(0.75f);
            }
        },250);
        b.postDelayed(new Runnable() {
            @Override
            public void run() {
                b.setAlpha(0.5f);
            }
        },300);
    }
}
