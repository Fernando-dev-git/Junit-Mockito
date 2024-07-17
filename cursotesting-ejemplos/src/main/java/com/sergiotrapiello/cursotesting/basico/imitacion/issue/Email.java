package com.sergiotrapiello.cursotesting.basico.imitacion.issue;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public final class Email {

	private String receiver;
	private String subject;

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(receiver).append(subject).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Email other = (Email) obj;
		return new EqualsBuilder().append(receiver, other.receiver).append(subject, other.subject).isEquals();
	}

	@Override
	public String toString() {
		return "Email [receiver=" + receiver + ", subject=" + subject + "]";
	}

}
