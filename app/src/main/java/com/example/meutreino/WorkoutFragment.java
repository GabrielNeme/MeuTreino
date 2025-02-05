package com.example.meutreino;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.LayoutInflater;
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
        assert getArguments() != null;
        int section = getArguments().getInt(ARG_SECTION);
        List<String> workouts = getWorkoutList(section);

        for (int i = 0; i < workouts.size(); i++) {
            String workout = workouts.get(i);

            LinearLayout itemLayout = new LinearLayout(getContext());
            itemLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            itemLayout.setOrientation(LinearLayout.HORIZONTAL);
            itemLayout.setGravity(Gravity.CENTER_VERTICAL);

            TextView textView = new TextView(getContext());
            textView.setText(workout);
            textView.setTextSize(22);
            textView.setLayoutParams(new LinearLayout.LayoutParams(
                    0,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    1));

            Button completeButton = new Button(getContext());
            completeButton.setText("✔");
            completeButton.setTextSize(18);
            completeButton.setOnClickListener(v -> {
                textView.setTextColor(Color.rgb(0, 150, 0));
                completeButton.setEnabled(false);
            });

            itemLayout.addView(textView);
            itemLayout.addView(completeButton);
            workoutContainer.addView(itemLayout);

            if (i < workouts.size() - 1) {
                View divider = new View(getContext());
                LinearLayout.LayoutParams dividerParams = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        2);
                dividerParams.setMargins(0, 8, 0, 8);
                divider.setLayoutParams(dividerParams);
                divider.setBackgroundColor(Color.GRAY);
                workoutContainer.addView(divider);
            }
        }
    }

    private List<String> getWorkoutList(int section) {
        List<String> workouts = new ArrayList<>();
        switch (section) {
            case 0:
                workouts.add("SUPINO INCLINADO ARTICULADO  (4x12)");
                workouts.add("SUPINO INCLINADO HALTER (4x12)");
                workouts.add("CRUCIFIXO MÁQUINA (4x12)");
                workouts.add("PECK DECK (4x12)");
                workouts.add("ROSCA DIRETA MÁQUINA (4x12)");
                workouts.add("ROSCA CONCENTRADA (4x12)");
                workouts.add("ROSCA NA BARRA H (4x12)");
                workouts.add("ROSCA 45° UNI. (4x12)");
                break;
            case 1:
                workouts.add("ESTEIRA 30min");
                workouts.add("BICICLETA 30min");
                workouts.add("ABDOMÊN TRI-SET");
                break;
            case 2:
                workouts.add("PANTURRILHAS AQUECIMENTO (3x12)");
                workouts.add("LEG PRESS 45° (4x12)");
                workouts.add("CADEIRA ADUTORA (4x12)");
                workouts.add("CADEIRA ABDUTORA (4x12)");
                workouts.add("CADEIRA EXTENSORA (4x12)");
                workouts.add("CADEIRA FLEXORA (4x12)");
                workouts.add("AGACHAMENTO HACK 45° (4x12)");
                workouts.add("PANTURRILHAS MÁQUINA (4x12)");
                workouts.add("PANTURRILHAS SENTADO MÁQUINA (4x15)");
                break;
            case 3:
                workouts.add("ENCOLHIMENTO DE OMBROS (4x12)");
                workouts.add("DESENVOLVIMENTO HALTER (4x12)");
                workouts.add("CRUCIFIXO HALTER (4x15)");
                workouts.add("ARNOLD PRESS UNI. (4x12)");
                workouts.add("ELEVAÇÃO LATERAL MÁQUINA (4x12)");
                workouts.add("ELEVAÇÃO FRONTAL SUPINADA (4x12)");
                workouts.add("ELEVAÇÃO LATERAL NO CROSS (4x12)");
                workouts.add("TRÍCEPS MÁQUINA (4x12)");
                workouts.add("TRÍCEPS NO CROSS CORDA (4x12)");
                workouts.add("TRÍCEPS COICE UNI. NO CROSS (4x12)");
                workouts.add("TRÍCEPS FRANCÊS UNI. HALTER (4x12)");
                break;
        }
        return workouts;
    }
}
