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

import java.util.Properties
import javax.activation.DataHandler
import javax.activation.FileDataSource
import javax.mail.*
import javax.mail.internet.*

// Specify the email address and password for your Outlook account
String username = "20191700862@cis.asu.edu.eg"
String password = ""

// Specify the recipient email address
String recipient = "ahmedashraaf09@gmail.com"

// Specify the file path of the file to be attached
String filePath = "C:/Users/elost/Desktop/Document.pdf"

// Set up the mail server properties
Properties props = new Properties()
props.put("mail.smtp.auth", "true")
props.put("mail.smtp.starttls.enable", "true")
props.put("mail.smtp.host", "smtp.office365.com")
props.put("mail.smtp.port", "587")

// Create a new session with the mail server
Session session = Session.getInstance(props, new Authenticator() {
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(username, password)
	}
})

try {
	// Create a new message
	MimeMessage message = new MimeMessage(session)
	message.setFrom(new InternetAddress(username))
	message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient))
	message.setSubject("Test email with attachment")

	// Create a multipart message body
	MimeMultipart multipart = new MimeMultipart()

	// Create a text/plain message body part
	MimeBodyPart messageBodyPart = new MimeBodyPart()
	messageBodyPart.setText("This is a test email with attachment.")
	multipart.addBodyPart(messageBodyPart)

	// Create a message body part for the attachment
	MimeBodyPart attachmentBodyPart = new MimeBodyPart()
	FileDataSource dataSource = new FileDataSource(filePath)
	attachmentBodyPart.setDataHandler(new DataHandler(dataSource))
	attachmentBodyPart.setFileName(dataSource.getName())
	multipart.addBodyPart(attachmentBodyPart)

	// Set the multipart message body as the message content
	message.setContent(multipart)

	// Send the message
	Transport.send(message)

	// Print a success message
	println("Email sent successfully!")
} catch (Exception ex) {
	// Print an error message
	println("Error sending email: " + ex.getMessage())
}