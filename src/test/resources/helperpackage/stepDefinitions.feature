Feature: Check tax on car?
  Testing structure

  Scenario: Tax check
    Given Valid Registration value of "LT09YJJ"
    When the website "cartaxcheck.co.uk" is live
    Then user should be taken to the "google.com" page
    And user types register of "LT09YJJ"
    And user clicks on the free button
    And car of color "Black" is returned