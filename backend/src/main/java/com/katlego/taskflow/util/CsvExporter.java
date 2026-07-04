package com.katlego.taskflow.util;

import java.io.PrintWriter;
import java.util.List;

import com.katlego.taskflow.dto.TaskResponse;

public class CsvExporter {

    public static void writeTasks(PrintWriter writer,
                                  List<TaskResponse> tasks) {

        writer.println("ID,Title,Description,Priority,Status,Due Date");

        for (TaskResponse task : tasks) {

            writer.printf("%d,%s,%s,%s,%s,%s%n",
                    task.getId(),
                    task.getTitle(),
                    task.getDescription(),
                    task.getPriority(),
                    task.getStatus(),
                    task.getDueDate());
        }

        writer.flush();
    }
}
