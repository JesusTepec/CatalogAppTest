# CatalogAppTest

La aplicación fue desarrollada integrando las sigueintes utilidades:

- Retrofit para el consumo de servicios web
- RxJava3 para manejar las peticiones y hacer reactiva la vista
- ViewModel para la gestión de los datos a la vista
- Picasso para mostrar imágenes
- Timber log en debug
- chuck interceptor UI para las peticiones REST

## Configuración inicial

Para que la app pueda iniciar u compilar correctamente se deben configurar el token para los servicios web y los ids para el consumo de las listas (articles y activities), en el archivo AppConstants.kt

## Usage

```kotlin
package com.catalogapptest


const val TOKEN = ""
const val SKILL_ID = 0
const val BABY_ID = 0

const val KEY_TOKEN = "token_rest"
const val KEY_SKILL_ID = "skill_id"
const val KEY_BABY_ID = "baby_id"

const val GET_ACTIVITIES = "/api/v3/catalogue/activities"
const val GET_ARTICLES = "/api/v3/catalogue/articles"
const val GET_ARTICLES_DETAIL = "/api/v3/articles"

```

## Mejoras no alcanzadas
Dagger 2 sí implemento pero no se logro implementar por completo en el proyecto.


