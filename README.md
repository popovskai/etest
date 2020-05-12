# etest
Ивана Поповска 163012 - Проект Веб програмирање 2019/20
Упатство за стартување на апликацијата:
- Преземање локално на etest repository
- Отворање и run на spring апликацијата (преку фајлот resources/data.sql при првиот run се креира user-от со

{
	"username": "user@test.com",
	"password": "password",
	"confirmPassword": "password",
	"fullName": "Test User"
}

endpoint за регистрација на корисник е http://localhost:8080/api/users/register 

json => { 
"username": "user@test.com",
	"password": "password",
	"confirmPassword": "password",
	"fullName": "Test User" }
	
- Oтворање на react апликацијата
- npm install преку командна линија 
- npm start  преку командна линија
- Бидејки е имплементиран spring security, за користење на функционалностите на апликацијата, задолжителна е најава со e-mail адреса user@test.com и лозинка password
