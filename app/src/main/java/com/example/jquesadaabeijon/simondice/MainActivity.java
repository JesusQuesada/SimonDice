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
    Button btnCentral;

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
        btnCentral=(Button)findViewById(R.id.buttonCentral);

        totalPoints = (TextView)findViewById(R.id.puntuacion);

        totalPoints.setText("" + points);

        sharedPref = this.getPreferences(Context.MODE_PRIVATE);

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
        findViewById(R.id.buttonCentral).setBackgroundColor(Color.GRAY);
        turns=0;
        points=0;
        totalPoints.setText(""+points);
        Button btnStart=(Button)findViewById(R.id.start);
        btnStart.setEnabled(true);
        btnCentral.setEnabled(true);
        TextView t= (TextView) findViewById(R.id.turno);
        t.setText("Turno: "+turns);
    }

    public void updateBackground(int color){
        View fondo=findViewById(R.id.buttonCentral);
        fondo.setBackgroundColor(color);
    }

    public void secuencyGenerate(){
        Random rd=new Random();
        int[] colorsStock={Color.RED,Color.BLUE,Color.GREEN,Color.YELLOW};
        colors[turns]=colorsStock[rd.nextInt(4)];
        TextView t= (TextView) findViewById(R.id.turno);
        t.setText("Turno: "+(turns+1));
        turns++;
        showSecuency();
        correct=true;
        check=0;
    }

    public void showSecuency(){
        final View fondo=findViewById(R.id.buttonCentral);
        secuency=0;
        for(int i=0;i<turns;i++) {
            if (colors[i] != 0) {
                fondo.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        updateBackground(colors[secuency]);
                        fondo.setAlpha(0.2f);
                    }
                }, (i+1) * 500);

                //cambios de brillo aqui dentro
                fondo.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        fondo.setAlpha(0.5f);
                    }
                },(500*(i+1))+100);
                fondo.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        fondo.setAlpha(0.9f);
                    }
                },(500*(i+1))+150);

                fondo.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        fondo.setAlpha(1f);
                    }
                },(500*(i+1))+250);
                fondo.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        fondo.setAlpha(0.9f);
                    }
                },(500*(i+1))+350);
                fondo.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        fondo.setAlpha(0.5f);
                    }
                },(500*(i+1))+400);

                fondo.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        fondo.setAlpha(0.2f);
                        secuency++;
                    }
                },(500*(i+1))+450);

            }

        }
        fondo.postDelayed(new Runnable() {
            @Override
            public void run() {
                fondo.setBackground(getResources().getDrawable(R.drawable.center_hover));
                fondo.setAlpha(1f);
                btnRed.setEnabled(true);
                btnGreen.setEnabled(true);
                btnBlue.setEnabled(true);
                btnYellow.setEnabled(true);
            }
        },(turns+1)*500);

        Button botonStart=(Button)findViewById(R.id.start);
        botonStart.setEnabled(false);
        btnCentral.setEnabled(false);
    }


    public void colorCheck(int color){
        Button btnStart=(Button)findViewById(R.id.start);
        final Button loser=(Button)findViewById(R.id.buttonCentral);
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
            btnCentral.setEnabled(true);
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
                    loser.setText("MAL");
                    loser.setScaleX(6f);
                    loser.setScaleY(3f);

                }
            },50);
            loser.postDelayed(new Runnable() {
                @Override
                public void run() {
                    loser.setText("");
                    loser.setScaleX(1f);
                    loser.setScaleY(1f);
                    loser.setBackground(getResources().getDrawable(R.drawable.center_hover));
                    TextView t= (TextView) findViewById(R.id.turno);
                    t.setText("Turno: 0");
                    btnRed.setVisibility(View.VISIBLE);
                    btnGreen.setVisibility(View.VISIBLE);
                    btnBlue.setVisibility(View.VISIBLE);
                    btnYellow.setVisibility(View.VISIBLE);
                    t.setVisibility(View.VISIBLE);
                }
            },1500);

            points=0;
            totalPoints.setText(""+points);
            newGame();
            btnStart.setEnabled(true);
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
