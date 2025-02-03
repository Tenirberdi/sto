# Getting started

To start all services run command `docker-compose up --build`

swagger routes:

bid-service - `http://localhost:8089/swagger-ui/index.html`

repair-service - `http://localhost:8091/swagger-ui/index.html`

check-service - `http://localhost:8090/swagger-ui/index.html`


[bid-service] create bid example:

`{
  "customerId": 1,
  "vehicleId": 1,
  "complaint": "Двигатель троит, повышенный расход топлива.",
  "attachments": [
    {
      "fileName": "engine_noise.mp4",
      "fileType": "video/mp4",
      "url": "https://sto.com/uploads/engine_noise.mp4"
    }
  ],
  "preferredDate": "2025-02-01",
  "notes": "Клиент просит перезвонить перед ремонтом"
}
`

[bid-service] Create customer example:

`{
    "firstName": "Иван",
    "lastName": "Петров",
    "phone": "+7 912 345 67 95"
}
`

[bid-service] Create vehicle:

`{
    "make": "Toyota",
    "model": "Camry",
    "year": 2020,
    "vin": "JTMZF33V70D123456",
    "mileage": 85000,
    "licensePlate": "А123ВЕ 78",
    "engineType": "2.5L бензин"
}`

[bid-service] to send bid for repair need to use `/bids/1/process`

[repair-service] to check status of repair use `/repair-status/bids/1`

[check-service] to check for bid use `/checks/14`
