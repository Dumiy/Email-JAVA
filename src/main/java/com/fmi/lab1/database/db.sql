<<<<<<< HEAD
CREATE TABLE `users` (
  `idusers` int(11) NOT NULL,
  `username` varchar(45) NOT NULL,
  `firstname` varchar(45) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  `email` varchar(30) NOT NULL,
  `creation` datetime NOT NULL,
  `password` varchar(30) NOT NULL,
  PRIMARY KEY (`idusers`)
);

CREATE TABLE `userlog` (
                         `id` int(11) NOT NULL,
                         `object` varchar(45) NOT NULL,
                         `time` datetime NOT NULL,
                         `action` varchar(45) NOT NULL,
                         PRIMARY KEY (`id`)
);
ALTER TABLE `users`
=======
CREATE TABLE `users` (
  `idusers` int(11) NOT NULL,
  `username` varchar(45) NOT NULL,
  `firstname` varchar(45) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  `email` varchar(30) NOT NULL,
  `creation` datetime NOT NULL,
  `password` varchar(30) NOT NULL,
  PRIMARY KEY (`idusers`)
);

CREATE TABLE `userlog` (
                         `id` int(11) NOT NULL,
                         `object` varchar(45) NOT NULL,
                         `time` datetime NOT NULL,
                         `action` varchar(45) NOT NULL,
                         PRIMARY KEY (`id`)
);
ALTER TABLE `users`
>>>>>>> 09cd27a5507523bbde0818cbc630ee96363bb076
ADD `password` varchar(30) NOT NULL;