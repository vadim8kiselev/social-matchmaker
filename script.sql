DROP TABLE users;
CREATE TABLE users
(
  user_id          VARCHAR(255) NOT NULL
    CONSTRAINT users_pkey
    PRIMARY KEY,
  first_name       TEXT,
  last_name        TEXT,
  screen_name      TEXT,
  sex              TEXT,
  online           TEXT,
  birthday         TEXT,
  city             TEXT,
  country          TEXT,
  mobile_phone     TEXT,
  home_phone       TEXT,
  skype            TEXT,
  facebook         TEXT,
  twitter          TEXT,
  livejournal      TEXT,
  instagram        TEXT,
  status           TEXT,
  last_seen        TEXT,
  career           TEXT,
  military         TEXT,
  university       TEXT,
  home_town        TEXT,
  photo_link       TEXT,
  relation         TEXT,
  relation_partner TEXT,
  interests        TEXT,
  music            TEXT,
  activities       TEXT,
  movies           TEXT,
  tv               TEXT,
  books            TEXT,
  games            TEXT,
  schools          TEXT,
  about            TEXT,
  quotes           TEXT,
  political        TEXT,
  languages        TEXT,
  religion         TEXT,
  inspired_by      TEXT,
  people_main      TEXT,
  life_main         TEXT,
  smoking          TEXT,
  alcohol          TEXT,
  deactivated      TEXT
);

DROP TABLE posts;
CREATE TABLE posts
(
  post_id        VARCHAR(255) NOT NULL
    CONSTRAINT posts_pkey
    PRIMARY KEY,
  from_id        TEXT,
  owner_id       TEXT,
  text           TEXT,
  date           TEXT,
  likes_count    TEXT,
  shares_count   TEXT,
  views_count    TEXT,
  comments_count TEXT,
  geo            TEXT,
  attachments    TEXT,
  type           TEXT,
  platform       TEXT
);

DROP TABLE groups;
CREATE TABLE groups
(
  group_id          VARCHAR(255) NOT NULL
    CONSTRAINT groups_pkey
    PRIMARY KEY,
  name              TEXT,
  screen_name       TEXT,
  status            TEXT,
  description       TEXT,
  type              TEXT,
  verified          TEXT,
  is_closed         TEXT,
  age_limits        TEXT,
  subscribers_count TEXT,
  city              TEXT,
  country           TEXT,
  photo_link        TEXT,
  links             TEXT,
  contacts          TEXT,
  site              TEXT,
  deactivated       TEXT
);
