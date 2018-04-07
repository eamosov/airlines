# airlines

Сборка:

docker build . -t airlines

Запуск:

docker run -it -p 8080:8080 airlines

REST API:
1) Получить список всех самолетов http://localhost:8080/airplains
2) Получить самолет по ID (н-р 1) http://localhost:8080/airplains/1
3) Получить список всех аэропортов http://localhost:8080/airports
4) Получить аэропорт по ID (н-р 1) http://localhost:8080/airports/1
5) Получить список всех маршрутов http://localhost:8080/routes
6) Получить маршрут по ID (н-р 1) http://localhost:8080/routes/1
7) Получить список всех полетов http://localhost:8080/flights
8) Получить полет по ID (н-р 1) http://localhost:8080/flights/1
9) Добавить полет самолета 2 по маршруту 3 в дату 2018-04-07 http://localhost:8080/flights/add/2018-04-07/2/3
10) Посчитать число посадок самолета 2 в аэропорту 4 http://localhost:8080/lands/2/4
11) Посчитать число посадок самолета 2 в аэропорту 4 в период 2018-04-03 - 2018-04-07 http://localhost:8080/lands/2/4/2018-04-03/2018-04-07
