
    create table country (
       id  serial not null,
        country_name varchar(255),
        number_of_days_to_issue_sim int4,
        primary key (id)
    );

    create table customer_sim_details (
       id  serial not null,
        is_document_upload boolean,
        payment_status varchar(255),
        flight_id int4,
        sim_id int4,
        primary key (id)
    );

    create table document (
       id  serial not null,
        document_name varchar(255),
        country_id int4,
        primary key (id)
    );

    create table flight_information (
       id  serial not null,
        destination_from varchar(255),
        destination_to varchar(255),
        end_time timestamp,
        pnr_no varchar(255),
        start_time timestamp,
        country_from int4,
        country_to int4,
        user_id int4,
        primary key (id)
    );

    create table sim_details (
       id  serial not null,
        company_name varchar(255),
        is_data_available boolean,
        number_of_days int4,
        package_details varchar(255),
        package_price int8,
        country_id int4,
        primary key (id)
    );

    create table user_information (
       id  serial not null,
        name varchar(255),
        phone_number varchar(255),
        primary key (id)
    );

    alter table customer_sim_details
       add constraint FKcdw7mid9dyq366d3tpccd5oxp
       foreign key (flight_id)
       references flight_information;

    alter table customer_sim_details
       add constraint FK448b7nubftad5ugegbrqhadb2
       foreign key (sim_id)
       references sim_details;


    alter table document
       add constraint FKchu8sfgaej2j8g29e6aywhtqg
       foreign key (country_id)
       references country;

    alter table flight_information
       add constraint FK1u94gj9gdeivqi8x0yae5g711
       foreign key (country_from)
       references country;

    alter table flight_information
       add constraint FKq5cieheigbcndbhkb2xl3luxv
       foreign key (country_to)
       references country;

    alter table flight_information
       add constraint FKf5bhi601p11p6f8w89s4ml1yy
       foreign key (user_id)
       references user_information;

    alter table sim_details
       add constraint FKmcvhiamt93t14w5qp5vt8to95
       foreign key (country_id)
       references country;

