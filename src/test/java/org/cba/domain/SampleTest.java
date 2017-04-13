package org.cba.domain;

import io.ebean.Ebean;
import org.testng.annotations.Test;

/**
 * Created by adam on 13/04/2017.
 */
public class SampleTest {
    @Test
    public void insert() {
        User user = new User();
        user.setName("org.cba.domain.SampleTest");
        Ebean.save(user);
    }
}