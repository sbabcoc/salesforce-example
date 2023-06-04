# Introduction

This **salesforce-example** project implements page models of the **Salesforce** `Lightning Web Components` [example site](https://conference-lwc-app.herokuapp.com/). Unfortunately, the example site this project was designed to navigate has been retired. I'll update this project if I can find a replacement.

## Page Model Structure

The page classes presented in this project are built on the [Selenium Foundation](https://github.com/Nordstrom/Selenium-Foundation) framework. A common [AppPage](https://github.com/sbabcoc/salesforce-example/blob/master/src/main/java/com/github/sbabcoc/model/AppPage.java) base class provides the shadow DOM context that encapsulates the actual content of the page.
Logical units of functionality within each page are modeled as shadow DOM components and conventional page components. For example, the initial "[root](https://github.com/sbabcoc/salesforce-example/blob/master/src/main/java/com/github/sbabcoc/model/LightningConferenceRoot.java)" page of the site includes a shadow DOM [session list](https://github.com/sbabcoc/salesforce-example/blob/master/src/main/java/com/github/sbabcoc/model/SessionList.java), which contains a mapped collection of [class session](https://github.com/sbabcoc/salesforce-example/blob/master/src/main/java/com/github/sbabcoc/model/ClassSession.java) page components.

## Demonstrated Features

This example project demonstrates the following features:

* Page model structure...
  * ... pages
  * ... conventional page components
  * ... shadow DOM components
  * ... component collections

* Site manipulation...
  * ... element location
  * ... element interaction
  * ... transition synchronization

This last feature (transition synchronization) is especially important to the stability of the model. Automation with inadequate synchronization is prone to non-deterministic behavior, which can trigger false negatives - test failures that indicate errors that don't actually exist. The transition synchronization facilities provided by **Selenium Foundation** enable the creation of stable page models with minimal custom implementation.

## NOTICE

The example site this project targets has been shut down. I have yet to find a replacement for this resource.