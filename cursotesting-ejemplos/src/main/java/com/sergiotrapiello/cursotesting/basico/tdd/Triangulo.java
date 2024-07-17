package com.sergiotrapiello.cursotesting.basico.tdd;

import com.sergiotrapiello.cursotesting.basico.exception.TriangleMalFormedException;

import lombok.Data;

@Data
public final class Triangulo {

	enum Type {
		EQUILATERAL, ISOSCELES, SCALENE
	}

	private int sideA, sideB, sideC;

	public Triangulo(int sideA, int sideB, int sideC) {
		validate(sideA, sideB, sideC);
		this.sideA = sideA;
		this.sideB = sideB;
		this.sideC = sideC;
	}

	private void validate(int sideA, int sideB, int sideC) {
		if (sideA + sideB <= sideC || sideA + sideC <= sideB || sideB + sideC <= sideA) {
			throw new TriangleMalFormedException();
		}
	}

	public Type getType() {
		if (isEquilateral()) {
			return Type.EQUILATERAL;
		}
		if (isIsosceles()) {
			return Type.ISOSCELES;
		}
		return Type.SCALENE;
	}

	private boolean isIsosceles() {
		return sideA == sideB || sideB == sideC || sideA == sideC;
	}

	private boolean isEquilateral() {
		return sideA == sideB && sideB == sideC;
	}

}
