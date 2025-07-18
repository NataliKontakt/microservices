### Команда для сборки и запуска
docker-compose up -d --build

### Данные для проверки
#### Send credit application
POST http://localhost:8081/api/credit
Content-Type: application/json

{
  "amount": 10000,
  "term": 12,
  "income": 4000,
  "liabilities": 1000,
  "creditScore": 700
}

#### Get application status
GET http://localhost:8081/api/credit/{{applicationId}}/status
