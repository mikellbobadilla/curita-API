# Curita API

Es una aplicación que se encarga de administrar el inventario de los productos típicos de una perfumería.

## Authors

- [Mikell Bobadilla](https://github.com/mikellbobadilla)
- [Leila Tello](https://github.com/LeilaTello)

## Environment Variables

Para iniciar este proyecto en modo producción, necesitas las siguientes variables de entorno.

`DB_USERNAME`, `DB_PASSWORD` `DB_HOST` `APP_PROFILE`

Si necesitas iniciar este proyecto en modo de desarrollo, solo necesitar usar el archivo compose.yml que se encuentra en
la carpeta raíz del proyecto.

## Tareas

<!-- To Leila -->

- Trabajar con la Entidad SectionEntity
    - Eliminar los campos de las tablas que tienen el campo requerido
- Desarrollar el servicio basico (CRUD)
    - Crear los Recuros utilizando DTOS
    - Generar los servicios
    - Personalizar las respuestas correctamente
    - Controlar las excepciones y errores que puedan ocurrir