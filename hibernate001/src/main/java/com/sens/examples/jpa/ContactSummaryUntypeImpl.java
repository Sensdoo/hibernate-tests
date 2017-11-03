package com.sens.examples.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Sensible on 03.11.2017.
 */

@Service("contactSummaryUntype")
@Repository
@Transactional
public class ContactSummaryUntypeImpl {

    @PersistenceContext(unitName = "emf")
    private EntityManager em;

    @Transactional
    public void displayAllContactSummary() {
        List result = em.createQuery("select c.firstName, c.lastName, t.telNumber " +
                "from Contact c left join c.contactTelDetails t where t.telType='Home'").getResultList();

        int count = 0;

        for (Iterator i = result.iterator(); i.hasNext(); ) {
            Object[] values = (Object[]) i.next();
            System.out.println(++count + ": " + values[0] + ", " + values[1] + ", " + values[2]);
        }
    }
}
