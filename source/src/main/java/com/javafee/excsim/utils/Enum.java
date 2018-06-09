package com.javafee.excsim.utils;

import java.util.stream.Stream;

public final class Enum {

	public enum ExceptionKind {
		NULL_POINTER_EXC("Null pointer exception"), IO_EXC("IO exception"), OUT_OF_BOUNDS("Array out of bounds exception"), STR_OUT_OF_BOUNDS("String index out of bounds exception"), ARITHMETIC_EXC("Arithmetic exception"), NUMBER_FORMAT_EXC("Number format exception"), MY_LOW_BALANCE_EXC("My low balance exception");

		private final String value;

		ExceptionKind(final String newValue) {
			value = newValue;
		}

		public String getValue() {
			return value;
		}

		public static ExceptionKind getByName(String exceptionName) {
			return Stream.of(ExceptionKind.values())
					.filter(item -> item.getValue().equals(exceptionName)).findFirst().get();
		}
	}
	
}
