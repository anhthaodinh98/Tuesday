Feature: DA_MP

  Background:
    Given I'm on Dashboard login page
    When I get repository on login form and save it "Repository_Name"
    And I get logged in with ADMIN

  @DeletePage
  Scenario: TC021 - Verify that user is able to edit the name of the page (Parent/Sibling) successfully
    When I click on Setting icon and choose "Add Page" item
    And I enter fields in Page dialog
      | Page Name | Parent Page |
      | Page1     | Overview    |
    And I click on "OK" button
    Then Page should "display" in the following "Overview>Page1" path
    When I click on Setting icon and choose "Add Page" item
    And I enter fields in Page dialog
      | Page Name | Parent Page |
      | Page2     | Page1       |
    And I click on "OK" button
    Then Page should "display" in the following "Overview>Page1>Page2" path
    When I go to page with "Overview>Page1" path
    And I click on Setting icon and choose "Edit" item
    And I edit fields in Page dialog
      | Field Name | Old Value | New Value |
      | Page Name  | Page1     | Page3     |
    And I click on "OK" button
    Then Page should "display" in the following "Overview>Page3" path
    When I go to page with "Overview>Page3>Page2" path
    And I click on Setting icon and choose "Edit" item
    And I edit fields in Page dialog
      | Field Name | Old Value | New Value |
      | Page Name  | Page2     | Page4     |
    And I click on "OK" button
    Then Page should "display" in the following "Overview>Page3>Page4" path
