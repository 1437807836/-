/**
 * 
 */
package com.yxbx.util;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;





/**
 * @author Hongten
 * @created 2014-5-20
 */
public class ExcelUtil {
	
	public void writeExcel( ArrayList<List<String>>  list, String path) throws Exception {
		if (list == null) {
			return;
		} else if (path == null || Common.EMPTY.equals(path)) {
			return;
		} else {
			String postfix = Util.getPostfix(path);
			if (!Common.EMPTY.equals(postfix)) {
				if (Common.OFFICE_EXCEL_2003_POSTFIX.equals(postfix)) {
					writeXls(list, path);
				} else if (Common.OFFICE_EXCEL_2010_POSTFIX.equals(postfix)) {
				//	writeXlsx(list, path);
				}
			}else{
				System.out.println(path + Common.NOT_EXCEL_FILE);
			}
		}
	}
	
	/**
	 * read the Excel file
	 * @param path the path of the Excel file
	 * @return
	 * @throws IOException
	 */
	public ArrayList<Map<String,Object>> readExcel(String path) throws IOException {
		if (path == null || Common.EMPTY.equals(path)) {
			return null;
		} else {
			String postfix = Util.getPostfix(path);
			if (!Common.EMPTY.equals(postfix)) {
//				if (Common.OFFICE_EXCEL_2003_POSTFIX.equals(postfix)) {
//					return readXls(path);
//				} else
					
					if (Common.OFFICE_EXCEL_2010_POSTFIX.equals(postfix)) {
					return readXlsx(path);
				}
			} else {
				System.out.println(path + Common.NOT_EXCEL_FILE);
			}
		}
		return null;
	}

	/**
	 * Read the Excel 2010
	 * @param path the path of the excel file
	 * @return
	 * @throws IOException
	 */
	public ArrayList<Map<String,Object>>  readXlsx(String path) throws IOException {
		System.out.println(Common.PROCESSING + path);
		InputStream is = new FileInputStream(path);
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
	
		ArrayList<Map<String,Object>> list1=new ArrayList<Map<String,Object>>();
		ArrayList<String> list5=new ArrayList<String>();
		// Read the Sheet
		for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
			XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
			if (xssfSheet == null) {
				continue;
			}
			
			// Read the Row
	       	XSSFRow hssfRow1 = xssfSheet.getRow(0);//取标题
	       	if(hssfRow1==null){
				continue;
			}
	        Map<String,Object> titleMap=new HashMap<String, Object>();
	        if(list1.isEmpty()){
	        	  for(int j=0;j<hssfRow1.getLastCellNum();j++){
		    	    String 	  strTitle=	parseExcel(hssfRow1.getCell(j));
		    	
		    	    titleMap.put(strTitle.equals("")?j+"未知":strTitle, strTitle);
		         	
		         	list5.add(strTitle.equals("")?j+"未知":strTitle);
		         	
			    	}
	        	  list1.add(titleMap);
	        }
		   
			// list2.add(list1);
			// Read the Row
			for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
				XSSFRow xssfRow = xssfSheet.getRow(rowNum);
				 Map<String,Object> bodyMap=new HashMap<String, Object>();
				
				if (xssfRow != null) {
					for(int y=0;y<hssfRow1.getLastCellNum();y++){
						if(xssfRow.getCell(y)==null){
							break;
						}else{	
							
							String  str=	getStringCellValue(xssfRow.getCell(y));
							if(str==null||str.equals("")){
								bodyMap.put(list5.get(y), "");
							}else{
								bodyMap.put(list5.get(y),str);
							}
							
							//xssfRow.getCell(y).setCellType(Cell.CELL_TYPE_STRING);
						//	String  str=getValue(xssfRow.getCell(y));
							
						}
						
					}
					list1.add(bodyMap);
				}
			}
		}
	
		return list1;
	}

	/**
	 * Read the Excel 2003-2007
	 * @param path the path of the Excel
	 * @return
	 * @throws IOException
	 */
	public ArrayList<List<String>> readXls(String path) throws IOException {
		System.out.println(Common.PROCESSING + path);
		InputStream is = new FileInputStream(path);
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
		ArrayList<String> list1=new ArrayList<String>();
		ArrayList<List<String>> list2=new ArrayList<List<String>>();
		// Read the Sheet
		for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
			HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
			if (hssfSheet == null) {
				continue;
			}
			// Read the Row
			HSSFRow hssfRow1 = hssfSheet.getRow(0);//取标题
			if(hssfRow1==null){
				continue;
			}
			for(int j=0;j<hssfRow1.getLastCellNum();j++){
				list1.add(getValue(hssfRow1.getCell(j)));
			}
			list2.add(list1);
			
			for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
				HSSFRow hssfRow = hssfSheet.getRow(rowNum);
				ArrayList<String> list3=new ArrayList<String>();
				if (hssfRow != null) {
					for(int y=0;y<hssfRow1.getLastCellNum();y++){
						
						list3.add(getValue(hssfRow.getCell(y)));
					}
					list2.add(list3);
				}
			}
		}
		return list2;
	}

	@SuppressWarnings("static-access")
	private String getValue(XSSFCell xssfRow) {
		if (xssfRow.getCellType() == xssfRow.CELL_TYPE_BOOLEAN) {
			return String.valueOf(xssfRow.getBooleanCellValue());
		} else if (xssfRow.getCellType() == xssfRow.CELL_TYPE_NUMERIC) {
			return String.valueOf(xssfRow.getNumericCellValue());
		} else {
			return String.valueOf(xssfRow.getStringCellValue());
		}
	}

	@SuppressWarnings("static-access")
	private String getValue(HSSFCell hssfCell) {
		if(hssfCell==null){
			return null;
		}
		if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(hssfCell.getBooleanCellValue());
		} else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
			return String.valueOf(hssfCell.getNumericCellValue());
		} else {
			return String.valueOf(hssfCell.getStringCellValue());
		}
	}
	
	public void writeXls(ArrayList<List<String>> list, String path) throws Exception {
		if (list == null) {
			return;
		}
		int countColumnNum = list.size();
		HSSFWorkbook book = new HSSFWorkbook();
		HSSFSheet sheet = book.createSheet("studentSheet");
		// option at first row.
		HSSFRow firstRow = sheet.createRow(0);
		HSSFCell[] firstCells = new HSSFCell[countColumnNum];
		List<String> options = list.get(0);
		for (int j = 0; j < options.size(); j++) {
			firstCells[j] = firstRow.createCell(j);
			firstCells[j].setCellValue(new HSSFRichTextString(options.get(j)));
		}
		//
		for (int i = 1; i < countColumnNum; i++) {
			HSSFRow row = sheet.createRow(i);
			List<String> list1 = list.get(i);
			for (int column = 0; column < options.size(); column++) {
			//	for(int y=0;y<list1.size();y++){
					HSSFCell no = row.createCell(column);
					no.setCellValue(list1.get(column));
			//	}
				
//				HSSFCell name = row.createCell(1);
//				HSSFCell age = row.createCell(2);
//				HSSFCell score = row.createCell(3);
//				no.setCellValue(student.getNo());
//				name.setCellValue(student.getName());
//				age.setCellValue(student.getAge());
//				score.setCellValue(student.getScore());
			}
		}
		File file = new File(path);
		OutputStream os = new FileOutputStream(file);
		System.out.println(Common.WRITE_DATA + path);
		book.write(os);
		os.close();
	}
	
