--Amenities
insert into Amenity values
(1, 'Lift', 'Lift');
insert into Amenity  values
(2,'Swimming pool', 'Swimming pool');
insert into Amenity  values
(3,'Internet Connection', 'Internet Connection');
insert into Amenity values
(4, 'Parking', 'Parking');
insert into Amenity values
(5,'Kitchen', 'Kitchen');

--Hotel
insert into Hotel
(HOTEL_ID, HOTEL_NAME, HOTEL_DESCRIPTION, HOUSE_NUMBER, STREET, CITY, STATE, ZIPCODE, COUNTRY, STATUS, RATING) 
values
(1, 'Sayaji', 'Sayaji Hotels are a group of luxury 5-star hotels across India.', '130, Vijay Nagar',
'Nagar Road', 'Indore', 'Madhya Pradesh', '452010', 'India', TRUE, 5);

insert into Hotel
(HOTEL_ID, HOTEL_NAME, HOTEL_DESCRIPTION, HOUSE_NUMBER, STREET, CITY, STATE, ZIPCODE, COUNTRY, STATUS, RATING) 
values
(2, 'Radisson Hotels', 'Find a Natural Balance with Work and Play, When You Stay at Radisson Hotels.', '12, Ring Rd,',
'Scheme 94', 'Indore', 'Madhya Pradesh', '452010', 'India', TRUE, 4.5);

insert into Hotel
(HOTEL_ID, HOTEL_NAME, HOTEL_DESCRIPTION, HOUSE_NUMBER, STREET, CITY, STATE, ZIPCODE, COUNTRY, STATUS, RATING) 
values
(3, 'Novotel', 'Novotel is a midscale hotel brand focused on modern, natural and intuitive design.', 'Weikfield It city Infopark',
'Nagar Road', 'Pune', 'Maharashtra', '411014', 'India', TRUE, 5);

insert into Hotel
(HOTEL_ID, HOTEL_NAME, HOTEL_DESCRIPTION, HOUSE_NUMBER, STREET, CITY, STATE, ZIPCODE, COUNTRY, STATUS, RATING) 
values
(4, 'Hyatt', 'Modern rooms & suites in a polished, high-end hotel with 2 restaurants, a spa & an outdoor pool.', 'Weikfield It city Infopark',
'Nagar Road', 'Pune', 'Maharashtra', '411014', 'India', TRUE, 5);

--HOTEL_AMENITIES
insert into HOTEL_AMENITIES(HOTEL_ID, AMENITY_ID) values (1 , 1);
insert into HOTEL_AMENITIES(HOTEL_ID, AMENITY_ID) values (1 , 2);
insert into HOTEL_AMENITIES(HOTEL_ID, AMENITY_ID) values (1 , 3);
insert into HOTEL_AMENITIES(HOTEL_ID, AMENITY_ID) values (1 , 4);

insert into HOTEL_AMENITIES(HOTEL_ID, AMENITY_ID) values (2 , 2);
insert into HOTEL_AMENITIES(HOTEL_ID, AMENITY_ID) values (2 , 3);
insert into HOTEL_AMENITIES(HOTEL_ID, AMENITY_ID) values (2 , 4);
insert into HOTEL_AMENITIES(HOTEL_ID, AMENITY_ID) values (2 , 5);

insert into HOTEL_AMENITIES(HOTEL_ID, AMENITY_ID) values (3 , 1);
insert into HOTEL_AMENITIES(HOTEL_ID, AMENITY_ID) values (3 , 3);
insert into HOTEL_AMENITIES(HOTEL_ID, AMENITY_ID) values (3 , 5);

insert into HOTEL_AMENITIES(HOTEL_ID, AMENITY_ID) values (4 , 2);
insert into HOTEL_AMENITIES(HOTEL_ID, AMENITY_ID) values (4 , 4);
insert into HOTEL_AMENITIES(HOTEL_ID, AMENITY_ID) values (4 , 5);

--Room
insert into room(ROOM_ID, ROOM_NAME, ROOM_DESCRIPTION, STATUS, HOTEL_ID)
values
(1, 'Pool View King', 'Pool View King', TRUE, 1);

