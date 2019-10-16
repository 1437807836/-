package com.yxbx.action;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;
import java.util.Map.Entry;

//http://poi.apache.org/spreadsheet/quick-guide.html

/**
 *
 * <b>功能：</b> excel操作工具类<br>
 * <b>作者：</b>孙志强<br>
 * <b>日期：</b>2017年3月11日 下午12:21:24
 *
 * @version 1.0 <br>
 */
public class ExcelUtils {
	protected Logger log = LoggerFactory.getLogger(ExcelUtils.class);
	public void testWrite() {
		ExcelUtils testExcel = new ExcelUtils();
		try {
			// 测试 导出
			LinkedHashMap<String, List<List<String>>> dataMap = new LinkedHashMap<String, List<List<String>>>();
			List<List<String>> list = new ArrayList<List<String>>();
			List<String> list1 = new ArrayList<String>();
			list1.add("zhangsan12312312123123123132");
			list1.add("zhangsan1");
			list1.add("zhangsan2");
			list1.add("zhangsan3123123123123");
			List<String> list2 = new ArrayList<String>();
			list2.add("zhangs");
			list2.add("zhangsan11");
			list2.add("zhangsan21");
			list2.add("zhangsan31");
			list.add(list1);
			list.add(list2);
			//设置文件的题目，下面的题目
			dataMap.put("题目", list);
			//将第一行设置成题目里面内容也添加传入方法中
			Workbook wb = testExcel.writeExcel(dataMap, "第一行标题");
			FileOutputStream fileOut = new FileOutputStream("D:/workbookT.xls");
			wb.write(fileOut);
			fileOut.close();

		} catch (Exception e) {
			log.error(e.getClass().getName()+":"+e.getMessage());
			e.printStackTrace();
		}
	}

	public void testRead() throws Exception {
		ExcelUtils testExcel = new ExcelUtils();
		InputStream is = new FileInputStream(new File("D:/workbookT.xls"));
		Map<String, List<String[]>> data = testExcel.readExcel(is, 1);

		for (Entry<String, List<String[]>> entry : data.entrySet()) {
			//每个key就是一个sheet的名字
			String key = entry.getKey();
			System.out.println(key);
			//每个值就是一个row的数据，一个row又是一个数组里面包含一行单元格的数据
			List<String[]> value = entry.getValue();
			//外层表示一行数据（一共多少行）
			for (int i = 0; i < value.size(); i++) {
				//里层表示每个单元格的数据，value.get(i).length表示每行数组的长度
				for (int j = 0; j < value.get(i).length; j++) {
					//遍历每行，中所有列的值
					System.out.println(value.get(i)[j]);
				}

			}
		}

		// System.out.println(data);
	}

