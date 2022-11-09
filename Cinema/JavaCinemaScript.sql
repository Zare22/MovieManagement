create database Cinema
go

use Cinema
go

create table Director
(
	IDDirector int primary key identity not null,
	FirstName nvarchar(50),
	LastName nvarchar(50)
)

go

create table Actor
(
	IDActor int primary key identity not null,
	FirstName nvarchar(50),
	LastName nvarchar(50)
)

go

create table Movie
(
	IDMovie int primary key identity not null,
	OriginalName nvarchar(50) unique,
	ImagePath nvarchar(max),
	MovieDescription nvarchar(max),
	DirectorID int foreign key references Director(IDDirector)
)

go

create table MovieActor
(
	IDMovieActor int primary key identity not null,
	MovieID int foreign key references Movie(IDMovie),
	ActorID int foreign key references Actor(IDActor)
)

go

create or alter proc updateMovie
	@IDMovie int,
	@OriginalName nvarchar(50),
	@ImagePath nvarchar(max),
	@MovieDescription nvarchar(max),
	@DirectorID int
as
begin
	update Movie
	set
		OriginalName = @OriginalName,
		ImagePath = @ImagePath,
		MovieDescription = @MovieDescription,
		DirectorID = @DirectorID
	where IDMovie = @IDMovie
end

go

create or alter proc deleteMovie
	@IDMovie int
as
begin
	delete
	from MovieActor where MovieID = @IDMovie
	delete
	from Movie where IDMovie = @IDMovie
end

go

create or alter proc selectMovies
as
begin
	select*
	from Movie
end

go

create or alter proc selectMovie
	@IDMovie int
as
begin
	select*
	from Movie
	where IDMovie = @IDMovie
end

go

create or alter proc selectMovieActors
	@IDMovie int
as
begin
	select *
	from Actor as a
	inner join MovieActor as ma on ma.ActorID = a.IDActor
	where ma.MovieID = @IDMovie
end

go

create or alter proc selectMovieDirector
	@IDMovie int
as
begin
	select*
	from Director as d
	inner join Movie as m on m.DirectorID = d.IDDirector
	where m.IDMovie = @IDMovie
end

go

create or alter proc selectDirectors
as
begin
	select*
	from Director
	order by LastName
end

go

create or alter proc createMovieAndDirector
	@OriginalName nvarchar(50),
	@ImagePath nvarchar(max),
	@MovieDescription nvarchar(max),
	@DirectorFirstName nvarchar(50),
	@DirectorLastName nvarchar(50),
	@IDDirector int output,
	@IDMovie int output
as
begin
	if exists
	(
		select 1
		from Director
		where @DirectorFirstName + @DirectorLastName = FirstName + LastName
	)
		begin
			declare @OldDirectorID int
			select @OldDirectorID = IDDirector
			from Director
			where @DirectorFirstName + @DirectorLastName = FirstName + LastName
			set @IDDirector = SCOPE_IDENTITY()
			
			if not exists
			(
				select 1
				from Movie
				where @OriginalName = OriginalName
			)

				insert into Movie
				values
				(
					@OriginalName,
					@ImagePath,
					@MovieDescription,
					@OldDirectorID
				)
				set @IDMovie = SCOPE_IDENTITY()
		end
	else
		begin
			declare @newDirectordId int
			insert into Director
			values(@DirectorFirstName, @DirectorLastName)
			set @IDDirector = SCOPE_IDENTITY()

			if not exists
			(
				select 1
				from Movie
				where @OriginalName = OriginalName
			)

				insert into Movie
				values
				(
					@OriginalName,
					@ImagePath,
					@MovieDescription,
					@IDDirector
				)
				set @IDMovie = SCOPE_IDENTITY()
		end
end

go

create or alter proc deleteMovieActor
	@MovieID int
as
begin
	delete
	from MovieActor
	where @MovieID = MovieID
end

go

create or alter proc createActorsForMovie
	@FirstName nvarchar(50),
	@LastName nvarchar(50),
	@OriginalName nvarchar(50)
as
begin
declare @ExistingActorID int
declare @NewActorID int
declare @MovieID int
	if exists
	(
		select 1
		from Actor
		where FirstName + LastName = @FirstName + @LastName
	)
		begin
			
			select @ExistingActorID = IDActor
			from Actor
			where FirstName + LastName = @FirstName + @LastName

			select @MovieID = IDMovie
			from Movie
			where OriginalName = @OriginalName

			insert into MovieActor
			values(@MovieID, @ExistingActorID)
		end
	else
		begin
			insert into Actor
			values(@FirstName, @LastName)
			set @NewActorID = SCOPE_IDENTITY()

			select @MovieID = IDMovie
			from Movie
			where OriginalName = @OriginalName

			insert into MovieActor
			values(@MovieID, @NewActorID)
		end
