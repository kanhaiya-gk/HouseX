create table if not exists user(
    user_id int primary key auto_increment,
    name varchar(25),
    phone_no varchar(10),
    email_id varchar(25),
	password varchar(25),
    rating double
);

create table if not exists house(
	house_id int primary key auto_increment,
    area double,
    construction_year year,
    street varchar(100),
    city varchar(20),
    state varchar(15),
	house_description varchar(200),
    bhk int,
    rent int,
    end_date date,
    user_id int,
    isoccupied bool,
    front longblob,
    constraint fk_houseUser foreign key(user_id) references user(user_id)
);

create table if not exists reviewHouse(
	user_id int,
    house_id int,
    decription varchar(200),
    rating int,
    constraint pk_reviewHouse primary key(user_id, house_id),
    constraint fk_reviewedHouse foreign key(house_id) references house(house_id),
    constraint fk_reviewedHouseByUser foreign key(user_id) references user(user_id)
);

create table if not exists bookmark(
	user_id int,
    house_id int,
    constraint pk_bookmarkHouse primary key(user_id, house_id),
    constraint fk_bookmarkedHouse foreign key(house_id) references house(house_id),
    constraint fk_bookmarkedHouseByUser foreign key(user_id) references user(user_id)
);

create table if not exists appointment(
	user_id int,
    house_id int,
    appoint_day date,
    constraint pk_appointment primary key(user_id, house_id),
    constraint fk_appointment foreign key(house_id) references house(house_id),
    constraint fk_appointmentOf foreign key(user_id) references user(user_id)
);

create table if not exists slots(
	user_id int,
    house_id int,
	slot date,
    constraint pk_slot primary key(user_id, house_id),
    constraint fk_slotHouse foreign key(house_id) references house(house_id),
    constraint fk_slotUser foreign key(user_id) references user(user_id)
);

create table if not exists image(
	image_id int auto_increment primary key,
	house_id int,
    house_image longblob,
    constraint fk_imageHouse foreign key(house_id) references house(house_id)
);

create table if not exists record(
	user_id int,
    house_id int,
    trans_date date,
    constraint pk_trans primary key(user_id, house_id),
    constraint fk_transHouse foreign key(house_id) references house(house_id),
    constraint fk_transUser foreign key(user_id) references user(user_id)
);

