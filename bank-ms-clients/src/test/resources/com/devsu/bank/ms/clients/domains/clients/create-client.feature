Feature: Create client

  Scenario: create a client is correct
    Given url baseUrl + '/v1/clients'
    And request { name: 'Jose Lema', gender: 'MALE', age: 25, personalId: '123456789', address: 'Otavalo sn y principal', phone: '098254785', password: '1234', state: true }
    When method post
    Then status 200
    And match $ == { "id": #number, "uid": "#string", "name": "Jose Lema", "gender": "MALE", "age": 25, "personalId": "123456789", "address": "Otavalo sn y principal", "phone": "098254785",   "state": true }

