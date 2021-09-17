delete from profile where true;
delete from save where true;

INSERT INTO profile(id,name,creationDate) VALUES(1,"Michel","2021-02-24 22:00:00");
INSERT INTO profile(id,name,creationDate) VALUES(2,"Jean","2021-09-17 19:56:00");
INSERT INTO profile(id,name,creationDate) VALUES(3,"Marc","2020-05-27 07:56:00");

INSERT INTO save(date,faim,fatigue,toilette,besoins,profile) VALUES("2021-02-25 22:00:01",0,0,0,0,1);
INSERT INTO save(date,faim,fatigue,toilette,besoins,profile) VALUES("2020-06-30 24:00:00",0,0,0,0,1);
INSERT INTO save(date,faim,fatigue,toilette,besoins,profile) VALUES("2020-05-27 07:56:00",0,0,0,0,3);

DELETE FROM profile WHERE id = 1 OR id=3;
SELECT * from save;
