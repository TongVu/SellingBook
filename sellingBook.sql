INSERT INTO publisher(name,
					phone,
					address,
					email)
VALUES      ('Stairway Press'     ,0933258257,'Phoenix, US'      ,'stairwaypress@gmail.com'),
            ('Dundurn Press'      ,0943268257,'Toronto, CA'      ,'dundrunpress@gmail.com'),
            ('Knopf Doubleday'    ,0968258250,'New York City, US','knopfdoubleday@gmail.com'),
            ('Crown Publishing'   ,0930250250,'New York City, US','crownpublishing@gmail.com'),
            ('Autumn House Press' ,0900058257,'Philadelphia, US' ,'autumnhousepress@gmail.com'),
            ('Algonquin Books'    ,0933288227,'Raleigh, US'      ,'algonquinbooks@gmail.com'),
            ('Dover Publications' ,0932258257,'New York City, US','doverpublications@gmail.com'),
            ('Drawn and Quarterly',0979258257,'Toronto, CA'      ,'drawnandquarterly@gmail.com'),
            ('Coffee House Press' ,0933258000,'Minneapolis, US'  ,'coffeehousepress@gmail.com');



INSERT INTO ebook(id,
				page,
				title,
				rating,				
				introduction,
				purchased,
				view_link_status,
				fk_publisher_id
				)
VALUES  	(10001, 420 , 'Cloud Cuckoo Land',                            2, 'An allusive Greek text casts a spell across millennia, capturing a glittering array of characters—from a teenager in 1453 Constantinople to an eco-terrorist in present day Idaho—in this opulent marvel of a novel by the Pulitzer-winning author of All the Light We Cannot See.'                  , false, 'inactivelink', 1),
            (10002, 420 , 'Kane and Abel',                              4.5, 'Kane and Abel is a 1979 novel by British author Jeffrey Archer. Released in the United Kingdom in 1979 and in the United States in February 1980, the book was an international success, selling over one million copies in its first week. It reached No. 1 on the New York Times best-seller list.', true , 'inactivelink', 1),
            (10003, 400 , 'Norwegian Wood',                             4.5, 'Norwegian Wood is a 1987 novel by Japanese author Haruki Murakami. The novel is a nostalgic story of loss and burgeoning sexuality. It is told from the first-person perspective of Toru Watanabe, who looks back on his days as a college student living in Tokyo.'                                 , true , 'inactivelink', 2),
            (10004, 505 , 'Kafka on the Shore',                         4.1, 'Kafka on the Shore is a 2002 novel by Japanese author Haruki Murakami. Its 2005 English translation was among The 10 Best Books of 2005 from The New York Times and received the World Fantasy Award for ,2006'                                                                                      , true , 'inactivelink', 3),
            (10005, 607 , 'The Wind-Up Bird Chronicle',                 4.2, 'The Wind-Up Bird Chronicle is a novel published in 1994–1995 by Japanese author Haruki Murakami. The American translation and its British adaptation, dubbed the only official translations, are by Jay Rubin and were first published in 1997.'                                                     , false, 'inactivelink', 4),
            (10006, 1484, '1Q84',                                       4.9, '1Q84 is a novel written by Japanese writer Haruki Murakami, first published in three volumes in Japan in 2009–10. It covers a fictionalized year of 1984 in parallel with a real one. The novel is a story of how a woman named Aomame begins to notice strange changes occurring in the world.'     , true , 'inactivelink', 7),
            (10007, 349 , 'Stiff: The Curious Lives of Human Cadavers', 4.0, 'Stiff: The Curious Lives of Human Cadavers is a 2003 nonfiction book by Mary Roach. Published by W. W. Norton & Company, it details the unique scientific contributions of the deceased.'                                                                                                            , false, 'inactivelink', 5),
            (10008, 390 , 'Educated',                                   4.5, 'Educated is a memoir by the American author Tara Westover. Westover recounts overcoming her survivalist Mormon family in order to go to college, and emphasizes the importance of education in enlarging her world.'                                                                                 , false, 'inactivelink', 6),
            (10009, 555 , 'English Grammar in Use',                     4.5, 'English Grammar in Use is a self-study reference and practice book for intermediate to advanced students of English. The book was written by Raymond Murphy and published by Cambridge University Press.'                                                                                            , false, 'inactivelink', 8),
            (10010, 210 , 'The Diaries of Adam and Eve',                4.7, 'Diaries of Adam and Eve, is a short text that tells two parallel stories, but from different points of view. These stories are, of course, the life of Adam since the Creator sent his mate to him, and the life of Eve since she appeared in the Garden of Eden'                                    , true , 'inactivelink', 9);
		

		
