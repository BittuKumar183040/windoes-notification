package com.bqp.bqphy.notificationservice.service.builder;

import com.bqp.bqphy.notificationservice.service.TemplateInterface;

public record RegistrationBuilder() implements TemplateInterface {

    @Override
    public String subject() {
        return "New Registration";
    }

    @Override
    public String render() {
        return """
            <html>
            <body>
                <h2>Welcome into BQPhy</h2>
                <p>Thank you for registering into BQPhy</p>
                <p>Fell free to reach out to use, if any thing is required.</p>
            </body>
            </html>
            """;
    }
}
