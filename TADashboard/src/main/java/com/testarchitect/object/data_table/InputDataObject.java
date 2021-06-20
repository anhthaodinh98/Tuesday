package com.testarchitect.object.data_table;

import io.cucumber.datatable.DataTable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Getter
public class InputDataObject {
    List<CommonObject> inputData;

    public InputDataObject(DataTable fields) {
        List<Map<String, String>> dataList = fields.asMaps(String.class, String.class);
        inputData = new ArrayList<>();
        for (Map<String, String> data : dataList) {
            inputData.add(new CommonObject(data));
        }
    }
}
