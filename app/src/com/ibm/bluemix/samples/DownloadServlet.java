package com.ibm.bluemix.samples;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.OutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Font;

@SuppressWarnings("serial")
public class DownloadServlet extends HttpServlet {
	private PostgreSQLClient db = new PostgreSQLClient();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Download Servlet");
		
		try {
			
			HttpSession session = request.getSession(true);
			String notesID = (String)session.getAttribute("NotesID");
			String roleID = (String)session.getAttribute("RoleID");
			
			//Get data(Track)
			List<EntityTrack> trackList = null;
			if (!notesID.equals("") && !roleID.equals("")) {
				trackList = db.getTrackList(notesID, roleID);
				if (trackList != null && trackList.size() > 0) {
					request.setAttribute("trackList", trackList);
				}
			}
			
			//Get data(Profile)
			List<EntityProfile> profileList = null;
			profileList = db.getProfileList();
			if (profileList != null && profileList.size() > 0) {
				request.setAttribute("profileList", profileList);
			}
			
			//Get data(TrackSum)
			List<EntityTrackSum> trackSumList = null;
			trackSumList = db.getTrackListSum(notesID, roleID);
			if (trackSumList != null && trackSumList.size() > 0) {
				request.setAttribute("TrackListSum", trackSumList);
			}
			
			//Download
			HSSFWorkbook wb = new HSSFWorkbook();
			
			HSSFCellStyle style = wb.createCellStyle();
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			
			HSSFCellStyle styleHeadColor = wb.createCellStyle();
			styleHeadColor.setFillForegroundColor(HSSFColor.YELLOW.index);
			styleHeadColor.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			styleHeadColor.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			styleHeadColor.setBorderTop(HSSFCellStyle.BORDER_THIN);
			styleHeadColor.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			styleHeadColor.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			styleHeadColor.setBorderRight(HSSFCellStyle.BORDER_THIN);
			Font font = null;
			font = wb.createFont();
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			styleHeadColor.setFont(font);
			
			HSSFCellStyle styleData = wb.createCellStyle();
			styleData.setBorderTop(HSSFCellStyle.BORDER_THIN);
			styleData.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			styleData.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			styleData.setBorderRight(HSSFCellStyle.BORDER_THIN);
			
			//Sum
			HSSFSheet sheetTrackSum = wb.createSheet("ByMonthly");
			sheetTrackSum.setColumnWidth(0, 5 * 3000);
			sheetTrackSum.setColumnWidth(1, 5 * 3000);
			int j = 0;
			for(j=2;j<=39;j++)
			{
				sheetTrackSum.setColumnWidth(j, 5 * 700);
			}
			
			//Header
			HSSFRow rowTrackSum = sheetTrackSum.createRow((int) 0);
			HSSFCell cellTrackSum = rowTrackSum.createCell((int) 0);
			cellTrackSum.setCellValue("PeM");
			cellTrackSum.setCellStyle(styleHeadColor);
			cellTrackSum = rowTrackSum.createCell((int) 1);
			cellTrackSum.setCellValue("Participant  (s)");
			cellTrackSum.setCellStyle(styleHeadColor);
			cellTrackSum = rowTrackSum.createCell((int) 2);
			cellTrackSum.setCellValue("Name");
			cellTrackSum.setCellStyle(styleHeadColor);
			
			
			int monthCount = 0;
			for (j=0;j<36;j=j+3)
			{
				monthCount++;
				cellTrackSum = rowTrackSum.createCell((int) j+3);
				cellTrackSum.setCellValue("2014-" + Integer.toString(monthCount));
				cellTrackSum.setCellStyle(styleHeadColor);
				wb.getSheet("ByMonthly").addMergedRegion(new CellRangeAddress(0, 0, j+3, j+5));
			}
				
			rowTrackSum = sheetTrackSum.createRow((int) 1);
			for (j=0;j<36;j=j+3)
			{
				cellTrackSum = rowTrackSum.createCell((int) j+3);
				cellTrackSum.setCellValue("Liquid Count");
				cellTrackSum.setCellStyle(styleHeadColor);
				cellTrackSum = rowTrackSum.createCell((int) j+4);
				cellTrackSum.setCellValue("Win $");
				cellTrackSum.setCellStyle(styleHeadColor);
				cellTrackSum = rowTrackSum.createCell((int) j+5);
				cellTrackSum.setCellValue("Win Hours");
				cellTrackSum.setCellStyle(styleHeadColor);

			}
			wb.getSheet("ByMonthly").addMergedRegion(new CellRangeAddress(0, 1, 0, 0));
			wb.getSheet("ByMonthly").addMergedRegion(new CellRangeAddress(0, 1, 1, 1));
			wb.getSheet("ByMonthly").addMergedRegion(new CellRangeAddress(0, 1, 2, 2));
			
			//Data(TrackSum)
			int PersonCount = 0;
			int LiquidCount = 0;
			
			int i = 1;
			monthCount = 0;
			String NotesIDTemp = "";
			int k = 0;
			
			int aLiquidCount[] = new int[12];
			int awinDollar[] = new int[12];
			int awinHour[] = new int[12];
			
			int aLiquidPersonCount[] = new int[12];
			int awinDollarPerson[] = new int[12];
			int awinHourPerson[] = new int[12];
			
			if (trackSumList != null) {
	        	 for (EntityTrackSum trackSum : trackSumList) {
	        		 
	        		 //different notes
	        		 if (!NotesIDTemp.equals(trackSum.getNotesID()))
	        		 {
	        			 monthCount = 0;
	        			 PersonCount++;
		        		 rowTrackSum = sheetTrackSum.createRow((int) i + 1);
		        		 cellTrackSum = rowTrackSum.createCell((int) 0);
		     			 cellTrackSum.setCellValue(trackSum.getPeMID());
		     			 cellTrackSum.setCellStyle(styleData);
		        		 cellTrackSum = rowTrackSum.createCell((int) 1);
		     			 cellTrackSum.setCellValue(trackSum.getNotesID());
		     			 cellTrackSum.setCellStyle(styleData);
		     			 cellTrackSum = rowTrackSum.createCell((int) 2);
		     			 cellTrackSum.setCellValue(trackSum.getName());
		     			 cellTrackSum.setCellStyle(styleData);
		     			 
		     			 for (k=0;k<36;k=k+3)
		     			 {
		     				monthCount++;
		     				aLiquidPersonCount[monthCount-1] = 0;
		     				awinDollarPerson[monthCount-1] = 0;
		     				awinHourPerson[monthCount-1] = 0;
		     				
		     				 if (trackSum.getRegisterDate() != null && !"".equals(trackSum.getRegisterDate()))
			     			 {
		     					 if (Integer.parseInt(trackSum.getRegisterDate().substring(0, 2)) == monthCount)
			     				{
			     				 cellTrackSum = rowTrackSum.createCell((int) k+3);
			     				 aLiquidPersonCount[monthCount-1] = aLiquidPersonCount[monthCount-1] + Integer.parseInt(trackSum.getLiquidCount());
				     			 cellTrackSum.setCellValue(aLiquidPersonCount[monthCount-1]);
				     			 cellTrackSum.setCellStyle(styleData);
				     			 cellTrackSum = rowTrackSum.createCell((int) k+4);
				     			 awinDollarPerson[monthCount-1] = awinDollarPerson[monthCount-1] + Integer.parseInt(trackSum.getWinDollar());
				     			 cellTrackSum.setCellValue(awinDollarPerson[monthCount-1]);
				     			 cellTrackSum.setCellStyle(styleData);
				     			 cellTrackSum = rowTrackSum.createCell((int) k+5);
				     			 awinHourPerson[monthCount-1] = awinHourPerson[monthCount-1] + Integer.parseInt(trackSum.getWinHours());
				     			 cellTrackSum.setCellValue(awinHourPerson[monthCount-1]);
				     			 cellTrackSum.setCellStyle(styleData);
				     			 
				     			 aLiquidCount[monthCount-1] = aLiquidCount[monthCount-1] + Integer.parseInt(trackSum.getLiquidCount());
				     			 awinDollar[monthCount-1] = awinDollar[monthCount-1] + Integer.parseInt(trackSum.getWinDollar());
				        		 awinHour[monthCount-1] = awinHour[monthCount-1] + Integer.parseInt(trackSum.getWinHours());
			     				}
			     				else
			     				{
			     					 cellTrackSum = rowTrackSum.createCell((int) k+3);
					     			 cellTrackSum.setCellStyle(styleData);
					     			 cellTrackSum = rowTrackSum.createCell((int) k+4);
					     			 cellTrackSum.setCellStyle(styleData);
					     			 cellTrackSum = rowTrackSum.createCell((int) k+5);
					     			 cellTrackSum.setCellStyle(styleData);
			     				}
			     			 }
		     				 else
		     				 {
		     					 cellTrackSum = rowTrackSum.createCell((int) k+3);
				     			 cellTrackSum.setCellStyle(styleData);
				     			 cellTrackSum = rowTrackSum.createCell((int) k+4);
				     			 cellTrackSum.setCellStyle(styleData);
				     			 cellTrackSum = rowTrackSum.createCell((int) k+5);
				     			 cellTrackSum.setCellStyle(styleData); 
		     				 }
		     			 }
		     			 

		        		 i ++; 
		        		 NotesIDTemp = trackSum.getNotesID();
	        		 }
	        		 //same notes
	        		 else
	        		 {
	        			 monthCount = 0;
	        			 for (k=0;k<36;k=k+3)
		     			 {
		     				monthCount++;
		     				if (trackSum.getRegisterDate() != null && !"".equals(trackSum.getRegisterDate()))
			     			 {
		     					if (Integer.parseInt(trackSum.getRegisterDate().substring(0, 2)) == monthCount)
			     				{
		     					 cellTrackSum = rowTrackSum.createCell((int) k+3);
			     				 aLiquidPersonCount[monthCount-1] = aLiquidPersonCount[monthCount-1] + Integer.parseInt(trackSum.getLiquidCount());
				     			 cellTrackSum.setCellValue(aLiquidPersonCount[monthCount-1]);
				     			 cellTrackSum.setCellStyle(styleData);
				     			 cellTrackSum = rowTrackSum.createCell((int) k+4);
				     			 awinDollarPerson[monthCount-1] = awinDollarPerson[monthCount-1] + Integer.parseInt(trackSum.getWinDollar());
				     			 cellTrackSum.setCellValue(awinDollarPerson[monthCount-1]);
				     			 cellTrackSum.setCellStyle(styleData);
				     			 cellTrackSum = rowTrackSum.createCell((int) k+5);
				     			 awinHourPerson[monthCount-1] = awinHourPerson[monthCount-1] + Integer.parseInt(trackSum.getWinHours());
				     			 cellTrackSum.setCellValue(awinHourPerson[monthCount-1]);
				     			 cellTrackSum.setCellStyle(styleData);
				     			 
				     			 aLiquidCount[monthCount-1] = aLiquidCount[monthCount-1] + Integer.parseInt(trackSum.getLiquidCount());
				     			 awinDollar[monthCount-1] = awinDollar[monthCount-1] + Integer.parseInt(trackSum.getWinDollar());
				        		 awinHour[monthCount-1] = awinHour[monthCount-1] + Integer.parseInt(trackSum.getWinHours());
			     				}
			     				else
			     				{
			     					 cellTrackSum = rowTrackSum.createCell((int) k+3);
					     			 cellTrackSum.setCellStyle(styleData);
					     			 cellTrackSum = rowTrackSum.createCell((int) k+4);
					     			 cellTrackSum.setCellStyle(styleData);
					     			 cellTrackSum = rowTrackSum.createCell((int) k+5);
					     			 cellTrackSum.setCellStyle(styleData);
			     				}
			     			 }
		     				 else
		     				 {
		     					 cellTrackSum = rowTrackSum.createCell((int) k+3);
				     			 cellTrackSum.setCellStyle(styleData);
				     			 cellTrackSum = rowTrackSum.createCell((int) k+4);
				     			 cellTrackSum.setCellStyle(styleData);
				     			 cellTrackSum = rowTrackSum.createCell((int) k+5);
				     			 cellTrackSum.setCellStyle(styleData); 
		     				 }
		     			 }
	        		 }
	        	 }
			}
			
			//Total
			rowTrackSum = sheetTrackSum.createRow((int) i + 1);
			cellTrackSum = rowTrackSum.createCell((int) 0);
 			cellTrackSum.setCellValue("Total");
 			cellTrackSum.setCellStyle(styleHeadColor);
 			cellTrackSum = rowTrackSum.createCell((int) 1);
 			cellTrackSum.setCellValue("");
 			cellTrackSum.setCellStyle(styleHeadColor);
 			cellTrackSum = rowTrackSum.createCell((int) 2);
 			cellTrackSum.setCellValue("");
 			cellTrackSum.setCellStyle(styleHeadColor);
 			
 			monthCount = 0;
 			for (j=0;j<36;j=j+3)
 			{
 				cellTrackSum = rowTrackSum.createCell((int) j+3);
 	 			cellTrackSum.setCellValue(aLiquidCount[monthCount]);
 	 			cellTrackSum.setCellStyle(styleData);
 	 			cellTrackSum = rowTrackSum.createCell((int) j+4);
 	 			cellTrackSum.setCellValue(awinDollar[monthCount]);
 	 			cellTrackSum.setCellStyle(styleData);
 	 			cellTrackSum = rowTrackSum.createCell((int) j+5);
 	 			cellTrackSum.setCellValue(awinHour[monthCount]);
 	 			cellTrackSum.setCellStyle(styleData);
 	 			
 	 			monthCount++;
 			}
 			
 			
 			wb.getSheet("ByMonthly").addMergedRegion(new CellRangeAddress(i + 1, i + 1, 0, 2));
			
			double PersonPer = 0;
			
			rowTrackSum = sheetTrackSum.createRow((int) i + 2);
			cellTrackSum = rowTrackSum.createCell((int) 0);
 			cellTrackSum.setCellValue("Participant Degree");
 			cellTrackSum.setCellStyle(styleHeadColor);
 			cellTrackSum = rowTrackSum.createCell((int) 1);
 			cellTrackSum.setCellValue("");
 			cellTrackSum.setCellStyle(styleHeadColor);
 			cellTrackSum = rowTrackSum.createCell((int) 2);
 			cellTrackSum.setCellValue("");
 			cellTrackSum.setCellStyle(styleHeadColor);
 			
 			monthCount = 0;
 			for (j=0;j<36;j=j+3)
 			{
 				if (PersonCount == 0)
 				{
 					PersonPer = 0;
 				}
 				else
 				{
 					PersonPer = aLiquidCount[monthCount]/PersonCount;
 				}
 				cellTrackSum = rowTrackSum.createCell((int) j+3);
 	 			cellTrackSum.setCellValue(Double.toString(PersonPer) + "%");
 	 			cellTrackSum.setCellStyle(styleData);
 	 			cellTrackSum = rowTrackSum.createCell((int) j+4);
 	 			cellTrackSum.setCellValue("");
 	 			cellTrackSum.setCellStyle(styleData);
 	 			cellTrackSum = rowTrackSum.createCell((int) j+5);
 	 			cellTrackSum.setCellValue("");
 	 			cellTrackSum.setCellStyle(styleData);
 	 			monthCount++;
 			}
 			

			wb.getSheet("ByMonthly").addMergedRegion(new CellRangeAddress(i + 2, i + 2, 0, 2));
			for (j=0;j<36;j=j+3)
			{
				wb.getSheet("ByMonthly").addMergedRegion(new CellRangeAddress(i + 2, i + 2, j+3, j+5));
			}
			
			//Header(Track)
			HSSFSheet sheet = wb.createSheet("Track");
			HSSFRow row = sheet.createRow((int) 0);
			HSSFCell cell = row.createCell((int) 0);
			cell.setCellValue("Notes ID");
			cell.setCellStyle(style);
			cell = row.createCell((int) 1);
			cell.setCellValue("LiquidID");
			cell.setCellStyle(style);
			cell = row.createCell((int) 2);
			cell.setCellValue("Name");
			cell.setCellStyle(style);
			cell = row.createCell((int) 3);
			cell.setCellValue("Technology");
			cell.setCellStyle(style);
			cell = row.createCell((int) 4);
			cell.setCellValue("Register Date");
			cell.setCellStyle(style);
			cell = row.createCell((int) 5);
			cell.setCellValue("Complete Date");
			cell.setCellStyle(style);
			cell = row.createCell((int) 6);
			cell.setCellValue("Status");
			cell.setCellStyle(style);
			cell = row.createCell((int) 7);
			cell.setCellValue("1st Win");
			cell.setCellStyle(style);
			cell = row.createCell((int) 8);
			cell.setCellValue("2nd Win");
			cell.setCellStyle(style);
			cell = row.createCell((int) 9);
			cell.setCellValue("Win Dollar");
			cell.setCellStyle(style);
			cell = row.createCell((int) 10);
			cell.setCellValue("Win Hour");
			cell.setCellStyle(style);
			cell = row.createCell((int) 11);
			cell.setCellValue("Win Point");
			cell.setCellStyle(style);
			
			//Data(Track)
			i = 0;
			if (trackList != null) {
	        	 for (EntityTrack track : trackList) {
	        		 
	        		 row = sheet.createRow((int) i + 1);
	        		 row.createCell((int) 0).setCellValue(track.getNotesID());
	        		 row.createCell((int) 1).setCellValue(track.getLiquidID());
	        		 row.createCell((int) 2).setCellValue(track.getName());
	        		 row.createCell((int) 3).setCellValue(track.getTechDisp());
	        		 row.createCell((int) 4).setCellValue(track.getRegisterDate());
	        		 row.createCell((int) 5).setCellValue(track.getCompleteDate());
	        		 row.createCell((int) 6).setCellValue(track.getStatusDisp());
	        		 row.createCell((int) 7).setCellValue(track.geIsFirstDisp());
	        		 row.createCell((int) 8).setCellValue(track.getIsSecondDisp());
	        		 row.createCell((int) 9).setCellValue(track.getWinDollar());
	        		 row.createCell((int) 10).setCellValue(track.getWinHour());
	        		 row.createCell((int) 11).setCellValue(track.getWinPoint());
	        		 
	        		 i ++;
	        	 }
			}
			
			//Profile
			HSSFSheet sheetProfile = wb.createSheet("Profile");
			//Header
			HSSFRow rowProfile = sheetProfile.createRow((int) 0);
			HSSFCell cellProfile = rowProfile.createCell((int) 0);
			cellProfile.setCellValue("Notes ID");
			cellProfile.setCellStyle(style);
			cellProfile = rowProfile.createCell((int) 1);
			cellProfile.setCellValue("Name");
			cellProfile.setCellStyle(style);
			cellProfile = rowProfile.createCell((int) 2);
			cellProfile.setCellValue("PeM");
			cellProfile.setCellStyle(style);
			cellProfile = rowProfile.createCell((int) 3);
			cellProfile.setCellValue("IL");
			cellProfile.setCellStyle(style);
			cellProfile = rowProfile.createCell((int) 4);
			cellProfile.setCellValue("Tech Domain");
			cellProfile.setCellStyle(style);
			cellProfile = rowProfile.createCell((int) 5);
			cellProfile.setCellValue("Current Utilization");
			cellProfile.setCellStyle(style);
			cellProfile = rowProfile.createCell((int) 6);
			cellProfile.setCellValue("Current Location");
			cellProfile.setCellStyle(style);
			cellProfile = rowProfile.createCell((int) 7);
			cellProfile.setCellValue("Is Working at Customer site");
			cellProfile.setCellStyle(style);
			cellProfile = rowProfile.createCell((int) 8);
			cellProfile.setCellValue("Is OnBench");
			cellProfile.setCellStyle(style);
			cellProfile = rowProfile.createCell((int) 9);
			cellProfile.setCellValue("Is Regiestered");
			cellProfile.setCellStyle(style);
			
			//Data(Profile)
			i = 0;
			if (profileList != null) {
	        	 for (EntityProfile profile : profileList) {
	        		 
	        		 rowProfile = sheetProfile.createRow((int) i + 1);
	        		 rowProfile.createCell((int) 0).setCellValue(profile.getNotesID());
	        		 rowProfile.createCell((int) 1).setCellValue(profile.getName());
	        		 rowProfile.createCell((int) 2).setCellValue(profile.getPemDisp());
	        		 rowProfile.createCell((int) 3).setCellValue(profile.getIlID());
	        		 rowProfile.createCell((int) 4).setCellValue(profile.getTechDisp());
	        		 rowProfile.createCell((int) 5).setCellValue(profile.getUtilization());
	        		 rowProfile.createCell((int) 6).setCellValue(profile.getLocation());
	        		 rowProfile.createCell((int) 7).setCellValue(profile.getOnSiteFlagDisp());
	        		 rowProfile.createCell((int) 8).setCellValue(profile.getOnBenchFlagDisp());
	        		 rowProfile.createCell((int) 9).setCellValue(profile.getRegiesteredFlagDisp());

	        		 i ++;
	        	 }
			}
			

			//Save
			response.setContentType("application/ms-excel");
			response.setHeader("Content-Disposition", "attachment;Filename=down.xls");
			OutputStream os = response.getOutputStream();
			wb.write(os);
			os.flush();
			os.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
