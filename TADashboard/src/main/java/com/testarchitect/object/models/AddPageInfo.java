package com.testarchitect.object.models;

import io.cucumber.datatable.DataTable;
import lombok.Getter;
import lombok.Setter;
import java.util.Map;

@Getter
@Setter
public class AddPageInfo {
    String pageName;
    String parentPage;
    String numberOfColumn;
    String displayAfter;
    String isPublic;
    String createdPages;

    public AddPageInfo(DataTable dataTable) {
        Map<String, String> columns = dataTable.asMaps().get(0);
        this.pageName = columns.get("Page Name");
        this.parentPage = columns.get("Parent Page");
        this.numberOfColumn = columns.get("Number Of Column");
        this.displayAfter = columns.get("Display After");
        this.isPublic = columns.get("Is Public");
        if (columns.get("Parent Page") != null)
            createdPages = String.format("%s>%s", columns.get("Parent Page"), columns.get("Page Name"));
        else
            createdPages = columns.get("Page Name");
    }

    public AddPageInfo(){
    }
}
