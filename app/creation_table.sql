DROP TABLE IF EXISTS profile;
DROP TABLE IF EXISTS save;
DROP TRIGGER IF EXISTS save_remove;
DROP TRIGGER IF EXISTS valid_date;

CREATE TABLE profile(
	id INTEGER PRIMARY KEY AUTOINCREMENT,
	name STRING NOT NULL,
	creationDate DATE
);

CREATE TABLE save(
	id INTEGER PRIMARY KEY AUTOINCREMENT,
	date DATE NOT NULL,
	faim INTEGER CHECK(faim>=0 AND faim<=100) NOT NULL,
	fatigue INTEGER CHECK(fatigue>=0 AND fatigue<=100) NOT NULL,
	toilette INTEGER CHECK (toilette>=0 AND toilette<=100) NOT NULL,
	besoins INTEGER CHECK (besoins>=0 AND besoins<=100) NOT NULL,
	profile NOT NULL,
	FOREIGN KEY (profile) REFERENCES profile(id)
);


CREATE TRIGGER save_remove
AFTER DELETE ON profile
BEGIN
	DELETE FROM save
	WHERE profile = OLD.id;
END;

CREATE TRIGGER valid_date
AFTER INSERT ON save
WHEN(NEW.date < (SELECT creationDate FROM profile WHERE id = NEW.profile))
BEGIN
		SELECT RAISE(ABORT, 'The save date can t be older than de profile creation');
END; 	

