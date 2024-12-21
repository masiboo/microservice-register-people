package com.mateudev.microserviceregister.util;


import java.util.Optional;

public class DebugInfoUtil {

	public static String logExceptionDetails(Exception e) {
		if (e == null) {
			System.err.println("Exception is null, cannot log exception details.");
			return"";
		}
		String className = "UnknownClass";
		String methodName = "UnknownMethod";
		int lineNumber = -1;
		String exceptionMessage = Optional.ofNullable(e.getMessage()).orElse("No message available");

		StackTraceElement[] stackTraceElements = e.getStackTrace();
		if (stackTraceElements != null && stackTraceElements.length > 0) {
			StackTraceElement element = stackTraceElements[0];
			className = Optional.of(element.getClassName()).orElse(className);
			methodName = Optional.of(element.getMethodName()).orElse(methodName);
			lineNumber = element.getLineNumber();
		}

		// Construct error details string
		String errorDetails = "ClassName=" + className + " | MethodName=" + methodName + " | LineNumber=" + lineNumber
				+ " | ExceptionMessage=" + exceptionMessage;

		// Build the stack trace safely with null checks
		StringBuilder stackTrace = new StringBuilder();
		if (stackTraceElements != null) {
			for (StackTraceElement el : stackTraceElements) {
				if (el != null) {
					stackTrace.append(el).append("\n");
				}
				else {
					stackTrace.append("Null stack trace element\n");
				}
			}
		}
		else {
			stackTrace.append("No stack trace available");
		}
		System.err.println("Error Details: "+errorDetails);
		System.err.println("Stack Trace: "+stackTrace);
		return errorDetails;
	}

}
