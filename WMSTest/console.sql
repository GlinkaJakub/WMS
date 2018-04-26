create table ProductGroup(
id_productgroup int not null identity
constraint pk_productgroup primary key
, name varchar(20) not null
)
create table Product(
id_product int not null identity
constraint pk_product primary key
, name varchar(50) not null
, group int null
constraint fk_productgroup_product foreign key
references ProductGroup(id_productgroup)
, maker varchar(50)  not null
, width int null
, height int null
, lenght int null
, weight int null
)
create table Provider(
id_provider int not null identity
constraint pk_provider primary key
, name varchar(100) not null
, nip char(10) not null
, city varchar(30)  null
, postcode char(6) null
, street varchar(40) null
, buildingnumber varchar(9) null
, phonenumber char(11) null
, email varchar(20) null

)
create table Client(
id_client int not null identity
constraint pk_client primary key
, name varchar(100) not null
, city varchar(30)  null
, postcode char(6) null
, street varchar(40) null
, buildingnumber varchar(9) null
, phonenumber char(11) null
, email varchar(20) null
)
create table Sector(
id_sector int not null identity
  constraint pk_sector primary key
, remainingspace int not null
, totalspace int not null
)
create table RackType(
id_racktype int not null
constraint pk_racktype primary key
, width int not null
, lenght int not null
, shelfnumber int not null
)
create table Rack(
id_rack int not null identity
constraint pk_rack primary key
, id_sector int not null
  constraint fk_rack_sector foreign key
  references Sector(id_sector)
, id_racktype int not null
  constraint fk_rack_rackype foreign key
  references RackType(id_racktype)
, remainingspace int not null
, totalspace int not null
)
create table Shelf(
id_shelf int not null identity
  constraint pk_shelf primary key
, id_rack int not null
  constraint fk_shelf_rack foreign key
  references Rack(id_rack)
, remainingspace int not null
, totalspace int not null
)
create table ProductCard(
id_card int not null identity
constraint pk_card primary key
, id_product int not null
  constraint fk_productcard_product foreign key
  references Product(id_product)
, placement int null
)

//dostawa wysylka
//provider/client



create table LedgerEntry(
id_product int not null
  constraint fk_productcard_ledgerentry foreign key
  references Product(id_product)
, date date not null
, inout int not null
, id_provider int null
  constraint fk_ledger_provider foreign key
  references Provider(id_provider)
, id_client int null
  constraint fk_provider foreign key
  references Client(id_client)
, placement int null
)

create procedure PutONShelf(@place int not null, @product int, )
as
    insert into ProductCard values (@product,@place)

    update Shelf
    set remainingspace=(remainingspace+1)
    where id_shelf=@place

go

create procedure AddProvider(@name varchar(100), @nip char(10),@city varchar(30)='', @postcode char(6)='', @street varchar(40)='', @buildingnumber varchar(9)='', @phonenumber char(11)='', @email varchar(20)='')
as
  insert into Provider values (@name,@nip,@city,@postcode,@street,@buildingnumber,@phonenumber,@email)
go


create procedure NewProduct(@name varchar(50), @group varchar(20)='', @maker varchar(50), @width int, @height int, @lenght int, @weight int)
as
    if @group is not null
    begin
        declare @id_group
        select @id_group=g.name from ProductGroup g
        where g.name=@group
        insert into Product values (@name,@id_group,@maker,@width,@height,@lenght,@weight)
    end
    else
    insert into Product values (@name,'',@maker,@width,@height,@lenght,@weight)
go

create procedure getProduct(@id int )
as
    select p.id_product,g.name,p.maker,p.width,p.lenght,p.height,p.weight from Product p
    join ProductGroup g on (g.id_productgroup=p.group)
    where id_product=@id
go

create procedure getProductCard(@id int)
as
    select * from ProductCard
    where id_card=@id
go
