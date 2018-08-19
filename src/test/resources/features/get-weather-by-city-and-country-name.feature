@get_weather_by_city_name_and_country
Feature: get weather by city name and country name

  @smoke
  Scenario Outline: get weather by city name (city name and country)
    When User call method get Weather By City Name "<CityName>" name and Country "<country>"
    Then User receives status code "<StatusCode>"

    Examples:
      |CityName |StatusCode|country|
      |London   |  200     |   GB  |
      |Kiev     |  200     |   UK  |
      |New York |  200     |   US  |
      |Not found|  404     |   Not |


  @regression
  Scenario Outline: get weather by city name and respons should be present (city name and country)
    When User call method get Weather By City Name "<CityName>" name and Country "<country>"
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
      |CityName |StatusCode|country|
      |London   |  200     |   GB  |
      |Kiev     |  200     |   UK  |
      |New York |  200     |   US  |


  @regression
  Scenario Outline: get weather by city name and check data from UI (city name and country)
    Given User save information about weather from weather.com by City "<CityName>"
    When User call method get Weather By City Name "<CityName>" name and Country "<country>"
    And User save information from EndPoint response
    Then User compare information from UI and EndPoint

    Examples:
      |CityName |country|
      |London   |   GB  |
      |Kiev     |   UK  |
      |New York |   US  |