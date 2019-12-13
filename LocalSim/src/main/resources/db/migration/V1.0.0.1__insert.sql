
INSERT INTO user_information (id,name,phone_number) VALUES
(1,'Ram','1234567890')
,(2,'Shyam','1234567890');

INSERT INTO country (id,country_name,number_of_days_to_issue_sim) VALUES
(1,'Indonesia',1)
,(2,'India',1)
;

INSERT INTO flight_information (id,destination_from,destination_to,end_time,pnr_no,start_time,country_from,country_to,user_id) VALUES
(1,'Delhi','Bali',NULL,'abcd',NULL,2,1,1)
;

INSERT INTO "document" (id,document_name,country_id) VALUES
(1,'Passport',1)
,(2,'Aadhar card',1)
,(3,'Your Picture',1)
;

INSERT INTO sim_details (id,company_name,is_data_available,number_of_days,package_details,package_price,country_id) VALUES
(1,NULL,true,10,'lallaalallalal',250,1)
,(2,NULL,true,10,'bababab',250,1)
,(3,NULL,true,10,'daddad',250,1)
,(4,NULL,true,10,'gagag',250,1)
,(5,NULL,true,10,'haaha',250,1)
,(6,NULL,true,10,'vavvvava',250,1)
;

INSERT INTO customer_sim_details (id,is_document_upload,payment_status,flight_id,sim_id) VALUES
(1,true,'Paid',1,3)
;