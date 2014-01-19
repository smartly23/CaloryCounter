insert into ACTIVITY ("NAME") values ('Badminton');
insert into CALORIES (AMOUNT, WEIGHTCAT, ACTIVITY_ID) values (266, '_130_', 1);
insert into CALORIES (AMOUNT, WEIGHTCAT, ACTIVITY_ID) values (317, '_155_', 1);
insert into CALORIES (AMOUNT, WEIGHTCAT, ACTIVITY_ID) values (368, '_180_', 1);
insert into CALORIES (AMOUNT, WEIGHTCAT, ACTIVITY_ID) values (419, '_205_', 1);
insert into ACTIVITY ("NAME") values ('Tennis');
insert into CALORIES (AMOUNT, WEIGHTCAT, ACTIVITY_ID) values (472, '_130_', 2);
insert into CALORIES (AMOUNT, WEIGHTCAT, ACTIVITY_ID) values (563, '_155_', 2);
insert into CALORIES (AMOUNT, WEIGHTCAT, ACTIVITY_ID) values (654, '_180_', 2);
insert into CALORIES (AMOUNT, WEIGHTCAT, ACTIVITY_ID) values (745, '_205_', 2);
insert into ACTIVITY ("NAME") values ('Aerobics');
insert into CALORIES (AMOUNT, WEIGHTCAT, ACTIVITY_ID) values (384, '_130_', 3);
insert into CALORIES (AMOUNT, WEIGHTCAT, ACTIVITY_ID) values (457, '_155_', 3);
insert into CALORIES (AMOUNT, WEIGHTCAT, ACTIVITY_ID) values (531, '_180_', 3);
insert into CALORIES (AMOUNT, WEIGHTCAT, ACTIVITY_ID) values (605, '_205_', 3);
insert into ACTIVITY ("NAME") values ('Basketball');
insert into CALORIES (AMOUNT, WEIGHTCAT, ACTIVITY_ID) values (354, '_130_', 4);
insert into CALORIES (AMOUNT, WEIGHTCAT, ACTIVITY_ID) values (422, '_155_', 4);
insert into CALORIES (AMOUNT, WEIGHTCAT, ACTIVITY_ID) values (490, '_180_', 4);
insert into CALORIES (AMOUNT, WEIGHTCAT, ACTIVITY_ID) values (558, '_205_', 4);
insert into ACTIVITY ("NAME") values ('Bowling');
insert into CALORIES (AMOUNT, WEIGHTCAT, ACTIVITY_ID) values (177, '_130_', 5);
insert into CALORIES (AMOUNT, WEIGHTCAT, ACTIVITY_ID) values (211, '_155_', 5);
insert into CALORIES (AMOUNT, WEIGHTCAT, ACTIVITY_ID) values (245, '_180_', 5);
insert into CALORIES (AMOUNT, WEIGHTCAT, ACTIVITY_ID) values (279, '_205_', 5);
insert into ACTIVITY ("NAME") values ('Cycling');
insert into CALORIES (AMOUNT, WEIGHTCAT, ACTIVITY_ID) values (472, '_130_', 6);
insert into CALORIES (AMOUNT, WEIGHTCAT, ACTIVITY_ID) values (563, '_155_', 6);
insert into CALORIES (AMOUNT, WEIGHTCAT, ACTIVITY_ID) values (654, '_180_', 6);
insert into CALORIES (AMOUNT, WEIGHTCAT, ACTIVITY_ID) values (745, '_205_', 6);
insert into ACTIVITY ("NAME") values ('Football');
insert into CALORIES (AMOUNT, WEIGHTCAT, ACTIVITY_ID) values (531, '_130_', 7);
insert into CALORIES (AMOUNT, WEIGHTCAT, ACTIVITY_ID) values (633, '_155_', 7);
insert into CALORIES (AMOUNT, WEIGHTCAT, ACTIVITY_ID) values (735, '_180_', 7);
insert into CALORIES (AMOUNT, WEIGHTCAT, ACTIVITY_ID) values (838, '_205_', 7);

insert into AUTHUSER (AGE, GENDER, "NAME", PASSWORD, USERNAME, USERROLE, WEIGHTCAT) values (23, 'Male', 'Administrator', 'ceb4f32325eda6142bd65215f4c0f371', 'admin', 'ADMIN', '_155_');

insert into AUTHUSER (AGE, GENDER, "NAME", PASSWORD, USERNAME, USERROLE, WEIGHTCAT) values (23, 'Male', 'John Lock', 'd07b4f27dbbba2e1e65371da320a928a', 'John', 'USER', '_155_');
insert into ACTIVITYRECORD (ACTIVITYDATE, CALORIESBURNT, DURATION, AUTHUSER_ID, CALORIES_ID) VALUES ('2013-10-10', 120, 100, 2, 2);
insert into ACTIVITYRECORD (ACTIVITYDATE, CALORIESBURNT, DURATION, AUTHUSER_ID, CALORIES_ID) VALUES ('2013-11-12', 150, 120, 2, 14);

insert into AUTHUSER (AGE, GENDER, "NAME", PASSWORD, USERNAME, USERROLE, WEIGHTCAT) values (31, 'Female', 'Anna Lucia', 'a695f38d5af95608541e962f064d9905', 'Anna', 'USER', '_130_');
insert into ACTIVITYRECORD (ACTIVITYDATE, CALORIESBURNT, DURATION, AUTHUSER_ID, CALORIES_ID) VALUES ('2013-11-10', 92, 60, 3, 5);

insert into AUTHUSER (AGE, GENDER, "NAME", PASSWORD, USERNAME, USERROLE, WEIGHTCAT) values (31, 'Female', 'Petra Divoka', 'cfdf549989ee72e13ea6e032c9ec40dd', 'Petra', 'USER', '_180_');
insert into ACTIVITYRECORD (ACTIVITYDATE, CALORIESBURNT, DURATION, AUTHUSER_ID, CALORIES_ID) VALUES ('2013-11-10', 50, 36, 4, 5);

