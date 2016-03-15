package com.ioraecological.ifp;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by vibhormungee on 14/03/16.
 */
public class QuizDialog extends DialogFragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dismissdialog, container);
        Button dismiss = (Button) view.findViewById(R.id.quiz_dismiss);
        dismiss.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        this.dismiss();
    }
}
