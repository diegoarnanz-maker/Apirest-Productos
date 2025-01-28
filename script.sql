CREATE DATABASE IF NOT EXISTS `productosbd_completo`;
USE `productosbd_completo`;

-- Tabla `familias`
DROP TABLE IF EXISTS `familias`;
CREATE TABLE `familias` (
  `codigo` int NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(45) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Datos para `familias`
INSERT INTO `familias` VALUES 
(1, 'bebidas'),
(2, 'ropa'),
(3, 'zapatos'),
(4, 'comida'),
(5, 'ferreteria');

-- Tabla `perfiles`
DROP TABLE IF EXISTS `perfiles`;
CREATE TABLE `perfiles` (
  `ID_PERFIL` int NOT NULL AUTO_INCREMENT,
  `NOMBRE` varchar(45) NOT NULL,
  PRIMARY KEY (`ID_PERFIL`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Datos para `perfiles`
INSERT INTO `perfiles` VALUES 
(1, 'ROLE_ADMINISTRADOR'),
(2, 'ROLE_GESTOR'),
(3, 'ROLE_CLIENTE');

-- Tabla `productos`
DROP TABLE IF EXISTS `productos`;
CREATE TABLE `productos` (
  `codigo` int NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(45) NOT NULL,
  `precio_unitario` decimal(11,2) DEFAULT NULL,
  `codigo_familia` int NOT NULL,
  `marca` varchar(15) DEFAULT NULL,
  `color` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  KEY `codigo_familia` (`codigo_familia`),
  CONSTRAINT `productos_ibfk_1` FOREIGN KEY (`codigo_familia`) REFERENCES `familias` (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Datos para `productos`
INSERT INTO `productos` VALUES
(1, 'Coca-Cola', 1.50, 1, 'Coca-Cola', 'negro'), -- bebida
(2, 'Camiseta', 15.00, 2, 'Nike', 'blanco'),    -- ropa
(3, 'Zapatillas', 50.00, 3, 'Adidas', 'negro'), -- zapatos
(4, 'Pan', 1.20, 4, 'Bimbo', 'blanco'),         -- comida
(5, 'Martillo', 12.00, 5, 'Stanley', 'amarillo'); -- ferretería

-- Tabla `usuarios`
DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE `usuarios` (
  `USERNAME` varchar(45) NOT NULL,
  `PASSWORD` varchar(200) NOT NULL,
  `NOMBRE` varchar(50) DEFAULT NULL,
  `APELLIDOS` varchar(50) DEFAULT NULL,
  `DIRECCION` varchar(100) DEFAULT NULL,
  `ENABLED` int NOT NULL DEFAULT '1',
  `FECHA_REGISTRO` date DEFAULT NULL,
  PRIMARY KEY (`USERNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Datos para `usuarios`
INSERT INTO `usuarios` VALUES
('admin', '{noop}12345678', 'Administrador', 'Principal', 'Calle Principal 1, Madrid', 1, '2021-06-01'),
('gestor', '{noop}12345678', 'Gestor', 'Secundario', 'Calle Secundaria 2, Sevilla', 1, '2021-03-01'),
('cliente', '{noop}12345678', 'Cliente', 'Final', 'Calle Final 3, Barcelona', 1, '2021-01-01');

-- Tabla `usuario_perfiles`
DROP TABLE IF EXISTS `usuario_perfiles`;
CREATE TABLE `usuario_perfiles` (
  `USERNAME` varchar(45) NOT NULL,
  `ID_PERFIL` int NOT NULL,
  PRIMARY KEY (`USERNAME`, `ID_PERFIL`),
  KEY `ID_PERFIL` (`ID_PERFIL`),
  CONSTRAINT `usuario_perfiles_ibfk_1` FOREIGN KEY (`USERNAME`) REFERENCES `usuarios` (`USERNAME`),
  CONSTRAINT `usuario_perfiles_ibfk_2` FOREIGN KEY (`ID_PERFIL`) REFERENCES `perfiles` (`ID_PERFIL`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Datos para `usuario_perfiles`
INSERT INTO `usuario_perfiles` VALUES
('admin', 1), -- ROLE_ADMINISTRADOR
('gestor', 2), -- ROLE_GESTOR
('cliente', 3); -- ROLE_CLIENTE
