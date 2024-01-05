-- For testing
DROP DATABASE if exists houserentalsystem ;
CREATE DATABASE if NOT exists houserentalsystem;
use houserentalsystem;

create table if not exists userEntity(
	--user_id int primary key auto_increment,
    email_id varchar(30) primary key,
    name varchar(25) NOT NULL,
    password varchar(250) NOT NULL,
    role varchar(20),
    enabled boolean,
    phone_no varchar(13) NOT NULL UNIQUE,
    rating double
);

create table if not exists house(
	house_id int primary key auto_increment,
    area double NOT NULL,
    construction_year year NOT NULL,
    street varchar(100) NOT NULL,
    city varchar(20) NOT NULL,
    state varchar(15) NOT NULL,
	house_description varchar(500) NOT NULL,
    bhk int NOT NULL,
    rent int NOT NULL,
    end_date date NOT NULL,
    email_id varchar(25),
    rating double,
    isoccupied bool,
    front longblob,
    constraint fk_houseUser foreign key(email_id) references userEntity(email_id)
);

create table if not exists reviewHouse(
	email_id varchar(25),
    house_id int,
    decription varchar(200),
    rating int,
    constraint pk_reviewHouse primary key(email_id, house_id),
    constraint fk_reviewedHouse foreign key(house_id) references house(house_id) ON DELETE CASCADE,
    constraint fk_reviewedHouseByUser foreign key(email_id) references userEntity(email_id)
);

create table if not exists bookmark(
	email_id varchar(25),
    house_id int,
    constraint pk_bookmarkHouse primary key(email_id, house_id),
    constraint fk_bookmarkedHouse foreign key(house_id) references house(house_id) ON DELETE CASCADE,
    constraint fk_bookmarkedHouseByUser foreign key(email_id) references userEntity(email_id)
);

create table if not exists appointment(
	email_id varchar(25),
    house_id int,
    appoint_day date NOT NULL,
    constraint pk_appointment primary key(email_id, house_id),
    constraint fk_appointment foreign key(house_id) references house(house_id) ON DELETE CASCADE,
    constraint fk_appointmentOf foreign key(email_id) references userEntity(email_id)
);

create table if not exists slots(
	-- email_id varchar(25),
    house_id int,
	date date NOT NULL,
    constraint pk_slot primary key(house_id, date),
    constraint fk_slotHouse foreign key(house_id) references house(house_id) ON DELETE CASCADE
    -- constraint fk_slotUser foreign key(email_id) references userEntity(email_id)
);

create table if not exists image(
	image_id int auto_increment primary key,
	house_id int,
    house_image longblob,
    constraint fk_imageHouse foreign key(house_id) references house(house_id) ON DELETE CASCADE
);

create table if not exists record(
	email_id varchar(25),
    house_id int,
    trans_date date,
    constraint pk_trans primary key(email_id, house_id),
    constraint fk_transHouse foreign key(house_id) references house(house_id) ON DELETE CASCADE,
    constraint fk_transUser foreign key(email_id) references userEntity(email_id)
);

