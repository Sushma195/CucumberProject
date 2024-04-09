@test1
Feature: Sign IN to KSRTC App

  Background: 
    Given I want to access ksrtc app

  Scenario Outline: Login to the application with username and password
    When I click on signin button
    And user enters sheetname "<sheetname>" and rownumber "<rownumber>"
    And I ckeckbox terms and condition
    And I click on login button
    Then I should be navigated to welcom page and verify the page "<message>"

    Examples: 
      | sheetname | rownumber | message                       |
      | Sheet1    |         0 | WelcomeTESTACCOUNT            |
      | Sheet1    |         1 | Loginincorrect.Pleasetryagain |
