package com.sergiotrapiello.cursotesting.basico.imitacion.issue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.ArgumentCaptor;

import com.sergiotrapiello.cursotesting.basico.imitacion.issue.Issue.Status;

class IssueMockitoTest {

	private Clock clock;

	@BeforeEach
	void setup() {
		clock = Clock.fixed(Instant.parse("2023-09-19T08:10:00.00Z"), ZoneId.systemDefault());
	}

	@ParameterizedTest
	@CsvSource({ "'Issue 1', 'Issue 1 - cerrada'", "'Otra issue', 'Otra issue - cerrada'" })
	void shouldClose(String issuTitle, String expectedSubject) {
		// GIVEN
		User reporte = defaultReporter();
		EmailSender emailSenderMock = mock(EmailSender.class);
		
		Issue issue = new Issue(clock, reporte, issuTitle, emailSenderMock);
		Email expectedEmailSent = Email.builder()
				.subject(expectedSubject)
				.receiver(reporte.getEmail())
				.build();
		
		// WHEN
		issue.close();

		// THEN
		assertEquals(Status.CLOSED, issue.getStatus(), "El estado de la issue deberia ser CLOSED. ");
		assertEquals(LocalDateTime.now(clock), issue.getClosedDate(), "No se ha establecido la fecha de cierre. ");
		ArgumentCaptor<Email> emailCaptor = ArgumentCaptor.forClass(Email.class);
		verify(emailSenderMock).send(emailCaptor.capture());
		assertEquals(expectedEmailSent.getSubject(), emailCaptor.getValue().getSubject());
	}

	private User defaultReporter() {
		return new User("reporter1@mail.com");
	}

}