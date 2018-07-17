package info.kszewczyk.seleniumtest.result;

import info.kszewczyk.seleniumtest.TestResult;
import org.junit.runner.Result;

public class SimpleConsoleTestResult implements TestResult {
    @Override
    public void process(Result result) {
        System.out.println("Total tests: " + result.getRunCount() + " executed in " + result.getRunTime() + " ms.");
        System.out.println("Test ignored: " + result.getIgnoreCount());
        System.out.println("Test failed: " + result.getFailureCount());
        result.getFailures().forEach(failure -> {
            System.out.println("Fail message: " + failure.getMessage());
            System.out.println("==== Exception   ================");
            failure.getException().printStackTrace(System.err);
            System.out.println("=================================");
        });
    }
}
