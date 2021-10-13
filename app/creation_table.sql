DROP TABLE IF EXISTS profile;
DROP TABLE IF EXISTS save;
DROP TABLE IF EXISTS attribute;
DROP TABLE IF EXISTS config;
DROP TRIGGER IF EXISTS save_remove;
DROP TRIGGER IF EXISTS valid_date_insert;
DROP TRIGGER IF EXISTS valid_date_update;
DROP TRIGGER IF EXISTS attrib_remove;
DROP TRIGGER IF EXISTS profile_replacer_update;
DROP TRIGGER IF EXISTS profile_replacer_insert;
DROP TRIGGER IF EXISTS config_remover;

CREATE TABLE profile(
	slot INTEGER PRIMARY KEY NOT NULL CHECK(slot=0 OR slot=1 OR slot=2),
	type TEXT NOT NULL CHECK(type='Chien' OR type='Chat' OR type='Lapin' OR type='Robot'),
	sex BOOLEAN NOT NULL,
	name TEXT NOT NULL,
	creationDate DATE
);

CREATE TABLE save(
	saveID INTEGER PRIMARY KEY AUTOINCREMENT ,
	date TEXT NOT NULL,
	location TEXT NOT NULL,
	mood TEXT NOT NULL CHECK(mood ='VERY_BAD' OR mood='BAD' or mood='GOOD' OR mood='VERY_GOOD'),
    shape TEXT NOT NULL CHECK(shape ='VERY_BAD' OR shape='BAD' or shape='GOOD' OR shape='VERY_GOOD'),
    current TEXT NOT NULL CHECK(current = 'AWAKE' OR current='ASLEEP' OR current = 'DEAD'),
	profile NOT NULL,
	FOREIGN KEY (profile) REFERENCES profile(slot)
);

CREATE TABLE attribute(
    id INTEGER PRIMARY KEY AUTOINCREMENT ,
    name TEXT NOT NULL,
    value INTEGER NOT NULL,
    save INTEGER NOT NULL,
    FOREIGN KEY (save) REFERENCES save(saveID)
);

CREATE TABLE config(
    darkMode BOOLEAN NOT NULL,
    lang TEXT NOT NULL
);

CREATE TRIGGER config_remover
BEFORE INSERT ON config
BEGIN
        DELETE FROM config WHERE true;
END;

CREATE TRIGGER save_remove
BEFORE DELETE ON profile
BEGIN
	DELETE FROM save
	WHERE profile = OLD.slot;
END;

CREATE TRIGGER attrib_remove
BEFORE DELETE ON save
BEGIN
    DELETE FROM attribute
    WHERE save = OLD.saveID;
END;

CREATE TRIGGER valid_date_insert
BEFORE INSERT ON save
WHEN(NEW.date < (SELECT creationDate FROM profile WHERE slot = NEW.profile))
BEGIN
		SELECT RAISE(ABORT, 'The save date can t be older than de profile creation');
END;

CREATE TRIGGER valid_date_update
    BEFORE UPDATE ON save
    WHEN(NEW.date < (SELECT creationDate FROM profile WHERE slot = NEW.profile))
BEGIN
    SELECT RAISE(ABORT, 'The save date can t be older than de profile creation');
END;


CREATE TRIGGER profile_replacer_insert
BEFORE INSERT ON profile
BEGIN
    DELETE FROM save
    WHERE profile = NEW.slot;
    DELETE FROM profile
    WHERE slot = NEW.slot;

end;

INSERT INTO config VALUES(false,'fr');

