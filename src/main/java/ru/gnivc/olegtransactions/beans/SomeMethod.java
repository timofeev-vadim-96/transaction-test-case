package ru.gnivc.olegtransactions.beans;

import org.springframework.stereotype.Component;

@Component
public class SomeMethod {
    public void doMethod(String text) throws Exception {
        throw new Exception();
    }
}