INSERT INTO author(
            dob,
            address,
            gender,
            first_name,
            last_name,
            email,
            phone,
            nationality)
VALUES 	( '1972-10-27', 'America'      , 'MALE'  , 'Anthony', 'Doerr'   , 'anthonydoerr@gmail.com' , 0909236237, 'American'),
		( '1939-04-15', 'London'       , 'MALE'  , 'Jeffrey', 'Archer'  , 'jeffreyarcher@gmail.com', 0909274942, 'British' ),
		( '1948-01-12', 'Tokyo'        , 'MALE'  , 'Haruki' , 'Murakami', 'hiramurakami@gmail.com' , 0909777250, 'Japanese'),
		( '1958-03-20', 'New Hampshire', 'FEMALE', 'Mary'   , 'Roach'   , 'maryroach@gmail.com'    , 0909555537, 'American'),
		( '1972-10-27', 'Idaho'        , 'FEMALE', 'Tara'   , 'Westover', 'tarawestover@gmail.com' , 0918394729, 'American'),
		( '1926-12-13', 'Michigan'     , 'MALE'  , 'Raymond', 'Murphy'  , 'raymondmurphy@gmail.com', 0943218590, 'American'),
		( '1834-11-30', 'Missouri'     , 'MALE'  , 'Mark'   , 'Twain'   , 'marktwain@gmail.com'    , 0909923626, 'American');



INSERT INTO category(name)
VALUES  (	'FANTASY'	  ),
		(	'SCI_FI'	  ),
		(	'MYSTERY'	  ),
		(	'THRILLER'	  ),
		(	'ROMANCE'	  ),
		(	'PROGRAMMING' ),
		(	'ENGLISH'	  ),
		(	'SELF_HELP'	  ),
		(	'BUSINESS'	  ),
		(	'ACTION_AND_ADVENTURE'	),
		(	'CLASSICS'	  ),
		(	'COMIC'	      ),
		(	'HISTORICAL'  ),
		(	'BIOGRAPHY'	  ),
		(	'CHILDREN'	  );
		
		
		
INSERT INTO category_ebook_relation(
	fk_category_id, 
	fk_ebook_id)
VALUES  (1 , 10001),
		(11, 10002),
		(5 , 10003),
		(10, 10004),
		(1 , 10005),
		(10, 10006),
		(13, 10007),
		(13, 10008),
		(7 , 10009),
		(5 , 10010);
	

	
INSERT INTO ebook_author_relation(
	fk_author_id, 
	fk_ebook_id)
VALUES (1,  10001),
		(2, 10002),
		(3, 10003),
		(3, 10004),
		(3, 10005),
		(3, 10006),
		(4, 10007),
		(5, 10008),
		(6, 10009),
		(7, 10010);
		
		
		
INSERT INTO account(
			address, 
			dob, 
			email, 
			first_name, 
			gender, 
			last_name, 
			phone		)
VALUES ('Tan Phu, Ho Chi Minh'  , '1995-04-24', 'tongging244@gmai.com', 'Tong'  , 'MALE'  , 'Vu'   , '0934155803'),
		('Binh Tan, Ho Chi Minh', '1996-05-15', 'banhhe155@gmai.com'  , 'Nguyen', 'FEMALE', 'Hanh' , '0934156783'),
		('01, Ho Chi Minh'      , '1996-08-08', 'thanhngan08@gmai.com', 'Nguyen', 'FEMALE', 'Ngan' , '0919777255'),
		('Seattle, USA'         , '1955-10-28', 'billybilly@gmai.com' , 'Bill'  , 'MALE'  , 'Gates', '0934266803'),
		('Binh Tan, Ho Chi Minh', '1994-06-29', 'nntanh@gmai.com'     , 'Nguyen', 'FEMALE', 'Anh'  , '0914074837'),
		('Tan Phu, Ho Chi Minh' , '1996-06-20', 'huuphuoc_ho@gmai.com', 'Ho'    , 'MALE'  , 'Phuoc', '0934155333'),
		('Tan Binh, Ho Chi Minh', '1999-07-07', 'thaotran077@gmai.com', 'Tran'  , 'FEMALE', 'Thao' , '0977155156'),
		('03, Ho Chi Minh'      , '1996-10-08', 'trinhne@gmai.com'    , 'Nguyen', 'FEMALE', 'Trinh', '0933155500');
		
		
		
