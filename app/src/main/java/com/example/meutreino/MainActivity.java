package com.example.meutreino;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {
    private TextView timerTextView;
    private Button startButton, stopButton, resetButton;
    private Handler timerHandler = new Handler();
    private long startTime = 0L;
    private long timeInMilliseconds = 0L;

    private Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
            int seconds = (int) (timeInMilliseconds / 1000);
            int minutes = seconds / 60;
            int milliseconds = (int) (timeInMilliseconds % 1000);
            seconds = seconds % 60;

            // Atualiza o formato para mm:ss:SSS (minutos:segundos:milissegundos)
            timerTextView.setText(String.format("%02d:%02d:%03d", minutes, seconds, milliseconds));

            timerHandler.postDelayed(this, 10); // Atualiza a cada 10ms para capturar milissegundos
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Window window = getWindow();
        window.setStatusBarColor(Color.TRANSPARENT);
        window.getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        );

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        ViewPager2 viewPager = findViewById(R.id.viewPager);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("");
        }

        WorkoutPagerAdapter adapter = new WorkoutPagerAdapter(this);
        viewPager.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Peito/\nBíceps");
                    break;
                case 1:
                    tab.setText("Inferiores 1");
                    break;
                case 2:
                    tab.setText("Costa/Tríceps");
                    break;
                case 3:
                    tab.setText("Inferiores 2");
                    break;
                case 4:
                    tab.setText("Ombro/\nAntBrç");
                    break;
            }
        }).attach();

        // Configurando o cronômetro
        timerTextView = findViewById(R.id.timerTextView);
        startButton = findViewById(R.id.startButton);
        stopButton = findViewById(R.id.stopButton);
        resetButton = findViewById(R.id.resetButton);

        startButton.setOnClickListener(v -> {
            // Inicia o cronômetro
            startTime = SystemClock.uptimeMillis() - timeInMilliseconds;
            timerHandler.postDelayed(timerRunnable, 0);

            // Desativa o botão "Iniciar" e ativa os outros dois
            startButton.setEnabled(false);
            stopButton.setEnabled(true);
            resetButton.setEnabled(true);

            // Ajustando as cores dos botões
            startButton.setBackgroundTintList(ContextCompat.getColorStateList(this, android.R.color.darker_gray)); // Cor de inatividade
            stopButton.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorRed)); // Cor de ativo
            resetButton.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorGray)); // Cor de ativo
        });

        stopButton.setOnClickListener(v -> {
            // Para o cronômetro
            timerHandler.removeCallbacks(timerRunnable);

            // Desativa o botão "Parar" e ativa o "Iniciar" e "Reiniciar"
            startButton.setEnabled(true);
            stopButton.setEnabled(false);
            resetButton.setEnabled(true);

            // Ajustando as cores dos botões
            startButton.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorGreen)); // Cor de ativo
            stopButton.setBackgroundTintList(ContextCompat.getColorStateList(this, android.R.color.darker_gray)); // Cor de inatividade
            resetButton.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorGray)); // Cor de ativo
        });

        resetButton.setOnClickListener(v -> {
            // Reinicia o cronômetro
            timerHandler.removeCallbacks(timerRunnable);
            startTime = 0L;
            timeInMilliseconds = 0L;
            timerTextView.setText("00:00:00");

            // Desativa o botão "Reiniciar" e ativa o "Iniciar" e "Parar"
            startButton.setEnabled(true);
            stopButton.setEnabled(false);
            resetButton.setEnabled(false);

            // Ajustando as cores dos botões
            startButton.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorGreen)); // Cor de ativo
            stopButton.setBackgroundTintList(ContextCompat.getColorStateList(this, android.R.color.darker_gray)); // Cor de inatividade
            resetButton.setBackgroundTintList(ContextCompat.getColorStateList(this, android.R.color.darker_gray)); // Cor de inatividade
        });

        stopButton.setEnabled(false);
        resetButton.setEnabled(false);

        startButton.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorGreen)); // Cor de ativo
        stopButton.setBackgroundTintList(ContextCompat.getColorStateList(this, android.R.color.darker_gray)); // Cor de inatividade
        resetButton.setBackgroundTintList(ContextCompat.getColorStateList(this, android.R.color.darker_gray)); // Cor de inatividade
    }
}
