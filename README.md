# Movie Management

Desktop application written in Java. Personal RSS parses created, repository pattern, connecting to SQL and various other topics covered. Database initialization script is inside of the Cinema folder. The application itself allows CRUD operations on various entites(Movies, Actors, Directors), adding/removing actors and directors to/from a movie. </br>

Below you can find short overview of screens:

## Login / Register screen
![Login](https://user-images.githubusercontent.com/81168288/201543580-de2d8806-9276-4215-a41b-9380fbc114b6.png)

Entry point to the application, main jframe holds only the logic for login/register and switching between panels

## Admin panel
![Admin](https://user-images.githubusercontent.com/81168288/201543698-eee98065-e978-4233-b90b-77a7471041b5.png)

Admin can call RSS parser to fill the database with movies from the following link: </br>
https://web.archive.org/web/20210228003317if_/https://www.blitz-cinestar.hr/rss.aspx?najava=1

## Home panel
![Home](https://user-images.githubusercontent.com/81168288/201543771-f374a8e2-f404-4558-96b8-c956e0446be8.png)

Home panel allows user to perform CRUD operations on all entities. Adding actors to a movie is done by clicking enter after you select them in the combo box. Removing them from a movie is done by clicking delete after you select them in the list

## Edit panel
![Edit](https://user-images.githubusercontent.com/81168288/201543883-d2f3a191-5d22-4789-99c4-240a1d374612.png)

Edit panel allows user to add existing actors and set the director of a movie by doing drag and drop actions
