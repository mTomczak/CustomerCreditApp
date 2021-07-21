# CustomerCreditApp

Pobranie i uruchomienie kontenerów:

W katalogu głównym projektu znajduje się plik docker-compose.yml 
docker-compose up - pobierze i uruchomi wszystkie obrazy. 


Dokumentacja dla każdego modułu w plikach nazwamodulu_doc.zip

Uruchomienie aplikacji:
Do działania aplikacji potrzebne są wszystkie trzy moduły:
- credit
- product
- customer

Aplikacje uruchamiają się na localhost na następujących portach:

- credit    localhost:8080
- product   localhost:8081
- customer  localhost:8082

Sprawdzić czy aplikacja działa można przechodząc pod endpoind /itisworking dla każdego z adresów. 
Jeśli aplikacja uruchomiła się poprawnie zostanie zwrócony String z odpowiednią informacją. 

Działanie aplikacji:

EndPoin "/createcredit" Przyjmuje parametry:
Imię
Nazwisko
Pesel
Nazwa produktu
Wartość produktu
Nazwa kredytu

Przykładowe wywołanie:
http://localhost:8080/createcredit?userFirstName=Imie&userSurname=Nazwisko&personalNumber=12345&productName=NazwaProduktu&productValue=1200021&creditName=NazwaKredytu

Zapisuje do bazy podane informacje i zwraca potwierdzenie w postaci Jonson z wprowadzonymi danymi. 

EndPoin "/getcredits" Jest bezparametrowy 

Przykładowe wywołanei 

http://localhost:8080/getcredits

Zwraca liste wszystkich kredytów wraz z informacją o kliencie i produkcie. 



