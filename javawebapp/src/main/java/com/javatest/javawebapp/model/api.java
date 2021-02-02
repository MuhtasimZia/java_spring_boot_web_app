package com.javatest.javawebapp.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

@JsonIgnoreProperties
public class api {

    private String nlmDisclaimer;
    private JsonNode userInput;
    private JsonNode interactionTypeGroup;

    public JsonNode getInteractionTypeGroup() {
        return interactionTypeGroup;
    }

    public void setInteractionTypeGroup(JsonNode interactionTypeGroup) {
        this.interactionTypeGroup = interactionTypeGroup;
    }

    public String getNlmDisclaimer() {
        return nlmDisclaimer;
    }

    public void setNlmDisclaimer(String nlmDisclaimer) {
        this.nlmDisclaimer = nlmDisclaimer;
    }

    public JsonNode getUserInput() {
        return userInput;
    }

    public void setUserInput(JsonNode userInput) {
        this.userInput = userInput;
    }
}