insert into room(ROOM_ID, ROOM_NAME, ROOM_DESCRIPTION, STATUS, HOTEL_ID)
values
(2, 'Deluxe King', 'Deluxe King', TRUE, 1);

insert into room(ROOM_ID, ROOM_NAME, ROOM_DESCRIPTION, STATUS, HOTEL_ID)
values
(3, 'Business Suite Room', 'Business Suite Room', TRUE, 2);

insert into room(ROOM_ID, ROOM_NAME, ROOM_DESCRIPTION, STATUS, HOTEL_ID)
values
(4, 'Superior Room', 'Superior Room', TRUE, 2);

insert into room(ROOM_ID, ROOM_NAME, ROOM_DESCRIPTION, STATUS, HOTEL_ID)
values
(5, 'Presidential Room', 'Presidential Room', TRUE, 3);

insert into room(ROOM_ID, ROOM_NAME, ROOM_DESCRIPTION, STATUS, HOTEL_ID)
values
(6, 'Queen Bed Room', 'Queen Bed Room', TRUE, 3);

insert into room(ROOM_ID, ROOM_NAME, ROOM_DESCRIPTION, STATUS, HOTEL_ID)
values
(7, 'Standard Delux Room', 'Standard Delux Room', TRUE,4);

insert into room(ROOM_ID, ROOM_NAME, ROOM_DESCRIPTION, STATUS, HOTEL_ID)
values
(8, 'Classic Delux Room', 'Classic Delux Room', TRUE, 4);

