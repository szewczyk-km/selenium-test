package info.kszewczyk.seleniumtest;

import org.junit.runner.Result;

public interface TestResult {

    void process(Result result);
}
