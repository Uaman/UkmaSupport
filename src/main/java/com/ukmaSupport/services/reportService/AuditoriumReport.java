package com.ukmaSupport.services.reportService;

import com.ukmaSupport.models.Order;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@Service("auditoriumReport")
public class AuditoriumReport extends AbstractExcelView {
    @Override
    protected void buildExcelDocument(Map<String, Object> model, org.apache.poi.hssf.usermodel.HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // get data model which is passed by the Spring container

        List<Order> listOrder = (List<Order>) model.get("orderList");
        // create a new Excel sheet
        HSSFSheet sheet = workbook.createSheet("Orders");
        sheet.setDefaultColumnWidth(20);
        sheet.setDefaultRowHeight((short) (2 * sheet.getDefaultRowHeightInPoints()));

        // create style for header cells
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.LIGHT_ORANGE.index);
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setColor(HSSFColor.WHITE.index);
        style.setFont(font);

        // create header row
        HSSFRow header = sheet.createRow(0);

        header.createCell(0).setCellValue("Id");
        header.getCell(0).setCellStyle(style);

        header.createCell(1).setCellValue("Assistant");
        header.getCell(1).setCellStyle(style);

        header.createCell(2).setCellValue("Status");
        header.getCell(2).setCellStyle(style);

        header.createCell(3).setCellValue("Title");
        header.getCell(3).setCellStyle(style);

        header.createCell(4).setCellValue("Auditorium");
        header.getCell(4).setCellStyle(style);

        header.createCell(5).setCellValue("Workplace");
        header.getCell(5).setCellStyle(style);

        header.createCell(6).setCellValue("Date");
        header.getCell(6).setCellStyle(style);


        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");

        // create data rows
        int rowCount = 1;

        for (Order aOrder : listOrder) {
            HSSFRow aRow = sheet.createRow(rowCount++);

            aRow.createCell(0).setCellValue(aOrder.getId());
            aRow.createCell(1).setCellValue(aOrder.getAssistantLastName());
            aRow.createCell(2).setCellValue(aOrder.getStatus());
            aRow.createCell(3).setCellValue(aOrder.getTitle());
            aRow.createCell(4).setCellValue(aOrder.getAuditorium());
            aRow.createCell(5).setCellValue(aOrder.getWorkplace_access_num());
            aRow.createCell(6).setCellValue(sdf.format(aOrder.getCreatedAt()));

        }
    }

}
