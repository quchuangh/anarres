package com.chuang.anarres;

import com.chuang.anarres.model.SeoDomainProposalVO;
import com.chuang.tauceti.support.exception.BusinessException;
import com.chuang.tauceti.tools.basic.reflect.BeanKit;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.*;

public class Excel<T> {

    private Sheet sheet;
    private Map<String, Field> indexFieldMap = new HashMap<>();
    private final String[] headField;
    private final Class<T> clazz;

    public Excel(String fileName, InputStream input, Class<T> clazz) {
        this.clazz = clazz;

        initSheet(fileName, input);

        initIndexFieldMap(clazz);

        Row headRow = sheet.getRow(sheet.getFirstRowNum());

        this.headField = new String[headRow.getLastCellNum() - headRow.getFirstCellNum()];
        for(int i = headRow.getFirstCellNum(); i < headRow.getLastCellNum(); i++) {
            Cell cell = headRow.getCell(i);
            if(cell == null) {
                continue;
            }
            String colsName = cell.getStringCellValue().trim();
            Field field = indexFieldMap.get(colsName);
            if(null == field) {
                continue;
            }
            headField[i] = field.getName();
        }
    }

    private void initIndexFieldMap(Class<T> clazz) {
        this.indexFieldMap = new HashMap<>();
        Arrays.stream(clazz.getDeclaredFields())
                .filter(field -> field.getAnnotation(SeoDomainProposalVO.Index.class) != null)
                .forEach(field -> {
                    SeoDomainProposalVO.Index index = field.getAnnotation(SeoDomainProposalVO.Index.class);
                    indexFieldMap.put(index.value(), field);
                });
    }

    private void initSheet(String filename, InputStream input) {
        try {
            Workbook wb;
            if (filename.endsWith(".xlsx")) {
                wb = new XSSFWorkbook(OPCPackage.open(input));
            } else if (filename.endsWith(".xls")) {
                wb = new XSSFWorkbook(input);
            } else {
                throw new BusinessException(-1, "文件格式错误");
            }

            this.sheet = wb.getSheetAt(0);     //读取sheet 0
        } catch (Exception e) {
            throw new BusinessException(-1, "文件读取异常", e);
        }
    }



    public List<T> read() throws IllegalAccessException, InstantiationException {
        int firstRowIndex = sheet.getFirstRowNum() + 1;   //第一行是列名，所以不读
        int lastRowIndex = sheet.getLastRowNum();

        List<T> list = new ArrayList<>();


        for(int rIndex = firstRowIndex; rIndex <= lastRowIndex; rIndex++) {   //遍历行
            Row row = sheet.getRow(rIndex);
            if (row == null) {
                continue;
            }
            T t = clazz.newInstance();
            int firstCellIndex = row.getFirstCellNum();
            int lastCellIndex = row.getLastCellNum();
            for (int i = firstCellIndex; i < lastCellIndex; i++) {   //遍历列
                Cell cell = row.getCell(i);
                if (cell == null || headField.length <= i || headField[i] == null) {
                    continue;
                }
                BeanKit.setProperty(t, headField[i], strValue(cell));
            }
            list.add(t);
        }

        return list;
    }

    public String strValue(Cell cell){
        try {
            return cell.getStringCellValue();
        } catch (Exception e) {
            return cell.toString();
        }
    }

}