	/**
	 *
	 * <b>功能：</b> excel导出，支持大数据模式，只能导出xlsx模式<br>
	 * <b>作者：</b>孙志强<br>
	 * <b>日期：</b> 2017年8月19日 上午1:19:22 <br>
	 *
	 * @param dataMap
	 *            map数据结构，key代表工作搏，value代表行数据
	 * @param title
	 *            标题行，内不会动态添加到第一行
	 * @return
	 * @throws IOException
	 */
	public Workbook writeExcel(LinkedHashMap<String, List<List<String>>> dataMap, String title) throws IOException {
		int rowaccess = 100;// 内存中缓存记录行数
		/* keep 100 rowsin memory,exceeding rows will be flushed to disk */
		//创建工作薄，添加了内存中缓存记录行数
		SXSSFWorkbook wb = new SXSSFWorkbook(rowaccess);
		//设置工作簿的样式，返回的是设置好的样式及字体样式的单元格
		CellStyle styleCenter = getStyle(wb, "center");
		//将数据进行循环
		for (Entry<String, List<List<String>>> map : dataMap.entrySet()) {
			//key代表题目
			String key = map.getKey();
			//根据标题得到Sheet
			Sheet sheet = wb.createSheet(key);
			// sheet第一行合并单元格，如果数据不是null
			if (map.getValue() != null && map.getValue().size() > 0) {
				//这是一个合并单元格的操作，从第列到得到第一个数据值的大小-1
				CellRangeAddress cra = new CellRangeAddress(0, 0, 0, map.getValue().get(0).size() - 1);
				//将第一行合并的单元格设置到sheet中
				sheet.addMergedRegion(cra);
			}
			// 标题行插入到第一行（得到key（标题）对应的里面的所有数据是一个List<List<String>>（里面的list代表数据库查出来的一行数据也就是lm））
			//首先将lm的第一行标题赋值进去，下角标是0代表添加到第一行
			List<List<String>> lm = map.getValue();
			List<String> titleList = new ArrayList<String>();
			titleList.add(title);
			lm.add(0, titleList);
			// 循环行数据
			//从0行开始
			for (int i = 0; i < lm.size(); i++) {
				//创建一行
				Row row = sheet.createRow(i);
				row.setHeight((short) 315);
				//得到一行数据
				List<String> listCell = lm.get(i);

				int colume = 0;
				// 循环列数据
				for (int j = 0; j < listCell.size(); j++) {
					//根据一行数据的多少创建相应的列，列从0开始
					Cell cell = row.createCell(colume);
					//设置创建单元格的样式
					cell.setCellStyle(styleCenter);
					//设置单元格的值，一行从0开始遍历并给对应的单元格赋值
					cell.setCellValue(String.valueOf(listCell.get(j))); // 其他转换成String
					//if (i == 1) {
					//每设置一个单元格后设置sheet对应列的宽度自适应，要不然里面的字就不能全部显示了
					sheet.autoSizeColumn(j);// 自动调整宽度
					//}
					//创建之后创建下一个并赋值
					colume++;
				}
				// 每当行数达到设置的值就刷新数据到硬盘,以清理内存
				if (i % rowaccess == 0) {
					((SXSSFSheet) sheet).flushRows();
				}
			}

//				sheet.setColumnWidth(5,5000);
//				sheet.setColumnWidth(6,4000);
//				sheet.setColumnWidth(7,4000);
		}
		return wb;
	}

