package mst.todoapp.automation

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable

import java.text.SimpleDateFormat as SimpleDateFormat
import java.util.Date
import java.util.Calendar

import com.kms.katalon.core.util.KeywordUtil as KeywordUtil

public class CalendarConverter {

	public String formatDateToString(String oldFormatterDate, String newFormatterDate, String date) {

		SimpleDateFormat sdfBefore = new SimpleDateFormat(oldFormatterDate);

		SimpleDateFormat sdfAfter = new SimpleDateFormat(newFormatterDate);

		Date parsedDate = null;

		String dateResult = "";

		try {
			parsedDate = sdfBefore.parse(date)

			dateResult = sdfAfter.format(parsedDate)
		}
		catch(Exception e) {

			dateResult = date

			KeywordUtil.markFailed("ParseDataType.formatDate()" + e.getMessage())
		}

		return dateResult;
	}


	public Calendar formatDateToCalendar(String oldFormatterDate, String date) {

		SimpleDateFormat sdfBefore = new SimpleDateFormat(oldFormatterDate);

		Date parsedDate = null;

		String dateResult = "";

		Calendar calendar = Calendar.getInstance()

		try {

			parsedDate = sdfBefore.parse(date)

			calendar.setTime(parsedDate)

			int dayOfMonth = calendar.get(calendar.DAY_OF_MONTH)
			int intMonth = calendar.get(calendar.MONTH)
			int intYear = calendar.get(calendar.YEAR)

			String dayOfMonthStr = String.valueOf(dayOfMonth);
			String monthStr = String.valueOf(intMonth+1);
			String yearStr = String.valueOf(intYear);
		}
		catch(Exception e) {

			KeywordUtil.markFailed("ParseDataType.formatDate()" + e.getMessage())
		}


		return calendar;
	}

	public int getDayOfMonth(Calendar calendar) {
		int dayOfMonth = calendar.get(calendar.DAY_OF_MONTH)

		return dayOfMonth;
	}

	public int getDayOfWeek(Calendar calendar) {
		int dayOfWeek = calendar.get(calendar.DAY_OF_WEEK)

		return dayOfWeek;
	}

	public int getMonth(Calendar calendar) {
		int intMonth = calendar.get(calendar.MONTH)
		int result = intMonth + 1

		return result
	}

	public int getYear(Calendar calendar) {
		int intYear = calendar.get(calendar.YEAR)
		return intYear
	}

	public int getWeekOfMonth(Calendar calendar) {
		int intWeek = calendar.get(calendar.WEEK_OF_MONTH)
		return intWeek
	}

	public String convertDateFormat(String inputDate) {
		try {

			SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd");

			Date date = inputDateFormat.parse(inputDate);

			SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd MMMM yyyy");

			return outputDateFormat.format(date);
		} catch (Exception e) {
			String msg = e.getMessage();
			KeywordUtil.logInfo("msg exception : " + msg)
			return null;
		}
	}
}
