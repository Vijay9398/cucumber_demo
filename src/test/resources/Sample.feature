Feature: sample feature

  Background:
    Given user is on 'addition'

  @addition
  Scenario: user does addition
    When user provide first number as 7
    And user provide second number as 10
    Then the result will be 17

  @addition
  Scenario: user does addition with different values
    When user provide first number as 20
    And user provide second number as 30
    Then the result will be 50

  @parameter
  Scenario: parameter check
    When i give "atreus" as a name
    And i give 10.0 as a double
    And i give 127 as a number
    And i given two numbers as 11 and 13
    And i give datatable as
      | name      | age | place  |
      | priya     | 30  | belagam|
      | sindhu    | 30  | belagam|
      | big boss  | 30  | belagam|


  @examples
  Scenario Outline: examples scenario
    When user provide first number as <first number>
    And user provide second number as <second number>
    Then the result will be <total>

    Examples:
      | first number | second number | total |
      |  7           | 10            | 17    |
      |  10          | 20            | 30    |
      |  13          | 39            | 17    |

  @examples_table
  Scenario Outline: examples with data table
    When user provides data as
      | first number  | <first number>  |
      | second number | <second number> |
    Then the result will be <total>

    Examples: {excelkwdbwhbfwbdckw}
      | first number | second number | total |
      |  7           | 10            | 17    |
      |  10          | 20            | 30    |
      |  13          | 39            | 17    |



