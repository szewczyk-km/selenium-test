package info.kszewczyk.seleniumtest;

import info.kszewczyk.seleniumtest.result.SimpleConsoleTestResult;
import org.apache.commons.cli.MissingOptionException;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.UnrecognizedOptionException;
import org.junit.runner.JUnitCore;

public class Run {

    public static void main(String[] args) {
        try {
            RunOptions.instance().parse(args);

            var junit = new JUnitCore();
            var testClasses = new TestClasses(Run.class.getPackage().getName());

            var result = junit.run(testClasses.load());

            new SimpleConsoleTestResult().process(result);

            if (result.wasSuccessful()) {
                System.exit(0);
            }
        } catch (MissingOptionException | UnrecognizedOptionException e) {
            RunOptions.instance().help();
        } catch (ParseException e) {
            e.printStackTrace(System.err);
        }
        System.exit(1);
    }

}
