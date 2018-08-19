@get_weather_by_city_id
Feature: get weather by city name

  @smoke
  Scenario Outline: get weather by city name (only city name)
    When User call method get Weather By City id "<CityId>"
    Then User receives status code "<StatusCode>"

    Examples:
      |CityId |StatusCode|
      |2223   |  200     |
      |3243   |  200     |
      |435445 |  200     |
      |0000000|  404     |

  @regression
  Scenario Outline: get weather by city name and respons should be present (only city name)
    When User call method get Weather By City id "<CityId>"
    Then User receives status code "<StatusCode>"
    And User search fields in response
      |coord|
      |lon|
      |lat|
      |weather|
      |main|
      |description|
      |icon|
      |base|

    Examples:
      |CityId |StatusCode|
      |2223   |  200     |
      |3243   |  200     |
      |435445 |  200     |

  @regression
  Scenario Outline: get weather by city name and check data from UI (only city name)
    Given User save information about weather from weather.com by City "<CityName>"
    When User call method get Weather By City id "<CityId>"
    And User save information from EndPoint response
    Then User compare information from UI and EndPoint

    Examples:
      |CityName |CityId|
      |London   |2223  |
      |Kiev     |3243  |
      |New York |435445|


