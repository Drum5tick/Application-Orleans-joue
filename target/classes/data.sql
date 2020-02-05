/* tasks */
INSERT INTO tasks (name) VALUES ('Animation de jeu'), 
								('Accueil du public'),
								('Buvette');
/* events */
INSERT INTO event (name,
				description, 
				tables, 
				protozone_tables,
				max_tables_per_editor, 
				event_beginning_date, 
				event_ending_date, 
				editors_registration_begin_date, 
				editors_registration_end_date,
				shops_registration_begin_date,
				shops_registration_end_date,
				volunteers_registration_begin_date,
				volunteers_registration_end_date,
				registration_open,
				registration_before,
				price_per_table,
				sale_option_price,
				shop_price,
				discount
				   )VALUES
				   		('Festival Orléans Joue 2020','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',200,20,20,'2020-2-1','2020-2-28','2019-10-1','2020-3-30','2019-10-1','2020-3-30','2019-10-1','2020-3-30',0,0,65,100,300,3),
						('Printemps des éditeurs', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',10,20,20,'2020-2-1','2020-2-28','2019-12-25','2020-3-30','2019-10-1','2020-3-30','2019-10-1','2020-3-30',0,0,65,100,300,3),
						('Edition 2019','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',200,20,20,'2019-2-1','2019-2-28','2018-10-1','2018-10-31','2018-10-1','2018-10-31','2018-10-1','2018-10-31',0,0,65,100,300,3);

/* event tasks */
INSERT INTO event_task (`event_id`, `task_id`) VALUES(1,1),(1,2),(1,3),(2,1),(2,2);

/* address */
INSERT INTO address (wording,
					phone_number1,
					phone_number2,
					postal_code,
					city, address1) 
			VALUES
				('Adresse société 1','1234567891','1234567891',45000, 'Orléans', '32 boulevard Alexandre Martin'),
				('Société de JM','0685459632','0238569874',45000, 'Orléans', '10 rue du faubourg Madeleine'),
				('Adresse société 2','1234567891','1234567891',45000, 'Orléans', '20 rue Eugène Vignat'),
				('Adresse société 3','1234567891','1234567891',45000, 'Orléans', '15 rue de la bretonnerie');
/* TODO */

INSERT INTO address (wording,phone_number1,phone_number2, postal_code, city, address1) VALUES('Mad Made Games','0675896552','0675896552',45000, 'Orléans', '15 rue de la berge');
INSERT INTO address (wording,phone_number1,phone_number2, postal_code, city, address1) VALUES('Coup de Poker','0675896552','0675896552',45000, 'Orléans', '45 rue de la rive');
INSERT INTO address (wording,phone_number1,phone_number2, postal_code, city, address1) VALUES('Echec & Math','0675896552','0675896552',45000, 'Orléans', '32 rue de la source');
INSERT INTO address (wording,phone_number1,phone_number2, postal_code, city, address1) VALUES('Le Sloubi Gagnant','0675896552','0675896552',45000, 'Orléans', '15 rue de Kaamelott');
INSERT INTO address (phone_number1) VALUES ('0659633265');
INSERT INTO address (phone_number1) VALUES ('0675896552');
INSERT INTO address (phone_number1) VALUES ('0775896552');
				
/* user_role */
				
INSERT INTO user_role (wording) 
	VALUES('ADMIN'),('EDITOR'),('SHOP'),('VOLUNTEER'),('PROTOZONE');
	
/* society */
	
INSERT INTO society (name,
					 address_id,
					 siret, tva) 
	VALUES
		('Ajax Games',2,'12345678901234', 'FR1234567890'),
		('Agate',1,'12345678901234', 'FR1234567890'),
		('Hexagonal',3,'12345678901234', 'FR1234567890'),
		('Pegasus',4,'12345678901234', 'FR1234567890');
/* TODO */
INSERT INTO society (name, address_id, siret, tva) VALUES('Mad Made Games', '5' ,'12345678901234', 'FR1234567890');
INSERT INTO society (name, address_id, siret, tva) VALUES('Coup de Poker', '6' ,'12345678901234', 'FR1234567890');
INSERT INTO society (name, address_id, siret, tva) VALUES('Echec & Math', '7' ,'12345678901234', 'FR1234567890');
INSERT INTO society (name, address_id, siret, tva) VALUES('Le Sloubi Gagnant', '8' ,'12345678901234', 'FR1234567890');
		

/* user */
INSERT INTO `user` (account_active,
					completed_user_infos,
					email,
					firstname,
					lastname,
					password,
					profile_picture,
					address_id,
					society_id,
					user_role_id)
		VALUES (1,1,'jeanmichel@editeur.fr','Jean-Michel','Apeupret','$2a$10$On7t41vTEVFlqOUrbSNL4uFZvQA.uG6U5EFjF0MRGaJ1S8CuqmmzW','pictures/profile/1.jpg',9,1,2),
			   (1,0,'chris@orleansjoue.fr','Chris','Tal','$2a$10$5oYjpj4fuXBTaTgg94g6JuuKh/I7v52cuYefYvJNeEigQyqoSaqb6','pictures/default_avatar.jpg',NULL,NULL,1),
			   (1,1,'editeur1@editeur.fr','Sophie','Dubois','$2a$10$On7t41vTEVFlqOUrbSNL4uFZvQA.uG6U5EFjF0MRGaJ1S8CuqmmzW','pictures/default_avatar.jpg',NULL,2,2),
			   (1,1,'benevole1@benevole.fr','Johan','Edmond','$2a$10$On7t41vTEVFlqOUrbSNL4uFZvQA.uG6U5EFjF0MRGaJ1S8CuqmmzW','pictures/default_avatar.jpg',NULL,NULL,4),
			(1,0,'editeur2@editeur.fr','Sébastien','Malraut','$2a$10$On7t41vTEVFlqOUrbSNL4uFZvQA.uG6U5EFjF0MRGaJ1S8CuqmmzW','pictures/default_avatar.jpg',NULL,3,2),
			(1,0,'editeur3@editeur.fr','Lucie','Apeupret','$2a$10$On7t41vTEVFlqOUrbSNL4uFZvQA.uG6U5EFjF0MRGaJ1S8CuqmmzW','pictures/default_avatar.jpg',NULL,4,2);

/* TODO */
			   
INSERT INTO `user` (`account_active`, `completed_user_infos`, `email`, `firstname`, `lastname`, `password`, `profile_picture`, `address_id`, `society_id`, `user_role_id`) VALUES (1, 1, 'benevole@orlj.fr', 'Jean', 'Joueurdepoker', '$2a$10$/Fbs6EGk2Eve7Ofe2FMb3eIWwNdcdKpKbyldYv3oUBvJoxDP8K3ma', 'pictures/default_avatar.jpg', 10, NULL, 4);
INSERT INTO `user` (`account_active`, `completed_user_infos`, `email`, `firstname`, `lastname`, `password`, `profile_picture`, `address_id`, `society_id`, `user_role_id`) VALUES (1, 1, 'shop2@orlj.fr', 'Pierre', 'Jejout', '$2a$10$FnWDjdOiW.vl33CI2V8AMOBX5KZWXqBMUI9ERCA5ykQDlRQEQdLOe', 'pictures/default_avatar.jpg', NULL, 5, 3);
INSERT INTO `user` (`account_active`, `completed_user_infos`, `email`, `firstname`, `lastname`, `password`, `profile_picture`, `address_id`, `society_id`, `user_role_id`) VALUES (1, 1, 'shop3@orlj.fr', 'Marie', 'Hasthejack', '$2a$10$FnWDjdOiW.vl33CI2V8AMOBX5KZWXqBMUI9ERCA5ykQDlRQEQdLOe', 'pictures/default_avatar.jpg', NULL, 6, 3);
INSERT INTO `user` (`account_active`, `completed_user_infos`, `email`, `firstname`, `lastname`, `password`, `profile_picture`, `address_id`, `society_id`, `user_role_id`) VALUES (1, 1, 'shop4@orlj.fr', 'Luke', 'Texaswalker', '$2a$10$FnWDjdOiW.vl33CI2V8AMOBX5KZWXqBMUI9ERCA5ykQDlRQEQdLOe', 'pictures/default_avatar.jpg', NULL, 7, 3);
INSERT INTO `user` (`account_active`, `completed_user_infos`, `email`, `firstname`, `lastname`, `password`, `profile_picture`, `address_id`, `society_id`, `user_role_id`) VALUES (1, 1, 'shop@orlj.fr', 'Perceval', 'Peredur', '$2a$10$FnWDjdOiW.vl33CI2V8AMOBX5KZWXqBMUI9ERCA5ykQDlRQEQdLOe', 'pictures/default_avatar.jpg', NULL, 8, 3);
INSERT INTO `user` (`account_active`, `completed_user_infos`, `email`, `firstname`, `lastname`, `password`, `profile_picture`, `address_id`,  `user_role_id`) VALUES (1, 1, 'paul@orlj.fr', 'Paul', 'Parker', '$2a$10$FnWDjdOiW.vl33CI2V8AMOBX5KZWXqBMUI9ERCA5ykQDlRQEQdLOe', 'pictures/default_avatar.jpg', NULL, 4);
INSERT INTO `user` (`account_active`, `completed_user_infos`, `email`, `firstname`, `lastname`, `password`, `profile_picture`, `address_id`, `society_id`, `user_role_id`) VALUES (1, 1, 'pierre@orlj.fr', 'Pierre', 'Lechene', '$2a$10$PMu6o7pxxSeWsxRVUbB5AOsjH0Jxai45MUMTi5BV6i2NrWD2MBgAm', 'pictures/default_avatar.jpg', 11, NULL, 5);

INSERT INTO `user` (`account_active`, `completed_user_infos`, `email`, `firstname`, `lastname`, `password`, `profile_picture`, `address_id`, `society_id`, `user_role_id`) VALUES (1, 1, 'protozone1@orlj.fr', 'Nicolas', 'Sareau', '$2a$10$PMu6o7pxxSeWsxRVUbB5AOsjH0Jxai45MUMTi5BV6i2NrWD2MBgAm', 'pictures/default_avatar.jpg', NULL, NULL, 5);
INSERT INTO `user` (`account_active`, `completed_user_infos`, `email`, `firstname`, `lastname`, `password`, `profile_picture`, `address_id`, `society_id`, `user_role_id`) VALUES (1, 1, 'protozone2@orlj.fr', 'Benoit', 'Thomson', '$2a$10$PMu6o7pxxSeWsxRVUbB5AOsjH0Jxai45MUMTi5BV6i2NrWD2MBgAm', 'pictures/default_avatar.jpg', NULL, NULL, 5);
INSERT INTO `user` (`account_active`, `completed_user_infos`, `email`, `firstname`, `lastname`, `password`, `profile_picture`, `address_id`, `society_id`, `user_role_id`) VALUES (1, 1, 'protozone3@orlj.fr', 'Fayza', 'Hamoud', '$2a$10$PMu6o7pxxSeWsxRVUbB5AOsjH0Jxai45MUMTi5BV6i2NrWD2MBgAm', 'pictures/default_avatar.jpg', NULL, NULL, 5);

/* editor registration */
INSERT INTO `editor_registration` (`agent_provided`, 
								   `comments`, 
								   `electrical_supply_need`, 
								   `registration_cost`,
								   `sale_option`, 
								   `state`, 
								   `subscription_date`,
								   `tables_quantity`, 
								   `volunteers_need`,
								   `volunteer_count`,
								   `event_id`,
								   `society_id`) 
VALUES (1, 'Besoin de 2 t-shirt supplémentaires.', 1, 360,1, 'pending', '2019-12-16 15:09:22', 5, 1, 2.5, 1, 1),
	   (0, 'Test de commentaires', 1, 230, 1, 'canceled', '2019-01-03 16:10:30', 3, 1, 1.5, 1, 2),
	   (0, 'Test de commentaires', 0, 300, 1, 'validated', '2019-01-03 16:10:30',2, 1, 1, 2, 3);
	   
/* volunteer registration */
	   
INSERT INTO `volunteer_registration` (`comments`,
									  `state`,
									  `subscription_date`,
									  `event_id`,
									  `user_id`)
	VALUES ('Test commentaires inscription','pending', '2019-1-10', 1,4),
		   ('Test commentaires inscription','validated', '2019-1-10', 1,7);


/* game */
		   
INSERT INTO `game` (`name`, 
					`author`, 
					`description`, 
					`publication_date`, 
					`picture`, 
					`web_link`,
					`society_id`,
					price
					) 
VALUES ('Sid Meier’s Civilization : Une Aube Nouvelle', 
		'James Kniffen', 
		'Sid Meier’s Civilization : Une Aube Nouvelle transpose l’un des plus grands succès du jeu vidéo jamais édité en jeu de stratégie sur plateau.',
		'2018-1-1',
		'/pictures/uploads/games_pictures/sid-meiers-civilization-une-aube-nouvelle.jpg',
'https://www.philibertnet.com/fr/fantasy-flight-games/68280-sid-meiers-civilization-une-aube-nouvelle-8435407619661.html?gclid=EAIaIQobChMInu7Tn7285gIVwbHtCh0QRAz2EAQYAyABEgINV_D_BwE',
1, 40),
	('CITADELLES',
		NULL,
		'La nouvelle édition luxueuse du célèbre jeu de ruse et d''''intrigues...Citadelles est un jeu de cartes incontournable, devenu l''''un des plus grands succès de ces dernières années.',
		'2018-1-1',
		'/pictures/uploads/games_pictures/citadelles--4eme-ed--p-image-68422-grande.jpg',
'https://www.espritjeu.com/jeux-de-societe/citadelles-4e-edition.html?gclid=EAIaIQobChMInu7Tn7285gIVwbHtCh0QRAz2EAQYASABEgJBT_D_BwE',
1,80),
	('JUST ONE',
		NULL,
		'Jouez tous ensemble pour découvrir les mots Mystère ! Just One est un jeu d''''ambiance et de déduction pour 3 à 7 joueurs.',
		'2018-1-1',
		NULL,
'https://www.espritjeu.com/just-one.html?gclid=EAIaIQobChMInu7Tn7285gIVwbHtCh0QRAz2EAQYBCABEgLW8fD_BwE',
1,0);

/* game registrations */

INSERT INTO game_registration VALUES(1,1),(1,2),(1,3);

/* agent */
INSERT INTO agent (accommodation,
				  garden_party,
				  firstname,
				  lastname,
				  phone,
				  email,
				  society_id) 
	VALUES (0,0,'Lucie', 'Petit', '0652897432', 'mailtest@editeur.fr',1),
		   (0,0,'Raynald', 'Normand', '0652894130', 'mailtest2@editeur.fr',1),
		   (0,0,'Audrey', 'Fouchard', '0652897748', 'mailtest3@editeur.fr',1),
		   (0,0,'Thomas', 'Tullot', '0652897432', 'mailtest@editeur.fr',4),
		   (0,0,'Jade', 'Kebir', '0652894130', 'mailtest2@editeur.fr',4),
		   (0,0,'Vincent', 'Jonas', '0652897748', 'mailtest3@editeur.fr',4);
		   
/* agent Registration */
		   
INSERT INTO agent_registration(editor_registration_id, agent_id) VALUE (1,1),(1,2),(1,3);

/* time slot */

INSERT INTO `time_slot`(`name`, `description`,`ordernumber`) VALUES ('Samedi','9h-12h',1), ('Samedi','12h-15h',2), ('Samedi','15h-19h',3), ('Samedi', 'Journée entière',4), ('Dimanche', '9h-12h',5), ('dimanche', '12h-15h',6), ('Dimanche', '15h-19h',7), ('Dimanche', 'Journée entière',8),('Garden Party','19h-22h',9);

/* Shop registration */
INSERT INTO `shop_registration` (`agent_provided`, `comments`, `electrical_supply_need`, `registration_cost`, `state`, `subscription_date`, `tables_quantity`, `event_id`, `society_id`) VALUES (b'1', 'Beaucoup de nouveau jeux cette année', b'1', '300', 'pending', '2020-01-16 15:09:22', '3', '1', '5');
INSERT INTO `shop_registration` (`agent_provided`, `comments`, `electrical_supply_need`, `registration_cost`, `state`, `subscription_date`, `tables_quantity`, `event_id`, `society_id`) VALUES (b'1', 'Absence 1h le premier jour', b'1', '300', 'validated', '2020-01-16 17:09:24', '1', '1', '6');
INSERT INTO `shop_registration` (`agent_provided`, `comments`, `electrical_supply_need`, `registration_cost`, `state`, `subscription_date`, `tables_quantity`, `event_id`, `society_id`) VALUES (b'1', 'Hâte de participer à cette nouvelle session', b'0', '300', 'canceled', '2020-01-25 19:09:24', '1', '1', '7');
INSERT INTO `shop_registration` (`agent_provided`, `comments`, `electrical_supply_need`, `registration_cost`, `state`, `subscription_date`, `tables_quantity`, `event_id`, `society_id`) VALUES (b'1', 'Quand tout le monde en a marre, on peut adapter les règles pour faire 10 jets de 2 dés moins 4. Ça donne un résultat entre 16 et 116.', b'0', '300', 'pending', '2020-01-27 19:09:24', '10', '1', '8');


/* agent shop registration */

INSERT INTO agent_shop_registration(shop_registration_id, agent_id) VALUE (1,4);
INSERT INTO agent_shop_registration(shop_registration_id, agent_id) VALUE (1,5);
INSERT INTO agent_shop_registration(shop_registration_id, agent_id) VALUE (1,6);

/* protozone registrations */

INSERT INTO protozone_registration(`comments`,`state`,subscription_date,event_id,user_id) VALUE ('Un escape game révolutionnaire cette année !','pending','2020-1-1',1,13);
INSERT INTO protozone_registration(`comments`,`state`,subscription_date,event_id,user_id) VALUE ('3 maquettes à autour du thème du Moyen-Age','validated','2020-1-1',1,14);
INSERT INTO protozone_registration(`comments`,`state`,subscription_date,event_id,user_id) VALUE ('Je présente un nouveau prototype basé sur le jeu de rôle collaboratif','canceled','2020-1-1',1,15);
