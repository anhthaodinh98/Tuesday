package com.testarchitect.pages;

import com.logigear.control.base.imp.BaseControl;
import com.logigear.control.base.imp.Clickable;
import com.logigear.control.common.imp.Button;
import com.logigear.control.common.imp.ComboBox;
import com.logigear.control.common.imp.TextBox;
import com.testarchitect.object.data_table.CommonObject;
import com.testarchitect.object.data_table.InputDataObject;

import java.util.List;

public class DataProfileFilterFieldsPage extends GeneralPage {
    private ComboBox cbbListCondition = new ComboBox("id=listCondition");
    private Button btnAddFilterButton = new Button("//button[contains(@onclick,'addProfile')]");
    private Button btnRemoveFilterButton = new Button("//button[contains(@onclick,'removeProfile')]");
    private ComboBox cbbAndOrCondition = (ComboBox) FilterField.AND_OR.getControl();
    private ComboBox cbbField = (ComboBox) FilterField.FIELD.getControl();
    private ComboBox cbbCondition = (ComboBox) FilterField.OPERATOR.getControl();
    private TextBox txtSearchText = (TextBox) FilterField.VALUE.getControl();

    /**
     * Check if these options in 3 fields (And/Or, Field, and Operator) displayed correctly or not
     *
     * @param field
     * @param lstExpectedOptions
     * @return boolean
     */
    public boolean areFilterFieldOptionsDisplayed(String field, List<String> lstExpectedOptions) {
        boolean result = false;
        switch (FilterField.fromName(field)) {
            case AND_OR:
                result = cbbAndOrCondition.getOptions().equals(lstExpectedOptions);
                break;
            case FIELD:
                result = cbbField.getOptions().equals(lstExpectedOptions);
                break;
            case OPERATOR:
                result = cbbCondition.getOptions().equals(lstExpectedOptions);
                break;
            default:
                throw new IllegalArgumentException(String.format("The %s field name does not exist", field));
        }
        return result;
    }

    public void fillFilterData(String fieldName, String value) {
        BaseControl baseControl = FilterField.fromName(fieldName).getControl();
        fillData(baseControl, value);
    }

    public void fillFilterFields(InputDataObject dataObject) {
        for (CommonObject object : dataObject.getInputData()) {
            fillFilterData(object.getField(), object.getValue());
        }
    }
}
