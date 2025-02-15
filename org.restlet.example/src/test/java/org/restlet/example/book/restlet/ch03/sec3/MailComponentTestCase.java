/**
 * Copyright 2005-2024 Qlik
 * 
 * The contents of this file is subject to the terms of the Apache 2.0 open
 * source license available at http://www.opensource.org/licenses/apache-2.0
 * 
 * Restlet is a registered trademark of QlikTech International AB.
 */

package org.restlet.example.book.restlet.ch03.sec3;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.data.Method;
import org.restlet.example.book.restlet.ch03.sec3.server.MailServerComponent;

/**
 * Concurrent test case with TestNG
 * 
 * @author Jerome Louvel
 */
public class MailComponentTestCase {

    private final MailServerComponent component;

    public MailComponentTestCase() throws Exception {
        component = new MailServerComponent();
    }

    @BeforeAll
    public void beforeSuite() throws Exception {
        component.start();
    }

    @Test
    public void makeCall() {
        Request request = new Request(Method.GET, "http://localhost:8111/");
        Response response = component.handle(request);

        Assertions.assertTrue(response.getStatus().isSuccess());
        Assertions.assertEquals("Welcome to the RESTful Mail Server application !",
                response.getEntityAsText());
    }

    @AfterAll
    public void afterSuite() throws Exception {
        component.stop();
    }

}
