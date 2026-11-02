package com.nttdata.stepsdefinitions;

import io.cucumber.java.AfterStep;
import java.util.concurrent.TimeUnit;

public class VideoHooks {

    @AfterStep
    public void pausarParaVideo() throws InterruptedException {
        if (Boolean.getBoolean("modoVideo")) {
            TimeUnit.SECONDS.sleep(2);
        }
    }
}