package com.sergiotrapiello.cursotesting.basico.imitacion.issue;

import java.time.Clock;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public final class Issue {

	enum Status {
		NEW, CLOSED
	}

	private final User reporter;
	private final String title;
	private final LocalDateTime openDate;
	private final Clock clock;
	private final EmailSender emailSender;
	private Status status;
	private LocalDateTime closedDate;

	public Issue(Clock clock, User reporter, String title, EmailSender emailSender) {
		this.clock = clock;
		this.reporter = reporter;
		this.title = title;
		this.status = Status.NEW;
		this.openDate = LocalDateTime.now(clock);
		this.emailSender = emailSender;
	}

	public void close() {
		this.status = Status.CLOSED;
		this.closedDate = LocalDateTime.now(clock);
		Email email = Email.builder()
				.receiver(reporter.getEmail())
				.subject(title + " - cerrada")
				.build();
		emailSender.send(email);
		}

}
