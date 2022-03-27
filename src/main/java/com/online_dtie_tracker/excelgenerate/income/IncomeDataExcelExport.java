package com.online_dtie_tracker.excelgenerate.income;

import com.online_dtie_tracker.Dto.IncomeDto;
import com.online_dtie_tracker.model.Income;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class IncomeDataExcelExport extends AbstractXlsxView {
    @Override
    protected void buildExcelDocument(Map<String, Object> model,
                                      Workbook workbook, HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
        response.addHeader("Content-Disposition","attachment;fileName=incomeData.xlsx");

        @SuppressWarnings("unchecked")
        List<IncomeDto> list = (List<IncomeDto>) model.get("list");

//         create one sheet
        Sheet sheet = workbook.createSheet("IncomeData");

        // create row0 as a header
        Row row0 = sheet.createRow(0);
        row0.createCell(0).setCellValue("ID");
        row0.createCell(1).setCellValue("Source");
        row0.createCell(2).setCellValue("Fixed Amount");
        row0.createCell(3).setCellValue("Current Amount");
        row0.createCell(4).setCellValue("Income Date");

        // create row1 onwards from List<T>
        int rowNum = 1;
        for(IncomeDto spec : list) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(spec.getId());
            row.createCell(1).setCellValue(spec.getSource());
            row.createCell(2).setCellValue(spec.getFixedAmount());
            row.createCell(3).setCellValue(spec.getAmount());
            row.createCell(4).setCellValue(spec.getIncomeDate());
        }

    }
}
