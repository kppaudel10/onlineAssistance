package com.online_dtie_tracker.excelgenerate.expenses;

import com.online_dtie_tracker.Dto.ExpensesDto;
import com.online_dtie_tracker.Dto.IncomeDto;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ExpensesDataExcelExport extends AbstractXlsxView {
    @Override
    protected void buildExcelDocument(Map<String, Object> model,
                                      Workbook workbook, HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
        response.addHeader("Content-Disposition","attachment;fileName=expensesData.xlsx");

        @SuppressWarnings("unchecked")
        List<ExpensesDto> list = (List<ExpensesDto>) model.get("list");

//         create one sheet
        Sheet sheet = workbook.createSheet("IncomeData");

        // create row0 as a header
        Row row0 = sheet.createRow(0);
        row0.createCell(0).setCellValue("ID");
        row0.createCell(1).setCellValue("Expenses Title");
        row0.createCell(2).setCellValue("Expenses Amount");
        row0.createCell(3).setCellValue("Paid Date");

        // create row1 onwards from List<T>
        int rowNum = 1;
        for(ExpensesDto spec : list) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(spec.getId());
            row.createCell(1).setCellValue(spec.getExpensesSource());
            row.createCell(2).setCellValue(spec.getExpensesAmount());
            row.createCell(3).setCellValue(spec.getPaidDate());
        }

    }
}
