package mst.todoapp.automation

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

import internal.GlobalVariable

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.testobject.TestObjectXpath
import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException
//import mst.todoapp.automation


import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import org.openqa.selenium.support.ui.Select

import org.openqa.selenium.interactions.Action
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.remote.server.handler.ClickElement
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import org.openqa.selenium.Keys as Keys

import java.sql.Timestamp
import java.util.Date
import java.util.Calendar
import java.text.SimpleDateFormat as SimpleDateFormat

import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileDriver
import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.nativekey.AndroidKey
import io.appium.java_client.android.nativekey.KeyEvent


public class HandleElement {

	CalendarConverter calendarConverter = new CalendarConverter()

	public void handleCalendar(String date, TestObject btnCalendar, TestObject btnNextMonth, TestObject btnPreviousMonth, TestObject btnDoneCalendarMobile) {
		String oldFormatterDate = "yyyy-MM-dd";

		Calendar calendar = calendarConverter.formatDateToCalendar(oldFormatterDate, date);

		int getDayOfWeek = calendarConverter.getDayOfWeek(calendar);
		int getWeek = calendarConverter.getWeekOfMonth(calendar);
		int getMonth = calendarConverter.getMonth(calendar);
		int getYear = calendarConverter.getYear(calendar);

		int monthNow = Calendar.getInstance().get(Calendar.MONTH)
		int differenceMonth = ((Calendar.getInstance().get(Calendar.MONTH) + 1) - getMonth)

		KeywordUtil.logInfo("getDayOfWeek = " + getDayOfWeek);
		KeywordUtil.logInfo("getWeek = " + getWeek);
		KeywordUtil.logInfo("getMonth = " + getMonth);
		KeywordUtil.logInfo("getYear = " + getYear);
		KeywordUtil.logInfo("differenceMonth = " + differenceMonth);
		KeywordUtil.logInfo("monthNow = " + monthNow);

		Thread.sleep(1000)

		Mobile.tap(btnCalendar, GlobalVariable.timeoutLoadingElementInSeccond);

		Thread.sleep(1000)

		if (differenceMonth < 0) {
			while (differenceMonth < 0) {
				Mobile.tap(btnNextMonth, GlobalVariable.timeoutLoadingElementInSeccond);
				differenceMonth++;
				Thread.sleep(500)
			}
		}

		if (differenceMonth > 0) {
			while (differenceMonth > 0) {
				Mobile.tap(btnPreviousMonth, GlobalVariable.timeoutLoadingElementInSeccond);
				differenceMonth--;
				Thread.sleep(500)
			}
		}

		Mobile.tap(findTestObject('Todo/Button/btnChooseCalendarDay', [('date') : date]), GlobalVariable.timeoutLoadingElementInSeccond);

		Mobile.tap(btnDoneCalendarMobile, GlobalVariable.timeoutLoadingElementInSeccond);
	}


	public void handleCalendarBackup(String date, TestObject btnCalendar, TestObject btnNextMonth, TestObject btnPreviousMonth, TestObject btnDoneCalendarMobile) {

		String oldFormatterDate = "yyyy-MM-dd"

		Calendar calendar = calendarConverter.formatDateToCalendar(oldFormatterDate, date)

		int getDayOfWeek = calendarConverter.getDayOfWeek(calendar)

		int getWeek = calendarConverter.getWeekOfMonth(calendar)

		int getMonth = calendarConverter.getMonth(calendar)

		int getYear = calendarConverter.getYear(calendar)

		int differenceYear = Calendar.getInstance().get(Calendar.YEAR) - getYear

		int differenceMonth = Calendar.getInstance().get(Calendar.MONTH) - getMonth

		KeywordUtil.logInfo("getDayOfWeek = " + getDayOfWeek)

		KeywordUtil.logInfo("getWeek = " + getWeek)

		KeywordUtil.logInfo("getMonth = " + getMonth)

		KeywordUtil.logInfo("getYear = " + getYear)

		KeywordUtil.logInfo("differenceYear = " + differenceYear)

		KeywordUtil.logInfo("differenceMonth = " + differenceMonth)


		Mobile.tap(findTestObject('Todo/Button/btnCalendarDate'), GlobalVariable.timeoutLoadingElementInSeccond)

		Mobile.tap(findTestObject('Todo/Button/btnPreviousMonth'), GlobalVariable.timeoutLoadingElementInSeccond)

		Mobile.tap(findTestObject('Todo/Button/btnDay1Calendar'), GlobalVariable.timeoutLoadingElementInSeccond)

		Mobile.tap(findTestObject('Todo/Button/btnDoneCalendar'), GlobalVariable.timeoutLoadingElementInSeccond)
	}
}