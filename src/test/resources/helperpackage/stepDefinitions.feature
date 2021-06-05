Feature: Check tax on car?
  Testing structure

  Scenario: Tax check
    Given Valid Registration value of "LT09YJJ"
    When the website "cartaxcheck.co.uk" is live
    Then user should be taken to the "google.com" page
    And user types register of "LT09YJJ"
    And user clicks on the free button
    And car of color "Black" is returned

  Scenario: Tax check invalid
    Given Valid Registration value of "LT23YFG"
    When the website "cartaxcheck.co.uk" is live
    Then user should be taken to the "google.com" page
    And user types register of "LT23YFG"
    And user clicks on the free button
    And user is given the vehicle not found message