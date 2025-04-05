package com.example.meutreino;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class WorkoutFragment extends Fragment {
    private static final String ARG_SECTION = "section_number";

    public static WorkoutFragment newInstance(int sectionNumber) {
        WorkoutFragment fragment = new WorkoutFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_workout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayout workoutContainer = view.findViewById(R.id.workoutContainer);
        int section = getArguments() != null ? getArguments().getInt(ARG_SECTION) : 0;
        List<String> workouts = getWorkoutList(section);

        for (String workout : workouts) {
            LinearLayout itemLayout = createWorkoutItem(workout);
            workoutContainer.addView(itemLayout);
        }
    }

    private LinearLayout createWorkoutItem(String workout) {
        LinearLayout itemLayout = new LinearLayout(getContext());
        itemLayout.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        itemLayout.setOrientation(LinearLayout.HORIZONTAL);
        itemLayout.setGravity(Gravity.CENTER_VERTICAL);
        itemLayout.setBackgroundColor(Color.WHITE);
        itemLayout.setPadding(20, 20, 20, 20);

        TextView textView = new TextView(getContext());
        textView.setText(workout);
        textView.setTextColor(Color.DKGRAY);
        textView.setTextSize(20);
        textView.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
        itemLayout.addView(textView);

        if (workout.contains("ESTEIRA") || workout.contains("ABDOMÊN")) {
            Button completeButton = createCompleteButton(itemLayout, textView);
            itemLayout.addView(completeButton);
        } else {
            itemLayout.addView(createButtonLayout(itemLayout, textView));
        }
        return itemLayout;
    }

    private LinearLayout createButtonLayout(LinearLayout itemLayout, TextView textView) {
        LinearLayout buttonsLayout = new LinearLayout(getContext());
        buttonsLayout.setOrientation(LinearLayout.HORIZONTAL);
        buttonsLayout.setGravity(Gravity.CENTER);

        for (int i = 0; i < 4; i++) {
            Button numberButton = createNumberButton(i, buttonsLayout, itemLayout, textView);
            buttonsLayout.addView(numberButton);
        }
        return buttonsLayout;
    }

    private Button createNumberButton(int index, LinearLayout buttonsLayout, LinearLayout itemLayout, TextView textView) {
        Button numberButton = new Button(getContext());
        numberButton.setText(String.valueOf(index + 1));

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(85, 85);
        params.setMargins(8, 0, 8, 0); // (left, top, right, bottom)

        numberButton.setLayoutParams(params);
        numberButton.setTextSize(12);
        numberButton.setBackgroundResource(R.drawable.button_background);
        numberButton.setTextColor(Color.DKGRAY);
        numberButton.setEnabled(index == 0);

        numberButton.setOnClickListener(v -> {
            numberButton.setBackgroundColor(Color.rgb(51, 176, 0)); // Verde #229A00
            numberButton.setTextColor(Color.WHITE);
            numberButton.setEnabled(false);

            if (index == 3) {
                buttonsLayout.setVisibility(View.GONE);
                itemLayout.setBackgroundColor(Color.rgb(51, 176, 0));
                textView.setTextColor(Color.WHITE);
            } else {
                buttonsLayout.getChildAt(index + 1).setEnabled(true);
            }
        });

        return numberButton;
    }


    private Button createCompleteButton(LinearLayout itemLayout, TextView textView) {
        Button completeButton = new Button(getContext());
        completeButton.setText("✔");
        completeButton.setTextSize(17);
        completeButton.setBackgroundResource(R.drawable.button_background);
        completeButton.setTextColor(Color.DKGRAY);

        completeButton.setOnClickListener(v -> {
            itemLayout.setBackgroundColor(Color.rgb(51, 176, 0));
            textView.setTextColor(Color.WHITE);
            itemLayout.removeView(completeButton);
        });
        return completeButton;
    }

    private List<String> getWorkoutList(int section) {
        List<String> workouts = new ArrayList<>();
        switch (section) {
            case 0:
                workouts.add("PECK DECK \n(4x12)");
                workouts.add("SUPINO INCLINADO HALTER \n(4x12)");
                workouts.add("CRUCIFIXO MÁQUINA \n(4x12)");
                workouts.add("SUPINO RETO \n(4x12)");
                workouts.add("ROSCA DIRETA MÁQUINA \n(3x10+10+10)");
                workouts.add("ROSCA MARTELO HALTER \n(4x15)");
                workouts.add("ROSCA DIRETA BARRA W \n(4x10)");
                workouts.add("ROSCA CONCENTRADA \n(4x12)");
                workouts.add("ESTEIRA \n15min");
                break;
            case 1:
                workouts.add("PANTURRILHA MÁQUINA \n(3x12)");
                workouts.add("LEG PRESS 45 \n(4x10)");
                workouts.add("CADEIRA ADUTORA \n(4x15)");
                workouts.add("AGACHAMENTO HACK 45 \n(4x12)");
                workouts.add("CADEIRA EXTENSORA \n(4x12+10+8+6)");
                workouts.add("PANTURRILHA SENTADO MÁQUINA \n(4x12)");
                workouts.add("ABDOMÊN");
                break;
            case 2:
                workouts.add("PULLEY ANTERIOR \n(4x12)");
                workouts.add("REMADA BAIXA TRIÂNGULO \n(4x12)");
                workouts.add("REMADA CAVALINHO MÁQUINA \n(4x12)");
                workouts.add("PULL DOWN \n(4x12)");
                workouts.add("TRÍCEPS MÁQUINA \n(4x12)");
                workouts.add("TRÍCEPS TESTA BARRA W \n(4x12)");
                workouts.add("TRÍCEPS FRANCÊS UNI. \n(4x12)");
                workouts.add("TRÍCEPS CORDA\n(4x12)");
                workouts.add("ESTEIRA \n15min");
                break;
            case 3:
                workouts.add("ELEVAÇÃO PÉLVICA \n(4x12)");
                workouts.add("CADEIRA ABDUTORA \n(4x15)");
                workouts.add("AGACHAMENTO SMITH\n \n(4x12)");
                workouts.add("CADEIRA FLEXORA \n(4x12+10+8+6)");
                workouts.add("AGACHAMENTO SUMÔ\n \n(4x10)");
                workouts.add("PANTURRILHA MÁQUINA \n(4x12)");
                workouts.add("ABDOMÊN");
                break;
            case 4:
                workouts.add("ELEVAÇÃO LATERAL MÁQUINA \n(4x12+10+8+6)");
                workouts.add("DESENVOLVIMENTO HALTER \n(4x12)");
                workouts.add("ELEVAÇÃO LATERAL NO CROSS \n(4x12)");
                workouts.add("CRUCIFIXO INVERTIDO \n(4x15)");
                workouts.add("ECOLHIMENTO DE OMBROS \n(4x12)");
                workouts.add("ARNOLD PRESS UNI. \n(4x12)");
                workouts.add("ROSCA NO CROSS INVERTIDA (I, II)\n(3x15)");
                workouts.add("ANTEBRAÇO NA BARRA \n(3x15)");
                workouts.add("ESTEIRA \n15min");
                break;
        }
        return workouts;
    }
}