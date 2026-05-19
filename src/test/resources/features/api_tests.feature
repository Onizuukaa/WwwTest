@API
Feature: Testowanie API Softra

  Scenario: Test publicznego API
    Given Ustawiam endpoint "/posts/1"
    When Wysyłam zapytanie GET
    Then Otrzymuję kod statusu 200
    Then W odpowiedzi znajduje się pole "id" o wartości "1"