end

go

create or alter proc selectActors
as
begin
	select*
	from Actor
	order by LastName
end

go

create table CinemaUser
(
	IDUser int primary key identity not null,
	Username nvarchar(50) unique not null,
	PasswordHash binary(64) not null,
	isAdmin bit not null
)

go

create or alter proc createNewUser
	@Username nvarchar(50),
	@Password nvarchar(50),
	@Response nvarchar(max) output
as
begin
	begin try
		declare @isAdmin bit = 0
		insert into CinemaUser
		values(@Username, HASHBYTES('SHA2_512', @Password), @isAdmin)

		set @Response = 'You have created your account successfully!'
	end try
	begin catch
		set @Response = ERROR_MESSAGE()
	end catch
end

go

create or alter proc createNewAdmin
	@Username nvarchar(50),
	@Password nvarchar(50),
	@Response nvarchar(max) output
as
begin
	begin try
		declare @isAdmin bit = 1
		insert into CinemaUser
		values(@Username, HASHBYTES('SHA2_512', @Password), @isAdmin)

		set @Response = 'You have created your account successfully!'
	end try
	begin catch
		set @Response = ERROR_MESSAGE()
	end catch
end
go
declare @Username nvarchar(50) = 'Admin',
		@Password nvarchar(50) = 'Pa$$w0rd',
		@response nvarchar(max)
exec createNewAdmin @UserName, @Password, @response output

go

create or alter proc checkUserCredentials
	@Username nvarchar(50),
	@Password nvarchar(50),
	@isSuccess bit output,
	@Response nvarchar(max) output,
	@isAdmin bit output
as
begin
	
	if exists
	(
		select 1
		from CinemaUser
		where Username = @Username and PasswordHash = HASHBYTES('SHA2_512', @Password)
	)
	begin
		select @isAdmin = isAdmin
		from CinemaUser
		where Username = @Username and PasswordHash = HASHBYTES('SHA2_512', @Password)
		set @isSuccess = 1
		set @Response = 'You have logged in successfully'
	end
	else
	begin
		set @isAdmin = 0
		set @isSuccess = 0
		set @Response = 'Wrong credentials'
	end
end

go

create or alter proc deleteAllData
as
begin
	delete from MovieActor
	delete from Actor
	delete from Movie
	delete from Director
end

go

create or alter proc createNewPerson
	@FirstName nvarchar(50),
	@LastName nvarchar(50),
	@Type bit
as
begin
	if @Type = 1
	begin
		insert into Actor
		values(@FirstName, @LastName)
	end
	else
	begin
		insert into Director
		values(@FirstName, @LastName)
	end
end

go

create or alter proc updatePerson
	@IDPerson int,
	@FirstName nvarchar(50),
	@LastName nvarchar(50),
	@Type bit
as
begin
	if @Type = 1
	begin
		update Actor
		set FirstName = @FirstName, LastName = @LastName
		where IDActor = @IDPerson
	end
	else
	begin
		update Director
		set FirstName = @FirstName, LastName = @LastName
		where IDDirector = @IDPerson
	end
end

go

create or alter proc deletePerson
	@IDPerson int,
	@Type bit
as
begin
	if @Type = 1
	begin
		delete
		from Actor
		where IDActor = @IDPerson
		delete
		from MovieActor
		where ActorID = @IDPerson
	end
	else
	begin
		delete
		from Director
		where IDDirector = @IDPerson
		update Movie
		set DirectorID = null
		where DirectorID = @IDPerson
	end
end

go

create or alter proc addSinglePersonToMovie
	@IDPerson int,
	@MovieID int,
	@Type bit,
	@Response nvarchar(50) output
as
begin
	if @Type = 1
		begin
			if not exists
			(
				select 1
				from MovieActor
				where MovieID = @MovieID and ActorID = @IDPerson
			)
			begin
				insert into MovieActor
				values(@MovieID, @IDPerson)
				set @Response = 'Actor was added to movie'
			end
			else
				begin
				set @Response = 'Actor is already part of that movie'
				end
		end
	else
	begin
		update Movie
		set DirectorID = @IDPerson
		where IDMovie = @MovieID
		set @Response = 'Director is set as selected movie director'
	end
end