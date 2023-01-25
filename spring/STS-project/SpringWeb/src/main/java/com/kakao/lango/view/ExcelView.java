package com.kakao.lango.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.kakao.lango.dto.ItemDTO;

public class ExcelView extends AbstractXlsView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, 
			Workbook workbook, 
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 시트 생성하기
		Sheet sheet = createFirstSheet(workbook);
		
		// 컬럼 이름 출력하기
		createColumnLabel(sheet);
		
		// 넘겨준 데이터를 가지고 출력하기
		List<ItemDTO> list = (List<ItemDTO>) model.get("list");
		
		// 반복문을 수행하여 행 단위로 출력하기
		int rowNum = 1;
		for (ItemDTO item : list) {
			createRow(sheet, item, rowNum++, workbook);
		}
	}
	
	// 하나의 행을 만들어주는 메서드
	private void createRow(Sheet sheet, ItemDTO item, int rowNum, Workbook workbook) {
		Row row = sheet.createRow(rowNum);
		
		Cell cell = row.createCell(0);
		cell.setCellValue(item.getItemname());
		
		cell = row.createCell(1);
		cell.setCellValue(item.getDescription());
		
		cell = row.createCell(2);
		cell.setCellValue(item.getPrice() + "원");
	}

	// 컬럼 이름을 출력하는 메서드
	private void createColumnLabel(Sheet sheet) {
		// 하나의 행을 생성하기
		Row firstRow = sheet.createRow(0);
		
		Cell cell = firstRow.createCell(0);
		cell.setCellValue("상품명");
		
		cell = firstRow.createCell(1);
		cell.setCellValue("설명");
		
		cell = firstRow.createCell(2);
		cell.setCellValue("가격");
	}

	// 시트를 만들어주는 메서드
	private Sheet createFirstSheet(Workbook workbook) {
		// 새로운 시트 생성하기
		Sheet sheet = workbook.createSheet();
		
		// 시트 이름 설정하기
		workbook.setSheetName(0, "ItemSheet");
		sheet.setColumnWidth(1, 256 * 20);
		
		return sheet;
	}
}
