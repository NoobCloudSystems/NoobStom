package xyz.luccboy.noobstom.config;

import lombok.Getter;

@Getter
public class Config {

    private final boolean velocityEnabled;
    private final String velocitySecret;

    public Config(final boolean velocityEnabled, final String velocitySecret) {
        this.velocityEnabled = velocityEnabled;
        this.velocitySecret = velocitySecret;
    }

}
