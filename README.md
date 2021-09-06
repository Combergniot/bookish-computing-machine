# bookish-computing-machine

Proste API pobierające dane użytkowników github-a. 

Dwie metody do pobierania informacji:

1. GET {host}/users/profile/{login} - np. http://localhost:8080/users/profile/octocat, pobiera pełen profil użytkownika
2. GET {host}/users/{login} - zwracające wybrane, podstawowe informacje + jakieś pozbawione większego sensu przeliczenia

Liczba wywołań drugiej usługi zostaje zapisane w bazie H2 w tabelu 'users'. Dostać się do niej z poziomu przeglądarki, 
po zbudowaniu pod: http://localhost:8080/h2. User 'sa', brak hasła. Dane dostępowe są zresztą zapisane w application.properties
