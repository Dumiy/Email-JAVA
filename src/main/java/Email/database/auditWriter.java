package Email.database;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.testng.Assert.assertTrue;

public abstract class auditWriter {

    public String convertToCSV(String[] data) {
        return Stream.of(data)
                .map(this::escapeSpecialCharacters)
                .collect(Collectors.joining(","));
    }

    public String escapeSpecialCharacters(String data) {
        String escapedData = data.replaceAll("\\R", " ");
        if (data.contains(",") || data.contains("\"") || data.contains("'")) {
            data = data.replace("\"", "\"\"");
            escapedData = "\"" + data + "\"";
        }
        return escapedData;
    }
    public void creatingAuditOutput(int Id, Date date, String action, Object created) throws IOException {
        List<String[]> dataLines = new ArrayList<>();
        StringBuilder temporary = new StringBuilder();
        temporary.append(Id);
        temporary.append(" ");
        temporary.append(date);
        temporary.append(" ");
        temporary.append(action);
        temporary.append(" ");
        temporary.append(created);
        temporary.append(" ");
        dataLines.add(new String[]
                {temporary.toString()});
        dataLines.add(new String[]
                {temporary.toString()});
        File csvOutputFile = new File("audit.csv");
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            pw.println(temporary);
        }
        assertTrue(csvOutputFile.exists());
    }

    }