--Inventory
insert into inventory(INVENTORY_ID, STAY_DATE, QUANTITY, PRICE, ROOM_ID)
values
(1, '2021-02-01', 5, 500, 1);
insert into inventory(INVENTORY_ID, STAY_DATE, QUANTITY, PRICE, ROOM_ID)
values
(2, '2021-02-02', 5, 500, 1);
insert into inventory(INVENTORY_ID, STAY_DATE, QUANTITY, PRICE, ROOM_ID)
values
(3, '2021-02-03', 5, 500, 1);
insert into inventory(INVENTORY_ID, STAY_DATE, QUANTITY, PRICE, ROOM_ID)
values
(4, '2021-02-04', 5, 500, 1);
insert into inventory(INVENTORY_ID, STAY_DATE, QUANTITY, PRICE, ROOM_ID)
values
(5, '2021-02-05', 5, 500, 1);
insert into inventory(INVENTORY_ID, STAY_DATE, QUANTITY, PRICE, ROOM_ID)
values
(6, '2021-02-01', 5, 450, 2);
insert into inventory(INVENTORY_ID, STAY_DATE, QUANTITY, PRICE, ROOM_ID)
values
(7, '2021-02-02', 5, 450, 2);
insert into inventory(INVENTORY_ID, STAY_DATE, QUANTITY, PRICE, ROOM_ID)
values
(8, '2021-02-03', 5, 450, 2);
insert into inventory(INVENTORY_ID, STAY_DATE, QUANTITY, PRICE, ROOM_ID)
values
(9, '2021-02-04', 5, 450, 2);
insert into inventory(INVENTORY_ID, STAY_DATE, QUANTITY, PRICE, ROOM_ID)
values
(10, '2021-02-05', 5, 450, 2);
insert into inventory(INVENTORY_ID, STAY_DATE, QUANTITY, PRICE, ROOM_ID)
values
(11, '2021-02-01', 5, 500, 3);
insert into inventory(INVENTORY_ID, STAY_DATE, QUANTITY, PRICE, ROOM_ID)
values
(12, '2021-02-02', 5, 500, 3);
insert into inventory(INVENTORY_ID, STAY_DATE, QUANTITY, PRICE, ROOM_ID)
values
(13, '2021-02-03', 5, 500, 3);
insert into inventory(INVENTORY_ID, STAY_DATE, QUANTITY, PRICE, ROOM_ID)
values
(14, '2021-02-04', 5, 500, 3);
insert into inventory(INVENTORY_ID, STAY_DATE, QUANTITY, PRICE, ROOM_ID)
values
(15, '2021-02-05', 5, 500, 3);
insert into inventory(INVENTORY_ID, STAY_DATE, QUANTITY, PRICE, ROOM_ID)
values
(16, '2021-02-01', 5, 450, 4);
insert into inventory(INVENTORY_ID, STAY_DATE, QUANTITY, PRICE, ROOM_ID)
values
(17, '2021-02-02', 5, 450, 4);
insert into inventory(INVENTORY_ID, STAY_DATE, QUANTITY, PRICE, ROOM_ID)
values
(18, '2021-02-03', 5, 450, 4);
insert into inventory(INVENTORY_ID, STAY_DATE, QUANTITY, PRICE, ROOM_ID)
values
(19, '2021-02-04', 5, 450, 4);
insert into inventory(INVENTORY_ID, STAY_DATE, QUANTITY, PRICE, ROOM_ID)
values
(20, '2021-02-05', 5, 450, 4);
insert into inventory(INVENTORY_ID, STAY_DATE, QUANTITY, PRICE, ROOM_ID)
values
(21, '2021-02-01', 5, 500, 5);
insert into inventory(INVENTORY_ID, STAY_DATE, QUANTITY, PRICE, ROOM_ID)
values
(22, '2021-02-02', 5, 500, 5);
insert into inventory(INVENTORY_ID, STAY_DATE, QUANTITY, PRICE, ROOM_ID)
values
(23, '2021-02-03', 5, 500, 5);
insert into inventory(INVENTORY_ID, STAY_DATE, QUANTITY, PRICE, ROOM_ID)
values
(24, '2021-02-04', 5, 500, 5);
insert into inventory(INVENTORY_ID, STAY_DATE, QUANTITY, PRICE, ROOM_ID)
values
(25, '2021-02-05', 5, 500, 5);
insert into inventory(INVENTORY_ID, STAY_DATE, QUANTITY, PRICE, ROOM_ID)
values
(26, '2021-02-01', 5, 450, 6);
insert into inventory(INVENTORY_ID, STAY_DATE, QUANTITY, PRICE, ROOM_ID)
values
(27, '2021-02-02', 5, 450, 6);
insert into inventory(INVENTORY_ID, STAY_DATE, QUANTITY, PRICE, ROOM_ID)
values
(28, '2021-02-03', 5, 450, 6);
insert into inventory(INVENTORY_ID, STAY_DATE, QUANTITY, PRICE, ROOM_ID)
values
(29, '2021-02-04', 5, 450, 6);
insert into inventory(INVENTORY_ID, STAY_DATE, QUANTITY, PRICE, ROOM_ID)
values
(30, '2021-02-05', 5, 450, 6);
insert into inventory(INVENTORY_ID, STAY_DATE, QUANTITY, PRICE, ROOM_ID)
values
(31, '2021-02-01', 5, 500, 7);
insert into inventory(INVENTORY_ID, STAY_DATE, QUANTITY, PRICE, ROOM_ID)
values
(32, '2021-02-02', 5, 500, 7);
insert into inventory(INVENTORY_ID, STAY_DATE, QUANTITY, PRICE, ROOM_ID)
values
(33, '2021-02-03', 5, 500, 7);
insert into inventory(INVENTORY_ID, STAY_DATE, QUANTITY, PRICE, ROOM_ID)
values
(34, '2021-02-04', 5, 500, 7);
insert into inventory(INVENTORY_ID, STAY_DATE, QUANTITY, PRICE, ROOM_ID)
values
(35, '2021-02-05', 5, 500, 7);
insert into inventory(INVENTORY_ID, STAY_DATE, QUANTITY, PRICE, ROOM_ID)
values
(36, '2021-02-01', 5, 450, 8);
insert into inventory(INVENTORY_ID, STAY_DATE, QUANTITY, PRICE, ROOM_ID)
values
(37, '2021-02-02', 5, 450, 8);
insert into inventory(INVENTORY_ID, STAY_DATE, QUANTITY, PRICE, ROOM_ID)
values
(38, '2021-02-03', 5, 450, 8);
insert into inventory(INVENTORY_ID, STAY_DATE, QUANTITY, PRICE, ROOM_ID)
values
(39, '2021-02-04', 5, 450, 8);
insert into inventory(INVENTORY_ID, STAY_DATE, QUANTITY, PRICE, ROOM_ID)
values
(40, '2021-02-05', 5, 450, 8);