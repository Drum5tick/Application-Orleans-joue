/* user_role */			
INSERT INTO `user_role` (`id`, `wording`) 
VALUES (1, 'ADMIN'), (2, 'EDITOR'), (3, 'SHOP'), (4, 'VOLUNTEER'), (5, 'PROTOZONE');


/* user */
INSERT INTO `user` (`account_active`, `completed_user_infos`, `email`, `firstname`, `lastname`, `password`, `profile_picture`, `address_id`, `society_id`, `user_role_id`) 
VALUES (b'1', b'0', 'admin@orleansjoue.fr', 'Géraud', 'Brugière', '$2a$10$6/6JJssrCSas/CpoXDV88Ol6AC.ZFSQB5dVg8wFJLIXGADYH5y2vq', NULL, NULL, NULL, 1);