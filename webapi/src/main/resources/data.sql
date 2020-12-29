INSERT INTO USER_MASTER (id, first_name, last_name, email, Profile_Type) VALUES
  ('ad582f4c-f3d2-4e20-bc45-c3840904eb06', 'Marvin', 'Schoobaar', 'marraesco@gmail.com', 'Developer' ),
  ('fbfdf210-0828-4567-bb10-646238933bed', 'Marlison', 'Schoobaar', 'mw.schoobaar@gmail.com', 'Administrator'),
  ('b444d56a-fbf4-4cf2-8bb4-c19ec27976db', 'Aldrin', 'Schoobaar', 'scobaraa@gmail.com', 'Employee');

INSERT INTO ORGANISATION (org_id, organisation_name, guid, email) VALUES
  ('5b5d8428-9276-47dd-8315-a018e4e48dfa', 'Organisation 1', 'f5ea50be-a06e-41d7-9b04-a6d87bd08986', 'mailfromtestenvironment@gmail.com'),
  ('fd7b81f8-05d6-4001-a6f7-df3c68943753', 'Organisation 2', 'ae34d83e-aa86-4c5f-afaf-6a93401f1934', 'marraescoGmail@gmail.com'),
  ('7a3dc90e-80ac-49f8-a4a9-9a2eb36c5744', 'Organisation 3', '464fc701-c2ee-4c0b-9af8-54d1c7a4f0fc', 'scobaraa@gmail.com');


INSERT INTO APP_AUTH (id, application_name, guid, email) values
  ('423bfd83-f74d-47bf-ad29-090500d33743','WEBAPI','7675319d-d696-4f47-9a67-142045cb422f','mailfromtestenvironment@gmail.com'),
  ('5fc50e9a-2a0b-44f2-9380-92da1666e86e','CHATSERVER','3108fabc-5e87-4ee0-a454-e21f08e496ec','mailfromtestenvironment@gmail.com');

INSERT INTO USER_DETAIL (id, birthday, street_name, house_number, zip, city, phonenumber, profile_status, last_seen) VALUES
  ('ad582f4c-f3d2-4e20-bc45-c3840904eb06',TO_DATE('17/12/2015', 'DD/MM/YYYY'), 'Eekhoordrade', '184', '2544ve','Den haag', '0648852755', 'ONLINE', TO_DATE('17/12/2015', 'DD/MM/YYYY')),
  ('fbfdf210-0828-4567-bb10-646238933bed',TO_DATE('17/12/2015', 'DD/MM/YYYY'), 'Marlistraat','1A' ,'1234as','Zoetermeer', '0687654321', 'BUSY', TO_DATE('17/12/2015', 'DD/MM/YYYY')),
  ('b444d56a-fbf4-4cf2-8bb4-c19ec27976db',TO_DATE('17/12/2015', 'DD/MM/YYYY'), 'AldrinStraat','33B','2263vn','Pijnacker','0612345678', 'OFFLINE', TO_DATE('17/12/2015', 'DD/MM/YYYY'));

INSERT INTO AUTH (id, auth, auth_key) VALUES
  ('ad582f4c-f3d2-4e20-bc45-c3840904eb06', 'NEpT2ThhJqAk2IBvRezMWFaUecT0Cu7nWwWxO82aZ5M=', 'ab27bb66-8536-4e24-acd3-bc7520e9c144'),
  ('fbfdf210-0828-4567-bb10-646238933bed', 'NEpT2ThhJqAk2IBvRezMWFaUecT0Cu7nWwWxO82aZ5M=', 'ab27bb66-8536-4e24-acd3-bc7520e9c144'),
  ('b444d56a-fbf4-4cf2-8bb4-c19ec27976db', 'NEpT2ThhJqAk2IBvRezMWFaUecT0Cu7nWwWxO82aZ5M=', 'ab27bb66-8536-4e24-acd3-bc7520e9c144');

INSERT INTO EMMA_ROLE (row_id, role_id, user_id,organisation_id, role_name) VALUES
  (1, 1,'ad582f4c-f3d2-4e20-bc45-c3840904eb06','ad582f4c-f3d2-4e20-bc45-c3840904eb06', 'ROLE01'),
  (2, 2,'ad582f4c-f3d2-4e20-bc45-c3840904eb06','fbfdf210-0828-4567-bb10-646238933bed', 'ROLE02'),
  (3, 3,'ad582f4c-f3d2-4e20-bc45-c3840904eb06','fbfdf210-0828-4567-bb10-646238933bed', 'ROLE00'),
  (4, 1,'fbfdf210-0828-4567-bb10-646238933bed','fbfdf210-0828-4567-bb10-646238933bed', 'ROLE01'),
  (5, 1,'b444d56a-fbf4-4cf2-8bb4-c19ec27976db','b444d56a-fbf4-4cf2-8bb4-c19ec27976db', 'ROLE01');

INSERT INTO USER_MASTER_ORGANISATION (row_id, uid, org_id) VALUES
(1, 'ad582f4c-f3d2-4e20-bc45-c3840904eb06','5b5d8428-9276-47dd-8315-a018e4e48dfa'),
(2, 'fbfdf210-0828-4567-bb10-646238933bed','fd7b81f8-05d6-4001-a6f7-df3c68943753'),
(3, 'b444d56a-fbf4-4cf2-8bb4-c19ec27976db','7a3dc90e-80ac-49f8-a4a9-9a2eb36c5744');