	/**
	 *
	 * <b>功能：</b> 读取xls,xlsx<br>
	 * <b>作者：</b>孙志强<br>
	 * <b>日期：</b> 2017年8月2日 上午9:07:26 <br>
	 *
	 * @param is
	 * @param readline
	 * @return
	 */
	public Map<String, List<String[]>> readExcel(InputStream is, int readline) {
		Workbook wb = null;
		Map<String, List<String[]>> mapList = null;
		List<String[]> listArray = null;
		String[] values = null;
		try {
			// 创建workbook对象
			//根据输入流（这里面有excel表中的数据）
			wb = WorkbookFactory.create(is);
			// 获取sheet页数
			int sheets = wb.getNumberOfSheets();
			if (sheets > 0) {
				//这个是保存多个sheet,sheet里面又包含多行的数据
				mapList = new HashMap<String, List<String[]>>();
			}
			for (int i = 0; i < sheets; i++) {
				// 这个数组用来保存一个sheet中得到的数据（也是一行一行的数组，说白了就是二维数据）
				listArray = new ArrayList<String[]>();
				// 按顺序得到每个sheet
				Sheet sheet = wb.getSheetAt(i);
				// 总行数,每个sheet的总行数
				int rowNum = sheet.getLastRowNum();
				//从下角标是1开始读也就是忽略了第一行（因为是题目）
				for (int j = readline; j <= rowNum; j++) {
					// 行，读取每行的数据
					Row row = sheet.getRow(j);
					//如果isBlankRow(row)：返回true（row是空的，或者是转换之后是空的）就不往下进行了
					//其实下面的row已经经过了转换
					if (row != null && !isBlankRow(row)) {
						// 读取最后一个单元格的编号
						int cessNum = row.getLastCellNum();
						// 创建一个数组和一行数据大小一样
						values = new String[cessNum]; // 列数
						for (int x = 0; x < cessNum; x++) {
							//得到excel表格中的每个单元格的数据
							Cell cell = row.getCell(x);
							//如果单元格的数据是null就设置成空字符串
							if (cell == null) {
								values[x] = "";
							} else {
								// 判断类型
								//上面的方法isBlankRow转化之后又做了一个转换
								//并将转换后的值赋值给了这个数组values
								switch (cell.getCellType()) {
									case Cell.CELL_TYPE_STRING:
										values[x] = cell.getRichStringCellValue().getString();
										break;
									case Cell.CELL_TYPE_NUMERIC:
										if (DateUtil.isCellDateFormatted(cell)) {
											values[x] = cell.getDateCellValue().toString();
										} else {
											values[x] = new DecimalFormat("######0.##").format(cell.getNumericCellValue());
										}
										break;
									case Cell.CELL_TYPE_BOOLEAN:
										if(cell.getBooleanCellValue()){
											values[x] = "true";
										}else{
											values[x] = "false";
										}
										break;
									case Cell.CELL_TYPE_FORMULA:
										values[x] = cell.getCellFormula();
										break;
									default:
										values[x] = "";
								}
							}
						}
					}
					listArray.add(values);
				}
				//将
				mapList.put(wb.getSheetName(i), listArray);
			}
			return mapList;
		} catch (Exception e) {
			log.error(e.getClass().getName()+":"+e.getMessage());
			e.printStackTrace();
			return null;
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					log.error(e.getClass().getName()+":"+e.getMessage());
					e.printStackTrace();
				}
			}
		}
	}

	public static boolean isBlankRow(Row row) {
		//没数据返回
		if (row == null) {
			return true;
		}
		//将result设置成true
		boolean result = true;
		//遍历一行里面所有的数据（单元格）
		for (int i = row.getFirstCellNum(); i < row.getLastCellNum(); i++) {
			//如果单元格式空就转换成null
			Cell cell = row.getCell(i, Row.RETURN_BLANK_AS_NULL);
			String value = "";
			//如果单元格里面的数据不是空
			if (cell != null) {
				//根据单元格中数据的类型，将数据的格式进行了转换
				//并将value返回来利用
				switch (cell.getCellType()) {
					case Cell.CELL_TYPE_STRING:
						value = cell.getStringCellValue();
						break;
					case Cell.CELL_TYPE_NUMERIC:
						value = String.valueOf((int) cell.getNumericCellValue());
						break;
					case Cell.CELL_TYPE_BOOLEAN:
						value = String.valueOf(cell.getBooleanCellValue());
						break;
					case Cell.CELL_TYPE_FORMULA:
						value = String.valueOf(cell.getCellFormula());
						break;
					default:
						break;
				}
				//如果转换之后返回值是空字符串那么就返回false代表转换失败
				if (!value.trim().equals("")) {

					result = false;
					break;
				}
			}
		}
		return result;
	}

	/**
	 *
	 * <b>功能：</b> 设置单元格样式<br>
	 * <b>作者：</b>孙志强<br>
	 * <b>日期：</b> 2017年3月10日 下午10:37:14 <br>
	 *
	 * @param wb
	 * @param horizontal
	 *            水平位置，left 居左，right，居右，center 居中
	 * @return
	 */
	private CellStyle getStyle(Workbook wb, String horizontal) {
		//得到工作簿下面的单元格Style
		CellStyle cellStyle = wb.createCellStyle() ;

		// cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		// cellStyle.setFillForegroundColor(new
		// HSSFColor.PALE_BLUE().getIndex());
		//设置单元格样式
		cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 设置垂直居中
		if (StringUtils.isEmpty(horizontal)) {
			cellStyle.setAlignment(CellStyle.ALIGN_FILL); // 自适应
		} else {
			if (horizontal.equals("left")) {
				cellStyle.setAlignment(CellStyle.ALIGN_LEFT); // 设置水平居左
			} else if (horizontal.equals("right")) {
				cellStyle.setAlignment(CellStyle.ALIGN_RIGHT); // 设置水平居右
			} else {
				cellStyle.setAlignment(CellStyle.ALIGN_CENTER); // 居中
			}
		}
		//得到工作簿下面字体
		Font font = wb.createFont(); // 创建字体样式
		//设置字体的样式
//		font.setBoldweight(Font.BOLDWEIGHT_BOLD); // 字体加粗
		font.setFontName("微软雅黑"); // 设置字体类型
		font.setFontHeightInPoints((short) 11); // 设置字体大小
		//将字体演示设置到单元格Style中
		cellStyle.setFont(font); // 为标题样式设置字体样式
		cellStyle.setBorderBottom(CellStyle.BORDER_THIN); // 下边框
		cellStyle.setBorderLeft(CellStyle.BORDER_THIN);// 左边框
		cellStyle.setBorderTop(CellStyle.BORDER_THIN);// 上边框
		cellStyle.setBorderRight(CellStyle.BORDER_THIN);// 右边框
		//将设置好的单元格样式及里面的字体样式返回
		return cellStyle;
	}
}
