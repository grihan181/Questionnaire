package com.opencode.practice.vaadin;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;
import org.springframework.security.access.prepost.PreAuthorize;

@Route("/test")
@PreAuthorize("hasAuthority('developers:read')")
public class TestRoute extends Div {
    public TestRoute() {
        setText("Hello world");
    }
}
