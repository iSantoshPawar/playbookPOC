package com.playbook.poc.utility;

import com.playbook.poc.dao.PlaybookDao;
import com.playbook.poc.entity.Playbook;
import com.playbook.poc.entity.PlaybookTask;
import com.playbook.poc.service.PlaybookData;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class ExcelHelperPOI {

    @Autowired
    PlaybookDao playbookDao;

    public void getPlaybookConfigurationFromTable(int id) {
        try {
            List<String> headerList = new LinkedList<>();
            //TODO add the all the fields dynamically
            List<String> playbookHeader = createExcelHeaderForPlaybook(headerList);
            Map<Integer, Map<String, PlaybookData>> playbookTaskData = fetchResultFromDynamicPlaybookQuery(id);
            //TODO above statement loads data from Dao, if POJO is available we can create a map and load in above map of maps.
            createPlaybookExcelForDownload(playbookHeader, playbookTaskData);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private List<String> createExcelHeaderForPlaybook(List<String> headerList) {
        List<String> testHeaderList = new LinkedList<>();
        //TODO add the header from parameter, following added for POC
        testHeaderList.add("PlaybookID");
        testHeaderList.add("TaskID");
        testHeaderList.add("TaskName");
        testHeaderList.add("TaskType");
        testHeaderList.add("IsRepeatable");
        testHeaderList.add("StartDate");
        testHeaderList.add("ModifiedOn");
        testHeaderList.add("RulesetName");
        return testHeaderList;
    }

    private Map<Integer, Map<String, PlaybookData>> fetchResultFromDynamicPlaybookQuery(int id) {
        Map<Integer, Map<String, PlaybookData>> playbooktaskDataMaster;
        playbooktaskDataMaster = new LinkedHashMap<>();
        Playbook playbook = playbookDao.getOne(id);
        List<PlaybookTask> playbookTaskList = playbook.getPlaybookTask();
        int i = 1;
        for (PlaybookTask playbookTask : playbookTaskList) {
            Map<String, PlaybookData> playbooktaskData = new LinkedHashMap<>();
            playbooktaskData.put("PlaybookID", new PlaybookData(String.valueOf(playbook.getPlaybookId()), PlaybookDataType.NUMERIC, null));
            playbooktaskData.put("TaskID", new PlaybookData(String.valueOf(playbookTask.getTaskID()), PlaybookDataType.NUMERIC, null));
            playbooktaskData.put("TaskName", new PlaybookData(playbookTask.getTask(), PlaybookDataType.STRING, null));
            playbooktaskData.put("TaskType", new PlaybookData(playbookTask.getTaskType(), PlaybookDataType.STRING, null));
            playbooktaskData.put("IsRepeatable", new PlaybookData(playbookTask.getIsRepeatable(), PlaybookDataType.BOOLEAN, null));
            playbooktaskData.put("StartDate", new PlaybookData(String.valueOf(playbookTask.getStartDate()), PlaybookDataType.DATE, "mm/dd/yyyy hh:mm:ss"));
            playbooktaskData.put("ModifiedOn", new PlaybookData(String.valueOf(playbookTask.getModifiedDate()), PlaybookDataType.DATE, "dd-mm-yyyy"));
            playbooktaskData.put("RulesetName", new PlaybookData(playbookTask.getRuleset().getRulesetTagname(), PlaybookDataType.STRING, null));
            playbooktaskDataMaster.put(i, playbooktaskData);
            i++;
        }

        return playbooktaskDataMaster;
    }

    public void createPlaybookExcelForDownload(List<String> headerList, Map<Integer, Map<String, PlaybookData>> tableData) throws IOException {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("PlaybookTask");
            createPlaybookHeader(workbook, sheet, headerList);
            loadDataInExcel(sheet, workbook, tableData);
            File currDir = new File(".");
            String path = currDir.getAbsolutePath();
            String fileLocation = path.substring(0, path.length() - 1) + "Playbook.xlsx";
            FileOutputStream outputStream = new FileOutputStream(fileLocation);
            workbook.write(outputStream);
        }
    }

    private void loadDataInExcel(Sheet sheet, Workbook workbook, Map<Integer, Map<String, PlaybookData>> playbookTaskDataMaster) {
        if (playbookTaskDataMaster != null) {
            CellStyle style;
            for (Map.Entry<Integer, Map<String, PlaybookData>> excelColumnData : playbookTaskDataMaster.entrySet()) {
                Row row = sheet.createRow(excelColumnData.getKey());
                Map<String, PlaybookData> playbookTaskDataMap = excelColumnData.getValue();
                int i = 0;
                for (Map.Entry<String, PlaybookData> playbookTaskData : playbookTaskDataMap.entrySet()) {
                    PlaybookData playbookTaskDataValue = playbookTaskData.getValue();
                    style = workbook.createCellStyle();
                    style.setWrapText(true);
                    switch (playbookTaskDataValue.getPlaybookType()) {
                        case STRING:
                        case NUMERIC:
                        case FORMULA:
                        case BOOLEAN:
                            break;
                        case DATE:
                            CreationHelper creationHelper = workbook.getCreationHelper();
                            style.setDataFormat(creationHelper.createDataFormat().getFormat(
                                    playbookTaskDataValue.getPlaybookDateFormat()));
                            break;
                    }
                    Cell cell = row.createCell(i);
                    cell.setCellValue(playbookTaskDataValue.getPlaybookValue());
                    cell.setCellStyle(style);
                    i++;
                }
            }
        }
    }

    private void createPlaybookHeader(Workbook workbook, Sheet sheet, List<String> headerList) {
        Row header = sheet.createRow(0);
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        XSSFFont font = ((XSSFWorkbook) workbook).createFont();
        font.setBold(true);
        headerStyle.setFont(font);
        Cell headerCell;
        for (int i = 0; i < headerList.size(); i++) {
            headerCell = header.createCell(i);
            headerCell.setCellValue(headerList.get(i));
            headerCell.setCellStyle(headerStyle);
        }
    }

}
