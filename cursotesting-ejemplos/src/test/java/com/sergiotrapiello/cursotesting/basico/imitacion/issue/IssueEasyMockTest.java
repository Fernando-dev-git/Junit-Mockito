package com.sergiotrapiello.cursotesting.basico.imitacion.issue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.easymock.Capture;
import org.easymock.EasyMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.sergiotrapiello.cursotesting.basico.imitacion.issue.Issue.Status;

class IssueEasyMockTest {

	private Clock clock;

	@BeforeEach
	void setup() {
		clock = Clock.fixed(Instant.parse("2023-09-19T08:10:00.00Z"), ZoneId.systemDefault());
	}

	@Test
	void shouldCreate() {

		// GIVEN
		User reporter = defaultReporter();
		String title = "Issue 1";

		// WHEN
		EmailSender emailSenderMock = EasyMock.mock(EmailSender.class);
		Issue createdIssue = new Issue(clock, reporter, title, emailSenderMock);

		// THEN
		assertEquals(Status.NEW, createdIssue.getStatus());
		assertEquals(LocalDateTime.now(clock), createdIssue.getOpenDate());
		assertEquals(title, createdIssue.getTitle());
		assertEquals(reporter, createdIssue.getReporter());
		assertNull(createdIssue.getClosedDate());
	}

	@ParameterizedTest
	@CsvSource( {"'Issue 1', 'Issue 1 - cerrada'",
		"'Otra issue', 'Otra issue - cerrada'"})
	void shouldClose(String issuTitle, String expectedSubject) {
		// GIVEN
		User reporte = defaultReporter();
		EmailSender emailSenderMock = EasyMock.mock(EmailSender.class);		
		
		Issue issue = new Issue(clock, reporte, issuTitle, emailSenderMock);		
		Email expectedEmailSent = Email.builder()
				.subject(expectedSubject)
				.receiver(reporte.getEmail())
				.build();
		
		// record expected
		Capture<Email> argumentsCapture = EasyMock.newCapture();
		emailSenderMock.send(EasyMock.capture(argumentsCapture));
		
		// WHEN
		EasyMock.replay(emailSenderMock);
		issue.close();
		

		// THEN
		assertEquals(Status.CLOSED, issue.getStatus(), "El estado de la issue deberia ser CLOSED. ");
		assertEquals(LocalDateTime.now(clock), issue.getClosedDate(), "No se ha establecido la fecha de cierre. ");
		
		assertEquals(expectedEmailSent.getSubject(), argumentsCapture.getValue().getSubject());
	}

	private User defaultReporter() {
		return new User("reporter1@mail.com");
	}

}