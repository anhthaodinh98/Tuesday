package com.testarchitect.object.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Getter
public enum BooleanText {
    TRUE("checked", "display", true),
    FALSE("unchecked", "not display", false);

    private final String checkOrNot;
    private final String displayOrNot;
    private final boolean value;

    public static BooleanText fromCheckOrNot(String booleanName) {
        BooleanText result = Arrays.stream(BooleanText.values())
                .filter(v -> v.checkOrNot.equals(booleanName.toLowerCase())).findAny().orElse(null);
        assert result != null : String.format("BooleanText with name: %s is not available", booleanName);
        return result;
    }

    public static BooleanText fromDisplayOrNot(String booleanName) {
        BooleanText result = Arrays.stream(BooleanText.values())
                .filter(v -> v.displayOrNot.equals(booleanName.toLowerCase())).findAny().orElse(null);
        assert result != null : String.format("BooleanText with name: %s is not available", booleanName);
        return result;
    }

    public static BooleanText fromAnyName(String booleanName) {
        BooleanText result = Arrays.stream(BooleanText.values())
                .filter(v -> {
                    List<String> allNames = Arrays.asList(v.checkOrNot, v.displayOrNot);
                    return allNames.contains(booleanName.toLowerCase());
                }).findAny().orElse(null);
        assert result != null : String.format("BooleanText with name: %s is not available", booleanName);
        return result;
    }

    public boolean getValue() {
        return this.value;
    }
}
