package info.kszewczyk.seleniumtest;

import org.apache.commons.cli.*;

import java.util.Optional;

public class RunOptions {

    public static final Option url = Option.builder("url")
            .argName("url")
            .hasArg()
            .desc("A url pointing to tested application")
            .longOpt("application-url")
            .required(true)
            .build();

    public static final Option seleniumRunEnvironment = Option.builder("senv")
            .argName("url")
            .hasArg()
            .required(true)
            .longOpt("selenium-run-environment")
            .desc("A url pointing to selenium run environment")
            .build();

    public static final Option capability = Option.builder("c")
            .argName("browser")
            .hasArg()
            .longOpt("capability")
            .desc("Desired capability passed to remote web driver (firefox, chrome, ie, edge, opera, safari")
            .build();

    public static final Option timeout = Option.builder("t")
            .argName("seconds")
            .hasArg()
            .longOpt("timeout")
            .desc("Driver implicit timeout")
            .build();

    private static RunOptions instance;

    public static RunOptions instance() {
        if (instance == null) {
            synchronized (RunOptions.class) {
                if (instance == null) {
                    instance = new RunOptions();
                }
            }
        }
        return instance;
    }

    private Options options = new Options();
    private Optional<CommandLine> cmd = Optional.empty();

    private RunOptions() {
        options.addOption(url);
        options.addOption(seleniumRunEnvironment);
        options.addOption(capability);
        options.addOption(timeout);
    }

    public void parse(String[] args) throws ParseException {
        cmd = Optional.of(new DefaultParser().parse(options, args));
    }

    public <T> T get(Option option) {
        return (T) cmd.orElseThrow(() -> new IllegalStateException("Command line argument not parsed")).getOptionValue(option.getOpt());
    }

    public void help() {
        new HelpFormatter().printHelp("run [OPTION]...", options);
    }

}
