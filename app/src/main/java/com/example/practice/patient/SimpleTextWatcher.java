package com.example.practice.patient;

import android.text.Editable;
import android.text.TextWatcher;

public class SimpleTextWatcher implements TextWatcher {

    public interface OnTextChanged {
        void onChange(String s);
    }

    private final OnTextChanged listener;

    public SimpleTextWatcher(OnTextChanged listener) {
        this.listener = listener;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        listener.onChange(s.toString());
    }

    @Override
    public void afterTextChanged(Editable s) {}
}
