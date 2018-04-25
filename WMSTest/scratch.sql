create table Product(
id_product int not null identity
constraint pk_product primary key
, name varchar(50) not null
, moreinfo ???

)
create table Provider(
id_provider int not null identity
constraint pk_provider primary key
, name varchar(100) not null
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
id_product int not null
  constraint fk_productcard_product foreign key
  references Product(id_product)
, placement int not null
)



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
