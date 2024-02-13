# teams
# REST-сервис для хранения данных о комнатах и постояльцах в хостеле
 - для запуска необходимо:
   - создать базу данных hostel (PostgreSQL). Данные владельца логин и пароль указать в application.yml.
   - в файле application.yml указать конфигурацию используемой базы данных, например:
      - url: jdbc:postgresql://localhost:5432/hostel
      - username: admin (логин владельца)
      - password: admin (пароль владельца)
   - запустить SightsApplication
      - Путь к .java: src/main/java/org/example/HostelApplication.java
# Управление комнатами
## Получение всех комнат
 - метод GET
 - URL "/room-read-all"  
       
\- Пример ответного JSON: 
   ```JSON
[
    {
        "id": 1,
        "floor": 10,
        "flat": 2,
        "typeGender": "MEN",
        "typeComfort": "LUXE",
        "numberOfSeats": 1,
        "totalSeats": 1,
        "dateOfChange": "2024-02-13",
        "dateOfAddition": "2024-02-13"
    }
]
  ```
## Добавление комнаты
  - метод POST
  - URL "/teams"
    - floor : этаж, ограничение с 1 по 10 этаж
    - flat : номер комнаты, ограничение с 1 по 100 квартиру
    - gender : пол (MEN, WOMEN)
    - typeComfort : тип комфорта (STANDARD, DE_LUXE, LUXE)
    - numberOfSeats : количество мест, ограничение  от 1 места    
  
   \- Пример JSON: 
   ```JSON
       {
          "floor" : 10,
          "flat" : 2,
          "typeGender" : "MEN",
          "typeComfort" : "LUXE",
          "numberOfSeats" : 1
       }
  ```
## Изменение комнаты
  - метод PUT
  - URL "/rooms/{numFlat}"
    - numFlat : номер комнаты
    - Параметры для изменения. Возможно частичное изменение:  
      \- flat - номер комнаты  
      \- typeGender - пол (MEN, WOMEN), только если комната пустая  
      \- typeComfort - тип комфорта (STANDARD, DE_LUXE, LUXE)
      \- numberOfSeats - количество мест, ограничение  от 1 места 
          
   \- Пример JSON: 
   ```JSON
       {
         "flat" : 2,
          "typeGender" : "MEN",
          "typeComfort" : "STANDARD",
          "numberOfSeats" : 2
       }
  ```
## Фильтрация по типу комнаты (М, Ж)
  - метод GET
  - URL "/room-read-all/gender/{gender}"
    - gender : MEN, WOMEN  
          
   \- Пример ответного JSON: 
   ```JSON
[
    {
        "id": 3,
        "floor": 10,
        "flat": 25,
        "typeGender": "WOMEN",
        "typeComfort": "LUXE",
        "numberOfSeats": 3,
        "totalSeats": 3,
        "dateOfChange": "2024-02-12",
        "dateOfAddition": "2024-02-12"
    }
]
  ```
## Фильтрация по тому, есть ли свободные места
  - метод GET
  - URL "/room-read-all/available-seats" 
          
   \- Пример ответного JSON: 
   ```JSON
[
    {
        "id": 4,
        "floor": 4,
        "flat": 25,
        "typeGender": "WOMEN",
        "typeComfort": "LUXE",
        "numberOfSeats": 3,
        "totalSeats": 3,
        "dateOfChange": "2024-02-12",
        "dateOfAddition": "2024-02-12"
    },
    {
        "id": 5,
        "floor": 6,
        "flat": 30,
        "typeGender": "MEN",
        "typeComfort": "DE_LUXE",
        "numberOfSeats": 4,
        "totalSeats": 4,
        "dateOfChange": "2024-02-13",
        "dateOfAddition": "2024-02-13"
    }
]
  ```
## Фильтрация по типу комфорта
  - метод GET
  - URL "/room-read-all/comfort/{typeComfort}"
    - typeComfort : тип комфорта (STANDARD, DE_LUXE, LUXE) 
          
   \- Пример ответного JSON: 
   ```JSON
[
    {
        "id": 1,
        "floor": 7,
        "flat": 23,
        "typeGender": "WOMEN",
        "typeComfort": "LUXE",
        "numberOfSeats": 2,
        "totalSeats": 2,
        "dateOfChange": "2024-02-12",
        "dateOfAddition": "2024-02-12"
    },
    {
        "id": 2,
        "floor": 2,
        "flat": 5,
        "typeGender": "WOMEN",
        "typeComfort": "LUXE",
        "numberOfSeats": 6,
        "totalSeats": 6,
        "dateOfChange": "2024-02-13",
        "dateOfAddition": "2024-02-13"
    }
]
  ```
## Удаление комнаты
  - метод DELETE
  - URL "/rooms/{numFlat}"
     - numFlat : номер комнаты
     - Комнату можно удалить, только если в ней нет постояльцев
