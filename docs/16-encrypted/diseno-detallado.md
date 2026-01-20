# Diseno detallado - Epica 16 Encrypted Storage (Nivel 4)

## Contexto
Proteccion de datos locales con cifrado.

## Alcance
- Cifrado de Settings y DB.
- Gestion de claves.
- Migracion desde storage sin cifrar.

## TA-ENC-F0 Dominio
- EncryptionPolicy y KeyProvider.

## TA-ENC-F1 Key Management
- Keystore/Keychain y rotacion.

## TA-ENC-F2 Settings cifrado
- Wrapper cifrado por namespace.

## TA-ENC-F3 DB cifrada
- SQLCipher u opcion equivalente.

## TA-ENC-F4 Migracion
- Plan de migracion y fallback.

## TA-ENC-F5 Testing
- Checklist QA y validacion de accesos.
