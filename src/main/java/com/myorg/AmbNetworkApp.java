package com.myorg;

import software.amazon.awscdk.core.App;

import java.util.Arrays;

public class AmbNetworkApp {
    public static void main(final String[] args) {
        App app = new App();

        new AmbNetworkStack(app, "AmbNetworkStack");

        app.synth();
    }
}
