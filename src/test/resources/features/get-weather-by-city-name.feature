@get_weather_by_city_name
Feature: get weather by city name

  @smoke
  Scenario Outline: get weather by city name (only city name)
    When User call method get Weather By City Name "<CityName>"
    Then User receives status code "<StatusCode>"

    Examples:
      |CityName |StatusCode|
      |London   |  200     |
      |Kiev     |  200     |
      |New York |  200     |
      |Not found|  404     |


  @regression
  Scenario Outline: get weather by city name and respons should be present (only city name)
    When User call method get Weather By City Name "<CityName>"
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
      |CityName |StatusCode|
      |London   |  200     |
      |Kiev     |  200     |
      |New York |  200     |

  @regression
  Scenario Outline: get weather by city name and check data from UI (only city name)
    Given User save information about weather from weather.com by City "<CityName>"
    When User call method get Weather By City Name "<CityName>"
    And User save information from EndPoint response
    Then User compare information from UI and EndPoint

    Examples:
      |CityName |
      |London   |
      |Kiev     |
      |New York |


