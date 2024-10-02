-- RoMan: structure de la table et données d'exemple

-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : jeu. 05 jan. 2023 à 20:44
-- Version du serveur : 10.4.22-MariaDB
-- Version de PHP : 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `roman`
--

-- --------------------------------------------------------

--
-- Structure de la table `adresses`
--

CREATE TABLE `adresses` (
  `idAdresse` int(11) NOT NULL,
  `coordonneesGPS` geometry NOT NULL,
  `libelle` varchar(50) DEFAULT NULL,
  `numeroVoie` smallint(5) UNSIGNED DEFAULT NULL,
  `complementNumero` varchar(10) DEFAULT NULL,
  `voie` varchar(50) DEFAULT NULL,
  `complementAdresse` varchar(50) DEFAULT NULL,
  `codePostal` char(5) DEFAULT NULL,
  `ville` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `adresses`
--

INSERT INTO `adresses` (`idAdresse`, `coordonneesGPS`, `libelle`, `numeroVoie`, `complementNumero`, `voie`, `complementAdresse`, `codePostal`, `ville`) VALUES
(1, 0xe61000000101000000029eb470599f47401e4e603aaddbea3f, 'Client de tauxigny-saint-bauld', 10, 'QUATER', 'Route des Masureaux', '', '37310', 'Tauxigny-Saint-Bauld'),
(2, 0xe61000000101000000029eb470599f47401e4e603aaddbea3f, 'Client de joué-lès-tours', 56, '', 'Rue de Verdun', '', '37300', 'Joué-lès-Tours'),
(3, 0xe61000000101000000029eb470599f47401e4e603aaddbea3f, 'Client de tours', 5, '', 'Allée des Vosges', '', '37000', 'Tours'),
(4, 0xe61000000101000000029eb470599f47401e4e603aaddbea3f, 'Client de la membrolle-sur-choisille', 11, '', 'Rue Maryse Bastié', '', '37390', 'La Membrolle-sur-Choisille'),
(5, 0xe61000000101000000029eb470599f47401e4e603aaddbea3f, 'Client de athée-sur-cher', 6, '', 'Rue des Sablons', '', '37270', 'Athée-sur-Cher'),
(6, 0xe61000000101000000029eb470599f47401e4e603aaddbea3f, 'Client de marcilly-sur-vienne', 38, '', 'Rue de Cambraye', '', '37800', 'Marcilly-sur-Vienne'),
(7, 0xe61000000101000000029eb470599f47401e4e603aaddbea3f, 'Client de tours', 49, '', 'Rue de Beaulieu', 'Batiment 2', '37100', 'Tours'),
(8, 0xe61000000101000000029eb470599f47401e4e603aaddbea3f, 'Client de abilly', 7, '', 'Impasse du Chateau', 'Batiment 1', '37160', 'Abilly'),
(9, 0xe61000000101000000029eb470599f47401e4e603aaddbea3f, 'Client de la ville-aux-dames', 1, '', 'Rue Françoise Giroud', '', '37700', 'La Ville-aux-Dames'),
(10, 0xe61000000101000000029eb470599f47401e4e603aaddbea3f, 'Client de montlouis-sur-loire', 11, NULL, 'Avenue Léonard de Vinci', '', '37270', 'Montlouis-sur-Loire'),
(11, 0xe61000000101000000029eb470599f47401e4e603aaddbea3f, 'Client de nouzilly', 21, '', 'Rue des Caves Rocherons', '', '37380', 'Nouzilly'),
(12, 0xe61000000101000000029eb470599f47401e4e603aaddbea3f, 'Client de larçay', 26, '', 'Rue des Caves à Goûter', '', '37270', 'Larçay'),
(13, 0xe61000000101000000029eb470599f47401e4e603aaddbea3f, 'Client de richelieu', 9, '', 'Route de Chinon', '', '37120', 'Richelieu'),
(14, 0xe61000000101000000029eb470599f47401e4e603aaddbea3f, 'Client de avoine', 33, 'QUATER', 'Avenue de la République', '', '37420', 'Avoine'),
(15, 0xe61000000101000000029eb470599f47401e4e603aaddbea3f, 'Client de ballan-miré', 21, '', 'Rue de l Aigrefin', '', '37510', 'Ballan-Miré'),
(16, 0xe61000000101000000029eb470599f47401e4e603aaddbea3f, 'Client de avrillé-les-ponceaux', 5, 'BIS', 'Route de St Symphorien les Ponce', 'Batiment 1', '37340', 'Avrillé-les-Ponceaux'),
(17, 0xe61000000101000000029eb470599f47401e4e603aaddbea3f, 'Client de larçay', 1, '', 'Rue de la Croix', '', '37270', 'Larçay'),
(18, 0xe61000000101000000029eb470599f47401e4e603aaddbea3f, 'Client de beaumont-en-véron', 13, '', 'Rue des Granderies', '', '37420', 'Beaumont-en-Véron'),
(19, 0xe61000000101000000029eb470599f47401e4e603aaddbea3f, 'Client de chaveignes', 5, '', 'Moulin Pinsard', '', '37120', 'Chaveignes'),
(20, 0xe61000000101000000029eb470599f47401e4e603aaddbea3f, 'Client de château-renault', 11, '', 'Rue de Blois', '', '37110', 'Château-Renault'),
(21, 0xe61000000101000000029eb470599f47401e4e603aaddbea3f, 'Client de chambray-lès-tours', 5, '', 'Allée des Violettes', 'Batiment 2', '37170', 'Chambray-lès-Tours'),
(22, 0xe61000000101000000029eb470599f47401e4e603aaddbea3f, 'Client de descartes', 41, '', 'Rue de la Libération', '', '37160', 'Descartes'),
(23, 0xe61000000101000000029eb470599f47401e4e603aaddbea3f, 'Client de tours', 290, '', 'Rue d Entraigues', '', '37000', 'Tours'),
(24, 0xe61000000101000000029eb470599f47401e4e603aaddbea3f, 'Client de nazelles-négron', 7, 'BIS', 'Allée des Lilas', '', '37530', 'Nazelles-Négron'),
(25, 0xe61000000101000000029eb470599f47401e4e603aaddbea3f, 'Client de loches', 29, '', 'Rue du Mal de Lattre de Tassigny', '', '37600', 'Loches'),
(26, 0xe61000000101000000029eb470599f47401e4e603aaddbea3f, 'Client de saint-étienne-de-chigny', 3, '', 'Rue de la Cueille', '', '37230', 'Saint-Étienne-de-Chigny'),
(27, 0xe61000000101000000029eb470599f47401e4e603aaddbea3f, 'Client de chédigny', 7, '', 'Route du Soleil Levant', '', '37310', 'Chédigny'),
(28, 0xe61000000101000000029eb470599f47401e4e603aaddbea3f, 'Client de nouans-les-fontaines', 11, '', 'Rue Victor Hugo', '', '37460', 'Nouans-les-Fontaines'),
(29, 0xe61000000101000000029eb470599f47401e4e603aaddbea3f, 'Client de la membrolle-sur-choisille', 8, '', 'Rue des Bordes', '', '37390', 'La Membrolle-sur-Choisille'),
(30, 0xe61000000101000000029eb470599f47401e4e603aaddbea3f, 'Client de bournan', 13, '', 'Rue Principale', '', '37240', 'Bournan'),
(31, 0xe61000000101000000029eb470599f47401e4e603aaddbea3f, 'Producteur de amboise', 1346, '', 'Chemin du Roi', '', '37400', 'Amboise'),
(32, 0xe61000000101000000029eb470599f47401e4e603aaddbea3f, 'Producteur de monnaie', 4, '', 'Rue des Chênes', '', '37380', 'Monnaie'),
(33, 0xe61000000101000000029eb470599f47401e4e603aaddbea3f, 'Producteur de tours', 129, '', 'Rue des Bordiers', '', '37100', 'Tours'),
(34, 0xe61000000101000000029eb470599f47401e4e603aaddbea3f, 'Producteur de tours', 45, '', 'Rue Jacques Blanco', '', '37100', 'Tours'),
(35, 0xe61000000101000000029eb470599f47401e4e603aaddbea3f, 'Producteur de notre-dame-d oé', 27, '', 'Rue Gabriella Mistral', '', '37390', 'Notre-Dame-d Oé'),
(36, 0xe61000000101000000029eb470599f47401e4e603aaddbea3f, 'Producteur de betz-le-château', 12, '', 'Rue des Ecoles', '', '37600', 'Betz-le-Château'),
(37, 0xe61000000101000000029eb470599f47401e4e603aaddbea3f, 'Producteur de joué-lès-tours', 11, '', 'Rue du Forgeron', '', '37300', 'Joué-lès-Tours'),
(38, 0xe61000000101000000029eb470599f47401e4e603aaddbea3f, 'Producteur de esvres', 15, 'QUATER', 'Rue de Beauregard', '', '37320', 'Esvres'),
(39, 0xe61000000101000000029eb470599f47401e4e603aaddbea3f, 'Producteur de courçay', 3, '', 'Place de l Eglise', '', '37310', 'Courçay'),
(40, 0xe61000000101000000029eb470599f47401e4e603aaddbea3f, 'Producteur de tours', 19, '', 'Rue de la Tour de Guise', '', '37000', 'Tours'),
(41, 0xe61000000101000000029eb470599f47401e4e603aaddbea3f, 'Producteur de tours', 17, '', 'Rue du chaudron', '', '37100', 'Tours'),
(42, 0xe61000000101000000029eb470599f47401e4e603aaddbea3f, 'Producteur de chanceaux-sur-choisille', 17, '', 'Avenue de Langennerie', '', '37390', 'Chanceaux-sur-Choisille'),
(43, 0xe61000000101000000029eb470599f47401e4e603aaddbea3f, 'Producteur de chemillé-sur-dême', 26, 'TER', 'Rue du Bois de Bourgneuf', '', '37370', 'Chemillé-sur-Dême'),
(44, 0xe61000000101000000029eb470599f47401e4e603aaddbea3f, 'Producteur de saint-christophe-sur-le-nais', 9, '', 'Rue du Vieux Château', '', '37370', 'Saint-Christophe-sur-le-Nais'),
(45, 0xe61000000101000000029eb470599f47401e4e603aaddbea3f, 'Producteur de tauxigny-saint-bauld', 21, '', 'Rue Haute', 'Batiment 1', '37310', 'Tauxigny-Saint-Bauld'),
(46, 0xe61000000101000000029eb470599f47401e4e603aaddbea3f, 'Producteur de tours', 201, '', 'Rue Febvotte', 'Batiment 4', '37000', 'Tours'),
(47, 0xe61000000101000000029eb470599f47401e4e603aaddbea3f, 'Producteur de tournon-saint-pierre', 11, '', 'Route de Preuilly', '', '37290', 'Tournon-Saint-Pierre'),
(48, 0xe61000000101000000029eb470599f47401e4e603aaddbea3f, 'Producteur de saint-paterne-racan', 21, '', 'Rue des Coteaux', 'Batiment 4', '37370', 'Saint-Paterne-Racan'),
(49, 0xe61000000101000000029eb470599f47401e4e603aaddbea3f, 'Producteur de amboise', 3, '', 'Rue Boris Vian', '', '37400', 'Amboise'),
(50, 0xe61000000101000000029eb470599f47401e4e603aaddbea3f, 'Producteur de la tour-saint-gelin', 14, '', 'Rue des Tilleuls', '', '37120', 'La Tour-Saint-Gelin');

-- --------------------------------------------------------

--
-- Structure de la table `clients`
--

CREATE TABLE `clients` (
  `idClient` int(11) NOT NULL,
  `nom` varchar(100) NOT NULL,
  `tel` char(10) DEFAULT NULL,
  `email` varchar(320) DEFAULT NULL,
  `siret` int(11) DEFAULT NULL,
  `particulier` tinyint(1) NOT NULL,
  `idAdresse` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `clients`
--

INSERT INTO `clients` (`idClient`, `nom`, `tel`, `email`, `siret`, `particulier`, `idAdresse`) VALUES
(1, 'Lacroix', '0162891261', 'lacroix43@example.com', 0, 1, 1),
(2, 'Levy', '0168484122', 'levy311@example.com', 2147483647, 0, 2),
(3, 'Picard', '0594112540', 'picard833@example.com', 2147483647, 0, 3),
(4, 'Dumas', '0293277925', 'dumas545@example.com', 2147483647, 0, 4),
(5, 'Jacob', '0484716929', 'jacob752@example.com', 0, 1, 5),
(6, 'Sanchez', '0598147223', 'sanchez447@example.com', 2147483647, 0, 6),
(7, 'Henry', '0422852071', 'henry717@example.com', 2147483647, 0, 7),
(8, 'Daniel', '0222584217', 'daniel746@example.com', 0, 1, 8),
(9, 'Martinez', '0223828779', 'martinez864@example.com', 2147483647, 0, 9),
(10, 'Bouvier', '0574001455', 'bouvier393@example.com', 2147483647, 0, 10),
(11, 'Gautier', '0784051725', 'gautier595@example.com', 2147483647, 0, 11),
(12, 'Le Gall', '0767831969', 'le gall109@example.com', 2147483647, 0, 12),
(13, 'Lemaire', '0202254446', 'lemaire45@example.com', 2147483647, 0, 13),
(14, 'Pasquier', '0717072949', 'pasquier519@example.com', 2147483647, 0, 14),
(15, 'Guyot', '0101290822', 'guyot647@example.com', 0, 1, 15),
(16, 'Perrot', '0212863834', 'perrot77@example.com', 2147483647, 0, 16),
(17, 'Tran', '0552213830', 'tran41@example.com', 2147483647, 0, 17),
(18, 'Francois', '0713677880', 'francois152@example.com', 0, 1, 18),
(19, 'Picard', '0603026797', 'picard879@example.com', 2147483647, 0, 19),
(20, 'Baron', '0315777002', 'baron620@example.com', 2147483647, 0, 20),
(21, 'Meyer', '0538277788', 'meyer521@example.com', 0, 1, 21),
(22, 'Vidal', '0214436949', 'vidal418@example.com', 2147483647, 0, 22),
(23, 'Breton', '0777658640', 'breton750@example.com', 0, 1, 23),
(24, 'Langlois', '0110361633', 'langlois806@example.com', 2147483647, 0, 24),
(25, 'Charles', '0221962708', 'charles554@example.com', 2147483647, 0, 25),
(26, 'Chevalier', '0412124390', 'chevalier172@example.com', 2147483647, 0, 26),
(27, 'Lambert', '0319983295', 'lambert957@example.com', 2147483647, 0, 27),
(28, 'Legrand', '0181383983', 'legrand289@example.com', 2147483647, 0, 28),
(29, 'Leger', '0722710646', 'leger805@example.com', 2147483647, 0, 29),
(30, 'Bousquet', '0522398068', 'bousquet546@example.com', 2147483647, 0, 30),
(31, 'Pereira', '0681161246', 'pereira997@example.com', 2147483647, 0, 31),
(32, 'Bertrand', '0790699569', 'bertrand612@example.com', 0, 1, 32),
(33, 'Fontaine', '0725835507', 'fontaine327@example.com', 2147483647, 0, 33),
(34, 'Meunier', '0121374216', 'meunier685@example.com', 0, 1, 34),
(35, 'Carpentier', '0551853934', 'carpentier185@example.com', 0, 1, 35),
(36, 'Mathieu', '0387060410', 'mathieu127@example.com', 0, 1, 36),
(37, 'Gay', '0115090015', 'gay345@example.com', 2147483647, 0, 37),
(38, 'Pons', '0697257082', 'pons63@example.com', 0, 1, 38),
(39, 'Chevalier', '0103986393', 'chevalier573@example.com', 2147483647, 0, 39),
(40, 'Garnier', '0509086181', 'garnier474@example.com', 2147483647, 0, 40),
(41, 'Fernandez', '0651249257', 'fernandez778@example.com', 2147483647, 0, 41),
(42, 'Nicolas', '0716140106', 'nicolas413@example.com', 2147483647, 0, 42),
(43, 'Robin', '0340903011', 'robin612@example.com', 2147483647, 0, 43),
(44, 'Leger', '0636751372', 'leger887@example.com', 0, 1, 44),
(45, 'Gillet', '0152529764', 'gillet792@example.com', 2147483647, 0, 45),
(46, 'Collin', '0550117715', 'collin506@example.com', 2147483647, 0, 46),
(47, 'Bertrand', '0719023593', 'bertrand412@example.com', 0, 1, 47),
(48, 'Collin', '0722624782', 'collin514@example.com', 2147483647, 0, 48),
(49, 'Etienne', '0620609634', 'etienne998@example.com', 0, 1, 49),
(50, 'Fernandes', '0231048248', 'fernandes196@example.com', 2147483647, 0, 50);

-- --------------------------------------------------------

--
-- Structure de la table `commandes`
--

CREATE TABLE `commandes` (
  `idCommande` int(11) NOT NULL,
  `libelle` varchar(50) DEFAULT NULL,
  `poids` smallint(6) DEFAULT NULL,
  `horaireDebut` time DEFAULT NULL,
  `horaireFin` time DEFAULT NULL,
  `note` text DEFAULT NULL,
  `defautLivraison` tinyint(1) NOT NULL,
  `dateInitiale` date DEFAULT NULL,
  `dateLivraison` datetime DEFAULT NULL,
  `idProducteur` int(11) NOT NULL,
  `idTournee` int(11) DEFAULT NULL,
  `idClient` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `commandes`
--

INSERT INTO `commandes` (`idCommande`, `libelle`, `poids`, `horaireDebut`, `horaireFin`, `note`, `defautLivraison`, `dateInitiale`, `dateLivraison`, `idProducteur`, `idTournee`, `idClient`) VALUES
(1, 'Commande de légumes congelés (épinards, haricots v', 443, '08:45:00', '14:15:00', 'Se souvenir de mettre de côté suffisamment de poulets pour satisfaire la commande de M. Blanc.', 0, '0000-00-00', '0000-00-00 00:00:00', 1, 1, 13),
(2, '', 384, '11:00:00', '16:00:00', 'Se souvenir de mettre de côté suffisamment de poulets pour satisfaire la commande de M. Blanc.', 0, '2022-12-26', '2022-12-26 15:10:00', 2, 2, 19),
(3, 'Commande de miel', 213, '09:45:00', '14:45:00', 'Prévoir de préparer les colis de la commande de Mme Martin avec soin.', 0, '2023-01-01', '2023-01-01 14:30:00', 3, 3, 32),
(4, 'Commande de fruits (pommes, oranges, bananes, etc.', 368, '17:45:00', '17:45:00', '', 1, '2023-01-02', '0000-00-00 00:00:00', 4, 4, 45),
(5, '', 438, '16:30:00', '16:30:00', 'Prévoir de préparer les colis de la commande de Mme Martin avec soin.', 1, '2022-12-23', '0000-00-00 00:00:00', 5, 5, 40),
(6, 'Commande de légumes biologiques (carottes, pommes ', 247, '10:15:00', '15:00:00', '', 0, '0000-00-00', '0000-00-00 00:00:00', 6, 6, 49),
(7, '', 64, '13:15:00', '19:45:00', '', 0, '0000-00-00', '0000-00-00 00:00:00', 7, 7, 26),
(8, 'Commande de vin rouge (cabernet sauvignon, merlot,', 158, '14:00:00', '19:30:00', '', 0, '2022-12-23', '2022-12-23 12:20:00', 8, 8, 46),
(9, 'Commande de lait pasteurisé', 258, '06:15:00', '11:15:00', 'Pensez à demander à Mme Durand si elle souhaite des produits frais ou congelés pour sa commande.', 0, '2022-12-22', '2022-12-22 15:40:00', 9, 9, 25),
(10, 'Commande de lait pasteurisé', 32767, '12:15:00', '18:30:00', 'Vérifier le stock de pommes avant de confirmer la commande de Mme Martin.', 1, '2022-11-29', '0000-00-00 00:00:00', 10, 10, 5),
(11, '', 96, '10:15:00', '15:45:00', 'Se souvenir de mettre de côté suffisamment de vin pour satisfaire la commande de M. Dubois.', 0, '2023-01-13', '2023-01-13 15:20:00', 11, 11, 30),
(12, 'Commande de poulets frais', 490, '09:15:00', '16:15:00', 'Se souvenir de mettre de côté suffisamment de poulets pour satisfaire la commande de M. Blanc.', 0, '2022-12-15', '2022-12-15 14:15:00', 12, 12, 45),
(13, 'Commande de poulets frais', 485, '12:00:00', '12:00:00', 'Prévoir de préparer les colis de la commande de Mme Martin avec soin.', 1, '2022-11-28', '0000-00-00 00:00:00', 13, 13, 34),
(14, '', 34, '07:00:00', '12:15:00', '', 0, '0000-00-00', '0000-00-00 00:00:00', 14, 14, 45),
(15, 'Commande de fromage (emmental, gorgonzola, chèvre,', 82, '12:45:00', '18:00:00', '', 0, '2022-11-22', '2022-11-22 14:55:00', 15, 15, 33),
(16, 'Commande de vin rouge (cabernet sauvignon, merlot,', 330, '08:45:00', '14:00:00', 'Prévoir de mettre de côté suffisamment de fromage pour satisfaire la commande de M. Robin.', 0, '2022-11-04', '2022-11-04 15:30:00', 16, 16, 8),
(17, '', 46, '12:30:00', '18:00:00', 'Prévoir de mettre de côté suffisamment de miel pour satisfaire la commande de Mme Durand.', 0, '2022-11-22', '2022-11-22 14:35:00', 17, 17, 41),
(18, 'Commande de viande de poulet (poulet entier, ailes', 335, '12:00:00', '17:15:00', '', 0, '0000-00-00', '0000-00-00 00:00:00', 18, 18, 31),
(19, '', 407, '11:00:00', '16:00:00', 'Appeler M. Dupont pour confirmer la commande de lait pour le 15 juin.', 0, '2023-01-09', '2023-01-09 12:40:00', 19, 19, 14),
(20, 'Commande de viande de poulet (poulet entier, ailes', 11, '06:15:00', '13:15:00', '', 0, '2022-12-08', '2022-12-08 15:35:00', 20, 20, 29),
(21, 'Commande de jus de fruit (orange, pomme, raisin, e', 461, '07:45:00', '13:00:00', '', 0, '2023-01-03', '2023-01-03 12:10:00', 1, 21, 25),
(22, '', 123, '14:15:00', '19:15:00', '', 0, '2022-11-09', '2022-11-09 12:30:00', 2, 22, 24),
(23, 'Commande de légumes en conserve (tomates, haricots', 32767, '07:30:00', '13:00:00', 'Se souvenir de mettre de côté suffisamment de confitures pour satisfaire la commande de Mme Dupont.', 1, '2022-11-01', '0000-00-00 00:00:00', 3, 23, 29),
(24, '', 199, '16:30:00', '16:30:00', '', 1, '2022-12-16', '0000-00-00 00:00:00', 4, 24, 10),
(25, 'Commande de viande de boeuf (filet, rôti, steak ha', 405, '13:00:00', '18:15:00', 'Vérifier le stock de pommes avant de confirmer la commande de Mme Martin.', 0, '2023-01-10', '2023-01-10 13:50:00', 5, 25, 47),
(26, 'Commande de légumes en conserve (tomates, haricots', 452, '08:30:00', '15:30:00', '', 0, '2022-11-02', '2022-11-02 15:05:00', 6, 26, 24),
(27, 'Commande de jus de fruit (orange, pomme, raisin, e', 32767, '06:45:00', '13:45:00', '', 1, '2022-11-14', '0000-00-00 00:00:00', 7, 27, 10),
(28, '', 282, '07:30:00', '14:30:00', 'Prévoir de mettre de côté suffisamment de miel pour satisfaire la commande de Mme Durand.', 0, '2022-12-21', '2022-12-21 12:35:00', 8, 28, 25),
(29, 'Commande de légumes biologiques (carottes, pommes ', 424, '11:45:00', '17:45:00', 'Se souvenir de mettre de côté suffisamment de vin pour satisfaire la commande de M. Dubois.', 0, '2022-12-07', '2022-12-07 13:10:00', 9, 29, 8),
(30, 'Commande de jus de fruit (orange, pomme, raisin, e', 173, '12:45:00', '17:15:00', 'Se souvenir de mettre de côté suffisamment de poissons pour satisfaire la commande de M. Dubois.', 0, '2022-12-10', '2022-12-10 12:35:00', 10, 30, 30),
(31, '', 402, '08:45:00', '14:30:00', 'Se souvenir de mettre de côté suffisamment de poissons pour satisfaire la commande de M. Dubois.', 0, '2022-11-18', '2022-11-18 12:30:00', 1, 31, 34),
(32, 'Commande de produits laitiers (beurre, crème, yaou', 491, '12:45:00', '17:15:00', 'Appeler M. Dupont pour confirmer la commande de lait pour le 15 juin.', 0, '2022-11-27', '2022-11-27 15:45:00', 2, 32, 2),
(33, '', 380, '08:00:00', '15:15:00', '', 0, '0000-00-00', '0000-00-00 00:00:00', 3, 33, 31),
(34, 'Commande de confitures (fraise, abricot, mûre, etc', 132, '11:45:00', '17:00:00', '', 0, '2022-11-09', '2022-11-09 15:40:00', 4, 34, 49),
(35, 'Commande de vin rouge (cabernet sauvignon, merlot,', 301, '07:15:00', '12:15:00', 'Préparer les documents nécessaires pour la livraison de la commande de M. Blanc à la coopérative locale.', 0, '2022-12-18', '2022-12-18 12:15:00', 5, 35, 31),
(36, 'Commande de jus de fruit (orange, pomme, raisin, e', 405, '10:45:00', '15:00:00', '', 0, '2022-11-26', '2022-11-26 12:15:00', 6, 36, 4),
(37, 'Commande de lait pasteurisé', 29, '13:45:00', '20:00:00', '', 0, '2023-01-11', '2023-01-11 12:50:00', 7, 37, 7),
(38, 'Commande de viande de poulet (poulet entier, ailes', 73, '13:00:00', '18:15:00', 'Préparer les étiquettes et les factures pour la commande de Mme Martin avant la livraison.', 0, '2022-12-02', '2022-12-02 12:00:00', 8, 38, 20),
(39, 'Commande de viande de boeuf (filet, rôti, steak ha', 294, '06:15:00', '13:30:00', 'Préparer les documents nécessaires pour la livraison de la commande de M. Blanc à la coopérative locale.', 0, '2022-11-11', '2022-11-11 12:00:00', 9, 39, 24),
(40, 'Commande de produits laitiers (beurre, crème, yaou', 155, '09:30:00', '14:00:00', 'Prévoir de mettre de côté suffisamment de fromage pour satisfaire la commande de M. Robin.', 0, '2023-01-10', '2023-01-10 15:40:00', 10, 40, 45),
(41, '', 7, '13:45:00', '18:45:00', 'Prévoir de préparer les colis de la commande de Mme Martin avec soin.', 0, '2022-11-05', '2022-11-05 14:30:00', 1, 41, 24),
(42, '', 108, '12:15:00', '18:30:00', 'Pensez à demander à M. Dubois s\'il souhaite ajouter des œufs à sa commande de légumes.', 0, '2022-12-10', '2022-12-10 12:00:00', 2, 42, 32),
(43, '', 374, '14:30:00', '19:30:00', 'Appeler M. Dupont pour confirmer la commande de lait pour le 15 juin.', 0, '2022-12-15', '2022-12-15 15:00:00', 3, 43, 16),
(44, '', 348, '08:45:00', '14:15:00', 'Appeler M. Dupont pour confirmer la commande de lait pour le 15 juin.', 0, '2023-01-13', '2023-01-13 12:55:00', 4, 44, 23),
(45, 'Commande de poulets frais', 81, '08:15:00', '13:15:00', 'Se souvenir de mettre de côté suffisamment de poissons pour satisfaire la commande de M. Dubois.', 0, '2023-01-07', '2023-01-07 13:40:00', 5, 45, 40),
(46, 'Commande de légumes congelés (épinards, haricots v', 329, '14:30:00', '21:30:00', 'Se souvenir de mettre de côté suffisamment de confitures pour satisfaire la commande de Mme Dupont.', 0, '2023-01-09', '2023-01-09 12:40:00', 6, 46, 25),
(47, '', 129, '14:45:00', '20:30:00', '', 0, '0000-00-00', '0000-00-00 00:00:00', 7, 47, 20),
(48, '', 166, '19:15:00', '19:15:00', '', 1, '2022-11-15', '0000-00-00 00:00:00', 8, 48, 3),
(49, 'Commande de viande de porc (jambon, saucisses, côt', 388, '10:30:00', '15:00:00', '', 0, '2022-12-29', '2022-12-29 15:25:00', 9, 49, 17),
(50, '', 369, '12:15:00', '17:00:00', 'Se souvenir de mettre de côté suffisamment de vin pour satisfaire la commande de M. Dubois.', 0, '2022-12-13', '2022-12-13 15:30:00', 10, 50, 28),
(51, 'Commande de viande de poulet (poulet entier, ailes', 76, '12:30:00', '12:30:00', 'Prévoir de réserver un espace de stockage pour la commande de M. Leroy avant la récolte.', 1, '2023-01-09', '0000-00-00 00:00:00', 1, 1, 23),
(52, 'Commande de produits de boulangerie (pain, croissa', 491, '09:00:00', '14:45:00', '', 0, '2022-12-06', '2022-12-06 13:20:00', 2, 2, 49),
(53, 'Commande de fromage de brebis', 130, '12:30:00', '18:00:00', 'Pensez à demander à Mme Durand si elle souhaite des produits frais ou congelés pour sa commande.', 0, '2023-01-06', '2023-01-06 12:10:00', 3, 3, 18),
(54, 'Commande de miel', 420, '12:30:00', '17:45:00', 'Pensez à demander à M. Dubois s\'il souhaite ajouter des œufs à sa commande de légumes.', 0, '0000-00-00', '0000-00-00 00:00:00', 4, 4, 6),
(55, 'Commande de poulets frais', 107, '06:45:00', '11:30:00', 'Pensez à demander à M. Blanc s\'il souhaite ajouter des produits laitiers à sa commande de légumes.', 0, '2023-01-02', '2023-01-02 14:50:00', 5, 5, 33),
(56, 'Commande de poulets frais', 272, '08:00:00', '14:00:00', 'Se souvenir de mettre de côté suffisamment de confitures pour satisfaire la commande de Mme Dupont.', 0, '2022-11-13', '2022-11-13 14:15:00', 6, 6, 30),
(57, '', 216, '09:30:00', '15:45:00', 'Pensez à demander à M. Dubois s\'il souhaite ajouter des œufs à sa commande de légumes.', 0, '0000-00-00', '0000-00-00 00:00:00', 7, 7, 2),
(58, '', 244, '09:30:00', '16:00:00', '', 0, '0000-00-00', '0000-00-00 00:00:00', 8, 8, 39),
(59, '', 364, '14:45:00', '21:45:00', 'Se souvenir de mettre de côté suffisamment de poulets pour satisfaire la commande de M. Blanc.', 0, '0000-00-00', '0000-00-00 00:00:00', 9, 9, 48),
(60, 'Commande de fromage (emmental, gorgonzola, chèvre,', 32767, '13:00:00', '18:45:00', '', 1, '2022-11-10', '0000-00-00 00:00:00', 10, 10, 5),
(61, 'Commande de produits laitiers (beurre, crème, yaou', 205, '12:30:00', '17:45:00', 'Se souvenir de mettre de côté suffisamment de vin pour satisfaire la commande de M. Dubois.', 0, '2022-11-18', '2022-11-18 13:00:00', 11, 11, 39),
(62, 'Commande de lait pasteurisé', 19, '07:30:00', '13:15:00', '', 0, '0000-00-00', '0000-00-00 00:00:00', 12, 12, 9),
(63, '', 293, '09:45:00', '15:45:00', 'Préparer les documents nécessaires pour la livraison de la commande de M. Blanc à la coopérative locale.', 0, '2022-11-26', '2022-11-26 14:05:00', 13, 13, 16),
(64, 'Commande de lait pasteurisé', 283, '13:30:00', '19:00:00', 'Confirmer auprès de M. Robin la date de livraison de sa commande de fromage.', 0, '2022-11-04', '2022-11-04 15:00:00', 14, 14, 36),
(65, 'Commande de fruits (pommes, oranges, bananes, etc.', 144, '09:00:00', '15:15:00', '', 0, '0000-00-00', '0000-00-00 00:00:00', 15, 15, 45),
(66, '', 45, '09:30:00', '14:45:00', '', 0, '0000-00-00', '0000-00-00 00:00:00', 16, 16, 29),
(67, 'Commande de confitures (fraise, abricot, mûre, etc', 154, '06:30:00', '11:45:00', '', 0, '2022-11-24', '2022-11-24 15:20:00', 17, 17, 41),
(68, 'Commande de fromage de chèvre', 164, '07:45:00', '12:30:00', 'Appeler M. Dupont pour confirmer la commande de lait pour le 15 juin.', 0, '0000-00-00', '0000-00-00 00:00:00', 18, 18, 1),
(69, '', 17, '07:00:00', '14:00:00', 'Se souvenir de mettre de côté suffisamment de confitures pour satisfaire la commande de Mme Dupont.', 0, '0000-00-00', '0000-00-00 00:00:00', 19, 19, 49),
(70, '', 115, '15:30:00', '15:30:00', '', 1, '2022-12-20', '0000-00-00 00:00:00', 20, 20, 22),
(71, 'Commande de légumes biologiques (carottes, pommes ', 178, '06:45:00', '11:15:00', 'Pensez à demander à M. Dubois s\'il souhaite ajouter des œufs à sa commande de légumes.', 0, '0000-00-00', '0000-00-00 00:00:00', 1, 21, 46),
(72, '', 465, '11:45:00', '16:30:00', 'Se souvenir de mettre de côté suffisamment de poulets pour satisfaire la commande de M. Blanc.', 0, '0000-00-00', '0000-00-00 00:00:00', 2, 22, 1),
(73, 'Commande de jus de fruit (orange, pomme, raisin, e', 32767, '07:45:00', '13:45:00', '', 1, '2022-12-15', '0000-00-00 00:00:00', 3, 23, 20),
(74, 'Commande de légumes en conserve (tomates, haricots', 361, '08:45:00', '13:00:00', 'Se souvenir de mettre de côté suffisamment de poissons pour satisfaire la commande de M. Dubois.', 0, '0000-00-00', '0000-00-00 00:00:00', 4, 24, 30),
(75, 'Commande de œufs frais', 32767, '06:15:00', '11:45:00', 'Se souvenir de mettre de côté suffisamment de poulets pour satisfaire la commande de M. Blanc.', 1, '2023-01-11', '0000-00-00 00:00:00', 5, 25, 38),
(76, '', 239, '12:00:00', '19:00:00', '', 0, '0000-00-00', '0000-00-00 00:00:00', 6, 26, 31),
(77, 'Commande de produits laitiers (beurre, crème, yaou', 175, '14:45:00', '14:45:00', 'Préparer les documents nécessaires pour la livraison de la commande de M. Blanc à la coopérative locale.', 1, '2022-12-27', '0000-00-00 00:00:00', 7, 27, 35),
(78, 'Commande de vin rouge (cabernet sauvignon, merlot,', 429, '10:30:00', '15:15:00', 'Appeler M. Dupont pour confirmer la commande de lait pour le 15 juin.', 0, '0000-00-00', '0000-00-00 00:00:00', 8, 28, 28),
(79, '', 187, '12:15:00', '19:00:00', 'Pensez à demander à Mme Durand si elle souhaite des produits frais ou congelés pour sa commande.', 0, '0000-00-00', '0000-00-00 00:00:00', 9, 29, 19),
(80, '', 155, '13:30:00', '18:45:00', '', 0, '2022-11-02', '2022-11-02 14:40:00', 10, 30, 16),
(81, 'Commande de jus de fruit (orange, pomme, raisin, e', 424, '06:15:00', '12:15:00', 'Se souvenir de mettre de côté suffisamment de poulets pour satisfaire la commande de M. Blanc.', 0, '2022-11-02', '2022-11-02 13:05:00', 1, 31, 26),
(82, 'Commande de miel', 91, '07:30:00', '12:00:00', 'Pensez à demander à M. Blanc s\'il souhaite ajouter des produits laitiers à sa commande de légumes.', 0, '0000-00-00', '0000-00-00 00:00:00', 2, 32, 15),
(83, '', 108, '21:00:00', '21:00:00', 'Appeler M. Dupont pour confirmer la commande de lait pour le 15 juin.', 1, '2022-11-22', '0000-00-00 00:00:00', 3, 33, 43),
(84, '', 114, '13:00:00', '18:00:00', 'Confirmer auprès de M. Robin la date de livraison de sa commande de fromage.', 0, '0000-00-00', '0000-00-00 00:00:00', 4, 34, 10),
(85, '', 241, '13:15:00', '19:45:00', 'Pensez à demander à M. Dubois s\'il souhaite ajouter des œufs à sa commande de légumes.', 0, '0000-00-00', '0000-00-00 00:00:00', 5, 35, 37),
(86, 'Commande de poissons frais (saumon, truite, etc.)', 229, '14:30:00', '21:00:00', 'Vérifier le stock de pommes avant de confirmer la commande de Mme Martin.', 0, '0000-00-00', '0000-00-00 00:00:00', 6, 36, 18),
(87, '', 101, '13:45:00', '19:15:00', '', 0, '2022-11-07', '2022-11-07 14:30:00', 7, 37, 43),
(88, 'Commande de fromage de brebis', 32767, '11:00:00', '16:15:00', 'Vérifier le stock de pommes avant de confirmer la commande de Mme Martin.', 1, '2022-11-18', '0000-00-00 00:00:00', 8, 38, 32),
(89, 'Commande de poulets frais', 336, '14:45:00', '19:30:00', 'Préparer les documents nécessaires pour la livraison de la commande de M. Blanc à la coopérative locale.', 0, '0000-00-00', '0000-00-00 00:00:00', 9, 39, 37),
(90, 'Commande de légumes en conserve (tomates, haricots', 228, '10:45:00', '15:45:00', '', 0, '2023-01-06', '2023-01-06 13:00:00', 10, 40, 44),
(91, '', 131, '10:15:00', '17:30:00', '', 0, '2022-11-11', '2022-11-11 13:10:00', 1, 41, 42),
(92, 'Commande de légumes congelés (épinards, haricots v', 416, '13:00:00', '20:45:00', '', 0, '0000-00-00', '0000-00-00 00:00:00', 2, 42, 30),
(93, 'Commande de confitures (fraise, abricot, mûre, etc', 443, '08:15:00', '13:15:00', '', 0, '0000-00-00', '0000-00-00 00:00:00', 3, 43, 22),
(94, 'Commande de jus de fruit (orange, pomme, raisin, e', 448, '16:00:00', '16:00:00', '', 1, '2022-12-25', '0000-00-00 00:00:00', 4, 44, 28),
(95, 'Commande de produits de boulangerie (pain, croissa', 316, '08:15:00', '15:15:00', 'Appeler M. Dupont pour confirmer la commande de lait pour le 15 juin.', 0, '2022-11-06', '2022-11-06 12:15:00', 5, 45, 30),
(96, '', 189, '09:45:00', '14:30:00', 'Vérifier le stock de pommes avant de confirmer la commande de Mme Martin.', 0, '2022-12-28', '2022-12-28 12:40:00', 6, 46, 5),
(97, 'Commande de produits laitiers (beurre, crème, yaou', 22, '11:30:00', '16:00:00', 'Prévoir de préparer les colis de la commande de Mme Martin avec soin.', 0, '2022-11-24', '2022-11-24 12:10:00', 7, 47, 32),
(98, 'Commande de viande de porc (jambon, saucisses, côt', 48, '14:30:00', '21:30:00', 'Prévoir de préparer les colis de la commande de Mme Martin avec soin.', 0, '2023-01-03', '2023-01-03 15:40:00', 8, 48, 9),
(99, 'Commande de poulets frais', 155, '14:30:00', '21:45:00', 'Se souvenir de mettre de côté suffisamment de confitures pour satisfaire la commande de Mme Dupont.', 0, '0000-00-00', '0000-00-00 00:00:00', 9, 49, 22),
(100, 'Commande de œufs frais', 134, '13:00:00', '19:00:00', 'Pensez à demander à M. Dubois s\'il souhaite ajouter des œufs à sa commande de légumes.', 0, '2022-11-15', '2022-11-15 13:00:00', 10, 50, 29),
(101, 'Commande de jus de fruit (orange, pomme, raisin, e', 483, '14:45:00', '19:45:00', 'Prévoir de mettre de côté suffisamment de miel pour satisfaire la commande de Mme Durand.', 0, '2022-11-20', '2022-11-20 14:50:00', 1, 1, 36),
(102, 'Commande de confitures (fraise, abricot, mûre, etc', 433, '12:15:00', '17:15:00', 'Rappeler à M. Leroy de régler sa commande de viande avant la livraison prévue le 22 juin.', 0, '0000-00-00', '0000-00-00 00:00:00', 2, 2, 3),
(103, '', 140, '08:15:00', '14:00:00', '', 0, '2022-11-12', '2022-11-12 13:55:00', 3, 3, 21),
(104, 'Commande de œufs frais', 32767, '08:45:00', '15:00:00', 'Préparer les étiquettes et les factures pour la commande de Mme Martin avant la livraison.', 1, '2022-11-07', '0000-00-00 00:00:00', 4, 4, 41),
(105, 'Commande de viande de porc (jambon, saucisses, côt', 433, '14:45:00', '20:15:00', '', 0, '2022-11-18', '2022-11-18 15:50:00', 5, 5, 28),
(106, 'Commande de miel', 289, '13:45:00', '19:30:00', 'Vérifier le stock de pommes avant de confirmer la commande de Mme Martin.', 0, '0000-00-00', '0000-00-00 00:00:00', 6, 6, 32),
(107, 'Commande de poissons frais (saumon, truite, etc.)', 258, '10:00:00', '16:15:00', '', 0, '2022-11-13', '2022-11-13 14:00:00', 7, 7, 40),
(108, 'Commande de viande de porc (jambon, saucisses, côt', 389, '12:45:00', '19:00:00', '', 0, '0000-00-00', '0000-00-00 00:00:00', 8, 8, 2),
(109, '', 222, '08:30:00', '13:45:00', '', 0, '2023-01-14', '2023-01-14 15:05:00', 9, 9, 6),
(110, 'Commande de légumes biologiques (carottes, pommes ', 414, '13:45:00', '20:45:00', 'Se souvenir de mettre de côté suffisamment de poulets pour satisfaire la commande de M. Blanc.', 0, '0000-00-00', '0000-00-00 00:00:00', 10, 10, 18),
(111, 'Commande de fruits (pommes, oranges, bananes, etc.', 85, '14:30:00', '20:15:00', 'Se souvenir de mettre de côté suffisamment de poulets pour satisfaire la commande de M. Blanc.', 0, '2023-01-09', '2023-01-09 15:00:00', 11, 11, 15),
(112, '', 135, '14:30:00', '20:00:00', 'Prévoir de mettre de côté suffisamment de fromage pour satisfaire la commande de M. Robin.', 0, '2022-12-30', '2022-12-30 15:15:00', 12, 12, 2),
(113, 'Commande de lait pasteurisé', 454, '12:45:00', '19:15:00', '', 0, '0000-00-00', '0000-00-00 00:00:00', 13, 13, 10),
(114, 'Commande de produits laitiers (beurre, crème, yaou', 32767, '09:45:00', '16:30:00', 'Prévoir de mettre de côté assez de légumes biologiques pour satisfaire la commande de Mme Durand.', 1, '2023-01-01', '0000-00-00 00:00:00', 14, 14, 37),
(115, 'Commande de fromage de brebis', 403, '13:15:00', '18:30:00', 'Se souvenir de mettre de côté suffisamment de poulets pour satisfaire la commande de M. Blanc.', 0, '2023-01-10', '2023-01-10 12:25:00', 15, 15, 20),
(116, '', 228, '13:15:00', '20:00:00', 'Pensez à demander à Mme Durand si elle souhaite des produits frais ou congelés pour sa commande.', 0, '2022-12-27', '2022-12-27 13:15:00', 16, 16, 45),
(117, '', 35, '10:30:00', '17:30:00', 'Confirmer auprès de M. Robin la date de livraison de sa commande de fromage.', 0, '2022-11-10', '2022-11-10 15:30:00', 17, 17, 25),
(118, 'Commande de jus de fruit (orange, pomme, raisin, e', 109, '13:30:00', '18:00:00', 'Pensez à demander à M. Dubois s\'il souhaite ajouter des œufs à sa commande de légumes.', 0, '2023-01-06', '2023-01-06 15:55:00', 18, 18, 39),
(119, 'Commande de fromage de brebis', 202, '13:30:00', '19:30:00', '', 0, '2022-11-10', '2022-11-10 14:35:00', 19, 19, 49),
(120, 'Commande de viande de boeuf (filet, rôti, steak ha', 21, '12:45:00', '19:00:00', 'Prévoir de préparer les colis de la commande de Mme Martin avec soin.', 0, '2022-12-08', '2022-12-08 13:00:00', 20, 20, 47),
(121, '', 402, '07:30:00', '13:45:00', 'Rappeler à M. Leroy de régler sa commande de viande avant la livraison prévue le 22 juin.', 0, '2022-11-30', '2022-11-30 13:55:00', 1, 21, 16),
(122, 'Commande de miel', 437, '06:45:00', '13:15:00', 'Prévoir de mettre de côté suffisamment de fromage pour satisfaire la commande de M. Robin.', 0, '2022-11-11', '2022-11-11 13:40:00', 2, 22, 45),
(123, 'Commande de viande de boeuf (filet, rôti, steak ha', 421, '09:30:00', '15:30:00', 'Se souvenir de mettre de côté suffisamment de poulets pour satisfaire la commande de M. Blanc.', 0, '2023-01-13', '2023-01-13 15:35:00', 3, 23, 30),
(124, '', 32767, '10:00:00', '16:00:00', 'Préparer les étiquettes et les factures pour la commande de Mme Martin avant la livraison.', 1, '2022-11-05', '0000-00-00 00:00:00', 4, 24, 46),
(125, '', 87, '13:45:00', '19:15:00', 'Prévoir de mettre de côté assez de légumes biologiques pour satisfaire la commande de Mme Durand.', 0, '2022-12-07', '2022-12-07 14:05:00', 5, 25, 27),
(126, 'Commande de fromage (emmental, gorgonzola, chèvre,', 31, '08:30:00', '13:45:00', 'Préparer les étiquettes et les factures pour la commande de Mme Martin avant la livraison.', 0, '0000-00-00', '0000-00-00 00:00:00', 6, 26, 8),
(127, 'Commande de viande de boeuf (filet, rôti, steak ha', 70, '14:00:00', '20:15:00', 'Prévoir de mettre de côté suffisamment de miel pour satisfaire la commande de Mme Durand.', 0, '0000-00-00', '0000-00-00 00:00:00', 7, 27, 32),
(128, '', 52, '14:45:00', '19:30:00', '', 0, '0000-00-00', '0000-00-00 00:00:00', 8, 28, 4),
(129, '', 50, '11:30:00', '18:15:00', 'Prévoir de réserver un espace de stockage pour la commande de M. Leroy avant la récolte.', 0, '2022-12-19', '2022-12-19 12:30:00', 9, 29, 44),
(130, 'Commande de poulets frais', 58, '13:15:00', '20:00:00', '', 0, '0000-00-00', '0000-00-00 00:00:00', 10, 30, 5),
(131, 'Commande de viande de boeuf (filet, rôti, steak ha', 11, '08:45:00', '15:30:00', 'Pensez à demander à M. Blanc s\'il souhaite ajouter des produits laitiers à sa commande de légumes.', 0, '2022-12-19', '2022-12-19 14:50:00', 1, 31, 6),
(132, 'Commande de légumes congelés (épinards, haricots v', 36, '12:15:00', '18:30:00', 'Pensez à demander à M. Dubois s\'il souhaite ajouter des œufs à sa commande de légumes.', 0, '2022-11-18', '2022-11-18 14:25:00', 2, 32, 33),
(133, 'Commande de fromage (emmental, gorgonzola, chèvre,', 173, '11:45:00', '18:15:00', '', 0, '2023-01-07', '2023-01-07 15:00:00', 3, 33, 30),
(134, 'Commande de poissons frais (saumon, truite, etc.)', 386, '12:15:00', '18:00:00', 'Se souvenir de mettre de côté suffisamment de poulets pour satisfaire la commande de M. Blanc.', 0, '2022-11-26', '2022-11-26 15:50:00', 4, 34, 37),
(135, '', 448, '16:30:00', '16:30:00', 'Prévoir de réserver un espace de stockage pour la commande de M. Leroy avant la récolte.', 1, '2023-01-12', '0000-00-00 00:00:00', 5, 35, 40),
(136, 'Commande de légumes en conserve (tomates, haricots', 234, '07:15:00', '13:30:00', 'Vérifier le stock de pommes avant de confirmer la commande de Mme Martin.', 0, '0000-00-00', '0000-00-00 00:00:00', 6, 36, 47),
(137, 'Commande de confitures (fraise, abricot, mûre, etc', 386, '08:00:00', '15:00:00', '', 0, '2022-11-03', '2022-11-03 14:45:00', 7, 37, 14),
(138, 'Commande de viande de porc (jambon, saucisses, côt', 343, '07:45:00', '14:30:00', 'Vérifier le stock de pommes avant de confirmer la commande de Mme Martin.', 0, '2022-11-13', '2022-11-13 12:00:00', 8, 38, 2),
(139, '', 255, '09:15:00', '15:00:00', '', 0, '2023-01-01', '2023-01-01 12:10:00', 9, 39, 9),
(140, 'Commande de viande de porc (jambon, saucisses, côt', 321, '11:45:00', '16:15:00', 'Se souvenir de mettre de côté suffisamment de vin pour satisfaire la commande de M. Dubois.', 0, '2022-11-10', '2022-11-10 15:30:00', 10, 40, 50),
(141, 'Commande de produits de boulangerie (pain, croissa', 32767, '14:00:00', '19:00:00', '', 1, '2022-12-30', '0000-00-00 00:00:00', 1, 41, 38),
(142, 'Commande de lait pasteurisé', 173, '08:30:00', '14:00:00', '', 0, '0000-00-00', '0000-00-00 00:00:00', 2, 42, 15),
(143, 'Commande de produits laitiers (beurre, crème, yaou', 206, '06:15:00', '11:30:00', 'Prévoir de réserver un espace de stockage pour la commande de M. Leroy avant la récolte.', 0, '0000-00-00', '0000-00-00 00:00:00', 3, 43, 16),
(144, 'Commande de jus de fruit (orange, pomme, raisin, e', 446, '12:15:00', '18:15:00', 'Rappeler à M. Leroy de régler sa commande de viande avant la livraison prévue le 22 juin.', 0, '0000-00-00', '0000-00-00 00:00:00', 4, 44, 29),
(145, 'Commande de fruits (pommes, oranges, bananes, etc.', 32767, '10:45:00', '17:30:00', 'Vérifier le stock de pommes avant de confirmer la commande de Mme Martin.', 1, '2022-12-23', '0000-00-00 00:00:00', 5, 45, 34),
(146, 'Commande de viande de porc (jambon, saucisses, côt', 65, '12:15:00', '12:15:00', '', 1, '2022-11-02', '0000-00-00 00:00:00', 6, 46, 44),
(147, 'Commande de poulets frais', 183, '08:15:00', '15:45:00', 'Préparer les documents nécessaires pour la livraison de la commande de M. Blanc à la coopérative locale.', 0, '2022-12-25', '2022-12-25 14:05:00', 7, 47, 43),
(148, 'Commande de fromage de chèvre', 95, '11:00:00', '16:15:00', 'Prévoir de préparer les colis de la commande de Mme Martin avec soin.', 0, '2023-01-08', '2023-01-08 13:45:00', 8, 48, 7),
(149, 'Commande de miel', 76, '12:15:00', '18:45:00', '', 0, '0000-00-00', '0000-00-00 00:00:00', 9, 49, 30),
(150, '', 231, '18:00:00', '18:00:00', 'Prévoir de mettre de côté assez de légumes biologiques pour satisfaire la commande de Mme Durand.', 1, '2022-11-14', '0000-00-00 00:00:00', 10, 50, 40),
(151, '', 287, '12:15:00', '19:30:00', 'Pensez à demander à M. Dubois s\'il souhaite ajouter des œufs à sa commande de légumes.', 0, '0000-00-00', '0000-00-00 00:00:00', 1, 1, 3),
(152, 'Commande de miel', 417, '14:15:00', '21:30:00', '', 0, '2022-12-15', '2022-12-15 14:25:00', 2, 2, 11),
(153, 'Commande de produits de boulangerie (pain, croissa', 429, '08:00:00', '15:45:00', 'Prévoir de préparer les colis de la commande de Mme Martin avec soin.', 0, '2023-01-06', '2023-01-06 12:35:00', 3, 3, 14),
(154, 'Commande de fromage (emmental, gorgonzola, chèvre,', 108, '12:00:00', '17:45:00', '', 0, '0000-00-00', '0000-00-00 00:00:00', 4, 4, 31),
(155, '', 197, '06:15:00', '12:30:00', '', 0, '0000-00-00', '0000-00-00 00:00:00', 5, 5, 25),
(156, 'Commande de produits laitiers (beurre, crème, yaou', 5, '15:30:00', '15:30:00', 'Se souvenir de mettre de côté suffisamment de vin pour satisfaire la commande de M. Dubois.', 1, '2022-11-28', '0000-00-00 00:00:00', 6, 6, 47),
(157, 'Commande de légumes en conserve (tomates, haricots', 368, '13:30:00', '19:15:00', 'Prévoir de mettre de côté assez de légumes biologiques pour satisfaire la commande de Mme Durand.', 0, '0000-00-00', '0000-00-00 00:00:00', 7, 7, 13),
(158, '', 77, '10:00:00', '16:00:00', '', 0, '2022-12-12', '2022-12-12 15:20:00', 8, 8, 34),
(159, 'Commande de viande de poulet (poulet entier, ailes', 147, '13:15:00', '13:15:00', 'Pensez à demander à M. Dubois s\'il souhaite ajouter des œufs à sa commande de légumes.', 1, '2022-12-27', '0000-00-00 00:00:00', 9, 9, 18),
(160, '', 258, '09:00:00', '14:15:00', 'Appeler M. Dupont pour confirmer la commande de lait pour le 15 juin.', 0, '0000-00-00', '0000-00-00 00:00:00', 10, 10, 14),
(161, 'Commande de vin rouge (cabernet sauvignon, merlot,', 200, '13:00:00', '19:45:00', 'Se souvenir de mettre de côté suffisamment de vin pour satisfaire la commande de M. Dubois.', 0, '2022-11-23', '2022-11-23 13:20:00', 11, 11, 12),
(162, '', 49, '08:30:00', '15:00:00', '', 0, '0000-00-00', '0000-00-00 00:00:00', 12, 12, 3),
(163, 'Commande de confitures (fraise, abricot, mûre, etc', 186, '08:15:00', '13:00:00', 'Pensez à demander à M. Blanc s\'il souhaite ajouter des produits laitiers à sa commande de légumes.', 0, '0000-00-00', '0000-00-00 00:00:00', 13, 13, 30),
(164, 'Commande de lait pasteurisé', 25, '11:00:00', '16:15:00', '', 0, '2022-12-25', '2022-12-25 14:50:00', 14, 14, 42),
(165, 'Commande de viande de poulet (poulet entier, ailes', 424, '08:00:00', '13:00:00', 'Pensez à demander à M. Blanc s\'il souhaite ajouter des produits laitiers à sa commande de légumes.', 0, '0000-00-00', '0000-00-00 00:00:00', 15, 15, 14),
(166, 'Commande de vin rouge (cabernet sauvignon, merlot,', 437, '11:30:00', '17:15:00', 'Se souvenir de mettre de côté suffisamment de poulets pour satisfaire la commande de M. Blanc.', 0, '2023-01-15', '2023-01-15 13:30:00', 16, 16, 24),
(167, 'Commande de fromage de brebis', 472, '08:30:00', '15:30:00', '', 0, '2022-12-21', '2022-12-21 12:30:00', 17, 17, 12),
(168, '', 494, '06:15:00', '12:45:00', 'Appeler M. Dupont pour confirmer la commande de lait pour le 15 juin.', 0, '2022-12-30', '2022-12-30 15:50:00', 18, 18, 27),
(169, 'Commande de fromage (emmental, gorgonzola, chèvre,', 118, '11:00:00', '16:30:00', 'Se souvenir de mettre de côté suffisamment de poissons pour satisfaire la commande de M. Dubois.', 0, '2022-12-03', '2022-12-03 15:00:00', 19, 19, 7),
(170, 'Commande de fromage de brebis', 265, '14:15:00', '21:45:00', 'Préparer les étiquettes et les factures pour la commande de Mme Martin avant la livraison.', 0, '0000-00-00', '0000-00-00 00:00:00', 20, 20, 43),
(171, '', 468, '14:30:00', '14:30:00', 'Se souvenir de mettre de côté suffisamment de vin pour satisfaire la commande de M. Dubois.', 1, '2022-11-27', '0000-00-00 00:00:00', 1, 21, 14),
(172, 'Commande de œufs frais', 32767, '14:00:00', '20:45:00', 'Prévoir de réserver un espace de stockage pour la commande de M. Leroy avant la récolte.', 1, '2022-12-22', '0000-00-00 00:00:00', 2, 22, 28),
(173, 'Commande de produits de boulangerie (pain, croissa', 256, '08:30:00', '15:45:00', 'Préparer les étiquettes et les factures pour la commande de Mme Martin avant la livraison.', 0, '0000-00-00', '0000-00-00 00:00:00', 3, 23, 48),
(174, 'Commande de miel', 146, '06:00:00', '12:15:00', 'Se souvenir de mettre de côté suffisamment de poissons pour satisfaire la commande de M. Dubois.', 0, '2022-11-17', '2022-11-17 13:40:00', 4, 24, 49),
(175, '', 271, '06:00:00', '13:00:00', 'Prévoir de mettre de côté assez de légumes biologiques pour satisfaire la commande de Mme Durand.', 0, '2022-11-11', '2022-11-11 15:10:00', 5, 25, 31),
(176, 'Commande de légumes congelés (épinards, haricots v', 107, '08:15:00', '13:00:00', 'Préparer les documents nécessaires pour la livraison de la commande de M. Blanc à la coopérative locale.', 0, '0000-00-00', '0000-00-00 00:00:00', 6, 26, 19),
(177, 'Commande de fromage de brebis', 230, '10:00:00', '15:15:00', 'Appeler M. Dupont pour confirmer la commande de lait pour le 15 juin.', 0, '0000-00-00', '0000-00-00 00:00:00', 7, 27, 19),
(178, '', 482, '08:30:00', '13:30:00', 'Pensez à demander à M. Dubois s\'il souhaite ajouter des œufs à sa commande de légumes.', 0, '2023-01-14', '2023-01-14 15:20:00', 8, 28, 35),
(179, '', 485, '13:15:00', '19:00:00', 'Préparer les documents nécessaires pour la livraison de la commande de M. Blanc à la coopérative locale.', 0, '0000-00-00', '0000-00-00 00:00:00', 9, 29, 46),
(180, '', 155, '10:15:00', '16:15:00', 'Pensez à demander à M. Blanc s\'il souhaite ajouter des produits laitiers à sa commande de légumes.', 0, '2022-11-18', '2022-11-18 12:45:00', 10, 30, 24),
(181, '', 425, '06:15:00', '13:15:00', 'Confirmer auprès de M. Robin la date de livraison de sa commande de fromage.', 0, '2022-12-25', '2022-12-25 14:55:00', 1, 31, 21),
(182, 'Commande de légumes biologiques (carottes, pommes ', 32767, '06:15:00', '13:45:00', 'Rappeler à M. Leroy de régler sa commande de viande avant la livraison prévue le 22 juin.', 1, '2023-01-01', '0000-00-00 00:00:00', 2, 32, 22),
(183, 'Commande de légumes congelés (épinards, haricots v', 236, '11:15:00', '17:00:00', '', 0, '0000-00-00', '0000-00-00 00:00:00', 3, 33, 34),
(184, 'Commande de miel', 353, '15:45:00', '15:45:00', '', 1, '2023-01-02', '0000-00-00 00:00:00', 4, 34, 20),
(185, 'Commande de produits laitiers (beurre, crème, yaou', 8, '13:00:00', '18:15:00', 'Se souvenir de mettre de côté suffisamment de poulets pour satisfaire la commande de M. Blanc.', 0, '2022-11-08', '2022-11-08 14:30:00', 5, 35, 26),
(186, 'Commande de jus de fruit (orange, pomme, raisin, e', 95, '16:00:00', '16:00:00', 'Se souvenir de mettre de côté suffisamment de poulets pour satisfaire la commande de M. Blanc.', 1, '2022-12-10', '0000-00-00 00:00:00', 6, 36, 21),
(187, 'Commande de jus de fruit (orange, pomme, raisin, e', 373, '10:00:00', '17:45:00', 'Pensez à demander à Mme Durand si elle souhaite des produits frais ou congelés pour sa commande.', 0, '0000-00-00', '0000-00-00 00:00:00', 7, 37, 35),
(188, 'Commande de produits laitiers (beurre, crème, yaou', 123, '06:15:00', '11:00:00', 'Rappeler à M. Leroy de régler sa commande de viande avant la livraison prévue le 22 juin.', 0, '2022-11-05', '2022-11-05 12:55:00', 8, 38, 35),
(189, '', 45, '11:15:00', '17:00:00', 'Prévoir de réserver un espace de stockage pour la commande de M. Leroy avant la récolte.', 0, '2023-01-01', '2023-01-01 15:30:00', 9, 39, 35),
(190, 'Commande de fromage (emmental, gorgonzola, chèvre,', 250, '18:30:00', '18:30:00', 'Se souvenir de mettre de côté suffisamment de poissons pour satisfaire la commande de M. Dubois.', 1, '2022-11-09', '0000-00-00 00:00:00', 10, 40, 7),
(191, 'Commande de lait pasteurisé', 54, '12:45:00', '18:15:00', 'Préparer les documents nécessaires pour la livraison de la commande de M. Blanc à la coopérative locale.', 0, '2022-11-21', '2022-11-21 12:40:00', 1, 41, 6),
(192, 'Commande de lait pasteurisé', 280, '13:45:00', '18:00:00', 'Se souvenir de mettre de côté suffisamment de confitures pour satisfaire la commande de Mme Dupont.', 0, '2022-12-12', '2022-12-12 14:35:00', 2, 42, 12),
(193, 'Commande de produits de boulangerie (pain, croissa', 347, '08:45:00', '15:30:00', 'Pensez à demander à Mme Durand si elle souhaite des produits frais ou congelés pour sa commande.', 0, '2022-12-04', '2022-12-04 13:30:00', 3, 43, 30),
(194, 'Commande de légumes biologiques (carottes, pommes ', 231, '12:30:00', '17:00:00', 'Pensez à demander à Mme Durand si elle souhaite des produits frais ou congelés pour sa commande.', 0, '2023-01-04', '2023-01-04 12:50:00', 4, 44, 14),
(195, 'Commande de jus de fruit (orange, pomme, raisin, e', 374, '10:00:00', '16:30:00', 'Prévoir de réserver un espace de stockage pour la commande de M. Leroy avant la récolte.', 0, '0000-00-00', '0000-00-00 00:00:00', 5, 45, 34),
(196, 'Commande de lait pasteurisé', 311, '13:30:00', '18:15:00', 'Préparer les étiquettes et les factures pour la commande de Mme Martin avant la livraison.', 0, '2022-11-20', '2022-11-20 13:15:00', 6, 46, 11),
(197, 'Commande de viande de poulet (poulet entier, ailes', 190, '14:00:00', '20:30:00', 'Prévoir de préparer les colis de la commande de Mme Martin avec soin.', 0, '2023-01-06', '2023-01-06 13:00:00', 7, 47, 28),
(198, 'Commande de miel', 239, '07:30:00', '12:45:00', '', 0, '2022-11-18', '2022-11-18 12:50:00', 8, 48, 1),
(199, '', 477, '14:45:00', '20:45:00', 'Se souvenir de mettre de côté suffisamment de vin pour satisfaire la commande de M. Dubois.', 0, '2023-01-02', '2023-01-02 12:40:00', 9, 49, 41),
(200, 'Commande de légumes en conserve (tomates, haricots', 187, '09:30:00', '16:30:00', 'Prévoir de mettre de côté suffisamment de fromage pour satisfaire la commande de M. Robin.', 0, '2022-12-31', '2022-12-31 14:05:00', 10, 50, 15),
(201, '', 206, '06:45:00', '13:15:00', 'Prévoir de mettre de côté assez de légumes biologiques pour satisfaire la commande de Mme Durand.', 0, '0000-00-00', '0000-00-00 00:00:00', 1, 1, 2),
(202, 'Commande de lait pasteurisé', 472, '09:15:00', '15:45:00', '', 0, '0000-00-00', '0000-00-00 00:00:00', 2, 2, 46),
(203, '', 386, '17:30:00', '17:30:00', '', 1, '2023-01-03', '0000-00-00 00:00:00', 3, 3, 49),
(204, 'Commande de fromage de brebis', 206, '12:30:00', '17:15:00', '', 0, '0000-00-00', '0000-00-00 00:00:00', 4, 4, 27),
(205, '', 46, '11:15:00', '18:00:00', 'Pensez à demander à M. Blanc s\'il souhaite ajouter des produits laitiers à sa commande de légumes.', 0, '2022-11-25', '2022-11-25 13:40:00', 5, 5, 11),
(206, 'Commande de légumes biologiques (carottes, pommes ', 217, '10:30:00', '15:15:00', 'Se souvenir de mettre de côté suffisamment de vin pour satisfaire la commande de M. Dubois.', 0, '2022-11-08', '2022-11-08 12:20:00', 6, 6, 12),
(207, 'Commande de fromage (emmental, gorgonzola, chèvre,', 200, '07:00:00', '13:30:00', 'Pensez à demander à M. Dubois s\'il souhaite ajouter des œufs à sa commande de légumes.', 0, '2022-12-15', '2022-12-15 13:10:00', 7, 7, 8),
(208, '', 464, '12:30:00', '18:45:00', 'Vérifier le stock de pommes avant de confirmer la commande de Mme Martin.', 0, '0000-00-00', '0000-00-00 00:00:00', 8, 8, 10),
(209, 'Commande de fruits (pommes, oranges, bananes, etc.', 374, '10:45:00', '16:15:00', '', 0, '2023-01-08', '2023-01-08 15:25:00', 9, 9, 38),
(210, 'Commande de œufs frais', 333, '09:00:00', '14:30:00', 'Pensez à demander à Mme Durand si elle souhaite des produits frais ou congelés pour sa commande.', 0, '0000-00-00', '0000-00-00 00:00:00', 10, 10, 8),
(211, '', 160, '14:15:00', '14:15:00', 'Se souvenir de mettre de côté suffisamment de confitures pour satisfaire la commande de Mme Dupont.', 1, '2022-11-17', '0000-00-00 00:00:00', 11, 11, 35),
(212, 'Commande de confitures (fraise, abricot, mûre, etc', 372, '08:45:00', '14:30:00', 'Prévoir de mettre de côté suffisamment de fromage pour satisfaire la commande de M. Robin.', 0, '0000-00-00', '0000-00-00 00:00:00', 12, 12, 41),
(213, 'Commande de légumes en conserve (tomates, haricots', 211, '13:00:00', '20:30:00', 'Préparer les étiquettes et les factures pour la commande de Mme Martin avant la livraison.', 0, '2022-11-12', '2022-11-12 12:25:00', 13, 13, 23),
(214, 'Commande de confitures (fraise, abricot, mûre, etc', 418, '11:00:00', '16:00:00', 'Préparer les étiquettes et les factures pour la commande de Mme Martin avant la livraison.', 0, '2022-12-08', '2022-12-08 13:25:00', 14, 14, 37),
(215, 'Commande de poissons frais (saumon, truite, etc.)', 465, '07:15:00', '14:00:00', '', 0, '2023-01-05', '2023-01-05 13:05:00', 15, 15, 28),
(216, 'Commande de vin rouge (cabernet sauvignon, merlot,', 256, '11:45:00', '18:30:00', '', 0, '2022-12-11', '2022-12-11 12:25:00', 16, 16, 5),
(217, '', 499, '06:15:00', '12:45:00', 'Rappeler à M. Leroy de régler sa commande de viande avant la livraison prévue le 22 juin.', 0, '2023-01-15', '2023-01-15 14:05:00', 17, 17, 42),
(218, '', 10, '13:15:00', '20:15:00', 'Confirmer auprès de M. Robin la date de livraison de sa commande de fromage.', 0, '0000-00-00', '0000-00-00 00:00:00', 18, 18, 39),
(219, 'Commande de produits laitiers (beurre, crème, yaou', 472, '14:00:00', '21:00:00', '', 0, '2022-12-29', '2022-12-29 15:05:00', 19, 19, 24),
(220, '', 131, '12:00:00', '18:45:00', 'Vérifier le stock de pommes avant de confirmer la commande de Mme Martin.', 0, '2022-11-18', '2022-11-18 13:40:00', 20, 20, 18),
(221, 'Commande de confitures (fraise, abricot, mûre, etc', 458, '13:00:00', '19:15:00', 'Se souvenir de mettre de côté suffisamment de poulets pour satisfaire la commande de M. Blanc.', 0, '2022-12-15', '2022-12-15 13:25:00', 1, 1, 19),
(222, 'Commande de produits laitiers (beurre, crème, yaou', 432, '08:30:00', '14:00:00', '', 0, '2022-12-28', '2022-12-28 13:45:00', 2, 2, 3),
(223, 'Commande de poulets frais', 460, '06:15:00', '11:15:00', '', 0, '2023-01-10', '2023-01-10 12:35:00', 3, 3, 17),
(224, '', 321, '09:30:00', '16:15:00', 'Se souvenir de mettre de côté suffisamment de poulets pour satisfaire la commande de M. Blanc.', 0, '0000-00-00', '0000-00-00 00:00:00', 4, 4, 20),
(225, 'Commande de vin rouge (cabernet sauvignon, merlot,', 432, '14:30:00', '21:30:00', 'Prévoir de réserver un espace de stockage pour la commande de M. Leroy avant la récolte.', 0, '0000-00-00', '0000-00-00 00:00:00', 5, 5, 50),
(226, '', 430, '11:15:00', '17:15:00', '', 0, '2023-01-13', '2023-01-13 13:30:00', 6, 6, 19),
(227, 'Commande de vin rouge (cabernet sauvignon, merlot,', 32767, '12:15:00', '18:45:00', 'Prévoir de préparer les colis de la commande de Mme Martin avec soin.', 1, '2022-12-23', '0000-00-00 00:00:00', 7, 7, 9),
(228, 'Commande de viande de porc (jambon, saucisses, côt', 452, '09:30:00', '14:00:00', 'Se souvenir de mettre de côté suffisamment de poissons pour satisfaire la commande de M. Dubois.', 0, '2022-12-15', '2022-12-15 12:35:00', 8, 8, 22),
(229, 'Commande de œufs frais', 297, '13:00:00', '19:00:00', 'Vérifier le stock de pommes avant de confirmer la commande de Mme Martin.', 0, '0000-00-00', '0000-00-00 00:00:00', 9, 9, 32),
(230, 'Commande de fromage de chèvre', 32767, '13:15:00', '20:00:00', '', 1, '2022-11-01', '0000-00-00 00:00:00', 10, 10, 31),
(231, 'Commande de viande de poulet (poulet entier, ailes', 35, '13:00:00', '20:00:00', '', 0, '2022-12-12', '2022-12-12 12:40:00', 11, 11, 21),
(232, 'Commande de légumes congelés (épinards, haricots v', 132, '09:45:00', '16:15:00', '', 0, '2023-01-15', '2023-01-15 12:25:00', 12, 12, 41),
(233, '', 273, '12:30:00', '18:30:00', 'Pensez à demander à M. Dubois s\'il souhaite ajouter des œufs à sa commande de légumes.', 0, '2022-12-20', '2022-12-20 15:45:00', 13, 13, 11),
(234, '', 167, '13:45:00', '13:45:00', 'Appeler M. Dupont pour confirmer la commande de lait pour le 15 juin.', 1, '2022-12-23', '0000-00-00 00:00:00', 14, 14, 39),
(235, 'Commande de viande de poulet (poulet entier, ailes', 32767, '11:45:00', '17:30:00', 'Appeler M. Dupont pour confirmer la commande de lait pour le 15 juin.', 1, '2023-01-02', '0000-00-00 00:00:00', 15, 15, 16),
(236, 'Commande de fromage de chèvre', 201, '11:30:00', '17:00:00', 'Appeler M. Dupont pour confirmer la commande de lait pour le 15 juin.', 0, '0000-00-00', '0000-00-00 00:00:00', 16, 16, 8),
(237, 'Commande de œufs frais', 164, '11:30:00', '18:45:00', 'Prévoir de mettre de côté assez de légumes biologiques pour satisfaire la commande de Mme Durand.', 0, '0000-00-00', '0000-00-00 00:00:00', 17, 17, 39),
(238, 'Commande de produits laitiers (beurre, crème, yaou', 277, '08:30:00', '15:30:00', 'Vérifier le stock de pommes avant de confirmer la commande de Mme Martin.', 0, '2022-11-10', '2022-11-10 12:45:00', 18, 18, 21),
(239, '', 272, '07:00:00', '13:00:00', 'Pensez à demander à M. Dubois s\'il souhaite ajouter des œufs à sa commande de légumes.', 0, '0000-00-00', '0000-00-00 00:00:00', 19, 19, 24),
(240, '', 188, '12:30:00', '19:15:00', 'Confirmer auprès de M. Robin la date de livraison de sa commande de fromage.', 0, '2022-11-14', '2022-11-14 14:05:00', 20, 20, 19),
(241, 'Commande de fromage de chèvre', 256, '17:30:00', '17:30:00', 'Préparer les documents nécessaires pour la livraison de la commande de M. Blanc à la coopérative locale.', 1, '2022-12-25', '0000-00-00 00:00:00', 1, 1, 3),
(242, 'Commande de vin rouge (cabernet sauvignon, merlot,', 261, '10:15:00', '17:15:00', 'Se souvenir de mettre de côté suffisamment de poissons pour satisfaire la commande de M. Dubois.', 0, '2022-11-22', '2022-11-22 15:10:00', 2, 2, 7),
(243, 'Commande de jus de fruit (orange, pomme, raisin, e', 166, '13:15:00', '20:15:00', '', 0, '0000-00-00', '0000-00-00 00:00:00', 3, 3, 20),
(244, '', 292, '11:00:00', '16:00:00', '', 0, '0000-00-00', '0000-00-00 00:00:00', 4, 4, 22),
(245, '', 191, '10:00:00', '15:15:00', 'Se souvenir de mettre de côté suffisamment de poissons pour satisfaire la commande de M. Dubois.', 0, '2023-01-14', '2023-01-14 13:30:00', 5, 5, 15),
(246, 'Commande de viande de porc (jambon, saucisses, côt', 133, '10:45:00', '16:45:00', 'Pensez à demander à M. Dubois s\'il souhaite ajouter des œufs à sa commande de légumes.', 0, '0000-00-00', '0000-00-00 00:00:00', 6, 6, 21),
(247, 'Commande de viande de boeuf (filet, rôti, steak ha', 209, '12:30:00', '19:30:00', 'Préparer les étiquettes et les factures pour la commande de Mme Martin avant la livraison.', 0, '0000-00-00', '0000-00-00 00:00:00', 7, 7, 26),
(248, 'Commande de fromage (emmental, gorgonzola, chèvre,', 323, '06:00:00', '13:45:00', 'Confirmer auprès de M. Robin la date de livraison de sa commande de fromage.', 0, '2022-11-28', '2022-11-28 15:35:00', 8, 8, 4),
(249, 'Commande de fromage (emmental, gorgonzola, chèvre,', 356, '13:45:00', '19:45:00', '', 0, '2023-01-12', '2023-01-12 14:55:00', 9, 9, 42),
(250, 'Commande de légumes biologiques (carottes, pommes ', 480, '08:45:00', '14:00:00', 'Prévoir de mettre de côté suffisamment de miel pour satisfaire la commande de Mme Durand.', 0, '0000-00-00', '0000-00-00 00:00:00', 10, 10, 36),
(251, 'Commande de fruits (pommes, oranges, bananes, etc.', 191, '10:15:00', '17:15:00', 'Pensez à demander à Mme Durand si elle souhaite des produits frais ou congelés pour sa commande.', 0, '2022-11-30', '2022-11-30 12:55:00', 11, 11, 1),
(252, '', 413, '07:30:00', '13:00:00', 'Prévoir de mettre de côté suffisamment de miel pour satisfaire la commande de Mme Durand.', 0, '0000-00-00', '0000-00-00 00:00:00', 12, 12, 23),
(253, 'Commande de poulets frais', 32767, '12:15:00', '19:45:00', '', 1, '2022-12-14', '0000-00-00 00:00:00', 13, 13, 20),
(254, 'Commande de viande de boeuf (filet, rôti, steak ha', 184, '12:45:00', '18:15:00', '', 0, '2022-12-03', '2022-12-03 14:25:00', 14, 14, 36),
(255, '', 248, '10:00:00', '17:30:00', '', 0, '2022-12-19', '2022-12-19 15:45:00', 15, 15, 16),
(256, '', 342, '06:30:00', '13:30:00', 'Prévoir de préparer les colis de la commande de Mme Martin avec soin.', 0, '2023-01-06', '2023-01-06 13:15:00', 16, 16, 22),
(257, 'Commande de confitures (fraise, abricot, mûre, etc', 76, '06:45:00', '11:15:00', '', 0, '2023-01-15', '2023-01-15 14:35:00', 17, 17, 42),
(258, 'Commande de légumes biologiques (carottes, pommes ', 106, '11:15:00', '18:15:00', '', 0, '2022-11-12', '2022-11-12 12:35:00', 18, 18, 27),
(259, '', 87, '10:45:00', '15:15:00', 'Pensez à demander à M. Dubois s\'il souhaite ajouter des œufs à sa commande de légumes.', 0, '0000-00-00', '0000-00-00 00:00:00', 19, 19, 40),
(260, 'Commande de fromage de brebis', 18, '13:15:00', '19:45:00', 'Prévoir de mettre de côté suffisamment de fromage pour satisfaire la commande de M. Robin.', 0, '2022-12-17', '2022-12-17 13:10:00', 20, 20, 48),
(261, '', 434, '09:30:00', '16:30:00', '', 0, '2023-01-14', '2023-01-14 15:10:00', 1, 1, 22),
(262, 'Commande de miel', 214, '09:00:00', '14:30:00', 'Pensez à demander à Mme Durand si elle souhaite des produits frais ou congelés pour sa commande.', 0, '2022-11-04', '2022-11-04 13:40:00', 2, 2, 1),
(263, '', 65, '15:30:00', '15:30:00', 'Prévoir de préparer les colis de la commande de Mme Martin avec soin.', 1, '2022-12-28', '0000-00-00 00:00:00', 3, 3, 6),
(264, '', 24, '10:30:00', '15:45:00', 'Vérifier le stock de pommes avant de confirmer la commande de Mme Martin.', 0, '0000-00-00', '0000-00-00 00:00:00', 4, 4, 22),
(265, 'Commande de légumes biologiques (carottes, pommes ', 76, '10:15:00', '15:45:00', '', 0, '0000-00-00', '0000-00-00 00:00:00', 5, 5, 32),
(266, 'Commande de légumes en conserve (tomates, haricots', 32767, '06:15:00', '11:15:00', '', 1, '2022-11-12', '0000-00-00 00:00:00', 6, 6, 5),
(267, 'Commande de légumes biologiques (carottes, pommes ', 492, '11:15:00', '16:30:00', 'Se souvenir de mettre de côté suffisamment de poissons pour satisfaire la commande de M. Dubois.', 0, '0000-00-00', '0000-00-00 00:00:00', 7, 7, 18),
(268, '', 32767, '08:15:00', '14:45:00', 'Prévoir de mettre de côté assez de légumes biologiques pour satisfaire la commande de Mme Durand.', 1, '2022-12-03', '0000-00-00 00:00:00', 8, 8, 28),
(269, 'Commande de produits laitiers (beurre, crème, yaou', 128, '09:15:00', '14:45:00', 'Prévoir de mettre de côté suffisamment de miel pour satisfaire la commande de Mme Durand.', 0, '2022-12-16', '2022-12-16 13:10:00', 9, 9, 50),
(270, 'Commande de poulets frais', 356, '09:15:00', '15:45:00', 'Préparer les étiquettes et les factures pour la commande de Mme Martin avant la livraison.', 0, '2022-11-05', '2022-11-05 13:10:00', 10, 10, 19),
(271, 'Commande de viande de boeuf (filet, rôti, steak ha', 40, '13:15:00', '19:45:00', 'Confirmer auprès de M. Robin la date de livraison de sa commande de fromage.', 0, '2023-01-06', '2023-01-06 12:35:00', 11, 11, 19),
(272, 'Commande de légumes congelés (épinards, haricots v', 426, '08:15:00', '13:45:00', 'Confirmer auprès de M. Robin la date de livraison de sa commande de fromage.', 0, '2022-11-01', '2022-11-01 14:45:00', 12, 12, 28),
(273, 'Commande de légumes en conserve (tomates, haricots', 427, '06:00:00', '13:15:00', 'Prévoir de mettre de côté assez de légumes biologiques pour satisfaire la commande de Mme Durand.', 0, '2022-12-08', '2022-12-08 13:15:00', 13, 13, 3),
(274, 'Commande de jus de fruit (orange, pomme, raisin, e', 29, '12:15:00', '17:15:00', 'Préparer les étiquettes et les factures pour la commande de Mme Martin avant la livraison.', 0, '2022-12-03', '2022-12-03 15:05:00', 14, 14, 34);
INSERT INTO `commandes` (`idCommande`, `libelle`, `poids`, `horaireDebut`, `horaireFin`, `note`, `defautLivraison`, `dateInitiale`, `dateLivraison`, `idProducteur`, `idTournee`, `idClient`) VALUES
(275, 'Commande de fromage (emmental, gorgonzola, chèvre,', 186, '13:00:00', '19:45:00', 'Prévoir de mettre de côté suffisamment de miel pour satisfaire la commande de Mme Durand.', 0, '2022-11-27', '2022-11-27 14:55:00', 15, 15, 15),
(276, '', 346, '12:45:00', '12:45:00', '', 1, '2022-11-25', '0000-00-00 00:00:00', 16, 16, 7),
(277, 'Commande de poulets frais', 198, '11:30:00', '16:45:00', 'Appeler M. Dupont pour confirmer la commande de lait pour le 15 juin.', 0, '2022-11-18', '2022-11-18 12:45:00', 17, 17, 47),
(278, 'Commande de légumes en conserve (tomates, haricots', 256, '07:45:00', '13:30:00', 'Préparer les étiquettes et les factures pour la commande de Mme Martin avant la livraison.', 0, '2022-11-20', '2022-11-20 14:55:00', 18, 18, 23),
(279, 'Commande de vin rouge (cabernet sauvignon, merlot,', 255, '11:30:00', '17:15:00', 'Prévoir de mettre de côté suffisamment de fromage pour satisfaire la commande de M. Robin.', 0, '2022-11-27', '2022-11-27 13:00:00', 19, 19, 16),
(280, 'Commande de produits laitiers (beurre, crème, yaou', 183, '10:00:00', '16:45:00', 'Se souvenir de mettre de côté suffisamment de vin pour satisfaire la commande de M. Dubois.', 0, '0000-00-00', '0000-00-00 00:00:00', 20, 20, 22),
(281, 'Commande de miel', 353, '09:00:00', '14:45:00', '', 0, '2022-11-19', '2022-11-19 13:35:00', 1, 1, 12),
(282, '', 279, '08:15:00', '14:00:00', '', 0, '0000-00-00', '0000-00-00 00:00:00', 2, 2, 7),
(283, 'Commande de confitures (fraise, abricot, mûre, etc', 246, '06:00:00', '12:00:00', '', 0, '0000-00-00', '0000-00-00 00:00:00', 3, 3, 50),
(284, 'Commande de fromage (emmental, gorgonzola, chèvre,', 328, '07:45:00', '14:15:00', 'Prévoir de préparer les colis de la commande de Mme Martin avec soin.', 0, '0000-00-00', '0000-00-00 00:00:00', 4, 4, 10),
(285, 'Commande de poulets frais', 428, '12:00:00', '17:15:00', '', 0, '0000-00-00', '0000-00-00 00:00:00', 5, 5, 16),
(286, 'Commande de poissons frais (saumon, truite, etc.)', 10, '12:15:00', '19:15:00', '', 0, '2022-12-27', '2022-12-27 13:30:00', 6, 6, 12),
(287, 'Commande de miel', 473, '12:15:00', '19:30:00', 'Vérifier le stock de pommes avant de confirmer la commande de Mme Martin.', 0, '2022-11-30', '2022-11-30 14:20:00', 7, 7, 24),
(288, 'Commande de fruits (pommes, oranges, bananes, etc.', 191, '12:15:00', '18:30:00', 'Prévoir de mettre de côté suffisamment de miel pour satisfaire la commande de Mme Durand.', 0, '2022-11-26', '2022-11-26 12:05:00', 8, 8, 37),
(289, '', 479, '15:30:00', '15:30:00', 'Se souvenir de mettre de côté suffisamment de poissons pour satisfaire la commande de M. Dubois.', 1, '2022-12-29', '0000-00-00 00:00:00', 9, 9, 27),
(290, '', 437, '12:00:00', '12:00:00', 'Se souvenir de mettre de côté suffisamment de vin pour satisfaire la commande de M. Dubois.', 1, '2023-01-03', '0000-00-00 00:00:00', 10, 10, 41),
(291, '', 78, '07:15:00', '14:30:00', 'Pensez à demander à M. Dubois s\'il souhaite ajouter des œufs à sa commande de légumes.', 0, '2022-12-03', '2022-12-03 14:10:00', 11, 11, 44),
(292, 'Commande de fromage de brebis', 422, '11:00:00', '16:30:00', 'Pensez à demander à Mme Durand si elle souhaite des produits frais ou congelés pour sa commande.', 0, '0000-00-00', '0000-00-00 00:00:00', 12, 12, 25),
(293, '', 125, '11:15:00', '18:15:00', '', 0, '0000-00-00', '0000-00-00 00:00:00', 13, 13, 21),
(294, 'Commande de poissons frais (saumon, truite, etc.)', 28, '14:00:00', '19:45:00', 'Prévoir de mettre de côté suffisamment de miel pour satisfaire la commande de Mme Durand.', 0, '0000-00-00', '0000-00-00 00:00:00', 14, 14, 19),
(295, '', 68, '07:00:00', '12:30:00', '', 0, '2022-11-08', '2022-11-08 14:00:00', 15, 15, 26),
(296, 'Commande de jus de fruit (orange, pomme, raisin, e', 34, '07:30:00', '13:00:00', 'Prévoir de mettre de côté assez de légumes biologiques pour satisfaire la commande de Mme Durand.', 0, '0000-00-00', '0000-00-00 00:00:00', 16, 16, 29),
(297, 'Commande de œufs frais', 150, '07:30:00', '14:45:00', 'Se souvenir de mettre de côté suffisamment de confitures pour satisfaire la commande de Mme Dupont.', 0, '2022-12-02', '2022-12-02 14:20:00', 17, 17, 28),
(298, '', 294, '14:30:00', '20:15:00', 'Prévoir de réserver un espace de stockage pour la commande de M. Leroy avant la récolte.', 0, '2023-01-06', '2023-01-06 14:00:00', 18, 18, 26),
(299, 'Commande de œufs frais', 195, '09:00:00', '14:30:00', '', 0, '2022-12-01', '2022-12-01 14:30:00', 19, 19, 41),
(300, '', 245, '06:15:00', '12:45:00', 'Prévoir de préparer les colis de la commande de Mme Martin avec soin.', 0, '2022-12-20', '2022-12-20 15:45:00', 20, 20, 21);

-- --------------------------------------------------------

--
-- Structure de la table `producteurs`
--

CREATE TABLE `producteurs` (
  `idProducteur` int(11) NOT NULL,
  `siret` char(14) NOT NULL,
  `nomEtablissement` varchar(100) NOT NULL,
  `tel` char(10) DEFAULT NULL,
  `idAdresse` int(11) NOT NULL,
  `idUtilisateur` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `producteurs`
--

INSERT INTO `producteurs` (`idProducteur`, `siret`, `nomEtablissement`, `tel`, `idAdresse`, `idUtilisateur`) VALUES
(1, '9945465112804', 'La Ferme du Potager', '0262318323', 31, 21),
(2, '9491670503313', 'Le Jardin de la Moisson', '0400753444', 32, 2),
(3, '2377607382995', 'Le Champ des Possibles', '0442784427', 33, 3),
(4, '5654606635092', 'L\'Étable du Fromage', '0445827202', 34, 4),
(5, '8703265758599', 'La Prairie des Merveilles', '0330907073', 35, 5),
(6, '6089968024508', 'Le Verger de la Cagnotte', '0552063296', 36, 6),
(7, '5992069491262', 'La Ferme de l\'Élevage', '0262938562', 37, 7),
(8, '6421120607059', 'Le Potager de l\'Oasis', '0453573046', 38, 8),
(9, '3203383312283', 'Le Jardin de la Récolte', '0698656029', 39, 9),
(10, '1916137991887', 'Le Champ des Rêves', '0188587898', 40, 10),
(11, '9550933140917', 'L\'Étable du Bonheur', '0439789893', 41, 11),
(12, '6566768370253', 'La Prairie des Delices', '0378792050', 42, 12),
(13, '9566384284125', 'Le Verger des Délices', '0162117694', 43, 13),
(14, '8331831295285', 'La Ferme des Saveurs', '0532527129', 44, 14),
(15, '9753634872206', 'Le Potager des Epices', '0571323866', 45, 15),
(16, '7481127348655', 'Le Jardin des Délices', '0660630749', 46, 16),
(17, '1121520737621', 'Le Champ des Merveilles', '0465852089', 47, 17),
(18, '2139291338214', 'L\'Étable de la Gourmandise', '0594745295', 48, 18),
(19, '3608720112747', 'La Prairie des Saveurs', '0497694740', 49, 19),
(20, '7166795943822', 'Le Verger des Plaisirs', '0452668619', 50, 20);

-- --------------------------------------------------------

--
-- Structure de la table `tournees`
--

CREATE TABLE `tournees` (
  `idTournee` int(11) NOT NULL,
  `horaireDebut` datetime DEFAULT NULL,
  `horaireFin` datetime DEFAULT NULL,
  `estimationDuree` time DEFAULT NULL,
  `note` varchar(50) DEFAULT NULL,
  `valide` tinyint(1) NOT NULL,
  `idVehicule` int(11) NOT NULL,
  `idProducteur` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `tournees`
--

INSERT INTO `tournees` (`idTournee`, `horaireDebut`, `horaireFin`, `estimationDuree`, `note`, `valide`, `idVehicule`, `idProducteur`) VALUES
(1, '2023-01-11 06:25:00', '2023-01-11 10:50:00', '03:28:44', '', 1, 1, 1),
(2, '2022-12-22 09:40:00', '2022-12-22 15:05:00', '02:34:57', 'Assurez-vous d\'avoir votre permis de conduire et v', 1, 2, 2),
(3, '2023-01-05 06:55:00', '2023-01-05 13:20:00', '02:29:30', 'N\'oubliez pas de charger votre téléphone et votre ', 1, 3, 3),
(4, '2022-12-21 08:25:00', '2022-12-21 13:00:00', '01:14:40', 'N\'oubliez pas de charger votre téléphone et votre ', 1, 4, 4),
(5, '2022-12-15 09:40:00', '2022-12-15 14:20:00', '03:51:16', 'N\'oubliez pas de prendre des pauses régulièrement ', 1, 5, 5),
(6, '2022-12-18 10:45:00', '2022-12-18 18:00:00', '05:22:17', 'Pensez à prendre de l\'eau et des collations avec v', 1, 6, 6),
(7, '2022-12-07 08:05:00', '2022-12-07 13:55:00', '02:42:15', 'Pensez à vérifier l\'état du colis avant de le reme', 1, 7, 7),
(8, '2023-01-24 09:45:00', '2023-01-24 14:10:00', '00:02:18', 'Pensez à vous étirer et à vous échauffer avant de ', 1, 8, 8),
(9, '2023-01-26 09:05:00', '2023-01-26 14:25:00', '05:04:41', 'Assurez-vous d\'avoir suffisamment de reçus de livr', 1, 9, 9),
(10, '2022-12-25 07:20:00', '2022-12-25 12:00:00', '01:19:43', '', 1, 10, 10),
(11, '2023-01-12 10:55:00', '2023-01-12 18:30:00', '05:50:21', 'Ne pas oublier de porter un équipement de protecti', 1, 11, 11),
(12, '2022-12-07 11:50:00', '2022-12-07 17:05:00', '03:35:29', 'Pensez à prendre de l\'eau et des collations avec v', 1, 12, 12),
(13, '2022-12-11 07:45:00', '2022-12-11 13:20:00', '05:03:18', 'N\'oubliez pas de mettre à jour votre GPS avant de ', 1, 13, 13),
(14, '2023-01-21 11:45:00', '2023-01-21 17:20:00', '00:19:14', 'Assurez-vous d\'avoir suffisamment de carburant pou', 1, 14, 14),
(15, '2023-01-01 11:20:00', '2023-01-01 15:35:00', '03:07:16', '', 1, 15, 15),
(16, '2022-12-21 06:05:00', '2022-12-21 10:35:00', '02:09:07', 'N\'oublie pas de prendre le paquet pour M. Dupont a', 1, 16, 16),
(17, '2022-12-24 07:30:00', '2022-12-24 13:55:00', '04:59:29', 'Pensez à vérifier la météo et à vous habiller en c', 1, 17, 17),
(18, '2022-12-23 08:50:00', '2022-12-23 15:15:00', '04:19:55', 'N\'oubliez pas de prendre des pauses régulièrement ', 1, 18, 18),
(19, '2022-12-29 09:50:00', '2022-12-29 16:55:00', '02:05:21', 'Penser à vérifier les pneus avant la tournée', 1, 19, 19),
(20, '2023-01-04 06:15:00', '2023-01-04 12:30:00', '04:48:45', 'N\'oubliez pas de prendre vos médicaments si vous e', 1, 20, 20),
(21, '2023-01-19 07:30:00', '2023-01-19 15:25:00', '06:50:18', '', 1, 21, 1),
(22, '2022-12-20 10:50:00', '2022-12-20 15:25:00', '01:50:20', '', 1, 22, 2),
(23, '2023-01-13 10:10:00', '2023-01-13 15:15:00', '02:40:23', 'Assurez-vous de vérifier l\'adresse avant de dépose', 1, 23, 3),
(24, '2022-12-05 09:55:00', '2022-12-05 15:15:00', '03:44:41', 'Assurez-vous d\'avoir votre permis de conduire et v', 1, 24, 4),
(25, '2022-12-06 09:55:00', '2022-12-06 15:00:00', '01:17:01', '', 1, 25, 5),
(26, '2023-01-12 06:50:00', '2023-01-12 10:55:00', '03:37:28', '', 1, 26, 6),
(27, '2023-01-08 06:30:00', '2023-01-08 11:30:00', '03:23:06', 'N\'oubliez pas de charger votre téléphone et votre ', 1, 27, 7),
(28, '2022-12-18 10:15:00', '2022-12-18 14:50:00', '00:16:59', '', 1, 28, 8),
(29, '2022-12-27 08:15:00', '2022-12-27 13:45:00', '02:05:43', 'Souvenez-vous de vérifier les heures de livraison ', 1, 29, 9),
(30, '2023-01-26 07:20:00', '2023-01-26 11:45:00', '02:38:31', 'N\'oubliez pas de prendre vos médicaments si vous e', 1, 30, 10),
(31, '2022-12-07 11:30:00', '2022-12-07 15:30:00', '02:46:50', '', 1, 1, 1),
(32, '2023-01-17 09:20:00', '2023-01-17 16:40:00', '04:12:21', 'Assurez-vous d\'avoir votre permis de conduire et v', 1, 2, 2),
(33, '2023-01-27 11:55:00', '2023-01-27 19:35:00', '00:42:51', 'Souvenez-vous de demander une signature pour la li', 1, 3, 3),
(34, '2022-12-05 09:15:00', '2022-12-05 17:10:00', '01:17:40', 'Assurez-vous d\'avoir votre permis de conduire et v', 1, 4, 4),
(35, '2022-12-18 08:05:00', '2022-12-18 14:40:00', '01:22:54', '', 1, 5, 5),
(36, '2022-12-11 06:05:00', '2022-12-11 13:55:00', '07:16:27', 'Pensez à vous étirer et à vous échauffer avant de ', 1, 6, 6),
(37, '2023-01-03 11:05:00', '2023-01-03 17:25:00', '04:52:26', 'Les clés se trouve dans la boite à chaussure', 1, 7, 7),
(38, '2022-12-21 11:30:00', '2022-12-21 18:10:00', '03:48:04', 'Souvenez-vous de vérifier les heures de livraison ', 1, 8, 8),
(39, '2023-02-01 10:45:00', '2023-02-01 16:10:00', '01:43:36', 'N\'oubliez pas de prendre des pauses régulièrement ', 1, 9, 9),
(40, '2023-01-23 07:25:00', '2023-01-23 15:10:00', '02:06:29', 'Pensez à vérifier la météo et à vous habiller en c', 1, 10, 10),
(41, '2023-01-27 10:25:00', '2023-01-27 18:00:00', '01:43:02', 'Ne pas oublier de vérifier la liste de colis avant', 1, 1, 1),
(42, '2023-01-15 10:45:00', '2023-01-15 15:40:00', '02:33:28', 'Pensez à vérifier la météo et à vous habiller en c', 1, 2, 2),
(43, '2023-01-23 06:25:00', '2023-01-23 11:20:00', '03:56:05', 'Pensez à vérifier votre itinéraire avant de démarr', 1, 3, 3),
(44, '2023-01-17 07:45:00', '2023-01-17 14:00:00', '04:51:54', 'Ne pas oublier de sonner à l\'interphone avant d\'en', 1, 4, 4),
(45, '2023-01-22 09:10:00', '2023-01-22 13:10:00', '00:38:39', 'Ne pas oublier de porter un équipement de protecti', 1, 5, 5),
(46, '2023-01-14 06:05:00', '2023-01-14 11:00:00', '03:09:54', '', 1, 6, 6),
(47, '2022-12-16 06:45:00', '2022-12-16 12:50:00', '01:09:03', 'Pensez à vérifier l\'état du colis avant de le reme', 1, 7, 7),
(48, '2023-01-17 10:20:00', '2023-01-17 17:40:00', '00:49:47', 'Penser à vérifier les pneus avant la tournée', 1, 8, 8),
(49, '2022-12-20 08:35:00', '2022-12-20 14:10:00', '03:01:03', 'N\'oubliez pas de mettre à jour votre GPS avant de ', 1, 9, 9),
(50, '2022-12-26 06:05:00', '2022-12-26 12:10:00', '04:52:36', 'Pensez à vérifier l\'état du colis avant de le reme', 1, 10, 10);

-- --------------------------------------------------------

--
-- Structure de la table `utilisateurs`
--

CREATE TABLE `utilisateurs` (
  `idUtilisateur` int(11) NOT NULL,
  `nomUtilisateur` varchar(50) NOT NULL,
  `mdp` varbinary(256) NOT NULL,
  `sel` varbinary(256) NOT NULL,
  `nom` varchar(50) DEFAULT NULL,
  `prenom` varchar(50) DEFAULT NULL,
  `email` varchar(320) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `utilisateurs`
--

INSERT INTO `utilisateurs` (`idUtilisateur`, `nomUtilisateur`, `mdp`, `sel`, `nom`, `prenom`, `email`) VALUES
(1, 'root', 0x6f743239764576735741777868704a715933684b716d37474561493873672f30414b4a57756b504248413d3d, 0x73354637506c55614771414e354273443145526c5577622b43646975427254377a71772b6869316b6e673d3d, 'Morel', 'Erwann', 'emorel@example.com'),
(2, 'ajulien', 0x584d554e4b4a736d2b705243587847366c7858547547664741397a594f6847337a3879454a4e5a6f51413d3d, 0x692f46306f326f62786f2f65364a6463494f5937346659585534455468376f4f715853376237363061773d3d, 'Julien', 'Angelo', 'ajulien@example.com'),
(3, 'rbarre', 0x5a7073522f724b6247467033426e426e766b3872644951577a4f6a6149496f51627a6c43344733464e413d3d, 0x6e536d6661344d645353425236693061386875415165414256443064624b57776c7768503042753546513d3d, 'Barre', 'Rosalie', 'rbarre@example.com'),
(4, 'lgarcia', 0x3137332f6e4d6a4335753671545a4f637465337833346948354e59317a5a4630502b63427478305456773d3d, 0x3170755345636b4c684e537a7a4b644d4f62646c4c444451487a617856795474736334473674313261513d3d, 'Garcia', 'Lya', 'lgarcia@example.com'),
(5, 'rleblanc', 0x6335444d4f6f58305261723071444d5259583275517442314b47326c306577775a6d426b4468755769513d3d, 0x5372684736364e39312b445066687664427a7a785474523631765654492b4b4a4f71334f52567a366a773d3d, 'Leblanc', 'Rayane', 'rleblanc@example.com'),
(6, 'gpicard', 0x4f4d576a6c724a665a7671484a46792f7971696a6844496c424434725569735869304667493862496e673d3d, 0x642b4861354f435958426d58336b6d465a484b4a6d5132664e7a634332456d352b6475426c2f324548413d3d, 'Picard', 'Georgette', 'gpicard@example.com'),
(7, 'aroy', 0x4152616d47757130364679616e5079727556744536686564734978644e6758664c30576b434d737532513d3d, 0x313972572b47713973654a766a42613430456c6b6876766f466c6d713344666e6e5866577653544a62773d3d, 'Roy', 'Aicha', 'aroy@example.com'),
(8, 'rlaporte', 0x7774427a2b5744576e6c6254453246344465664341306232356d4b45465a79465178596971324f5851513d3d, 0x634c437a6b4b504e306f4a4450432f4f6a5674645673447a4f5a434b30744f787a64706d596e505662673d3d, 'Laporte', 'Raphaël', 'rlaporte@example.com'),
(9, 'mmercier', 0x2f7143326d6d39687972424c41464f6839436b2b4445587279334b7442616356323671533746724133413d3d, 0x46477244424e464c362f68394c754c4b6b796c79663536396777566344434b547851742b6c6a786c6a673d3d, 'Mercier', 'Maxime', 'mmercier@example.com'),
(10, 'dboyer', 0x664c48564c5632373064734c76344f4537494a61386f33786b5950526b347353456f384558506b784b773d3d, 0x563243464550637a2b445461577954465030794c566a3835786f34435650774a6858476f3938666b77513d3d, 'Boyer', 'Delphine', 'dboyer@example.com'),
(11, 'mcarpentier', 0x38645043454a6276563144616b4c75486333436e574872506556434b6871524c6c754a46544c362b56513d3d, 0x5948367771636b573632476d313950514e6e5a4b556b4d6c59796d4a76434431416b4936726e4d4475673d3d, 'Carpentier', 'Matis', 'mcarpentier@example.com'),
(12, 'abarbier', 0x6b543166733159786947467a3833466e436749652b59464b41496f65655746444458772f4c76634454513d3d, 0x674f703533492f38662f6f3374646e3277463474564c4c2f597a31546c6a77756c74312b4e31427a31673d3d, 'Barbier', 'Anatole', 'abarbier@example.com'),
(13, 'fpierre', 0x44575671345678495a67554c686262374a495572497378326358594a4c526472616a565a4a4472464e773d3d, 0x76366864704541452f736e584c6e56334e31626d5479487572636365376a514a667a6538376c635952773d3d, 'Pierre', 'Flora', 'fpierre@example.com'),
(14, 'claporte', 0x446a71477059755371576a716d374b652b2f47334b4447505554625846657341384f6e373574664942413d3d, 0x45637a6e4d4d6e5145686f6339556f4b6a595a714c5378765741556a71786b6b51366d7047342f6854773d3d, 'Laporte', 'Cloé', 'claporte@example.com'),
(15, 'smillet', 0x2f7371585969546953696b346c674c66617263493651717251436a56716679346b49396b4942686974513d3d, 0x38723171434e445a7872675a34436a4c4c31796e344b59437a4e5067334733763045727556524c3557513d3d, 'Millet', 'Sébastien', 'smillet@example.com'),
(16, 'ndaniel', 0x774630534b6e574454366a4a463855583372584f393567654c49314d44596f487934474a4976394259513d3d, 0x635678356b753747556c6c412b386d31415a4e313049376f68594345786544385a432b774173556c30673d3d, 'Daniel', 'Naomie', 'ndaniel@example.com'),
(17, 'djacob', 0x39535936562f69386559306c636b5567574e7959555a5a55684a6b616d434e312f4d51352f6e5a5a72413d3d, 0x4872744775776e6e676d37662b524b4d774f6c6158366b4c726e4b642b725371726449754657775041773d3d, 'Jacob', 'Dimitri', 'djacob@example.com'),
(18, 'fbonnet', 0x5a4a66494355656e5348574d61332f6c474c426d30686c44794f4d4933674d42703436437450506a31673d3d, 0x3342752b4e7159614c786e566f457a4e534d4a74736c766357457057455258765654334f7631537579413d3d, 'Bonnet', 'Frank', 'fbonnet@example.com'),
(19, 'pantoine', 0x5573314541534f71447a35684b39724e6f4b524a596a36394578383734764454644670646d36306258773d3d, 0x4a45486578556a744c6a4b6f462f6e7353496a6d6b4458425561744b6d564d6f47456f31397875795a773d3d, 'Antoine', 'Philippe', 'pantoine@example.com'),
(20, 'pdasilva', 0x67594e5a67636b6c477a7961344e5536456567513234483432574a596b4d4349584c50537956584d76773d3d, 0x75493632664142423770557548397654393064384d794b437465624e35356c6c772f35724256446c67413d3d, 'Da Silva', 'Peter', 'pdasilva@example.com'),
(21, 'elebrun', 0x695a566f6f6961463556714c38726439666d545243495a62323569654f6d7353325637414d4d6a684e413d3d, 0x6f4774712f63333178696f6c5a372b3946387946456f46354874663750352f426b79314b6850595654513d3d, 'Lebrun', 'Eric', 'elebrun@example.com'),
(22, 'dleroux', 0x4b6b3147676874526c34696e55766930723850536e566f63564667706e48796572615467595a724159413d3d, 0x667a65474e354c5963356d514f52745861374a347433622b43383537523474554f6b4754397233494a513d3d, 'Le Roux', 'Dolores', 'dleroux@example.com'),
(23, 'groy', 0x5a3939536771736b54524373374f3474572f772b3155306f636133666f2b54684c626d7853466f4572673d3d, 0x3474734e73783932566356396b795963524341456a35524576494e4d63624b304d32764f5638646364773d3d, 'Roy', 'Gabrielle', 'groy@example.com'),
(24, 'hdubois', 0x4f45357a4c385a6f4b584165644b746d454862676342636a4b427237434f66776b4d30645846427045773d3d, 0x724f4f6c4f5759705953494b513530457a4474515a3054322f76647a4575432f5250677a506e2f574e513d3d, 'Dubois', 'Halima', 'hdubois@example.com'),
(25, 'rhenry', 0x75516765785172764b6763623946467436487531686b456171384a615937505570344365623132314b673d3d, 0x42587354376d503430706e466f416b4d63776251516d41472b472f4764712b4d52586b724f7334734b513d3d, 'Henry', 'Reda', 'rhenry@example.com'),
(26, 'trenard', 0x68384577396951747a4e7a516a776666443237596a754e75764e36452f71575a5433534a3465336e72513d3d, 0x715a4a7156536c36696d54432f454673474a54646756316d54356c36476330707773312f714b456354513d3d, 'Renard', 'Thérèse', 'trenard@example.com'),
(27, 'bbarre', 0x6a7a472b2f705a687268443962796a4f5931367272537a662b59344a58433433572b6c446e4f474b36773d3d, 0x6a4247756d6d6e4639594145326d79344b786634514f686f7551514b4f3666307378326c357a64784b513d3d, 'Barre', 'Blandine', 'bbarre@example.com'),
(28, 'tparis', 0x3579476a6b343573543074797136647538784833516f65424d4242516979636b504a58717057347066673d3d, 0x76667a41396939384673574e315673744934416976537932395359633531783937327a54376930716b413d3d, 'Paris', 'Tony', 'tparis@example.com'),
(29, 'nleger', 0x4f4575595151636b4a51535065306c665777555a364663786a366e4c35474175655076765870534430513d3d, 0x4674444d702b7a61435a4935434d69746b74387168735047384750652f6a73394e524732694c766169773d3d, 'Leger', 'Norbert', 'nleger@example.com'),
(30, 'sbertin', 0x6b4b305762572f714c72696e367a664e38584a572f686f4237547165416a795a54534b597a66493572673d3d, 0x385867576c7143593151664b6d4757484f736341656f5235685573466c547271456a52516b64505951773d3d, 'Bertin', 'Simon', 'sbertin@example.com'),
(31, 'lleroy', 0x4d5a696479536a4f535553523772614d307745322b6d46546537384455385768676f664a505a754b31513d3d, 0x3458654344704f64564745704f4f5a74473863503874784b436a396a377868505a6f3769344c386a4a773d3d, 'Leroy', 'Louison', 'lleroy@example.com'),
(32, 'dbaron', 0x46344f7930432f34754255664a756f31307045424b7a4b57634262334a2b7537766b39594f52686553513d3d, 0x69712f512b6935666e6637553752655a71356837766d4e6a6f4937666d7a7661423479306c66644e49773d3d, 'Baron', 'Davy', 'dbaron@example.com'),
(33, 'alegrand', 0x33356a74565674587a414348537a4b376279464d6a327159474c6853774944364c4f30574934754b59673d3d, 0x572f6651314a586a5848462b474d397a635051447979717042444d63354463547061344e6e676a4f46413d3d, 'Legrand', 'Auriane', 'alegrand@example.com'),
(34, 'alegrand2', 0x4d74646a6563487956466c76566962304b32576b4f54475358334a756574315067354d576263366269513d3d, 0x6466626134534c706d6a51432f373359694e442b77464772494e533132747069676e54425572656269413d3d, 'Legrand', 'Agathe', 'alegrand@example.com'),
(35, 'ymichel', 0x34706562574a6635365233744d793336656548794a3039764a45336633745731704243716459664a79513d3d, 0x4d7a583575516b7545714f366671545454566938484b367a77646e3264703672756c5a377172484f6f513d3d, 'Michel', 'Younes', 'ymichel@example.com'),
(36, 'aboulanger', 0x475a32536a646f677265584d37684763456763426331556f4e6f4f6668635142504f74533550317167413d3d, 0x6873346c786c45494451525a7351346544696c34457151387659314a536e2f4f366c3245385365496a673d3d, 'Boulanger', 'Assia', 'aboulanger@example.com'),
(37, 'slouis', 0x37614442547847425874436274496f767667485a482b495756372b66557750565345457569586b712f773d3d, 0x696a31336c7572686361697239314e325533475943344d6d547833776f794c576f557a6c51392b3557413d3d, 'Louis', 'Samir', 'slouis@example.com'),
(38, 'slambert', 0x377a3950312b61726b375849584f4a4379564a7576574977514c7546497943623545584d4833653231773d3d, 0x64325542344b7139674c376b78345070674f386d3341746b387934512f384f775373524c6839572b48413d3d, 'Lambert', 'Solange', 'slambert@example.com'),
(39, 'mcousin', 0x724e6c444f724547456b56536e73456a4d77596f7a5576386834416f6832327076304c4f6e66714a43673d3d, 0x54752f3164383069414664713541454d4732466e533737446c2f507538493732326e58393868345050513d3d, 'Cousin', 'Mathilde', 'mcousin@example.com'),
(40, 'gcousin', 0x346135744f616c6e7a767665633172616d7067636a4d6536794c577a6746757736766b522b72784c35513d3d, 0x564d47476e65395341444e48547a416d6553743153413847414246494f4232783642464b594f766a41513d3d, 'Cousin', 'Gwenael', 'gcousin@example.com'),
(41, 'pcolin', 0x716e686e784b52624a35576d6c6347722b753243636b6f736b537242774366586f324b707546356638413d3d, 0x5032397333785132676879664d524a3731786d646176692f39624e516e544a6c6256374a6f75615361773d3d, 'Colin', 'Pauline', 'pcolin@example.com'),
(42, 'rdufour', 0x4a6b555a4763615663367a69664d686c326a50655879516e6c33474c6f6d55656d676f4e4467764b68413d3d, 0x564249524c485a34653938764d4438436e792f72317a506f39796378773478706a6244464145724f4a513d3d, 'Dufour', 'Roxane', 'rdufour@example.com'),
(43, 'lchevalier', 0x644c366f5242366d385442455569574b345364584f427843746445396335694441624e597a4d583941513d3d, 0x496167453174643654336f756b6b634a336670555270454852597675485a44337375616a7764646d33773d3d, 'Chevalier', 'Luis', 'lchevalier@example.com'),
(44, 'lbenoit', 0x3975524c34624a3651352f7974644b30623177456f62794b74477a65734a306e334266596f4c624a32413d3d, 0x385a6f6c6877325a2f4373366862454b6a49754f4c554a434155376a3463436d6d3771564730727a46773d3d, 'Benoit', 'Laëtitia', 'lbenoit@example.com'),
(45, 'smallet', 0x735a42523654364e476e63543036773034547547435a35514e49365359595631735a42526f54693052413d3d, 0x77626d736e31706365736f665867522f416d734c5a3351456744613472614b7537454a557857715075413d3d, 'Mallet', 'Solenn', 'smallet@example.com'),
(46, 'mdavid', 0x3864736334494f38774f7836304d4b373549686970367845516f6c332b66577374594f7070312b4f64413d3d, 0x3037686971397863665a4f41766b555a7168324938567277686577494d6f4f57684e5a5235612b4b63413d3d, 'David', 'Malo', 'mdavid@example.com'),
(47, 'fdupuy', 0x514646353165796b6273486d2b43744146793531656432695a3072705349733156426e36757a623970673d3d, 0x736856355964694175596d585972467947593242304261796b6d516b346a4278777230386246623153513d3d, 'Dupuy', 'Flore', 'fdupuy@example.com'),
(48, 'elacombe', 0x49486d446b4a627a4b7138686c536c68544b6969645558365846564b734a4d494e4f30477645317759673d3d, 0x314e7a7a463374536a58655761414278575558636b3846714f6376564b36483446516751613154356f673d3d, 'Lacombe', 'Eloise', 'elacombe@example.com'),
(49, 'rjulien', 0x72644f646d7673784e744457783846436f515a56512f6f38443862626e756353476267505a2f445a2b773d3d, 0x37576e33505656635a6643787436456b6e4144457730372b31765743674546773841383770796a452f413d3d, 'Julien', 'Raymond', 'rjulien@example.com'),
(50, 'ifontaine', 0x4c373434537774736b356944746b2f444e5a2b526743592b4c723744304c3666643763634a4b2b3751513d3d, 0x72584266496f743470386239346b3974585a464930352f445876377079376d3750654b513630374432673d3d, 'Fontaine', 'Ilyas', 'ifontaine@example.com');

-- --------------------------------------------------------

--
-- Structure de la table `vehicules`
--

CREATE TABLE `vehicules` (
  `idVehicule` int(11) NOT NULL,
  `immatriculation` char(7) DEFAULT NULL,
  `poidsMax` smallint(5) UNSIGNED NOT NULL,
  `libelle` varchar(50) DEFAULT NULL,
  `idProducteur` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `vehicules`
--

INSERT INTO `vehicules` (`idVehicule`, `immatriculation`, `poidsMax`, `libelle`, `idProducteur`) VALUES
(1, 'WR155WW', 140, 'Véhicule 1', 1),
(2, 'WO779EH', 530, 'Camion 7', 2),
(3, 'OE319MW', 410, 'Citroën', 3),
(4, 'BF031VY', 790, 'Camion 6', 4),
(5, 'TR263FR', 470, 'Nissan', 5),
(6, 'LO942PA', 270, 'Camion 4', 6),
(7, 'DM085RW', 760, 'Camion 1', 7),
(8, 'IF753PS', 980, 'Véhicule 3', 8),
(9, 'HT168SE', 390, 'Camion 10', 9),
(10, 'IP035UR', 50, 'Camion 6', 10),
(11, 'NU066PP', 850, 'Mercedes', 11),
(12, 'XT987VJ', 140, 'Camion 1', 12),
(13, 'LQ226BO', 610, 'Peugeot', 13),
(14, 'IS517XV', 860, 'Camion 8', 14),
(15, 'NN510BG', 350, 'Camion 10', 15),
(16, 'FU374YK', 810, 'Ford', 16),
(17, 'FL906GM', 680, 'Volkswagen', 17),
(18, 'PJ258FH', 840, 'Toyota', 18),
(19, 'PK193YV', 290, 'Peugeot', 19),
(20, 'QC506OT', 320, 'Véhicule 1', 20),
(21, 'RQ866ZN', 950, 'Camion 3', 1),
(22, 'ZN910SS', 770, 'Mercedes', 2),
(23, 'FY385GR', 30, 'Volkswagen', 3),
(24, 'MG273NH', 600, 'Véhicule 8', 4),
(25, 'FK809VA', 370, 'Camion 3', 5),
(26, 'VV781IN', 120, 'Camion 8', 6),
(27, 'VX197OF', 560, 'Camion 2', 7),
(28, 'WZ664SP', 800, 'Camion 7', 8),
(29, 'ZW685NX', 770, 'Camion 3', 9),
(30, 'BP819ZZ', 290, 'Véhicule 7', 10),
(31, 'CH158QM', 620, 'Camion 7', 11),
(32, 'PA422AK', 570, 'Peugeot', 12),
(33, 'DN601OG', 790, 'Camion 5', 13),
(34, 'LP783JV', 580, 'Camion 6', 14),
(35, 'HF348VD', 80, 'Véhicule 2', 15),
(36, 'JS587YS', 940, 'Camion 8', 16),
(37, 'BX677XA', 410, 'Camion 10', 17),
(38, 'DP940IE', 670, 'Camion 9', 18),
(39, 'LP333DD', 60, 'Véhicule 6', 19),
(40, 'TN667ZK', 590, 'Véhicule 3', 20),
(41, 'IS431SG', 40, 'Camion 1', 1),
(42, 'IR728EM', 300, 'Renault', 2),
(43, 'BC166BS', 720, 'Véhicule 5', 3),
(44, 'MN157LL', 140, 'Camion 2', 1),
(45, 'RM467MD', 410, 'Citroën', 1),
(46, 'OC769HC', 240, 'Camion 3', 1),
(47, 'HA160BH', 300, 'Camion 6', 1),
(48, 'NJ387BF', 540, 'Camion 9', 1),
(49, 'QC704NB', 830, 'Nissan', 1),
(50, 'AC763ID', 530, 'Fiat', 1);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `adresses`
--
ALTER TABLE `adresses`
  ADD PRIMARY KEY (`idAdresse`);

--
-- Index pour la table `clients`
--
ALTER TABLE `clients`
  ADD PRIMARY KEY (`idClient`),
  ADD KEY `idAdresse` (`idAdresse`);

--
-- Index pour la table `commandes`
--
ALTER TABLE `commandes`
  ADD PRIMARY KEY (`idCommande`),
  ADD KEY `idProducteur` (`idProducteur`),
  ADD KEY `idTournee` (`idTournee`),
  ADD KEY `idClient` (`idClient`);

--
-- Index pour la table `producteurs`
--
ALTER TABLE `producteurs`
  ADD PRIMARY KEY (`idProducteur`),
  ADD UNIQUE KEY `idUtilisateur` (`idUtilisateur`),
  ADD UNIQUE KEY `siret` (`siret`),
  ADD KEY `idAdresse` (`idAdresse`);

--
-- Index pour la table `tournees`
--
ALTER TABLE `tournees`
  ADD PRIMARY KEY (`idTournee`),
  ADD KEY `idVehicule` (`idVehicule`),
  ADD KEY `idProducteur` (`idProducteur`);

--
-- Index pour la table `utilisateurs`
--
ALTER TABLE `utilisateurs`
  ADD PRIMARY KEY (`idUtilisateur`),
  ADD UNIQUE KEY `nomUtilisateur` (`nomUtilisateur`);

--
-- Index pour la table `vehicules`
--
ALTER TABLE `vehicules`
  ADD PRIMARY KEY (`idVehicule`),
  ADD UNIQUE KEY `immatriculation` (`immatriculation`),
  ADD KEY `idProducteur` (`idProducteur`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `adresses`
--
ALTER TABLE `adresses`
  MODIFY `idAdresse` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=51;

--
-- AUTO_INCREMENT pour la table `clients`
--
ALTER TABLE `clients`
  MODIFY `idClient` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=51;

--
-- AUTO_INCREMENT pour la table `commandes`
--
ALTER TABLE `commandes`
  MODIFY `idCommande` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=301;

--
-- AUTO_INCREMENT pour la table `producteurs`
--
ALTER TABLE `producteurs`
  MODIFY `idProducteur` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT pour la table `tournees`
--
ALTER TABLE `tournees`
  MODIFY `idTournee` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=51;

--
-- AUTO_INCREMENT pour la table `utilisateurs`
--
ALTER TABLE `utilisateurs`
  MODIFY `idUtilisateur` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=51;

--
-- AUTO_INCREMENT pour la table `vehicules`
--
ALTER TABLE `vehicules`
  MODIFY `idVehicule` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=51;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `clients`
--
ALTER TABLE `clients`
  ADD CONSTRAINT `clients_ibfk_1` FOREIGN KEY (`idAdresse`) REFERENCES `adresses` (`idAdresse`) ON DELETE CASCADE;

--
-- Contraintes pour la table `commandes`
--
ALTER TABLE `commandes`
  ADD CONSTRAINT `commandes_ibfk_1` FOREIGN KEY (`idProducteur`) REFERENCES `producteurs` (`idProducteur`) ON DELETE CASCADE,
  ADD CONSTRAINT `commandes_ibfk_2` FOREIGN KEY (`idTournee`) REFERENCES `tournees` (`idTournee`) ON DELETE CASCADE,
  ADD CONSTRAINT `commandes_ibfk_3` FOREIGN KEY (`idClient`) REFERENCES `clients` (`idClient`) ON DELETE CASCADE;

--
-- Contraintes pour la table `producteurs`
--
ALTER TABLE `producteurs`
  ADD CONSTRAINT `producteurs_ibfk_1` FOREIGN KEY (`idAdresse`) REFERENCES `adresses` (`idAdresse`),
  ADD CONSTRAINT `producteurs_ibfk_2` FOREIGN KEY (`idUtilisateur`) REFERENCES `utilisateurs` (`idUtilisateur`) ON DELETE CASCADE;

--
-- Contraintes pour la table `tournees`
--
ALTER TABLE `tournees`
  ADD CONSTRAINT `tournees_ibfk_1` FOREIGN KEY (`idVehicule`) REFERENCES `vehicules` (`idVehicule`) ON DELETE CASCADE,
  ADD CONSTRAINT `tournees_ibfk_2` FOREIGN KEY (`idProducteur`) REFERENCES `producteurs` (`idProducteur`) ON DELETE CASCADE;

--
-- Contraintes pour la table `vehicules`
--
ALTER TABLE `vehicules`
  ADD CONSTRAINT `vehicules_ibfk_1` FOREIGN KEY (`idProducteur`) REFERENCES `producteurs` (`idProducteur`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
