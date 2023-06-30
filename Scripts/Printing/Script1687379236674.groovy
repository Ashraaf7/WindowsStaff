import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
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

import java.io.File

// Specify the path of the Word document to be printed
String docPath = "C:/Users/elost/Desktop/Document.docx"

// Specify the path of the PDF file to be saved
String pdfPath = "C:/Users/elost/Desktop/Document.pdf"


// Create a File object for the Word document
File docFile = new File(docPath)

// Build the Pandoc command
String[] pandocCmd = ["pandoc", docPath, "-o", pdfPath]
// Execute the command
Process process = Runtime.getRuntime().exec(pandocCmd)

// Wait for the command to complete
process.waitFor()

// Check the exit status of the command
if (process.exitValue() == 0) {
    println("Conversion successful!")
} else {
    println("Conversion failed with exit code " + process.exitValue())
}
