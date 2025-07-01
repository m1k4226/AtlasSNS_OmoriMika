--初期ユーザー
INSERT INTO users(username, email, password, bio, icon_image) values
('田中太郎','taro@exam.com','taro123','ゲームが好きです','images/icon1.png'),
('高橋花子','hana@exam.com','hana123','限界社畜OL','images/icon2.png'),
('鈴木ゆり','yurin@exam.com','yuri123','週末コスプレイヤー','images/icon3.png'),
('橋本なお','naon@exam.com','nao123','猫吸い仲間募集中','images/icon4.png'),
('阿部まお','maon@exam.com','mao123','旅行大好き','images/icon5.png'),
('吉田ゆう','yuuu@exam.com','yuu123','趣味はラーメン巡り','images/icon6.png'),
('川崎さき','saki@exam.com','saki123','古のヲタク','images/icon7.png'),
('百瀬まな','mana@exam.com','mana123','世界中でダンスしてます','images/icon1.png'),
('和田こう','kouu@exam.com','koo123','格闘','images/icon2.png'),
('神田けん','kenn@exam.com','ken123','ゴルフはじめたい','images/icon3.png');

--初期投稿
INSERT INTO posts(user_id, post) values
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
