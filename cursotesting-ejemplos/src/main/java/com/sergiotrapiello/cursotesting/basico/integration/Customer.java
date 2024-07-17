package com.sergiotrapiello.cursotesting.basico.integration;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

	private Integer id;

	private String legalIdentifier;

	private String name;

	private String lastName;

	private String email;

	private String phoneNumber;

	@Override
	public String toString() {
		return "Customer [id=" + id + ", legalIdentifier=" + legalIdentifier + ", name=" + name + ", lastName="
				+ lastName + ", email=" + email + ", phoneNumber=" + phoneNumber + "]";
	}

	public Integer getId() {
		return id;
	}

	/**
	 * Una vez establecido, el id no puede cambiar
	 * 
	 * @param id
	 * @throws UnsupportedOperationException si {@link #getId()} != null
	 */
	public void setId(Integer id) {
		if (this.id != null) {
			throw new UnsupportedOperationException("El id no se puede re-asignar");
		}
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, id, lastName, legalIdentifier, name, phoneNumber);
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
		Customer other = (Customer) obj;
		return Objects.equals(email, other.email) && Objects.equals(id, other.id)
				&& Objects.equals(lastName, other.lastName) && Objects.equals(legalIdentifier, other.legalIdentifier)
				&& Objects.equals(name, other.name) && Objects.equals(phoneNumber, other.phoneNumber);
	}
	
}
