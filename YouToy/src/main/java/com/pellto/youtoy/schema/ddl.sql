# user 테이블 생성
create table User
(
    id        int auto_increment,
    email     varchar(32) not null,
    pwd       varchar(32) not null,
    createdAt datetime    not null,
    constraint user_id_uindex
        primary key (id)
);


# channel 테이블 생성
create table Channel
(
    id          int auto_increment,
    ownerId     int          not null,
    handle      varchar(32)  not null,
    displayName varchar(32)  not null,
    description varchar(512) not null,
    banner      varchar(512) not null,
    profile     varchar(512) not null,
    createdAt   datetime     not null,
    constraint channel_id_uindex
        primary key (id)
);


# video 테이블 생성
create table Video
(
    id          int auto_increment,
    channelId   int          not null,
    title       varchar(32)  not null,
    viewCount   int          not null,
    description varchar(512) not null,
    createdAt   datetime     not null,
    constraint video_id_uindex
        primary key (id)
);

# Subscribe Table 생성
create table Subscribe
(
    id        int auto_increment,
    channelId int      not null,
    userId    int      not null,
    createdAt datetime not null,
    constraint subscribe_id_uindex
        primary key (id)
);

# user Info 추가
## 생년월일 컬럼 추가
ALTER TABLE User
    ADD birthDate datetime AFTER pwd;
## 이름 컬럼 추가
ALTER TABLE User
    ADD name varchar(32) AFTER pwd;

# short 테이블 추가
create table Short
(
    id          int auto_increment,
    channelId   int          not null,
    title       varchar(32)  not null,
    viewCount   int          not null,
    description varchar(512) not null,
    createdAt   datetime     not null,
    constraint Short_id_uindex
        primary key (id)
);

# 채널 어드민 관계 테이블 추가
create table ChannelAdmin
(
    id        int auto_increment,
    channelId int      not null,
    userId    int      not null,
    createdAt datetime not null,
    constraint ChannelAdmin_id_uindex
        primary key (id)
);