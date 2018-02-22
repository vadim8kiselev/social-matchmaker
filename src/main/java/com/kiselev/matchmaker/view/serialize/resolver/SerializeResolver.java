package com.kiselev.matchmaker.view.serialize.resolver;

import com.kiselev.matchmaker.view.serialize.SerializeView;
import com.kiselev.matchmaker.view.serialize.implementation.csv.CSVSerializeView;
import com.kiselev.matchmaker.view.serialize.implementation.excel.ExcelSerializeView;
import com.kiselev.matchmaker.view.serialize.implementation.json.JSONSerializeView;
import com.kiselev.matchmaker.view.serialize.implementation.self.DefaultSerializeView;
import com.kiselev.matchmaker.view.serialize.implementation.xml.XMLSerializeView;
import org.springframework.beans.factory.annotation.Autowired;

public class SerializeResolver {

    @Autowired
    private ExcelSerializeView excelSerializeView;

    @Autowired
    private JSONSerializeView jsonSerializeView;

    @Autowired
    private XMLSerializeView xmlSerializeView;

    @Autowired
    private CSVSerializeView csvSerializeView;

    @Autowired
    private DefaultSerializeView defaultSerializeView;

    public SerializeView resolve(String type) {
        switch (type) {
            case "xls":
            case "xlsx":
                return excelSerializeView;
            case "xml":
                return xmlSerializeView;
            case "json":
                return jsonSerializeView;
            case "csv":
                return csvSerializeView;
            default:
                return defaultSerializeView;
        }
    }
}
