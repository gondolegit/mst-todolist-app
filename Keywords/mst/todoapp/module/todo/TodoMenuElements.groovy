package mst.todoapp.module.todo

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
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

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException
import com.kms.katalon.core.configuration.RunConfiguration as RunConfiguration
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile

import mst.todoapp.automation.CalendarConverter
import mst.todoapp.automation.HandleElement

import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileDriver
import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.nativekey.AndroidKey
import io.appium.java_client.android.nativekey.KeyEvent


public class TodoMenuElements {

	HandleElement handleElement = new HandleElement()
	CalendarConverter calendarConverter = new CalendarConverter()

	public void addTodo(String action, String name, String date, String type, String description, String fileName) {

		if(action.contains("Add")) {
			Mobile.tap(findTestObject('Todo/Button/btnAddTodo'), GlobalVariable.timeoutLoadingElementInSeccond)

			if(!name.isEmpty()) {

				Mobile.waitForElementPresent(findTestObject('Todo/Input/Name'), GlobalVariable.timeoutLoadingElementInSeccond, FailureHandling.STOP_ON_FAILURE)

				Mobile.setText(findTestObject('Todo/Input/Name'), name, GlobalVariable.timeoutLoadingElementInSeccond,
						FailureHandling.STOP_ON_FAILURE)
			}

			if(!date.isEmpty()) {

				handleElement.handleCalendar(date.toString(), findTestObject('Todo/Button/btnCalendarDate'),findTestObject('Todo/Button/btnNextMonth'), findTestObject('Todo/Button/btnPreviousMonth'), findTestObject('Todo/Button/btnDoneCalendar'))
			}

			if(!type.isEmpty()) {

				Mobile.tap(findTestObject('Todo/Input/selectType'), GlobalVariable.timeoutLoadingElementInSeccond)

				if(type.equals("Primary")) {
					Mobile.tap(findTestObject('Todo/Button/btnPrimary'), GlobalVariable.timeoutLoadingElementInSeccond)
				}else if(type.equals("Secondary")) {
					Mobile.tap(findTestObject('Todo/Button/btnSecondary'), GlobalVariable.timeoutLoadingElementInSeccond)
				}else if(type.equals("Other")) {
					Mobile.tap(findTestObject('Todo/Button/btnOther'), GlobalVariable.timeoutLoadingElementInSeccond)
				}
			}

			if(!description.isEmpty()) {
				Mobile.setText(findTestObject('Todo/Input/Description'), 'Description Testing 1', GlobalVariable.timeoutLoadingElementInSeccond,
						FailureHandling.STOP_ON_FAILURE)
			}

			if(!fileName.isEmpty()) {

				AndroidDriver driverAndroid = MobileDriverFactory.getDriver();

				driverAndroid.pushFile("/storage/emulated/0/Pictures/"+fileName, new File(RunConfiguration.getProjectDir() + "/Data Files/Assets/" + fileName))

				Mobile.tap(findTestObject('Todo/Button/btnChooseFile'), GlobalVariable.timeoutLoadingElementInSeccond, FailureHandling.STOP_ON_FAILURE)

				Thread.sleep(1000)

				Mobile.waitForElementPresent(findTestObject('Todo/Button/btnChangesListImage'), GlobalVariable.timeoutLoadingElementInSeccond, FailureHandling.STOP_ON_FAILURE)

				Thread.sleep(1000)

				Mobile.tap(findTestObject('Todo/Button/btnChangesListImage'), GlobalVariable.timeoutLoadingElementInSeccond, FailureHandling.STOP_ON_FAILURE)

				Thread.sleep(1000)

				Mobile.waitForElementPresent(findTestObject('Todo/Input/TestImage',[('fileName') : fileName]), GlobalVariable.timeoutLoadingElementInSeccond, FailureHandling.STOP_ON_FAILURE)

				Mobile.tap(findTestObject('Todo/Input/TestImage',[('fileName') : fileName]), GlobalVariable.timeoutLoadingElementInSeccond, FailureHandling.STOP_ON_FAILURE)
			}

			Mobile.tap(findTestObject('Todo/Button/btnSaveButtonTodo'), GlobalVariable.timeoutLoadingElementInSeccond, FailureHandling.STOP_ON_FAILURE)
		}

		if(action.contains("View")) {
			viewTodo(name, date, type, description, fileName)
		}
	}

	public void viewTodo(String name, String date, String type, String description, String file) {

		Mobile.tap(findTestObject('Todo/Input/textNameTodo', [('name') : name]), GlobalVariable.timeoutLoadingElementInSeccond)

		Mobile.waitForElementPresent(findTestObject('Todo/View/viewName'), 3, FailureHandling.STOP_ON_FAILURE)

		//		Mobile.verifyElementText(findTestObject('Todo/View/viewName'), name, FailureHandling.STOP_ON_FAILURE)
		//		Mobile.verifyElementText(findTestObject('Todo/View/viewDate'), calendarConverter.convertDateFormat(date), FailureHandling.STOP_ON_FAILURE)
		//		Mobile.verifyElementText(findTestObject('Todo/View/viewType'), type, FailureHandling.STOP_ON_FAILURE)
		//		Mobile.verifyElementText(findTestObject('Todo/View/viewDescription'), description, FailureHandling.STOP_ON_FAILURE)

		Mobile.verifyMatch(Mobile.getText(findTestObject('Todo/View/viewName'), 1, FailureHandling.STOP_ON_FAILURE), name, false, FailureHandling.STOP_ON_FAILURE)

		Mobile.verifyMatch(Mobile.getText(findTestObject('Todo/View/viewDate'), 1, FailureHandling.STOP_ON_FAILURE), calendarConverter.convertDateFormat(date.toString()), false, FailureHandling.STOP_ON_FAILURE)

		Mobile.verifyMatch(Mobile.getText(findTestObject('Todo/View/viewType'), 1, FailureHandling.STOP_ON_FAILURE), type, false, FailureHandling.STOP_ON_FAILURE)

		Mobile.verifyMatch(Mobile.getText(findTestObject('Todo/View/viewDescription'), 1, FailureHandling.STOP_ON_FAILURE), description, false, FailureHandling.STOP_ON_FAILURE)
	}
}