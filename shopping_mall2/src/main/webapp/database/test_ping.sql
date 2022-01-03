create schema shopping_mall;
use shopping_mall;
create table board(
	no int primary key auto_increment,
    is_secret int not null,
    user_id varchar(20) not null,
    title varchar(50) not null,
    content varchar(1000) not null,
    views int default 0,
    regDate datetime not null,
    answer int default 0
);
select * from board;

create table comments(
	no int primary key auto_increment,
	board_no int not null,
    user_id varchar(20) not null,
    comments varchar(500) not null,
    regDate datetime not null,
    foreign key (board_no)
    references board(no) on delete cascade on update cascade
);
select * from comments;

create table users(
	id varchar(20) PRIMARY KEY,
	pw varchar(20),
    name varchar(10),
	gender varchar(10),
	PN varchar(20),
	email varchar(40),
	address varchar(80),
	regdate datetime default current_timestamp
);
select * from users;

create table clothes(
	code int primary key auto_increment not null,
    size varchar(5) not null, -- S M L XL
    name varchar(20) not null, 
    img varchar(50) not null,
    price int not null,
    cnt int not null,
    tem varchar(20) not null, -- 적정온도
    category varchar(20) not null -- 타입(분류) ex) 아우터, 상의, 하의...
);
select * from clothes;

create table reviews(
	id varchar(20) primary key not null, -- 작성자 아이디
    code int not null, -- 구매한 상품 코드
	content varchar(500) not null,
    regdate datetime default current_timestamp,
    foreign KEY (id) references users(id) on delete cascade on update cascade,
    foreign KEY (code) references clothes(code) on delete cascade on update cascade
);
select * from reviews;

create table carts(
	id varchar(20) primary key not null, -- 구매자 아이디
    code int not null, -- 상품 코드
    count int not null, -- 장바구니에 담은 수량
    foreign KEY (id) references users(id) on delete cascade on update cascade,
    foreign KEY (code) references clothes(code) on delete cascade on update cascade
);
select * from carts;

create table imgs(
	code int primary key not null,
    img1 varchar(50) not null,
    img2 varchar(50) not null,
    img3 varchar(50) not null,
    foreign KEY (code) references clothes(code) on delete cascade on update cascade
);
select * from imgs;