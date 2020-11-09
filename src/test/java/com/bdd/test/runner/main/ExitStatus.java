package com.bdd.test.runner.main;

import io.cucumber.core.plugin.Options;
import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.*;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.max;
import static java.util.Collections.min;
import static java.util.Comparator.comparing;

public class ExitStatus implements ConcurrentEventListener {

    private static final byte DEFAULT = 0x0;
    private static final byte ERRORS = 0x1;

    private final List<Result> results = new ArrayList<>();
    private final Options options;

    private final EventHandler<TestCaseFinished> testCaseFinishedHandler = event -> results.add(event.getResult());

    public ExitStatus(Options options) {
        this.options = options;
    }

    @Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestCaseFinished.class, testCaseFinishedHandler);
    }

    byte exitStatus() {
        return isSuccess() ? DEFAULT : ERRORS;
    }

    boolean isSuccess() {
        if (results.isEmpty()) {
            return true;
        }

        if (options.isWip()) {
            Result leastSeverResult = min(results, comparing(Result::getStatus));
            return !leastSeverResult.getStatus().is(Status.PASSED);
        } else {
            Result mostSevereResult = max(results, comparing(Result::getStatus));
            return mostSevereResult.getStatus().isOk();
        }
    }

    Status getStatus() {
        if (results.isEmpty()) {
            return Status.PASSED;
        }
        Result mostSevereResult = max(results, comparing(Result::getStatus));
        return mostSevereResult.getStatus();
    }

}
