package com.linuxense.javadbf;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.junit.Assert;
import org.junit.Test;

public class FixtureCp1251Test {
	@Test
	public void test8b() throws Exception {
		File file = new File("src/test/resources/fixtures/cp1251.dbf");
		InputStream inputStream = null;
		try {
			inputStream = new BufferedInputStream(new FileInputStream(file));
			DBFReader reader = new DBFReader(inputStream);
			
			DBFHeader header = reader.getHeader();
			
			Assert.assertNotNull(header);
			Assert.assertEquals(2, header.fieldArray.length);
			
			Assert.assertEquals("RN", header.fieldArray[0].getName());
			Assert.assertEquals(DBFDataType.NUMERIC, header.fieldArray[0].getType());
			Assert.assertEquals(0, header.fieldArray[0].getDecimalCount());
			Assert.assertEquals(4, header.fieldArray[0].getFieldLength());		
			
			Assert.assertEquals("NAME", header.fieldArray[1].getName());
			Assert.assertEquals(DBFDataType.CHARACTER, header.fieldArray[1].getType());
			Assert.assertEquals(0, header.fieldArray[1].getDecimalCount());
			Assert.assertEquals(100, header.fieldArray[1].getFieldLength());	
			
			
			
			Assert.assertEquals(4, header.numberOfRecords);
			
			
			Object[] row = null;
			
			row = reader.nextRecord();			
			Assert.assertEquals(1, ((Number) row[0]).intValue());
			Assert.assertEquals("àìáóëàòîðíî-ïîëèêëèíè÷åñêîå", ((String)row[1]).trim());
			
			row = reader.nextRecord();			
			Assert.assertEquals(2, ((Number) row[0]).intValue());
			Assert.assertEquals("áîëüíè÷íîå", ((String)row[1]).trim());
			
			row = reader.nextRecord();			
			Assert.assertEquals(3, ((Number) row[0]).intValue());
			Assert.assertEquals("ÍÈÈ", ((String)row[1]).trim());
			
			row = reader.nextRecord();			
			Assert.assertEquals(4, ((Number) row[0]).intValue());
			Assert.assertEquals("îáðàçîâàòåëüíîå ìåäèöèíñêîå ó÷ðåæäåíèå", ((String)row[1]).trim());
			
			row = reader.nextRecord();		
			Assert.assertNull(row);
			

			
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}

	}
}


/*

Database: cp1251.dbf
Type: (30) Visual FoxPro
Memo File: false
Records: 4

Fields:
Name             Type       Length     Decimal
------------------------------------------------------------------------------
RN               N          4          0         
NAME             C          100        0         

*/