INSERT INTO credit_card(
					card_number,
					balance, 
					expired_date, 
					fk_account_id
					)
VALUES (100001, 10000 , '2040-06-06', 1),
		(100002, 800  , '2038-12-16', 1),
		(100003, 5000 , '2025-07-25', 2),
		(100004, 9880 , '2030-01-19', 3),
		(100005, 500  , '2032-06-30', 3), -- card 5, account 3
		(100006, 2300 , '2042-05-01', 4),
		(100007, 4800 , '2050-06-15', 4),
		(100008, 20000, '2030-11-07', 5),
		(100009, 15000, '2028-10-13', 6),
		(100010, 12030, '2031-03-27', 7),
		(100011, 9836 , '2034-08-14', 8),
		(100012, 7442 , '2035-02-05', 5);
		
		
		
INSERT INTO comment(
				book_rating, 
				comment_content, 
				comment_upvote, 
				date, 
				fk_account_id)
VALUES (4, 'Its been a few years since the brilliant All the Light We Cannot See but this great big hefty novel is well worth the wait. As with Light there are multiple characters storylines weaving in and out, but as Doerr prefigures the chapters with the location and year, it is very easy to keep all the strands separate. There is Konstance, on board a spaceship travelling towards a planet she will never set foot upon. There is Anna, in Constantinople in 1439 to 1452, and Omeir in the Rhodope Mountains of Bulgaria in the same years. And there is Seymour in the early years of the 21st century, and Zeno whose quietly uneventful life stretches from the depression years mostly spent in Ohio, but with emphasis on the 20th of February 2020 when he is helping a group of primary school kids rehearse for a play they are performing the next night in their local library. What links all these characters in their different eras, is a story from the ancient Greek - Diogenes. Cloud Cuckoo Land, which tells of the misadventures and travels of Aethon, a simple shepherd searching for a better life. Extracts of this fabled story introduce each part of the narratives, and it is a testament to Doerrs skills as a writer, that all these elements come together in a satisfying, and ultimately redemptive way. At times the stories are truly heartbreaking, and take place in very dark times, but the power of words and how story-telling (and the guardians of this knowledge - librarians) link people through the ages is the centre of this beautiful novel. I cannot recommend it highly enough - and as the prologue says: "Stranger, whoever you are, open this to learn what will amaze you."  Lindy Jones',
		23, '2020-02-22', 2), -- Cloud Cuckoo Land
 
		(5, 'Cloud Cuckoo Land is stupendous accomplishment. Doerr braids three stories across time and space yet makes every character believable and memorable. A big book that read much faster than I anticipated. The prose is glorious. There are multiple themes, but I enjoyed seeing how far one would go to find the happiness they want as well as the strength of young and diverse people. I highly recommend it.'                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   , 
		10, '2020-02-18', 3), -- Cloud Cuckoo Land
		
		(4, 'This is the first book of Haruki Murakami that I have read and actually worked on as my thesis in M.A. My thesis is about application of magical realism in this novel. On the one hand, since the timing is not linear and the characters are inconspicuous, particularly Kano sisters, it could be included in this genre. However, the recurring uncanny events and protagonists reaction of surprise to them would contradict the main element of authorial reticence in this novel. Overall, the story is mesmerizing and the stream of consciousness is elaborately taken into account. Particularly, the scene of the war with Russian troops is so alive that every moment is blood curdling. I have been enthralled by the narrating power of the author.', 
		20, '2021-04-20', 1), -- wind-up bird
		
		(4, 'Absolutely Amazing . I enjoyed the book bit by bit . helped me alot in my exams . i dont have the proper words of appreciation to put  for this amazing book . Im in awe of this book and hard work put by the author Raymond Murphy . Long live Raymond . God Bless you sir lots of love and appreciation.', 
		23, '2018-05-05', 5), -- English book
		
		(3, 'For me reading this book started out like slipping on a pair of comfortable slippers. Tara set the literary table with captivating descriptions of her home, surrounding mountains, and her quirky but congenial family while inviting you in with her wide eyed naïveté and innocence. But before you know it you are on an emotional roller coaster ride with no end in sight. I knew nothing of the author’s background before reading this and so had no idea what to expect or where she was going. I’m so glad I took this ride. Well worth the price of admission. In fact one of the best modern day books I’ve ever read. Highly recommended! I would say though that if you have strong and rigid fundamental religious beliefs you’d best take a pass because this may challenge that rigidity.', 
		10, '2021-10-15', 6), -- educated
		
		(4, 'A classic...extraordinary humorous (if stereotypical, in a fun way!) look at male/female differences in thought patterns, and a study in the value of compromise and understanding in male/female marriages. While Biblically based literarily, non-religious.', 
		8 , '2022-01-30', 6), -- The Diaries of Adam and Eve
		
		(5, 'Another excellent Samuel Clemons writing. I have not laughed so hard in many months when my computer reads this to me. This USED to be part of "Letters From The Earth", which is another great text by Mr. Clemons. I recommend both books if you wish to see a cultural perspective of some older stories which emanated from some great theological writings. (WARNING: If you are offended by any other perspective on religion other than your own, do NOT get either book.', 
		2 , '2019-06-29', 7), -- The Diaries of Adam and Eve
		
		(4, 'It’s a very different book from what I have read so far. I would recommend it to people who can be patient since it is a triology. What I really liked about the book was it is very dream like. It takes you on a journey where a man and a woman who are in love somehow are drawn towards each other after 20 years . They met as 10 years old and found similar feeling of loneliness clutching them at their hearts. Somehow they think of each other although there wasn’t any solid interaction. The plot moves in a pretty fast pace in the first book, gets sluggish I the second and picks up again I the third. There is something that happens to you as you read. The steaminess and the parallel world rubs off a little on the reader too. That’s how I felt. So go for it if you want something different', 
		50, '2020-03-04', 8), -- 1Q84
		
		(3, '1Q84 actually refers to the year 1984 where the Q stands for question .The irony is that I read this novel in the year 2020 which in itself is quite a peculiar year. This novel follows two protagonists Tengo and Aomame ( later in the novel we see a third one as well). Both of them are doing something dangerous and unknowingly, in a strange intertwining of fates, they are together drawn to a strange world(feels kind of like a parallel universe but it is not so as told by one of the characters),face to face with a religious organization. Tengo is writing a novel with the help of a teenager named Fuka Eri ,on the other hand Aomame is entrusted with a dangerous task, both of these can be quite menacing to this religious sect .The two of them must accomplish their tasks and find each other to go back to the real world. I found many of the side characters to be quite interesting like Tamaru and the dowager who are like the voices of reason in this world where fact and fiction seem to be coalescing together. Ushikawa ,who seemed to be an epitome of logic as well as bad luck ,feels like a collateral damage in the trios endeavor against the sect . I really felt bad for this character. Overall the novel has a magical feel about it. Murakami takes you on a ride to this strange new world of 1Q84 with two moons in the sky and characters with complex feelings and thoughts.', 
		13, '2021-03-12', 8); -- 1Q84
		
		
		