//	public void writeXlsx(List<Student> list, String path) throws Exception {
//		if (list == null) {
//			return;
//		}
//		//XSSFWorkbook
//		int countColumnNum = list.size();
//		XSSFWorkbook book = new XSSFWorkbook();
//		XSSFSheet sheet = book.createSheet("studentSheet");
//		// option at first row.
//		XSSFRow firstRow = sheet.createRow(0);
//		XSSFCell[] firstCells = new XSSFCell[countColumnNum];
//		String[] options = { "no", "name", "age", "score" };
//		for (int j = 0; j < options.length; j++) {
//			firstCells[j] = firstRow.createCell(j);
//			firstCells[j].setCellValue(new XSSFRichTextString(options[j]));
//		}
//		//
//		for (int i = 0; i < countColumnNum; i++) {
//			XSSFRow row = sheet.createRow(i + 1);
//			Student student = list.get(i);
//			for (int column = 0; column < options.length; column++) {
//				XSSFCell no = row.createCell(0);
//				XSSFCell name = row.createCell(1);
//				XSSFCell age = row.createCell(2);
//				XSSFCell score = row.createCell(3);
//				no.setCellValue(student.getNo());
//				name.setCellValue(student.getName());
//				age.setCellValue(student.getAge());
//				score.setCellValue(student.getScore());
//			}
//		}
//		File file = new File(path);
//		OutputStream os = new FileOutputStream(file);
//		System.out.println(Common.WRITE_DATA + path);
//		book.write(os);
//		os.close();
//	}

	public  List<String[]> rosolveFile(String path, String suffix,  
            int startRow) throws IOException, FileNotFoundException {  
        Workbook xssfWorkbook = null;  
        String read_excel2003_2007_path = Common.STUDENT_INFO_XLS_PATH;
		InputStream is = new FileInputStream(path);
      
        if ("xls".equals(suffix)) {  
            xssfWorkbook = new HSSFWorkbook(is);  
        } else if ("xlsx".equals(suffix)) {  
            xssfWorkbook = new XSSFWorkbook(is);  
        }  
        Sheet xssfSheet = xssfWorkbook.getSheetAt(0);  
        if (xssfSheet == null) {  
            return null;  
        }  
        ArrayList<String[]> list = new ArrayList<String[]>();  
        int lastRowNum = xssfSheet.getLastRowNum();  
        for (int rowNum = startRow; rowNum <= lastRowNum; rowNum++) {  
            if (xssfSheet.getRow(rowNum) != null) {  
                Row xssfRow = xssfSheet.getRow(rowNum);  
                short firstCellNum = xssfRow.getFirstCellNum();  
                short lastCellNum = xssfRow.getLastCellNum();  
                if (firstCellNum != lastCellNum) {  
                    String[] values = new String[lastCellNum];  
                    for (int cellNum = firstCellNum; cellNum < lastCellNum; cellNum++) {  
                        Cell xssfCell = xssfRow.getCell(cellNum);  
                        if (xssfCell == null) {  
                            values[cellNum] = "";  
                        } else {  
                            values[cellNum] = parseExcel(xssfCell);  
                        }  
                    }  
                    list.add(values);  
                }  
            }  
        }  
        return list;  
    }  
	private String parseExcel(Cell cell) {  
        String result = new String();  
        switch (cell.getCellType()) {  
        case HSSFCell.CELL_TYPE_NUMERIC:// 数字类型  
            if (HSSFDateUtil.isCellDateFormatted(cell)) {// 处理日期格式、时间格式  
                SimpleDateFormat sdf = null;  
                if (cell.getCellStyle().getDataFormat() == HSSFDataFormat  
                        .getBuiltinFormat("h:mm")) {  
                    sdf = new SimpleDateFormat("HH:mm");  
                } else {// 日期  
                    sdf = new SimpleDateFormat("yyyy-MM-dd");  
                }  
                Date date = cell.getDateCellValue();  
                result = sdf.format(date);  
            } else if (cell.getCellStyle().getDataFormat() == 58) {  
                // 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)  
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
                double value = cell.getNumericCellValue();  
                Date date = org.apache.poi.ss.usermodel.DateUtil  
                        .getJavaDate(value);  
                result = sdf.format(date);  
            }else {  
                double value = cell.getNumericCellValue();  
                System.out.println(value+"--------haha ");
                CellStyle style = cell.getCellStyle();  
                DecimalFormat format = new DecimalFormat("0.00");  
                String temp = style.getDataFormatString();  
                // 单元格设置成常规  
                if (temp.equals("General")) {  
                    format.applyPattern("#");  
                }  
                result = format.format(value);  
                System.out.println(result+"--------haha1 ");
            }  
            break;  
        case HSSFCell.CELL_TYPE_STRING:// String类型  
            result = cell.getRichStringCellValue().toString().trim();  
          
           
            if(result!=null&&result.contains("\n")){
            	String str[]=result.split("\n");
            	String str1=new String();
            	for(int i=0;i<str.length;i++){
            		if(i==0){
            			str1=str[i];
            			continue;
            		}
            		str1=str1+"/"+str[i];
            	}
            	result=str1;
            }
            
            break;  
        case HSSFCell.CELL_TYPE_BLANK:  
            result = "";  
        default:  
            result = "";  
            break;  
        }  
        return result;  
    }  
	private static String getStringCellValue(Cell cell) {// 获取单元格数据内容为字符串类型的数据
        String strCell = "";
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
        case Cell.CELL_TYPE_FORMULA:
            // cell.getCellFormula();
            try {
            /*
             * 此处判断使用公式生成的字符串有问题，因为HSSFDateUtil.isCellDateFormatted(cell)判断过程中cell
             * .getNumericCellValue();方法会抛出java.lang.NumberFormatException异常
             */
             if (HSSFDateUtil.isCellDateFormatted(cell)) {
             	Date date = cell.getDateCellValue();
             	strCell = (date.getYear() + 1900) + "-" + (date.getMonth() + 1) +"-" + date.getDate();
             	break;
             } else {
             	strCell = String.valueOf(cell.getNumericCellValue());
            }
             } catch (IllegalStateException e) {
             	strCell = String.valueOf(cell.getRichStringCellValue());
             }
            break;
        case Cell.CELL_TYPE_STRING:
            strCell = cell.getStringCellValue();
            break;
        case Cell.CELL_TYPE_NUMERIC:
            if (HSSFDateUtil.isCellDateFormatted(cell)) {
            	  SimpleDateFormat sdf = null;  
                  if (cell.getCellStyle().getDataFormat() == HSSFDataFormat  
                          .getBuiltinFormat("h:mm")) {  
                      sdf = new SimpleDateFormat("HH:mm");  
                  } else {// 日期  
                      sdf = new SimpleDateFormat("yyyy-MM-dd");  
                  }  
                  Date date = cell.getDateCellValue();  
                  strCell = sdf.format(date);  
                break;
            } else {
            	 double value = cell.getNumericCellValue();                
                 CellStyle style = cell.getCellStyle();  
                 DecimalFormat format = new DecimalFormat("0.00");  
                 String temp = style.getDataFormatString();  
                 // 单元格设置成常规  
                 if (temp.equals("General")) {  
                     format.applyPattern("#");  
                 }  
                 strCell = format.format(value);  
               // strCell = String.valueOf(cell.getNumericCellValue());
                break;
            }
        case Cell.CELL_TYPE_BOOLEAN:
            strCell = String.valueOf(cell.getBooleanCellValue());
            break;
        case Cell.CELL_TYPE_BLANK:
            strCell = "";
            break;
        default:
            strCell = "";
            break;
        }
        return strCell;
    }

	
	
}
