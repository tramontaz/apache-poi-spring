package net.chemodurov.tutorial.apachepoispring.poi;

import net.chemodurov.tutorial.apachepoispring.dao.HibernateBookDAOImpl;
import net.chemodurov.tutorial.apachepoispring.model.entities.Book;
import net.chemodurov.tutorial.apachepoispring.model.repository.BookRepository;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.util.List;

@Component
public class BookPoi {

    @Autowired
    private BookRepository repository;

    public void export(String path) throws Exception {
        try (HSSFWorkbook workbook = new HSSFWorkbook()) {
            HSSFSheet sheet = workbook.createSheet("Books");

            /**
             *
             */
            CreationHelper creationHelper = workbook.getCreationHelper();

            /**
             * header style
             */
            HSSFCellStyle cellHeaderStyle = workbook.createCellStyle();
            cellHeaderStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            cellHeaderStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cellHeaderStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            cellHeaderStyle.setAlignment(HorizontalAlignment.CENTER);
            cellHeaderStyle.setWrapText(true);

            /**
             *
             */
            int row_num = 0;

            /**
             * columns wide
             */
            sheet.setColumnWidth(0, 4500);
            sheet.setColumnWidth(0, 4500);
            sheet.setColumnWidth(0, 4500);
            sheet.setColumnWidth(0, 4500);

            /**
             * create header for file
             */
            Row firstRow = sheet.createRow(row_num++);
            firstRow.setHeightInPoints(60.f);

            /**
             *
             */
            Cell cell_id = firstRow.createCell(0);
            cell_id.setCellValue("Id");
            cell_id.setCellType(CellType.STRING);
            cell_id.setCellStyle(cellHeaderStyle);

            /**
             *
             */
            Cell cell_title = firstRow.createCell(1);
            cell_title.setCellValue("title");
            cell_title.setCellType(CellType.STRING);
            cell_title.setCellStyle(cellHeaderStyle);

            /**
             *
             */
            Cell cell_author = firstRow.createCell(2);
            cell_author.setCellValue("author");
            cell_author.setCellType(CellType.STRING);
            cell_author.setCellStyle(cellHeaderStyle);

            /**
             *
             */
            Cell cell_price = firstRow.createCell(3);
            cell_price.setCellValue("price");
            cell_price.setCellType(CellType.STRING);
            cell_price.setCellStyle(cellHeaderStyle);


            List<Book> books = repository.findAll();

            for (Book book : books) {
                Row row = sheet.createRow(row_num++);

                /**
                 *
                 */
                Cell cellId = row.createCell(0);
                cellId.setCellValue(book.getId());
                cellId.setCellType(CellType.STRING);

                /**
                 *
                 */
                Cell cellTitle = row.createCell(1);
                cellTitle.setCellValue(book.getTitle());
                cellTitle.setCellType(CellType.STRING);

                /**
                 *
                 */
                Cell cellAuthor = row.createCell(2);
                cellAuthor.setCellValue(book.getAuthor());
                cellAuthor.setCellType(CellType.STRING);

                /**
                 *
                 */
                Cell cellPrice = row.createCell(3);
                cellPrice.setCellValue(String.valueOf(book.getPrice()));
                cellPrice.setCellType(CellType.STRING);
            }

            try (FileOutputStream outputStream = new FileOutputStream(path)) {
                workbook.write(outputStream);
                workbook.close();
            }
        }
    }
}