INSERT INTO comment_ebook_relation(
					fk_comment_id, 
					fk_ebook_id)
VALUES  (1, 10001),
		(2, 10001),
		(3, 10005),
		(4, 10009),
		(5, 10008),
		(6, 10010),
		(7, 10010),
		(8, 10006),
		(9, 10006);
		
		
		
INSERT INTO invoice(
				invoice_date, 
				is_pay, 
				quantity, 
				total_payment, 
				fk_account_id, 
				fk_credit_card_id)
VALUES  ('2020-02-21', true , 2, 120, 1, 1),
		('2021-08-08', false, 0, 300, 2, 3),
		('2021-05-18', false, 0,   0, 3, 4),
		('2020-08-18', false, 0,   0, 4, 6),
		('2021-05-18', true , 4, 180, 5, 8),
		('2019-06-19', true , 3, 155, 6, 9),
		('2018-04-20', false, 0,   0, 7, 10),
		('2022-05-15', true , 1,  18, 8, 11),
		('2017-06-29', false, 0,   0, 1, 1);
		
		
		
INSERT INTO invoice_detail(
	date_added, 
	ebook_price, 
	fk_ebook_id, 
	fk_invoice_id)
VALUES  ('2020-02-01', 18, 10001, 1),
		('2019-04-21', 7 , 10010, 2),
		('2021-08-13', 22, 10002, 3),
		('2022-10-07', 23, 10003, 4),
		('2021-02-09', 19, 10004, 5),
		('2020-12-16', 18, 10005, 6),
		('2020-04-03', 16, 10006, 7),
		('2020-11-27', 40, 10007, 8),
		('2019-05-25', 35, 10008, 9),
		('2020-11-27', 30, 10009, 9);
