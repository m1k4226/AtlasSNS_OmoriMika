--初期ユーザー
INSERT INTO users(id, username, email, password, bio, icon_image) values
(1,'田中太郎','taro@exam.com','taro1234','ゲームが好きです','/images/icon1.png'),
(2,'高橋花子','hana@exam.com','hana5678','限界社畜OL','/images/icon2.png'),
(3,'鈴木ゆり','yurin@exam.com','yurin123','週末コスプレイヤー','/images/icon3.png'),
(4,'橋本なお','naon@exam.com','nyaon123','猫吸い仲間募集中','/images/icon4.png'),
(5,'阿部まお','maon@exam.com','maomao123','旅行大好き','/images/icon5.png'),
(6,'吉田ゆう','yuuu@exam.com','yunyun123','趣味はラーメン巡り','/images/icon6.png'),
(7,'川崎さき','saki@exam.com','sakiki123','古のヲタク','/images/icon7.png'),
(8,'百瀬まな','mana@exam.com','manana123','世界中でダンスしてます','/images/icon1.png'),
(9,'和田こう','kouu@exam.com','kookoo123','格闘','/images/icon2.png'),
(10,'神田けん','kenn@exam.com','kenken123','ゴルフはじめたい','/images/icon3.png');

--初期投稿
INSERT INTO posts(user_id, content) values
('1','最初に触ったゲームはマリ男64です'),
('1','2時間ぶっ通しで狩ってきたです'),
('2','深夜3時に帰宅'),
('3','来月4日のイベント出ます！'),
('4','5匹で戯れてるかわいい'),
('5','今年は6カ国行きたいな〜');

--初期FF関係
INSERT INTO follows(following_id, followed_id) values
('1','3'),
('1','4'),
('2','1'),
('2','3'),
('2','4'),
('3','1'),
('4','1'),
('5','2');
