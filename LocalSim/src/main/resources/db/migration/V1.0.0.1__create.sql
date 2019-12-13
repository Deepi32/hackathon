
INSERT INTO user_information (id,name,phone_number) VALUES
(1,'Ram','1234567890')
,(2,'Shyam','1234567890');

INSERT INTO country (id,country_name,number_of_days_to_issue_sim) VALUES
(1,'Indonesia',1)
,(2,'India',1)
;

INSERT INTO flight_information (id,destination_from,destination_to,end_time,booking_id,start_time,country_from,country_to,user_id) VALUES
(1,'Delhi','Bali',NULL,'abcd',NULL,2,1,1)
;

INSERT INTO document (id,document_name,country_id,document_info) VALUES
(1,'Passport',1,'Please upload image of your passport')
,(2,'Aadhar card',1,'Please upload Aadhar card')
,(3,'Your Picture',1,'Please upload your picture')
;

INSERT INTO sim_details (id, available_data_volume, data_speed, is_data_available, number_of_days, operators, package_details, package_price, country_id ) VALUES
(1,'20GB','3G/4G',true,10, 'Airtel','edfewfe',250,1)
,(2,'20GB','3G',true,10,'Airtel','edfewfe',250,1)
,(3,'20GB','2G',true,10,'Airtel','edfewfe',250,1)
,(4,'20GB','4G',true,10,'Airtel','edfewfe',250,2)
,(5,'20GB','3G',true,10,'Airtel','edfewfe',250,2)
,(6,'20GB','3G',true,10,'Airtel','edfewfe',250,2)
;

INSERT INTO customer_details (id, is_document_upload,payment_amount,payment_status,destination_to,flight_id,sim_id,user_id, is_on_arrival_visa) VALUES
(1,false,NULL,'PAID',1,1,3,NULL, false)
;