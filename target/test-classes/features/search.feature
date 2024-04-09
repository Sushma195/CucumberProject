@test2
Feature: Search for Bus

  Background: 
    Given I want to access KSRTC app

  Scenario Outline: Search for bus from source to destination place
    When user must be logged into ksrtc app with valid credentials "<sheetname>" and rownumber "<rownumber>"
    And user enters "<leavingfrom>" leaving from details
    And select leaving from city from drop list
    And user enters "<goingto>" going to details
    And select going to city from drop list
    And user enters departure date "<departuredate>"
    And user enters return date "<returndate>"
    And user clicks on search for bus button
    Then user should verify for source to destination details both ways in service page

    Examples: 
      | sheetname | rownumber | leavingfrom | goingto   | departuredate   | returndate      |
      | Sheet1    |         0 | Bangalore   | Mangalore | 1-February-2024 | 3-February-2024 |
