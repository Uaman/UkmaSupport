package com.ukmaSupport.services;

import com.ukmaSupport.models.User;
import com.ukmaSupport.services.impl.UserServiceImpl;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@Service("reportService")
public class ReportService extends AbstractExcelView {
    @Autowired
    UserServiceImpl userService;
    @Override
    protected void buildExcelDocument(Map<String, Object> model, org.apache.poi.hssf.usermodel.HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // get data model which is passed by the Spring container

        List<User> listUsers = (List<User>) model.get("listUsers");
        // create a new Excel sheet
        HSSFSheet sheet = workbook.createSheet("Users");
        sheet.setDefaultColumnWidth(30);

        // create style for header cells
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.BLUE.index);
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setColor(HSSFColor.WHITE.index);
        style.setFont(font);

        // create header row
        HSSFRow header = sheet.createRow(0);

        header.createCell(0).setCellValue("User id");
        header.getCell(0).setCellStyle(style);

        header.createCell(1).setCellValue("Email");
        header.getCell(1).setCellStyle(style);

        header.createCell(2).setCellValue("Status Account");
        header.getCell(2).setCellStyle(style);

        header.createCell(3).setCellValue("Date of Entry");
        header.getCell(3).setCellStyle(style);


        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");

        // create data rows
        int rowCount = 1;

        for (User aUser : listUsers) {
            HSSFRow aRow = sheet.createRow(rowCount++);
            aRow.createCell(0).setCellValue(aUser.getId());
            aRow.createCell(1).setCellValue(aUser.getEmail());
            aRow.createCell(2).setCellValue(aUser.getAccountStatus());
            aRow.createCell(3).setCellValue(sdf.format(aUser.getDateOfEntry()));

        }
    }

}
