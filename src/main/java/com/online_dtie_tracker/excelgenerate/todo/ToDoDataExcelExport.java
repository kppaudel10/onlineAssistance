package com.online_dtie_tracker.excelgenerate.todo;

import com.online_dtie_tracker.Dto.IncomeDto;
import com.online_dtie_tracker.Dto.ToDoDto;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class ToDoDataExcelExport extends AbstractXlsxView {
    @Override
    protected void buildExcelDocument(Map<String, Object> model,
                                      Workbook workbook, HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
        response.addHeader("Content-Disposition","attachment;fileName=todoData.xlsx");

        @SuppressWarnings("unchecked")
        List<ToDoDto> list = (List<ToDoDto>) model.get("list");

//         create one sheet
        Sheet sheet = workbook.createSheet("IncomeData");

        // create row0 as a header
        Row row0 = sheet.createRow(0);
        row0.createCell(0).setCellValue("ID");
        row0.createCell(1).setCellValue("Title");
        row0.createCell(2).setCellValue("Status");
        row0.createCell(3).setCellValue("Date");


        // create row1 onwards from List<T>
        int rowNum = 1;
        for(ToDoDto spec : list) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(spec.getId());
            row.createCell(1).setCellValue(spec.getTitle());
            row.createCell(2).setCellValue(spec.getToDoStatus().name());
            row.createCell(3).setCellValue(spec.getToDoDate());

        }

    }
}
