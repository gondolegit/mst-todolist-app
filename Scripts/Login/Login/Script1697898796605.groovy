import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

Mobile.startApplication(RunConfiguration.getProjectDir() + "/Data Files/Assets/" + GlobalVariable.apkName, true, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Login/Username'), GlobalVariable.timeoutLoadingElementInSeccond, FailureHandling.STOP_ON_FAILURE)

Mobile.setText(findTestObject('Login/Username'), 'mstqa', GlobalVariable.timeoutLoadingElementInSeccond, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Login/Password'), GlobalVariable.timeoutLoadingElementInSeccond, FailureHandling.STOP_ON_FAILURE)

Mobile.setText(findTestObject('Login/Password'), 'P@ssw0rd', GlobalVariable.timeoutLoadingElementInSeccond, FailureHandling.STOP_ON_FAILURE)

Mobile.doubleTap(findTestObject('Login/btnLogin'),GlobalVariable.timeoutLoadingElementInSeccond, FailureHandling.STOP_ON_FAILURE)

Mobile.waitForElementPresent(findTestObject('Todo/Button/btnAddTodo'), GlobalVariable.timeoutLoadingElementInSeccond, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementVisible(findTestObject('Todo/Button/btnAddTodo'), GlobalVariable.timeoutLoadingElementInSeccond, FailureHandling.STOP_ON_FAILURE)

