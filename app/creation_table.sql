DROP TABLE IF EXISTS profile;
DROP TABLE IF EXISTS save;
DROP TABLE IF EXISTS attribute;
DROP TRIGGER IF EXISTS save_remove;
DROP TRIGGER IF EXISTS valid_date;

CREATE TABLE profile(
	slot INTEGER NOT NULL UNIQUE CHECK(slot=0 OR slot=1 OR slot=2),
	type TEXT NOT NULL CHECK(type='Chien' OR type='Chat' OR type='Lapin' OR type='Robot'),
	name TEXT NOT NULL,
	creationDate DATE
);

CREATE TABLE save(
	id INTEGER PRIMARY KEY AUTOINCREMENT,
	date DATE NOT NULL,
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
    FOREIGN KEY (save) REFERENCES save(id)
);

CREATE TRIGGER save_remove
AFTER DELETE ON profile
BEGIN
	DELETE FROM save
	WHERE profile = OLD.slot;
END;

CREATE TRIGGER valid_date
BEFORE INSERT ON save
WHEN(NEW.date < (SELECT creationDate FROM profile WHERE id = NEW.profile))
BEGIN
		SELECT RAISE(ABORT, 'The save date can t be older than de profile creation');
END;

CREATE TRIGGER profile_replacer
BEFORE INSERT ON profile
BEGIN
    DELETE FROM profile
    WHERE slot = NEW.slot;
end;

