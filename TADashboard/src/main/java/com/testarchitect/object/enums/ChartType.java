package com.testarchitect.object.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum ChartType {
    PIE("Pie"),
    SINGLE_BAR("Single Bar"),
    STACKED_BAR("Stacked Bar"),
    GROUP_BAR("Group Bar"),
    LINE("Line");

    private String chartType;

    public static ChartType fromName(String chartTypeOption) {
        ChartType option = Arrays.stream(ChartType.values()).filter(
                v -> v.chartType.equalsIgnoreCase(chartTypeOption)).findAny().orElse(null);
        if (option == null)
            throw new RuntimeException(String.format("%s option is not exist", chartTypeOption));
        return option;
    }
}
