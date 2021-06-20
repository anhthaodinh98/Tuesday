package com.testarchitect.object.data_table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.Map;

@AllArgsConstructor
@Getter
@Setter
public class CommonObject {
    private String field;
    private String value;

    public CommonObject(Map<String, String> data) {
        this.field = data.get("Field");
        this.value = data.get("Value");
    }
}