INSERT INTO  GROUP_MEMBER (id, group_id, user_id,is_administrator, joined_on, last_seen) VALUES
(1, 'd25384c6-803a-477e-851d-8f177fbf312a', 'ad582f4c-f3d2-4e20-bc45-c3840904eb06', true, TO_DATE('17/12/2015', 'DD/MM/YYYY'), TO_DATE('17/12/2015', 'DD/MM/YYYY')),
(2, 'd25384c6-803a-477e-851d-8f177fbf312a', 'fbfdf210-0828-4567-bb10-646238933bed', true, TO_DATE('17/12/2015', 'DD/MM/YYYY'), TO_DATE('17/12/2015', 'DD/MM/YYYY')),
(3, 'd25384c6-803a-477e-851d-8f177fbf312a', 'b444d56a-fbf4-4cf2-8bb4-c19ec27976db', true, TO_DATE('17/12/2015', 'DD/MM/YYYY'), TO_DATE('17/12/2015', 'DD/MM/YYYY'));

INSERT INTO TOPIC_GROUP (id, group_id, created_by, created_on, organisation_id, group_name, is_external_accessible, is_public) VALUES
(1, 'd25384c6-803a-477e-851d-8f177fbf312a', 'Marvin Schoobaar', TO_DATE('17/12/2015', 'DD/MM/YYYY'), '5b5d8428-9276-47dd-8315-a018e4e48dfa', 'NIEUWE GROUP CHAT', true, true);

INSERT INTO MESSAGE (id , sender, send_to, send_date, read_on, body ) VALUES
(1,'ad582f4c-f3d2-4e20-bc45-c3840904eb06', 'fbfdf210-0828-4567-bb10-646238933bed', TO_DATE('17/12/2015', 'DD/MM/YYYY'),TO_DATE('17/12/2015', 'DD/MM/YYYY'),'This is the message body!'),
(2,'b444d56a-fbf4-4cf2-8bb4-c19ec27976db', 'ad582f4c-f3d2-4e20-bc45-c3840904eb06', TO_DATE('17/12/2015', 'DD/MM/YYYY'),TO_DATE('17/12/2015', 'DD/MM/YYYY'),'This is the message body!'),
(3,'fbfdf210-0828-4567-bb10-646238933bed', 'ad582f4c-f3d2-4e20-bc45-c3840904eb06', TO_DATE('17/12/2015', 'DD/MM/YYYY'),TO_DATE('17/12/2015', 'DD/MM/YYYY'),'This is the message body!'),
(4,'ad582f4c-f3d2-4e20-bc45-c3840904eb06', 'fbfdf210-0828-4567-bb10-646238933bed', TO_DATE('17/12/2015', 'DD/MM/YYYY'),TO_DATE('17/12/2015', 'DD/MM/YYYY'),'This is the message body!'),
(5,'ad582f4c-f3d2-4e20-bc45-c3840904eb06', 'fbfdf210-0828-4567-bb10-646238933bed', TO_DATE('17/12/2015', 'DD/MM/YYYY'),TO_DATE('17/12/2015', 'DD/MM/YYYY'),'This is the message body!'),
(6,'ad582f4c-f3d2-4e20-bc45-c3840904eb06', 'ad582f4c-f3d2-4e20-bc45-c3840904eb06', TO_DATE('17/12/2015', 'DD/MM/YYYY'),TO_DATE('17/12/2015', 'DD/MM/YYYY'),'This is the message body!'),
(7,'fbfdf210-0828-4567-bb10-646238933bed', 'b444d56a-fbf4-4cf2-8bb4-c19ec27976db', TO_DATE('17/12/2015', 'DD/MM/YYYY'),TO_DATE('17/12/2015', 'DD/MM/YYYY'),'This is the message body!');

INSERT INTO GROUP_MESSAGE (id , sender, send_to, send_date, read_on, body ) VALUES
(1,'ad582f4c-f3d2-4e20-bc45-c3840904eb06', 'd25384c6-803a-477e-851d-8f177fbf312a', TO_DATE('17/12/2015', 'DD/MM/YYYY'),TO_DATE('17/12/2015', 'DD/MM/YYYY'),'This is the message body!'),
(2,'fbfdf210-0828-4567-bb10-646238933bed', 'd25384c6-803a-477e-851d-8f177fbf312a', TO_DATE('17/12/2015', 'DD/MM/YYYY'),TO_DATE('17/12/2015', 'DD/MM/YYYY'),'This is the message body!'),
(3,'b444d56a-fbf4-4cf2-8bb4-c19ec27976db', 'd25384c6-803a-477e-851d-8f177fbf312a', TO_DATE('17/12/2015', 'DD/MM/YYYY'),TO_DATE('17/12/2015', 'DD/MM/YYYY'),'This is the message body!');

INSERT INTO CONVERSATION (id, user_id, send_to) VALUES
(1, 'ad582f4c-f3d2-4e20-bc45-c3840904eb06', 'fbfdf210-0828-4567-bb10-646238933bed'),
(2, 'ad582f4c-f3d2-4e20-bc45-c3840904eb06', 'b444d56a-fbf4-4cf2-8bb4-c19ec27976db'),
(3, 'fbfdf210-0828-4567-bb10-646238933bed', 'ad582f4c-f3d2-4e20-bc45-c3840904eb06'),
(4, 'fbfdf210-0828-4567-bb10-646238933bed', 'b444d56a-fbf4-4cf2-8bb4-c19ec27976db'),
(5, 'b444d56a-fbf4-4cf2-8bb4-c19ec27976db', 'ad582f4c-f3d2-4e20-bc45-c3840904eb06'),
(6, 'b444d56a-fbf4-4cf2-8bb4-c19ec27976db', 'fbfdf210-0828-4567-bb10-646238933bed');