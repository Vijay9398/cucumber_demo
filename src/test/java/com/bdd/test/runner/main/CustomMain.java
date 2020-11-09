package com.bdd.test.runner.main;

import io.cucumber.core.cli.Main;
import io.cucumber.core.logging.Logger;
import io.cucumber.core.logging.LoggerFactory;
import io.cucumber.core.options.CommandlineOptionsParser;
import io.cucumber.core.options.CucumberProperties;
import io.cucumber.core.options.CucumberPropertiesParser;
import io.cucumber.core.options.RuntimeOptions;
import io.cucumber.core.runtime.Runtime;

import java.util.Optional;

public class CustomMain {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String... argv) {
        byte exitStatus = run(argv, Thread.currentThread().getContextClassLoader());
        System.exit(exitStatus);
    }

    /**
     * Launches the Cucumber-JVM command line.
     *
     * @param  argv runtime options. See details in the
     *              {@code cucumber.api.cli.Usage.txt} resource.
     * @return      0 if execution was successful, 1 if it was not (test
     *              failures)
     */
    public static byte run(String... argv) {
        return run(argv, Thread.currentThread().getContextClassLoader());
    }

    /**
     * Launches the Cucumber-JVM command line.
     *
     * @param  argv        runtime options. See details in the
     *                     {@code cucumber.api.cli.Usage.txt} resource.
     * @param  classLoader classloader used to load the runtime
     * @return             0 if execution was successful, 1 if it was not (test
     *                     failures)
     */
    public static byte run(String[] argv, ClassLoader classLoader) {
        RuntimeOptions propertiesFileOptions = new CucumberPropertiesParser()
                .parse(CucumberProperties.fromPropertiesFile())
                .build();

        RuntimeOptions environmentOptions = new CucumberPropertiesParser()
                .parse(CucumberProperties.fromEnvironment())
                .build(propertiesFileOptions);

        RuntimeOptions systemOptions = new CucumberPropertiesParser()
                .parse(CucumberProperties.fromSystemProperties())
                .build(environmentOptions);

        CommandlineOptionsParser commandlineOptionsParser = new CommandlineOptionsParser(System.out);
        RuntimeOptions runtimeOptions = commandlineOptionsParser
                .parse(argv)
                .addDefaultGlueIfAbsent()
                .addDefaultFeaturePathIfAbsent()
                .addDefaultFormatterIfAbsent()
                .addDefaultSummaryPrinterIfAbsent()
                .enablePublishPlugin()
                .build(systemOptions);

        Optional<Byte> exitStatus = commandlineOptionsParser.exitStatus();
        if (exitStatus.isPresent()) {
            return exitStatus.get();
        }

        final CustomRuntime runtime = CustomRuntime.builder()
                .withRuntimeOptions(runtimeOptions)
                .withClassLoader(() -> classLoader)
                .build();

        runtime.run();
        return runtime.exitStatus();
    }

}
