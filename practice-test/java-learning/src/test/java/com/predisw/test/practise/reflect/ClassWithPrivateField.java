package com.predisw.test.practise.reflect;


import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

public class ClassWithPrivateField {

    private List<String> settings = new ArrayList<>();

    public ClassWithPrivateField() {

        settings.add("the No.1");

    }

    public List<String> getSettings() {
        return settings;
    }

    public void setSettings(List<String> settings) {
        this.settings = settings;
    }
}
