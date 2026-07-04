package com.katlego.taskflow.util;

import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import com.katlego.taskflow.dto.TaskResponse;

import jakarta.servlet.http.HttpServletResponse;

public class PdfExporter {

    private List<TaskResponse> tasks;

    public PdfExporter(List<TaskResponse> tasks) {
        this.tasks = tasks;
    }

    public void export(HttpServletResponse response) {

        try {

            Document document = new Document();

            PdfWriter.getInstance(
                    document,
                    response.getOutputStream());

            document.open();

            Font titleFont = new Font();
            titleFont.setSize(20);

            Paragraph title =
                    new Paragraph("TaskFlow Report", titleFont);

            title.setAlignment(Paragraph.ALIGN_CENTER);

            document.add(title);

            document.add(new Paragraph(" "));

            PdfPTable table = new PdfPTable(5);

            table.setWidthPercentage(100);

            table.addCell("ID");
            table.addCell("Title");
            table.addCell("Priority");
            table.addCell("Status");
            table.addCell("Due Date");

            for (TaskResponse task : tasks) {

                table.addCell(String.valueOf(task.getId()));
                table.addCell(task.getTitle());
                table.addCell(task.getPriority().toString());
                table.addCell(task.getStatus().toString());
                table.addCell(task.getDueDate().toString());

            }

            document.add(table);

            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
