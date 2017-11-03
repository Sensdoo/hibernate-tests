package com.sens.examples.jpa.interfaces;

import com.sens.examples.models.jpa.ContactSummary;

import java.util.List;

/**
 * Created by Sensible on 03.11.2017.
 */

public interface ContactSummaryService {
    List<ContactSummary> findAll();
}
