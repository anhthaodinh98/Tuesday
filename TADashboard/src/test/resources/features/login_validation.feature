Feature: DA_LOGIN

  Background:
    Given I'm on Dashboard login page
    And I get repository on login form and save it "Repository_Name"

  Scenario: TC001 - Verify that user can login specific repository successfully
    When I get logged in with ADMIN
    Then The welcome account and repository "Repository_Name" display correctly on Dashboard main page


  Scenario Outline: TC002 TC003 - Verify login fail with invalid username and password
    When I login with "<Username>" and "<Password>"
    Then System should show "<Error Message>" message

    Examples:
      | Username      | Password | Error Message                   |
      | abc           | abc      | Username or password is invalid |
      | administrator | abc      | Username or password is invalid |


  Scenario: TC007 - Verify that Username is not case sensitive
    When I login with "ADMINISTRATOR" and ""
    Then The welcome account and repository "Repository_Name" display correctly on Dashboard main page
    When I click on Logout button
    And I login with "administrator" and ""
    Then The welcome account and repository "Repository_Name" display correctly on Dashboard main page


  Scenario: TC010 - Verify that the page works correctly without input entered to Password and Username fields
    When I login with "" and ""
    Then System should show "Please enter username!" message
