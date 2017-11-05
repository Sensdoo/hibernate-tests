package com.sens.examples.springdata;

import org.springframework.data.domain.AuditorAware;

/**
 * Created by Sensible on 04.11.2017.
 */
public class AuditorAwareBean implements AuditorAware<String> {
    @Override
    public String getCurrentAuditor() {
        return "prospring4";
    }
}
